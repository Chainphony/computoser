FROM ubuntu:20.04

RUN apt-get update && apt-get install -y maven timidity ffmpeg

WORKDIR /computoser
COPY . .

RUN ./install_jave.sh
RUN ./compile.sh
