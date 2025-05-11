pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Test') {
            steps {
                echo 'GitHub 웹훅 연동 테스트!!'
            }
        }
    }
}
