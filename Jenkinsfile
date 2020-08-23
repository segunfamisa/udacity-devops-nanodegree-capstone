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
							make login-to-docker \
								username=$USERNAME \
								password=$PASSWORD
						'''

                        sh '''
							make push-docker-image \
								build_number=latest \
								dockerpath=segunfamisa/capstone-app
						'''

                        sh ''' 
                            make push-docker-image \
                                build_number=$BUILD_NUMBER \
                                dockerpath=segunfamisa/capstone-app
                        '''
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
            	withAWS(credentials: 'aws-credentials', region: 'us-west-2') {
                    sh 'make deploy'
                }
            }
        }
    }
}
