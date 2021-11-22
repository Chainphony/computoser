FROM ubuntu:20.04

RUN apt-get update && apt-get install -y maven 

WORKDIR /computoser
COPY . .

RUN ./install_jave.sh
RUN ./compile.sh
