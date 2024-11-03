pipeline {
    agent any

    environment {
            DOCKER_CREDENTIALS_ID = 'docker-hub-credentials'
            DOCKER_IMAGE = 'ahmedharleyy/ski' // Replace with your image name
        }

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
        
        stage('Build') {
            steps {
                sh 'mvn package'
            }
        }

        stage('Deploy to Nexus') {
            steps {
                sh 'mvn clean deploy -DskipTests'
            }
        }
        
        stage('Build Docker Image') {
                            steps {
                                sh 'docker build -t $DOCKER_IMAGE .'
                            }
        }

         stage('Push Docker Image') {
                    steps {
                        script {
                            // Log in to Docker Hub
                            docker.withRegistry('https://index.docker.io/v1/', DOCKER_CREDENTIALS_ID) {
                                // Push the Docker image
                                sh 'docker push $DOCKER_IMAGE'
                            }
                        }
                    }
                }

    }
}