pipeline {
    agent any
    stages {
        stage('Lint') {
            steps {
                sh 'make lint'
            }
        }

        stage('Build app & Run tests') {
            steps {
                sh 'make build-app-run-tests'
            }
        }

        stage('Build & Push Docker Image') {
            steps {
                sh 'make build-push-docker-image'
            }
        }

        stage('Deploy') {
            steps {
            	sh 'make deploy'
            }
        }
    }
}
