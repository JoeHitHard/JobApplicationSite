#!/bin/sh

cd ./job-app-commons || exit
./gradlew clean build publish
cd ..


cd ./job-app-applicant || exit
./gradlew clean build
cd ..

cd ./job-app-company || exit
./gradlew clean build
cd ..

docker-compose down --rmi all --volumes --remove-orphans job-app-applicant job-app-company
docker-compose build
docker-compose up -d
