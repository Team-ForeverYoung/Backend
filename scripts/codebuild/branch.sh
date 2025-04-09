#!/bin/bash
BRANCH=${1#refs/heads/}
case "$BRANCH" in
  main)    echo "Main 브랜치 빌드 시작" ;;
  develop) echo "Develop 브랜치 빌드 시작" ;;
  *)       echo "오류: 지원되지 않는 브랜치 ($BRANCH)"; exit 1 ;;
esac
