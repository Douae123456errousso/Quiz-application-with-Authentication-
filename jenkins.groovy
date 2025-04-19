pipeline {
    agent any

    tools {
        maven 'Maven 3.8.8'
        jdk 'JDK 17'
    }

    environment {
        DEPLOY_DIR = 'C:\\deploy\\quiz-app'
        JAR_NAME = 'quiz-app.jar' // change si ton jar a un autre nom
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Douae123456errousso/Quiz-application-with-Authentication-.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Tests') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Code Coverage') {
            steps {
                bat 'mvn jacoco:report'
            }
        }

        stage('Package') {
            steps {
                bat 'mvn package -DskipTests'
            }
        }

        stage('Deploy Locally') {
            steps {
                script {
                    // Créer le dossier si besoin
                    bat "if not exist %DEPLOY_DIR% mkdir %DEPLOY_DIR%"

                    // Copier le JAR (le plus récent dans target)
                    bat 'for /f %%f in (\'dir /b /a:-d target\\*.jar\') do copy target\\%%f %DEPLOY_DIR%\\%JAR_NAME% /Y & goto done :done'

                    // Tuer un ancien process Java si l'app est déjà lancée (optionnel)
                    bat 'for /f "tokens=2" %%i in (\'tasklist ^| findstr java.exe\') do taskkill /PID %%i /F'

                    // Lancer l'application
                    bat "start java -jar %DEPLOY_DIR%\\%JAR_NAME%"
                }
            }
        }
    }

    post {
        always {
            junit 'target\\surefire-reports\\*.xml'
            archiveArtifacts artifacts: 'target\\site\\jacoco\\', fingerprint: true
        }
        failure {
            echo '❌ Le pipeline a échoué.'
        }
        success {
            echo '✅ Déploiement local terminé ! L’application est lancée.'
        }
    }
}