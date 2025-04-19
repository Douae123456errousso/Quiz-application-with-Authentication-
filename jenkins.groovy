pipeline {
    agent any

    tools {
        maven 'Maven 3.8.8'   
        jdk 'JDK 17'            
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Douae123456errousso/Quiz-application-with-Authentication-.git'
            }
        }

        stage('Compile') {
            steps {
                bat 'mvn compile'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Code Coverage') {
            steps {
                bat 'mvn jacoco:report'
            }
        }
    }

    post {
        always {
            junit 'target\\surefire-reports\\*.xml'
            archiveArtifacts artifacts: 'target\\site\\jacoco\\**', fingerprint: true
        }
        failure {
            echo 'Le build a échoué.'
        }
    }
}