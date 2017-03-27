package com.incadence.bdp;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

public final class GdeltConstants {
	
	// GDELT actual field names to Coalesce feature names
	public Map<String, String> actor1GdeltToFeatureMap;  
	public Map<String, String> actor1FeatureToGdeltMap;  
	public Map<String, String> actor2GdeltToFeatureMap;  
	public Map<String, String> actor2FeatureToGdeltMap;  
	public Map<String, String> eventGdeltToFeatureMap;  
	
	// Clean null event for JSON
	public Map<String,JSONObject> nullEventMap;  
	
	// Sample GDELT data json strings
	public String sampleEvent_ID498859055;  
	public String sampleEvent_ID498847267;  
	
	public GdeltConstants() {
		
		// Coalesce actor entities don't know if they are actor 1 or 2
		actor1GdeltToFeatureMap = new HashMap<String, String>();
		actor1GdeltToFeatureMap.put("Actor1Code","ActorCode");
		actor1GdeltToFeatureMap.put("Actor1Name","ActorName");
		actor1GdeltToFeatureMap.put("Actor1CountryCode","ActorCountryCode");
		actor1GdeltToFeatureMap.put("Actor1KnownGroupCode","ActorKnownGroupCode");
		actor1GdeltToFeatureMap.put("Actor1EthnicCode","ActorEthnicCode");
		actor1GdeltToFeatureMap.put("Actor1Religion1Code","ActorReligion1Code");
		actor1GdeltToFeatureMap.put("Actor1Religion2Code","ActorReligion2Code");
		actor1GdeltToFeatureMap.put("Actor1Type1Code","ActorType1Code");
		actor1GdeltToFeatureMap.put("Actor1Type2Code","ActorType2Code");
		actor1GdeltToFeatureMap.put("Actor1Type3Code","ActorType3Code");
		actor1GdeltToFeatureMap.put("Actor1Geo_Type","ActorGeoType");
		actor1GdeltToFeatureMap.put("Actor1Geo_FullName","ActorGeoFullname");
		actor1GdeltToFeatureMap.put("Actor1Geo_CountryCode","ActorGeoCountryCode");
		actor1GdeltToFeatureMap.put("Actor1Geo_ADM1Code","ActorGeoADM1Code");
		actor1GdeltToFeatureMap.put("Actor1Geo_FeatureID","ActorGeoFeatureID");
		actor1GdeltToFeatureMap.put("Actor1Geo_Lat","ActorGeoLocation");  // Feature lat/long is one field
		actor1GdeltToFeatureMap.put("Actor1Geo_Long","ActorGeoLocation");
			
		
		actor1FeatureToGdeltMap = new HashMap<String, String>();
		actor1FeatureToGdeltMap.put("ActorCode", "Actor1Code");
		actor1FeatureToGdeltMap.put("ActorName", "Actor1Name");
		actor1FeatureToGdeltMap.put("ActorCountryCode", "Actor1CountryCode");
		actor1FeatureToGdeltMap.put("ActorKnownGroupCode", "Actor1KnownGroupCode");
		actor1FeatureToGdeltMap.put("ActorEthnicCode", "Actor1EthnicCode");
		actor1FeatureToGdeltMap.put("ActorReligion1Code","Actor1Religion1Code");
		actor1FeatureToGdeltMap.put("ActorReligion2Code", "Actor1Religion2Code");
		actor1FeatureToGdeltMap.put("ActorType1Code", "Actor1Type1Code");
		actor1FeatureToGdeltMap.put("ActorType2Code", "Actor1Type2Code");
		actor1FeatureToGdeltMap.put("ActorType3Code", "Actor1Type3Code");
		actor1FeatureToGdeltMap.put("ActorGeoType", "Actor1Geo_Type");
		actor1FeatureToGdeltMap.put("ActorGeoFullname", "Actor1Geo_FullName");
		actor1FeatureToGdeltMap.put("ActorGeoCountryCode", "Actor1Geo_CountryCode");
		actor1FeatureToGdeltMap.put("ActorGeoADM1Code", "Actor1Geo_ADM1Code");
		actor1FeatureToGdeltMap.put("ActorGeoFeatureID", "Actor1Geo_FeatureID");
		actor1FeatureToGdeltMap.put("ActorGeoLocation", "Actor1Geo_Lat");  // Feature lat/long is one field
		actor1FeatureToGdeltMap.put("ActorGeoLocation", "Actor1Geo_Long");
		
		
		actor2GdeltToFeatureMap = new HashMap<String, String>();
		actor2GdeltToFeatureMap.put("Actor2Code","ActorCode");
		actor2GdeltToFeatureMap.put("Actor2Name","ActorName");
		actor2GdeltToFeatureMap.put("Actor2CountryCode","ActorCountryCode");
		actor2GdeltToFeatureMap.put("Actor2KnownGroupCode","ActorKnownGroupCode");
		actor2GdeltToFeatureMap.put("Actor2EthnicCode","ActorEthnicCode");
		actor2GdeltToFeatureMap.put("Actor2Religion1Code","ActorReligion1Code");
		actor2GdeltToFeatureMap.put("Actor2Religion2Code","ActorReligion2Code");
		actor2GdeltToFeatureMap.put("Actor2Type1Code","ActorType1Code");
		actor2GdeltToFeatureMap.put("Actor2Type2Code","ActorType2Code");
		actor2GdeltToFeatureMap.put("Actor2Type3Code","ActorType3Code");
		actor2GdeltToFeatureMap.put("Actor2Geo_Type","ActorGeoType");
		actor2GdeltToFeatureMap.put("Actor2Geo_FullName","ActorGeoFullname");
		actor2GdeltToFeatureMap.put("Actor2Geo_CountryCode","ActorGeoCountryCode");
		actor2GdeltToFeatureMap.put("Actor2Geo_ADM1Code","ActorGeoADM1Code");
		actor2GdeltToFeatureMap.put("Actor2Geo_FeatureID","ActorGeoFeatureID");
		actor2GdeltToFeatureMap.put("Actor2Geo_Lat","ActorGeoLocation");  // Feature lat/long is one field
		actor2GdeltToFeatureMap.put("Actor2Geo_Long","ActorGeoLocation");
		
		
		actor2FeatureToGdeltMap = new HashMap<String, String>();
		actor2FeatureToGdeltMap.put("ActorCode", "Actor2Code");
		actor2FeatureToGdeltMap.put("ActorName", "Actor2Name");
		actor2FeatureToGdeltMap.put("ActorCountryCode", "Actor2CountryCode");
		actor2FeatureToGdeltMap.put("ActorKnownGroupCode", "Actor2KnownGroupCode");
		actor2FeatureToGdeltMap.put("ActorEthnicCode", "Actor2EthnicCode");
		actor2FeatureToGdeltMap.put("ActorReligion1Code", "Actor2Religion1Code");
		actor2FeatureToGdeltMap.put("ActorReligion2Code", "Actor2Religion2Code");
		actor2FeatureToGdeltMap.put("ActorType1Code", "Actor2Type1Code");
		actor2FeatureToGdeltMap.put("ActorType2Code", "Actor2Type2Code");
		actor2FeatureToGdeltMap.put("ActorType3Code", "Actor2Type3Code");
		actor2FeatureToGdeltMap.put("ActorGeoType", "Actor2Geo_Type");
		actor2FeatureToGdeltMap.put("ActorGeoFullname", "Actor2Geo_FullName");
		actor2FeatureToGdeltMap.put("ActorGeoCountryCode", "Actor2Geo_CountryCode");
		actor2FeatureToGdeltMap.put("ActorGeoADM1Code", "Actor2Geo_ADM1Code");
		actor2FeatureToGdeltMap.put("ActorGeoFeatureID", "Actor2Geo_FeatureID");
		actor2FeatureToGdeltMap.put("ActorGeoLocation", "Actor2Geo_Lat");  // Feature lat/long is one field
		actor2FeatureToGdeltMap.put("ActorGeoLocation", "Actor2Geo_Long");
		
		
		eventGdeltToFeatureMap = new HashMap<String, String>();
		eventGdeltToFeatureMap.put("GLOBALEVENTID", "GlobalEventID");
		eventGdeltToFeatureMap.put("SQLDATE", "Day");
		eventGdeltToFeatureMap.put("SOURCEURL", "SourceURL");
		eventGdeltToFeatureMap.put("DATEADDED", "DateAdded");
		eventGdeltToFeatureMap.put("ActionGeo_Type", "ActionGeoType");
		eventGdeltToFeatureMap.put("ActionGeo_FullName", "ActionGeoFullname");
		eventGdeltToFeatureMap.put("ActionGeo_CountryCode", "ActionGeoCountryCode");
		eventGdeltToFeatureMap.put("ActionGeo_ADM1Code", "ActionGeoADM1Code");
		eventGdeltToFeatureMap.put("ActionGeo_FeatureID", "ActionGeoFeatureID");
		eventGdeltToFeatureMap.put("MonthYear", "MonthYear");
		eventGdeltToFeatureMap.put("Year", "Year");
		eventGdeltToFeatureMap.put("FractionDate", "FractionDate");
		eventGdeltToFeatureMap.put("IsRootEvent", "IsRootEvent");
		eventGdeltToFeatureMap.put("EventCode", "EventCode");
		eventGdeltToFeatureMap.put("EventBaseCode", "EventBaseCode");
		eventGdeltToFeatureMap.put("EventRootCode", "EventRootCode");
		eventGdeltToFeatureMap.put("QuadClass", "QuadClass");
		eventGdeltToFeatureMap.put("GoldsteinScale", "GoldsteinScale");
		eventGdeltToFeatureMap.put("NumMentions", "NumMentions");
		eventGdeltToFeatureMap.put("NumSources", "NumSources");
		eventGdeltToFeatureMap.put("NumArticles", "NumArticles");
		eventGdeltToFeatureMap.put("AvgTone", "AvgTone");
		eventGdeltToFeatureMap.put("NumSources", "NumSources");
		eventGdeltToFeatureMap.put("ActionGeo_Lat", "ActionGeoLocation");  // feature lat/long is one field
		eventGdeltToFeatureMap.put("ActionGeo_Long", "ActionGeoLocation");  
	
		
		nullEventMap = new HashMap<String, JSONObject>();
		nullEventMap.put("GLOBALEVENTID", (JSONObject)null);
		nullEventMap.put("SQLDATE", (JSONObject)null);
		nullEventMap.put("MonthYear", (JSONObject)null);
		nullEventMap.put("Year", (JSONObject)null);
		nullEventMap.put("FractionDate", (JSONObject)null);
		nullEventMap.put("Actor1Code", (JSONObject)null);
		nullEventMap.put("Actor1Name", (JSONObject)null);
		nullEventMap.put("Actor1CountryCode", (JSONObject)null);
		nullEventMap.put("Actor1KnownGroupCode", (JSONObject)null);
		nullEventMap.put("Actor1EthnicCode", (JSONObject)null);
		nullEventMap.put("Actor1Religion1Code", (JSONObject)null);
		nullEventMap.put("Actor1Religion2Code", (JSONObject)null);
		nullEventMap.put("Actor1Type1Code", (JSONObject)null);
		nullEventMap.put("Actor1Type2Code", (JSONObject)null);
		nullEventMap.put("Actor1Type3Code", (JSONObject)null);
		nullEventMap.put("Actor2Code", (JSONObject)null);
		nullEventMap.put("Actor2Name", (JSONObject)null);
		nullEventMap.put("Actor2CountryCode", (JSONObject)null);
		nullEventMap.put("Actor2KnownGroupCode", (JSONObject)null);
		nullEventMap.put("Actor2EthnicCode", (JSONObject)null);
		nullEventMap.put("Actor2Religion1Code", (JSONObject)null);
		nullEventMap.put("Actor2Religion2Code", (JSONObject)null);
		nullEventMap.put("Actor2Type1Code", (JSONObject)null);
		nullEventMap.put("Actor2Type2Code", (JSONObject)null);
		nullEventMap.put("Actor2Type3Code", (JSONObject)null);
		nullEventMap.put("IsRootEvent", (JSONObject)null);
		nullEventMap.put("EventCode", (JSONObject)null);
		nullEventMap.put("EventBaseCode", (JSONObject)null);
		nullEventMap.put("EventRootCode", (JSONObject)null);
		nullEventMap.put("QuadClass", (JSONObject)null);
		nullEventMap.put("GoldsteinScale", (JSONObject)null);
		nullEventMap.put("NumMentions", (JSONObject)null);
		nullEventMap.put("NumSources", (JSONObject)null);
		nullEventMap.put("NumArticles", (JSONObject)null);
		nullEventMap.put("AvgTone", (JSONObject)null);
		nullEventMap.put("Actor1Geo_Type", (JSONObject)null);
		nullEventMap.put("Actor1Geo_FullName", (JSONObject)null);
		nullEventMap.put("Actor1Geo_CountryCode", (JSONObject)null);
		nullEventMap.put("Actor1Geo_ADM1Code", (JSONObject)null);
		nullEventMap.put("Actor1Geo_Lat", (JSONObject)null);
		nullEventMap.put("Actor1Geo_Long", (JSONObject)null);
		nullEventMap.put("Actor1Geo_FeatureID", (JSONObject)null);
		nullEventMap.put("Actor2Geo_Type", (JSONObject)null);
		nullEventMap.put("Actor2Geo_FullName", (JSONObject)null);
		nullEventMap.put("Actor2Geo_CountryCode", (JSONObject)null);
		nullEventMap.put("Actor2Geo_ADM1Code", (JSONObject)null);
		nullEventMap.put("Actor2Geo_Lat", (JSONObject)null);
		nullEventMap.put("Actor2Geo_Long", (JSONObject)null);
		nullEventMap.put("Actor2Geo_FeatureID", (JSONObject)null);
		nullEventMap.put("ActionGeo_Type", (JSONObject)null);
		nullEventMap.put("ActionGeo_FullName", (JSONObject)null);
		nullEventMap.put("ActionGeo_CountryCode", (JSONObject)null);
		nullEventMap.put("ActionGeo_ADM1Code", (JSONObject)null);
		nullEventMap.put("ActionGeo_Lat", (JSONObject)null);
		nullEventMap.put("ActionGeo_Long", (JSONObject)null);
		nullEventMap.put("ActionGeo_FeatureID", (JSONObject)null);
		nullEventMap.put("DATEADDED", (JSONObject)null);
		nullEventMap.put("SOURCEURL", (JSONObject)null);
	
		
		sampleEvent_ID498859055 = "{\"GLOBALEVENTID\":\"498859055\","
    			+ "\"SQLDATE\":\"20160102\",\"MonthYear\":\"201601\",\"Year\":\"2016\","
    			+ "\"FractionDate\":\"2016.0055\",\"Actor1Code\":\"MOSSHI\","
    			+ "\"Actor1Name\":\"SHIA\",\"Actor1CountryCode\":null,"
    			+ "\"Actor1KnownGroupCode\":null,\"Actor1EthnicCode\":null,"
    			+ "\"Actor1Religion1Code\":\"MOS\",\"Actor1Religion2Code\":\"SHI\","
    			+ "\"Actor1Type1Code\":null,\"Actor1Type2Code\":null,"
    			+ "\"Actor1Type3Code\":null,\"Actor2Code\":\"SAU\",\"Actor2Name\":\"RIYADH\","
    			+ "\"Actor2CountryCode\":\"SAU\",\"Actor2KnownGroupCode\":null,"
    			+ "\"Actor2EthnicCode\":null,\"Actor2Religion1Code\":null,"
    			+ "\"Actor2Religion2Code\":null,\"Actor2Type1Code\":null,"
    			+ "\"Actor2Type2Code\":null,\"Actor2Type3Code\":null,\"IsRootEvent\":\"0\","
    			+ "\"EventCode\":\"114\",\"EventBaseCode\":\"114\",\"EventRootCode\":\"11\","
    			+ "\"QuadClass\":\"3\",\"GoldsteinScale\":\"-2.0\",\"NumMentions\":\"1\","
    			+ "\"NumSources\":\"1\",\"NumArticles\":\"1\",\"AvgTone\":\"-4.55486542443064\","
    			+ "\"Actor1Geo_Type\":\"1\",\"Actor1Geo_FullName\":\"China\","
    			+ "\"Actor1Geo_CountryCode\":\"CH\",\"Actor1Geo_ADM1Code\":\"CH\","
    			+ "\"Actor1Geo_Lat\":\"35.0\",\"Actor1Geo_Long\":\"105.0\","
    			+ "\"Actor1Geo_FeatureID\":\"CH\",\"Actor2Geo_Type\":\"5\","
    			+ "\"Actor2Geo_FullName\":\"Eastern Province, Ash Sharqiyah, Saudi Arabia\","
    			+ "\"Actor2Geo_CountryCode\":\"SA\",\"Actor2Geo_ADM1Code\":\"SA06\","
    			+ "\"Actor2Geo_Lat\":\"22.5\",\"Actor2Geo_Long\":\"51.0\","
    			+ "\"Actor2Geo_FeatureID\":\"-3093178\",\"ActionGeo_Type\":\"4\","
    			+ "\"ActionGeo_FullName\":\"Tehran, Tehran, Iran\","
    			+ "\"ActionGeo_CountryCode\":\"IR\",\"ActionGeo_ADM1Code\":\"IR26\","
    			+ "\"ActionGeo_Lat\":\"35.75\",\"ActionGeo_Long\":\"51.5148\","
    			+ "\"ActionGeo_FeatureID\":\"10074674\",\"DATEADDED\":\"20160102\","
    			+ "\"SOURCEURL\":\"http://thevillagessuntimes.com/2016/01/02/iran-accuses-saudis-of-supporting-terrorism-after-execution/\"}";
		
		
		sampleEvent_ID498847267 = "{\"GLOBALEVENTID\":\"498847267\",\"SQLDATE\":\"20160102\",\"MonthYear\":\"201601\","
				+ "\"Year\":\"2016\",\"FractionDate\":\"2016.0055\",\"Actor1Code\":\"SYR\","
				+ "\"Actor1Name\":\"SYRIAN\",\"Actor1CountryCode\":\"SYR\",\"Actor1KnownGroupCode\":null,"
				+ "\"Actor1EthnicCode\":null,\"Actor1Religion1Code\":null,\"Actor1Religion2Code\":null,"
				+ "\"Actor1Type1Code\":null,\"Actor1Type2Code\":null,\"Actor1Type3Code\":null,"
				+ "\"Actor2Code\":\"SYRMIL\",\"Actor2Name\":\"SYRIA\",\"Actor2CountryCode\":\"SYR\","
				+ "\"Actor2KnownGroupCode\":null,\"Actor2EthnicCode\":null,\"Actor2Religion1Code\":null,"
				+ "\"Actor2Religion2Code\":null,\"Actor2Type1Code\":\"MIL\",\"Actor2Type2Code\":null,"
				+ "\"Actor2Type3Code\":null,\"IsRootEvent\":\"0\",\"EventCode\":\"190\","
				+ "\"EventBaseCode\":\"190\",\"EventRootCode\":\"19\",\"QuadClass\":\"4\","
				+ "\"GoldsteinScale\":\"-10.0\",\"NumMentions\":\"1\",\"NumSources\":\"1\","
				+ "\"NumArticles\":\"1\",\"AvgTone\":\"-7.90405033425088\",\"Actor1Geo_Type\":\"4\","
				+ "\"Actor1Geo_FullName\":\"Deir Qaq, ?alab, Syria\",\"Actor1Geo_CountryCode\":\"SY\","
				+ "\"Actor1Geo_ADM1Code\":\"SY09\",\"Actor1Geo_Lat\":\"36.3062\","
				+ "\"Actor1Geo_Long\":\"37.4439\",\"Actor1Geo_FeatureID\":\"-2541141\","
				+ "\"Actor2Geo_Type\":\"4\",\"Actor2Geo_FullName\":\"Deir Qaq, ?alab, Syria\","
				+ "\"Actor2Geo_CountryCode\":\"SY\",\"Actor2Geo_ADM1Code\":\"SY09\","
				+ "\"Actor2Geo_Lat\":\"36.3062\",\"Actor2Geo_Long\":\"37.4439\","
				+ "\"Actor2Geo_FeatureID\":\"-2541141\",\"ActionGeo_Type\":\"4\","
				+ "\"ActionGeo_FullName\":\"Deir Qaq, ?alab, Syria\","
				+ "\"ActionGeo_CountryCode\":\"SY\",\"ActionGeo_ADM1Code\":\"SY09\","
				+ "\"ActionGeo_Lat\":\"36.3062\",\"ActionGeo_Long\":\"37.4439\","
				+ "\"ActionGeo_FeatureID\":\"-2541141\",\"DATEADDED\":\"20160102\","
				+ "\"SOURCEURL\":\"http://www.veteranstoday.com/2016/01/02/iranian-commander-describes-isil-as-us-israel-proxy-fighting-with-saudi-qatari-turkish-funding/\"}";	
	
	}


}
