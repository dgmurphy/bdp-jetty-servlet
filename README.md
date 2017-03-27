## tiny-gdelt-servlet
A Jetty/Jersey project that returns GDELT rows in JSON format.

Responds at this URL:

Return 2 hard-coded events:   http://localhost:8080/tiny-gdelt/webapi/gdelt/test

Search BDP using OGC Filters: http://localhost:8080/tiny-gdelt/webapi/gdelt/bdp

## Installation

1.  Put connection info in src/main/resources/accumuloConnectionInfo.xml
2.  Run maven install and copy war to $JETTY_HOME/demo-base/webapps
3.  java -Xdebug -Xrunjdwp:transport=dt_socket,address=8787,server=y,suspend=n -jar $JETTY_HOME/start.jar



