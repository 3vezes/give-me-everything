#!/bin/bash 

give-me-everything() {
  CLASSPATH=$HOME/.give-me-everything/give-me-everything.jar
  java -jar $CLASSPATH "$@"
}
