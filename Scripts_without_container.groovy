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
                sh 'mvn clean package'
            }
        }
        
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        
        stage('Deploy') {
            steps {
                sh '''
                # Example deployment script
                cp target/my-app.jar /path/to/deploy/
                # Restart application or other deployment steps
                '''
            }
        }
    }
}
