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
            parallel {
                stage('Run tests') {
                    steps {
                        sh 'echo stage: Run tests'
                    }
                }

                stage('Build app') {
                    steps {
                        sh 'echo stage: Build jar'
                    }
                }
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
