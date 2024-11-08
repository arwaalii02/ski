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

        
         stage('SonarQueb analysis') {
            steps {
                echo 'Analyse de la Qualité du Code : ';
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=69iSASxPosition?';
            }
        }
        
        stage('Deploy to Nexus') {
    steps {
        // Deploy to Nexus Repository
        sh '''
            echo "Deploying to Nexus..."
            mvn deploy -Dusername=admin -Dpassword=admin
        '''
    }
}

        
       
    }
}
