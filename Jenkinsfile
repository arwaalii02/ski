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
        
        stage('Image') {
            steps {
                echo 'Création Image : ';
                sh 'docker build -t ahmedharleyy/achat-image:1.0.0 .';
            }
        }

        stage('Dockerhub') {
            steps {
                echo 'Push Image to dockerhub : ';
                sh 'docker login -u ahmedharleyy -p Aghx?2001';
                sh 'docker push ahmedharleyy/achat-image:1.0.0';
            }
        }

        stage('Docker-Compose') {
            steps {
                echo 'Staet Backend + DB : ';
                sh 'docker compose up -d';
            }
        }

    }
}