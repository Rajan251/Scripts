pipeline {
    agent any

    environment {
        DOCKER_CREDENTIALS_ID = 'your-docker-credentials-id' // Replace with your Docker credentials ID
        DEV_SERVER = 'user@dev-server' // Replace with your development server details
        APP_NAME = 'my-app'
        IMAGE_TAG = "${APP_NAME}:${env.BUILD_ID}"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Test in Container') {
            steps {
                script {
                    docker.image('maven:3.6.3-jdk-8').inside {
                        sh 'mvn clean test'
                    }
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    app = docker.build("${IMAGE_TAG}")
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('', DOCKER_CREDENTIALS_ID) {
                        app.push()
                    }
                }
            }
        }

        stage('Deploy to Development Server') {
            steps {
                script {
                    // SSH into the development server and deploy the Docker container
                    sshagent(['your-ssh-credentials-id']) { // Replace with your SSH credentials ID
                        sh """
                        ssh -o StrictHostKeyChecking=no ${DEV_SERVER} << EOF
                        docker pull ${IMAGE_TAG}
                        docker stop ${APP_NAME} || true
                        docker rm ${APP_NAME} || true
                        docker run -d --name ${APP_NAME} -p 8080:8080 ${IMAGE_TAG}
                        EOF
                        """
                    }
                }
            }
        }
    }
    
    post {
        always {
            cleanWs()
        }
    }
}
