#!/bin/bash

echo "도커 로그인 시도"
echo "$DOCKER_PASSWORD" | docker login -u $DOCKER_USER_NAME --password-stdin