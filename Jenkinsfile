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
                sh 'make build-docker-image'
                
                script {
                    withCredentials([
                        usernamePassword(
                            credentialsId: 'docker-hub-credentials', 
                            usernameVariable: 'USERNAME', 
                            passwordVariable: 'PASSWORD'
                            )]) {
                        sh ''' 
                            make push-docker-image \
                                username=$USERNAME \
                                password=$PASSWORD \
                                build_number=$BUILD_NUMBER \
                                dockerpath=segunfamisa/capstone-app
                        
                        '''
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
            	sh 'make deploy'
            }
        }
    }
}
