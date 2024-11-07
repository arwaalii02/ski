pipeline {
    agent any
    environment {
            NEXUS_URL = 'http://192.168.50.4:8081/repository/maven-releases/'
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
         stage('Deploy to Nexus') {
                                 steps {
                                     dir('DevOps_Project') {
                                         withCredentials([usernamePassword(credentialsId: 'nexus-credentials', passwordVariable: 'NEXUS_PASSWORD', usernameVariable: 'NEXUS_USERNAME')]) {
                                             // Execute Maven deploy command
                                             sh 'mvn deploy -Dusername=$NEXUS_USERNAME -Dpassword=$NEXUS_PASSWORD'
                                         }
                                     }
                                 }
                             }



             stage('Scan') {
                 steps {

                         script {
                             sh 'chmod +x ./mvnw'
                         }
                         withSonarQubeEnv('sonarqube') {
                             sh '''./mvnw sonar:sonar \
                               -Dsonar.java.binaries=target/classes \
                               -Dsonar.jacoco.reportPaths=target/jacoco.exec'''
                         }

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

post {
        always {
            script {
                def jobName = env.JOB_NAME
                def buildNumber = env.BUILD_NUMBER
                def pipelineStatus = currentBuild.result ?: 'UNKNOWN'
                def bannerColor = pipelineStatus.toUpperCase() == 'SUCCESS' ? 'green' : 'red'

                def body = """
                <html>
                <body>
                    <div style="border: 4px solid ${bannerColor}; padding: 10px;">
                        <h2>${jobName} - Build ${buildNumber}</h2>
                        <div style="background-color: ${bannerColor}; padding: 10px;">
                            <h3 style="color: white;">Pipeline Status: ${pipelineStatus.toUpperCase()}</h3>
                        </div>
                        <p>Check the <a href="${env.BUILD_URL}">console output</a>.</p>
                    </div>
                </body>
                </html>
                """

                emailext(
                    subject: "${jobName} - Build ${buildNumber} - ${pipelineStatus.toUpperCase()}",
                    body: body,
                    to: 'emna.bouzouita@esprit.tn',
                    from: 'professoraadi@outlook.com',
                    replyTo: 'professoraadi@outlook.com',
                    mimeType: 'text/html'
                )
            }
        }
    }
}