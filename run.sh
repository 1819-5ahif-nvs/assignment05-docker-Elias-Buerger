#!/bin/bash
cd Wildfly
mvn clean
mvn package
cd ..
docker-compose down
docker-compose up --build
echo "Ready!"