#!/bin/bash
cd "$(dirname "$0")"
cd Wildfly
mvn clean
mvn package
cd ..
docker-compose down
docker-compose up --build