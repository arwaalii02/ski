pipeline {
    agent any

    stages {
        stage('Main') {
            steps {
                // Compile the Spring Boot project
                echo "Echo Test of Emna Branch"
            }
        }

        stage('Build') {
            steps {
                // Check out the code from the repository
                checkout scm

                // Run Maven clean install
                sh 'mvn clean package'
            }
        }
        stage('test') {
                    steps {
                        // Check out the code from the repository
                        checkout scm

                        // Run Maven clean install
                        sh 'mvn test'
                    }
                }
        stage('MVN SonarQube') {
            steps {
                    withSonarQubeEnv('Sonar') {
                        sh 'mvn sonar:sonar'
                    }
                }
        }
    }
}