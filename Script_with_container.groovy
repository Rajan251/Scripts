#demo script with container 
pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Build') {
            steps {
                script {
                    docker.image('maven:3.6.3-jdk-8').inside {
                        sh 'mvn clean package'
                    }
                }
            }
        }
        
        stage('Test') {
            steps {
                script {
                    docker.image('maven:3.6.3-jdk-8').inside {
                        sh 'mvn test'
                    }
                }
            }
        }
        
        stage('Docker Build') {
            steps {
                script {
                    def app = docker.build("my-app:${env.BUILD_ID}")
                }
            }
        }
        
        stage('Deploy') {
            steps {
                script {
                    docker.image("my-app:${env.BUILD_ID}").run('-p 8080:8080')
                }
            }
        }
    }
}
