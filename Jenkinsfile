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

        stage('Build Docker Image') {
            steps {
                script {
                    try {
                        sh "docker build -t ${DOCKER_IMAGE} ."
                        currentBuild.description = (currentBuild.description ?: '') + "Build Docker Image: ✅\n"
                    } catch (Exception e) {
                        currentBuild.description = (currentBuild.description ?: '') + "Build Docker Image: ❌\n"
                        throw e
                    }
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    try {
                        withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_USERNAME')]) {
                            sh(returnStatus: true, script: "echo \$DOCKER_PASSWORD | docker login -u \$DOCKER_USERNAME --password-stdin")
                        }
                        sh "docker push ${DOCKER_IMAGE}"
                        currentBuild.description = (currentBuild.description ?: '') + "Push Docker Image: ✅\n"
                    } catch (Exception e) {
                        currentBuild.description = (currentBuild.description ?: '') + "Push Docker Image: ❌\n"
                        throw e
                    }
                }
            }
        }

        stage('Run Docker Compose') {
            steps {
                script {
                    try {
                        sh 'docker-compose up -d'
                        currentBuild.description = (currentBuild.description ?: '') + "Run Docker Compose: ✅\n"
                    } catch (Exception e) {
                        currentBuild.description = (currentBuild.description ?: '') + "Run Docker Compose: ❌\n"
                        throw e
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

