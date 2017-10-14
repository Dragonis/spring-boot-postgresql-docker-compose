all: install up

docker:
	sudo apt install docker.io -y
	sudo apt install docker-compose -y
	curl -L https://github.com/docker/machine/releases/download/v0.12.2/docker-machine-`uname -s`-`uname -m` >/tmp/docker-machine
	chmod +x /tmp/docker-machine
	sudo cp /tmp/docker-machine /usr/local/bin/docker-machine
# docker-uninstall:
    # sudo apt remove docker -y
    # sudo apt remove docker-compose -y

install:
	sudo ./mvnw clean install
	# or
 	# sudo docker run -it --rm --name my-maven-project -v "$PWD":/usr/src/mymaven -w /usr/src/mymaven -v /var/run/:/var/run/ maven mvn clean install

up:
	cd src/main/docker && sudo docker-compose up

# WORKING
#
# To remove old containers or images
# 1. In terminal type the command: sudo gedit ~/.bashrc
# 2. On the end of file add these aliases:
#   alias dr='sudo docker rm $(sudo docker ps -a -q) -f'
#   alias di='sudo docker rmi $(sudo docker images -q) -f'
# 3. Then in terminal you can use shortkeys:
#    dr - to remove all conainers
#       and
#    di - to remove all images
#

# DO NOT WORKING
#clean:
#	# Delete every Docker image
#	sudo docker rm $(sudo docker ps -a -q) -f
#	# sudo docker stop $(sudo docker ps -a -q)
#remove:
#	# Delete every Docker image
#	sudo docker rmi $(sudo docker images -q) -f