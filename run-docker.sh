#!/bin/bash
docker_compose_comand=""

if ! type docker > /dev/null; then
  echo "Docker not installed. See: https://docs.docker.com/engine/"
  docker_compose_comand="docker compose"
  exit 1
fi

if hash docker compose 2>/dev/null; then
  echo "Command 'docker compose' exists, using it"
  docker_compose_comand="docker compose"
elif hash docker-compose 2>/dev/null; then
  echo "Command 'docker-compose' exists using it"
  docker_compose_comand="docker-compose"
else
  echo "Not found any docker-compose command (v1 or v2). See: https://docs.docker.com/compose/cli-command/"
  exit 1
fi

clean_flag="false"

while getopts 'c' flag; do
  case "${flag}" in
    c) clean_flag="true" ;;
    *) echo "Invalid argument"
       exit 1 ;;
  esac
done

if "$clean_flag" = "true"; then
  echo "Cleaning app"
  ./gradlew clean
fi

echo "Building app"

./gradlew build

cp -rT docker build/docker

eval "$docker_compose_comand -f build/docker/docker-compose.yml up --build"
