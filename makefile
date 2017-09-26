install:
sudo ./mvnw clean install
# or
# docker run -it --rm --name my-maven-project -v "$PWD":/usr/src/mymaven -w /usr/src/mymaven -v /var/run/:/var/run/ maven mvn clean install

up:
cd src/main/docker && sudo docker-compose up

# http://localhost:8080