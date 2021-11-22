#!/bin/bash

docker build -f DockerfileDev -t computoser_dev:latest ./config
docker run --rm -v ${PWD}:/computoser computoser_dev:latest bash -c ./install_jave.sh
docker run --rm -v ${PWD}:/computoser computoser_dev:latest bash -c ./compile.sh
docker run --rm -it -v ${PWD}:/computoser computoser_dev:latest bash
