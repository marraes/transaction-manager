#!/bin/bash
if ! type docker > /dev/null; then
  echo "Docker not installed. See: https://docs.docker.com/engine/"
  dockerComposeCommand="docker compose"
  exit 1
fi

if hash docker compose 2>/dev/null; then
  echo "Command 'docker compose' exists, using it"
  dockerComposeCommand="docker compose"
elif hash docker-compose 2>/dev/null; then
  echo "Command 'docker-compose' exists using it"
  dockerComposeCommand="docker-compose"
else
  echo "Not found any docker-compose command (v1 or v2). See: https://docs.docker.com/compose/cli-command/"
  exit 1
fi


echo "Building app"

./gradlew clean build

cp -rT docker build/docker

eval "$dockerComposeCommand -f build/docker/docker-compose.yml up"
