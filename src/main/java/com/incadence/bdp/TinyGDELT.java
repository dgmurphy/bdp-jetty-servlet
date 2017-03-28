package com.incadence.bdp;

import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.CachedRowSet;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.codehaus.jettison.json.JSONException;
import org.geotools.data.Query;
import org.geotools.filter.text.cql2.CQL;
import org.geotools.filter.text.cql2.CQLException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.opengis.filter.Filter;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.incadencecorp.coalesce.common.exceptions.CoalesceException;
import com.incadencecorp.coalesce.common.exceptions.CoalescePersistorException;
import com.incadencecorp.coalesce.framework.CoalesceFramework;
import com.incadencecorp.coalesce.framework.persistance.ServerConn;
import com.incadencecorp.coalesce.framework.persistance.accumulo.AccumuloPersistor;

@Path("gdelt")
public class TinyGDELT {
	
	private GdeltConstants GDC; 
	private int hasOneActor;
	private int hasTwoActors;
	List<String> uniqueGlobalIDs;
	
	public TinyGDELT() throws JSONException {
		
		GDC = new GdeltConstants();

	}

    /**
     * Method handling HTTP GET requests for a tiny sample of GDELT data. 
     *
     * @return JSONArray of sample GDELT events that will be returned as a APPLICATION_JSON response.
     */
    @SuppressWarnings("unchecked")
	@GET
    @Path("test")
    @Produces(MediaType.APPLICATION_JSON) 
    public JSONArray getIt() {
     	
    	JSONObject jev1, jev2;
    	JSONArray jarr = new JSONArray();
    	JSONParser jpar = new JSONParser();
 
		try {
			jev1 = (JSONObject) jpar.parse(GDC.sampleEvent_ID498847267);
			jarr.add(jev1);
			
			jev2 = (JSONObject) jpar.parse(GDC.sampleEvent_ID498859055);
			jarr.add(jev2);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 			
	
		return jarr;

    }
 
    
    /**
     * Method handling HTTP GET requests for GDELT data from BDP
     *
     * @return JSONArray of GDELT events using a BDP Query
     */    
    @SuppressWarnings("unchecked")
	@GET
    @Path("bdp")
    @Produces(MediaType.APPLICATION_JSON)   
    public JSONArray coalSearch() 
    		throws CQLException, SQLException, JSONException, CoalesceException, ParseException, IOException {
    		
    	Map<String,String> connectionInfo = getAccumuloConnectioInfo();
    	
    	String dbName = connectionInfo.get("dbName");
	   	String zookeepers = connectionInfo.get("zookeepers");
	   	String user = connectionInfo.get("user");
	   	String password = connectionInfo.get("password");

	   	ServerConn conn = new ServerConn.Builder().db(dbName).serverName(zookeepers).user(user).password(password).build();
    	
	   	AccumuloPersistor persistor = new AccumuloPersistor(conn);
    	CoalesceFramework coalesceFramework = new CoalesceFramework();
    	coalesceFramework.setAuthoritativePersistor(persistor);	
    	
    	//Filter filter = CQL.toFilter("FractionDate > 2017.052095");
    	//Filter filter = CQL.toFilter("GlobalEventID = '618742302'");
    	//Filter filter = CQL.toFilter("AvgTone > 5 OR AvgTone < -10");
    	Filter filter = CQL.toFilter("AvgTone < -8.0");
    	Query query = new Query("OEEvent_GDELT_0.1.EventSection.EventRecordset", filter);

		long startTime = System.currentTimeMillis();  //start timer

  		CachedRowSet rowset = persistor.search(query);
	    //JSONArray returnJsonArr = new JSONArray();  // The return value
  		
  		HashMap<String, JSONObject> eventsMap = getEventsMap(rowset);
  		JSONArray returnJsonArr = buildEventsJsonArray(coalesceFramework, eventsMap);	 //adds the actor data
	
		System.out.println("Raw Rows: " + rowset.size() + " Unique IDs: " +  uniqueGlobalIDs.size() + 
				" Has 1 actor: " + hasOneActor + " Has 2 actors: " + hasTwoActors);

		long stopTime = System.currentTimeMillis();
		System.out.println("Elapsed time was " + (stopTime - startTime)/1000.0 + " seconds.");
		
		//System.out.println(returnJsonArr.toString());
		
		return returnJsonArr;
		
    }

	// return a Map of <objectId, JSON Event>
    private HashMap<String, JSONObject> getEventsMap(CachedRowSet crs) 
    		throws SQLException {
    	
    	//JSONArray returnJsonArr = new JSONArray();
    	HashMap<String, JSONObject> eventsMap = new HashMap<String, JSONObject>();
    	
    	uniqueGlobalIDs = new ArrayList<String>();
		String gid = "";
		int row = 0;
		System.out.println("Rowset size: " + crs.size());
    	while (crs.next()) {
    		
    		JSONObject outEventJson = new JSONObject(GDC.nullEventMap);
    		
			// Skip null entityKeys, null GlobalEventIDs, and duplicate entities
    		String objectId = crs.getString(AccumuloPersistor.ENTITY_KEY_COLUMN_NAME);
			gid = crs.getString("GlobalEventID");
			if ((objectId == null) || (gid == null)) 
				continue;
			
			if (!uniqueGlobalIDs.contains(gid)) 
				uniqueGlobalIDs.add(gid);
			else 
				continue;
    		
			// Copy rowset attributes over to the JSON object
			for (Map.Entry<String, String> entry : GDC.eventGdeltToFeatureMap.entrySet())  
				outEventJson.put(entry.getKey(), crs.getString(entry.getValue()));


			//expand the event location point into lon/lat
			if(crs.getString("ActionGeoLocation") != null) {
				String agLocation = crs.getString("ActionGeoLocation");
				agLocation = agLocation.substring(agLocation.indexOf("(") + 1, agLocation.indexOf(")"));
				String[] latlong = agLocation.split(" ");
				outEventJson.put("ActionGeo_Lat", latlong[1]);
				outEventJson.put("ActionGeo_Long", latlong[0]);
	    	}
			
	    	if (row < 10)
	    		System.out.println("Row: " + row + " GID: " + gid);  //print a few IDs

	    	eventsMap.put(objectId, outEventJson);
	    	
	    	++row;
	    	
	    	if (row%100 == 0)
	    		System.out.println("Row: " + row);
    	}
    	
    	return eventsMap;
    	
    }
    
    
    private JSONArray buildEventsJsonArray(CoalesceFramework cf,HashMap<String, JSONObject> eventsMap) 
    		throws CoalescePersistorException {
    	
    	JSONArray  resultJsonArr = new JSONArray();
    	JSONObject myEventJson = null;
    	
    	String[] objectIds = eventsMap.keySet().toArray(new String[eventsMap.size()]);
    	
    	//  EXPENSIVE CALL to getEntityXmls
    	System.out.println("Fetching entity XMLs");
    	String[] ceXmls = cf.getEntityXmls(objectIds);
    	
    	for (int i = 0; i < ceXmls.length; ++i) {

    		String ceXml = ceXmls[i];

    		Map<String, String> actorMap = getActorMap(ceXml);
    		myEventJson = eventsMap.get(actorMap.get("eventId")); // get the event

    		int  numActors = Integer.parseInt(actorMap.get("numActors"));
    		if(numActors > 0) { 
    			String[] actorXmlArr = null;
    			String[] actorKeys = new String[numActors];
    			actorKeys[0] = actorMap.get("actor1");
    			++hasOneActor;
    			if (numActors > 1) {
    				actorKeys[1] = actorMap.get("actor2");
    				++hasTwoActors;
    			}

    			//  EXPENSIVE CALL to getEntityXmls
    			actorXmlArr = cf.getEntityXmls(actorKeys);

    			for (int j = 0; j < actorXmlArr.length; ++j) {
    				// Add related actor data back to this event
    				myEventJson = addActor(myEventJson, actorXmlArr[j], j+1);    
    			}
    		} // add actor info

    		if (myEventJson != null)
    			resultJsonArr.add(myEventJson); // add the event to the response

    		if (i > 0 && i % 100 == 0)
    			System.out.println("Row: " + i);
    		
    	} // event xmls loop
    	
    	return resultJsonArr;
    }
    
    private Map<String, String> getActorMap(String xmlString) {
    	
    	Map<String, String> actorMap = new HashMap<String, String>();
    	int numActors = 0;
    	String eventKey = "no event key";
    	
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;  
		InputSource insrc = new InputSource( new StringReader( xmlString ));
 
		try  
		{  
			builder = factory.newDocumentBuilder(); 
			Document doc = builder.parse(insrc);
			doc.getDocumentElement().normalize();
			NodeList nodes = doc.getElementsByTagName("linkage");
			boolean actor1Found = false;
			boolean actor2Found = false;
			for (int i = 0; i < nodes.getLength(); ++i) {
				Node n = nodes.item(i);		
				NamedNodeMap nnm = n.getAttributes();
				Node e2n = nnm.getNamedItem("entity2name");
				if ( (e2n.getNodeValue().equals("OEActor")) || 
					 (e2n.getNodeValue().equals("OEAgent"))) {
					if (!actor1Found) {
						Node e2id = nnm.getNamedItem("entity2key");
						actorMap.put("actor1", e2id.getNodeValue());
						actor1Found = true;
						++numActors;
					}
					else if (!actor2Found) { 
						Node e2id = nnm.getNamedItem("entity2key");
						actorMap.put("actor2", e2id.getNodeValue());
						actor2Found = true;
						++numActors;
					}
				} // linkage is an actor
			} // loop over linkages
			
			nodes = doc.getElementsByTagName("entity");
			if (nodes.getLength() > 0) {
				Node n = nodes.item(0);
				NamedNodeMap nnm = n.getAttributes();
				Node eKey = nnm.getNamedItem("key");
				eventKey = eKey.getNodeValue();
			}
			if (nodes.getLength() > 1)
				System.out.println("WARN: Event XML has multiple entity tags");

			
			actorMap.put("eventId", eventKey);
			actorMap.put("numActors", Integer.toString(numActors));
			
		} catch (Exception e) {  
			e.printStackTrace();  
		} 		
		
    	return actorMap;
    }
    
    private JSONObject addActor(JSONObject json, String xmlString, int actorNum) {
    	
    	Map<String,String> featureToGdeltMap = GDC.actor1FeatureToGdeltMap;
    	if (actorNum > 1)
    		featureToGdeltMap = GDC.actor2FeatureToGdeltMap;
    	
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;  
		InputSource insrc = new InputSource( new StringReader( xmlString ));
		
		try  
		{  
			builder = factory.newDocumentBuilder(); 
			Document doc = builder.parse(insrc);
			doc.getDocumentElement().normalize();
			NodeList nodes = doc.getElementsByTagName("field");
			
			for (int i = 0; i < nodes.getLength(); ++i) {
				String featureName = null;
				String featureValue = null;
				Node n = nodes.item(i);		
				NamedNodeMap nnm = n.getAttributes();
				if(nnm.getNamedItem("value") == null)
					continue;
				
				featureValue = nnm.getNamedItem("value").getNodeValue();
				
				if(nnm.getNamedItem("name") != null) {
					featureName = nnm.getNamedItem("name").getNodeValue();
					//unformat the actor location
					if(featureName.equals("ActorGeoLocation")) {
						String agLocation = featureValue;
						agLocation = agLocation.substring(agLocation.indexOf("(") + 1, agLocation.indexOf(")"));
						String[] latlong = agLocation.split(" ");
						json.put("Actor"+actorNum+"Geo_Lat", latlong[1]);
						json.put("Actor"+actorNum+"Geo_Long", latlong[0]);
						
					} else if (featureToGdeltMap.keySet().contains(featureName)) {
						json.put(featureToGdeltMap.get(featureName), featureValue);
					}
				}  // node name not null
				
			} // node loop
			
		} catch (Exception e) {  
			e.printStackTrace();  
		} 
	
    	return json;
    }
    
    

    
    private Map<String,String> getAccumuloConnectioInfo () {
    	
    	URL resourceUrl = TinyGDELT.class.getResource("/accumuloConnectionInfo.xml");
    	
    	Map<String,String> connectionInfo = new HashMap<String,String>();  //return val
  	
    	try {
    		java.nio.file.Path p = Paths.get(resourceUrl.toURI());
    		String connectionXml = readFile(p.toString(), StandardCharsets.UTF_8);
    		
    		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    		InputSource insrc = new InputSource( new StringReader( connectionXml ));

    		DocumentBuilder builder = factory.newDocumentBuilder(); 
    		Document doc = builder.parse(insrc);
    		Node conn = doc.getElementsByTagName("connectionInfo").item(0);
    		NodeList info = conn.getChildNodes();
    		for (int i = 0; i < info.getLength(); ++i) {
    			Node n = info.item(i);	
    			if (n.getNodeType() == Node.ELEMENT_NODE)	
    				connectionInfo.put(n.getNodeName(), n.getTextContent());
    		}

    		return connectionInfo;

    	} catch (Exception e1) {
    		e1.printStackTrace();
    		System.out.println("Could not connect to Accumulo");
    	}

    	return connectionInfo;
    }
    
    
	static String readFile(String path, Charset encoding) 
			  throws IOException 
			{
			  byte[] encoded = Files.readAllBytes(Paths.get(path));
			  return new String(encoded, encoding);
			}
 
    
    
    public static void main(String[] args) 
    		throws CQLException, SQLException, JSONException, CoalesceException, ParseException, IOException {
    	
    	TinyGDELT tgd = new TinyGDELT();
    	
    	// Two events hardcoded
    	JSONArray jarr = tgd.getIt();
    	System.out.println("testIt() returned JSONArray of length " + jarr.size());
    	
    	// Coalesce Search
		jarr = tgd.coalSearch();
		System.out.println("coalSearch() returned JSONArray of length " + jarr.size());
		
		System.out.println("DONE");
    	
    }
}