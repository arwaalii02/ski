pipeline {
    agent any

    environment {
        SONARQUBE_URL = 'http://192.168.50.4:9000'  // SonarQube URL
        SONARQUBE_TOKEN = credentials('sonarqube-token')  // Reference the SonarQube token stored in Jenkins credentials
        SONAR_PROJECT_KEY = 'registration-ski'
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout the project from GitHub
                git branch: 'sahbi', url: 'https://github.com/arwaalii02/ski.git'
            }
        }

            stage('Maven Clean') {
            steps {
                echo 'Nettoyage du Projet : '
                sh 'mvn clean'
            }
        }

        stage('Maven Compile') {
            steps {
                echo 'Construction du Projet : '
                sh 'mvn compile'
            }
        }

        stage('Maven Test (Skip)') {
            steps {
                echo 'Test du Projet (skipped) : '
                sh 'mvn test -DskipTests'
            }
        }
    }
}
