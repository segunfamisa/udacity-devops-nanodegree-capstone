pipeline {
    agent any
    stages {
        stage('Lint') {
            steps {
                sh './gradlew ktlintCheck'
                // sh 'hadolint Dockerfile'
            }
        }

        stage('Build app & Run tests') {
            steps {
                sh './gradlew clean build'
                sh './gradlew test'
            }
        }

        stage('Build & Push Docker Image') {
            steps {
                sh 'echo stage: Build and Push Docker Image'
            }
        }

        stage('Deploy') {
            steps {
                sh 'echo stage: Deploy'
            }
        }
    }
}
