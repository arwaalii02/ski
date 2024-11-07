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

             stage('SonarQueb analysis') {
            steps {
                echo 'Analyse de la Qualité du Code : ';
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=69iSASxPosition?';
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
