#!/bin/bash
#$CODEBUILD_SOURCE_VERSION" == refs/heads/*
#$CODEBUILD_WEBHOOK_TRIGGER" == branch/*

if [[ "$CODEBUILD_SOURCE_VERSION" == refs/heads/* ]]; then
  BRANCH=${CODEBUILD_SOURCE_VERSION#refs/heads/}
elif [[ "$CODEBUILD_WEBHOOK_TRIGGER" == branch/* ]]; then
  BRANCH=${CODEBUILD_WEBHOOK_TRIGGER#branch/}
else
  echo "브렌치 이름을 추출 할 수 없습니다 codebuild에 담긴 환경변수를 확인하세요"
  echo "환경변수 CODEBUILD_SOURCE_VERSION:$CODEBUILD_SOURCE_VERSION"
  echo "환경변수 CODEBUILD_WEBHOOK_TRIGGER:$CODEBUILD_WEBHOOK_TRIGGER"
  exit 1;
fi

case $BRANCH in
  main)
    echo "메인 브렌치 빌드 시작"
    ;;
  develop)
    echo "디벨롭 브렌치 빌드 시작"
    ;;
  *)
    echo "메인 또는 디벨롭 브렌치가 아님 종료할게요"
    exit 1
esac

export BRANCH
