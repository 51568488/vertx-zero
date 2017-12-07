#!/usr/bin/env bash
image_name="silentbalanceyh/up-cronus"
container_name="up-cronus"

docker stop ${container_name}
docker rm ${container_name}
docker rmi ${image_name}:latest

mvn clean compile assembly:single package

docker run -p 6801:6083 --name ${container_name} ${image_name}