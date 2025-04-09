#!/bin/bash

# 브랜치 이름 추출 로직
if [[ "$CODEBUILD_SOURCE_VERSION" == refs/heads/* ]]; then
  BRANCH=${CODEBUILD_SOURCE_VERSION#refs/heads/}
elif [[ "$CODEBUILD_WEBHOOK_TRIGGER" == branch/* ]]; then
  BRANCH=${CODEBUILD_WEBHOOK_TRIGGER#branch/}
else
  echo "❌ 오류: 브랜치 이름을 추출할 수 없습니다."
  echo "CODEBUILD_SOURCE_VERSION: $CODEBUILD_SOURCE_VERSION"
  echo "CODEBUILD_WEBHOOK_TRIGGER: $CODEBUILD_WEBHOOK_TRIGGER"
  exit 1
fi

# 브랜치 조건별 로직 처리
case "$BRANCH" in
  main)
    echo "✅ Main 브랜치 빌드 시작"
    ;;
  develop)
    echo "✅ Develop 브랜치 빌드 시작"
    ;;
  *)
    echo "❌ 오류: 지원되지 않는 브랜치 ($BRANCH)"
    exit 1
    ;;
esac
