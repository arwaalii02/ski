pipeline {
    agent any
    environment {

            DOCKERHUB_CREDENTIALS = credentials('dockerhubcredentials')
            DOCKER_IMAGE = 'emna2023/emna-bouzouita-5gamix:latest'
            VERSION = "latest"
        }

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
       stage('Docker Build') {
                steps {
                    script {
                        echo 'Building Docker image...'
                         // Ensure you're in the correct directory containing the Dockerfile
                            sh "docker build -t emna2023/emna-bouzouita-5gamix:latest ."

                    }
                }
            }
                stage('Push Docker Image') {
                        steps {
                            echo 'Pushing Docker image to DockerHub...'
                            script {
                                withCredentials([usernamePassword(credentialsId: 'dockerhubcredentials', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                                    sh 'echo $DOCKER_PASSWORD | docker login --username $DOCKER_USERNAME --password-stdin || exit 1'
                                    sh 'docker push emna2023/emna-bouzouita-5gamix:latest'
                                }
                            }
                        }
                    }
                }

}