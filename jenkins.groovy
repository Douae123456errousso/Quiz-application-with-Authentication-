pipeline {
    agent any

    tools {
        maven 'Maven 3.8.8'   
        jdk 'JDK 17'            
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/Douae123456errousso/Quiz-application-with-Authentication-.git'
            }
        }

        stage('Compile') {
            steps {
                sh 'mvn compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Code Coverage') {
            steps {
                sh 'mvn jacoco:report'
            }
        }
    }

    post {
        always {
            junit '*/target/surefire-reports/.xml'
            archiveArtifacts artifacts: 'target/site/jacoco/**', fingerprint: true
        }
        failure {
            echo 'Le build a échoué.'
        }
    }
}