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
                    withSonarQubeEnv('Sonarqube') {
                        sh 'mvn sonar:sonar'
                    }
                }
        }

        stage('nexus') {
            steps {
                // Check out the code from the repository
                checkout scm

                // Run Maven clean install
                sh 'mvn deploy -e -X -Dnexus.login=admin -Dnexus.password=Aghx?2001'
            }
        }
        


    }
}