pipeline {
    agent any

    environment {
        DOCKER_CREDENTIALS_ID = 'docker-hub-credentials'
        DOCKER_IMAGE = 'ahmedharleyy/ski'
        SLACK_CHANNEL = '#devops' // Replace with your Slack channel
    }

    stages {
        stage('Main') {
            steps {
                echo "Echo Test of Ahmed Branch"
                slackSend(channel: env.SLACK_CHANNEL, message: "Main stage started.", color: "#00FF00")
            }
        }

        stage('Compile') {
            steps {
                checkout scm
                sh 'mvn compile'
                slackSend(channel: env.SLACK_CHANNEL, message: "Compile stage completed.", color: "#00FF00")
            }
        }

        stage('Test') {
            steps {
                checkout scm
                sh 'mvn -Dtest=GestionStationSkiApplicationTests test'
                slackSend(channel: env.SLACK_CHANNEL, message: "Test stage completed.", color: "#00FF00")
            }
        }

        stage('MVN SonarQube') {
            steps {
                withSonarQubeEnv('Sonarqube') {
                    sh 'mvn sonar:sonar'
                }
                slackSend(channel: env.SLACK_CHANNEL, message: "SonarQube analysis completed.", color: "#00FF00")
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
                slackSend(channel: env.SLACK_CHANNEL, message: "Build stage completed.", color: "#00FF00")
            }
        }

        stage('Deploy to Nexus') {
            steps {
                sh 'mvn clean deploy -DskipTests'
                slackSend(channel: env.SLACK_CHANNEL, message: "Deployed to Nexus.", color: "#00FF00")
            }
        }

        stage('Image') {
            steps {
                script {
                    def imageExists = sh(
                        script: "docker manifest inspect ahmedharleyy/ski-image:1.0.0 > /dev/null 2>&1",
                        returnStatus: true
                    ) == 0

                    if (!imageExists) {
                        echo 'Building Image:'
                        sh 'docker build -t ahmedharleyy/ski-image:1.0.0 .'
                        slackSend(channel: env.SLACK_CHANNEL, message: "Docker image built.", color: "#00FF00")
                    } else {
                        echo 'Image already exists, skipping build.'
                    }
                }
            }
        }

        stage('Dockerhub') {
            steps {
                script {
                    def imageExists = sh(
                        script: "docker manifest inspect ahmedharleyy/ski-image:1.0.0 > /dev/null 2>&1",
                        returnStatus: true
                    ) == 0

                    if (!imageExists) {
                        echo 'Pushing Image to Docker Hub:'
                        sh 'docker login -u ahmedharleyy -p Aghx?2001'
                        sh 'docker push ahmedharleyy/ski-image:1.0.0'
                        slackSend(channel: env.SLACK_CHANNEL, message: "Image pushed to Docker Hub.", color: "#00FF00")
                    } else {
                        echo 'Image already exists on Docker Hub, skipping push.'
                    }
                }
            }
        }

        stage('Docker-Compose') {
            steps {
                echo 'Start Backend + DB:'
                sh 'docker compose up -d'

                script {
                    def mysqlReady = false
                    for (int i = 0; i < 20; i++) {
                        def result = sh(script: 'docker exec -i $(docker ps -q -f name=mysql-container) mysqladmin ping -h localhost', returnStatus: true)
                        if (result == 0) {
                            mysqlReady = true
                            break
                        }
                        sleep 10
                    }

                    if (!mysqlReady) {
                        error 'MySQL is not ready after waiting for 200 seconds.'
                    } else {
                        slackSend(channel: env.SLACK_CHANNEL, message: "Docker-Compose started successfully.", color: "#00FF00")
                    }
                }
            }
        }
    }

   post {
    success {
        slackSend(channel: env.SLACK_CHANNEL, message: "Pipeline completed successfully.", color: 'good')
    }
    failure {
        slackSend(channel: env.SLACK_CHANNEL, message: "Build failed: ${env.BUILD_URL}", color: 'danger')
    }
}
}

