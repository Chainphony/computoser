FROM ubuntu:20.04

RUN apt-get update && apt-get install -y maven 

WORKDIR /computoser
COPY . .

RUN mvn install:install-file -Dfile=./lib/jave-1.0.2.jar -DgroupId=jave -DartifactId=jave \
    -Dversion=1.0.2 -Dpackaging=jar
RUN mvn package
