#!/bin/bash

if [[ "$CODEBUILD_SOURCE_VERSION" == refs/heads/* ]]; then
  BRANCH=${CODEBUILD_SOURCE_VERSION#refs/heads/}
elif [[ "$CODEBUILD_WEBHOOK_TRIGGER" == branch/* ]]; then
  BRANCH=${CODEBUILD_WEBHOOK_TRIGGER#branch/}
else
  echo "브렌치 이름을 추출 할 수 없습니다. codebuild에 담긴 환경변수를 확인하세요"
  echo "환경변수 CODEBUILD_SOURCE_VERSION:$CODEBUILD_SOURCE_VERSION"
  echo "환경변수 CODEBUILD_WEBHOOK_TRIGGER:$CODEBUILD_WEBHOOK_TRIGGER"
  exit 1;
fi

case $BRANCH in
  develop)
    TAG="dev-$CODEBUILD_WEBHOOK_PREV_COMMIT"
    docker build -t "$DOCKER_USER_NAME/foreverBE:$TAG" ../../.
    docker push "$DOCKER_USER_NAME/foreverBE:$TAG"
    ;;
  main)
    TAG="prod-$CODEBUILD_WEBHOOK_PREV_COMMIT"
    docker build -t "$DOCKER_USER_NAME/foreverBE:$TAG" ../../.
    docker push "$DOCKER_USER_NAME/foreverBE:$TAG"
    ;;
  *)
    echo "지원되지 않는 브랜치: $BRANCH"
    exit 1
    ;;
esac
