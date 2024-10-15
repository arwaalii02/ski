pipeline {
    agent any

    stages {
        stage('Main') {
            steps {
                // Compile the Spring Boot project
                echo "Echo Test of Ahmed Branch"
            }
        }

        // stage('Build') {
        //     steps {
        //         // Check out the code from the repository
        //         checkout scm

        //         // Run Maven clean install
        //         sh 'mvn clean package'
        //     }
        // }

        stage('compile') {
            steps {
                // Check out the code from the repository
                checkout scm

                // Run Maven clean install
                sh 'mvn compile'
            }
        }

        stage('MVN SonarQube') {
            steps {
                    withSonarQubeEnv('Sonarqube') {
                        sh 'mvn sonar:sonar'
                    }
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
        


    }
}