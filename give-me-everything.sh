#!/bin/bash 

give-me-everything() {
  CLASSPATH=REPLACE_WITH_YOUR_JAR_LOCATION
  java -jar $CLASSPATH "$@"
}
