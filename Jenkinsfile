pipeline {
    agent any

    environment {
        SONARQUBE_URL = 'http://192.168.50.4:9000'  // SonarQube URL
        SONARQUBE_TOKEN = credentials('sonarqube-token')  // Reference the SonarQube token stored in Jenkins credentials
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout the project from GitHub
                git branch: 'sahbi', url: 'https://github.com/arwaalii02/ski.git'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                // Run SonarQube analysis
                echo 'Running SonarQube analysis...'
                withSonarQubeEnv('SonarQube') {
                    // Run SonarQube analysis using Maven or your specific build tool
                    sh './mvnw sonar:sonar -Dsonar.host.url=${SONARQUBE_URL} -Dsonar.login=${SONARQUBE_TOKEN}'
                }
            }
        }
    }

    post {
        success {
            echo 'Build succeeded!'
        }
        failure {
            echo 'Build failed!'
        }
    }
}
