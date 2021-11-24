# Computoser

An algorithm for music composition. Results can be seen at http://computoser.com

The code of the algorithm is in the main package (com.music), the rest of the packages contain the model and the classes required for the website computoser.com. 

## Native Build
In order to run the algorithm simply run the main method of the Generator class. For example:
```
mkdir out
mvn compile exec:java -Dexec.mainClass="com.music.Generator" -Dexec.args="-measures 20 -out ./out -scale MAJOR -tempo VERY_FAST"
```

Or, alternatively, build the project with maven and deploy the war file (with the path to the properties file configured using -Dmusic.config.location=...)

## Docker Build
In order to build and run in Docker:
```
# build
docker build -t computoser:latest .

# create a container and run inside
docker run --rm -it computoser:latest bash
mvn compile exec:java -Dmaven.repo.local=/computoser/.m2 -Dexec.mainClass="com.music.Generator" -Dexec.args="-measures 20 -out ./out -scale MAJOR -tempo VERY_FAST"
```

## Development Docker Build
Run the `start_dev_docker.sh` script. It will start a Docker container with all dependencies downloaded.
Once in the container, exeute `./compile.sh` to compile.
```
cd computoser
./start_dev_docker.sh

# inside the container
./compile.sh
```

## Reference
The paper describing the algorithm in details is available <a href="https://www.academia.edu/9696759/Computoser_-_rule-based_probability-driven_algorithmic_music_composition">here</a> and <a href="http://arxiv.org/abs/1412.3079">here</a>
