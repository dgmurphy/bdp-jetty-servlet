## tiny-gdelt-servlet
A Jetty/Jersey project that queries the accumulo DB for GDELT events.

URLs:
   tiny-gdelt/webapi/gdelt/test
       Return a couple of hard-coded events
       
    tiny-gdelt/webapi/gdelt/typenames
       Show the typenames and features in the datastore
       
    tiny-gdelt/webapi/gdelt/artifact/618529378
      Get an artifact with a given GlobalEventID (s/b only 1)
      
    tiny-gdelt/webapi/gdelt/bdp
       Query Accumulo for events (hardcoded filter)   
       (todo: add query params for date, geofence, others)
   
    tiny-gdelt/webapi/gdelt/artifacts?limit=1000
       Query by GDELT Raw artifact (events and actors embedded in raw text)
   			(todo: add query params for date range)

## Installation

1.  Put connection info in src/main/resources/accumuloConnectionInfo.xml (edit the blank one)
2.  Test by running TinyGDELT as Java application.
3.  Run maven install and copy war to $JETTY_HOME/demo-base/webapps
4.  java -Xdebug -Xrunjdwp:transport=dt_socket,address=8787,server=y,suspend=n -jar $JETTY_HOME/start.jar





