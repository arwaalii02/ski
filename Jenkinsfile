pipeline {
    agent any
    environment {
        NEXUS_URL = 'http://192.168.50.4:8081/repository/maven-releases/'
        DOCKER_IMAGE = 'emna2023/emna-bouzouita-5gamix'
        VERSION = "latest"
        GIT_BRANCH = 'emna'
    }

    stages {
        stage('Main') {
            steps {
                echo "Echo Test of Emna Branch"
            }
        }

        stage('Build & Package') {
            steps {

                    script {
                        // Print current directory and list files
                        sh 'pwd'
                        sh 'ls -la' // List files in the current directory

                        // Change permission to make mvnw executable
                        sh 'chmod +x ./mvnw'


                    }
                    // Build the project
                    sh './mvnw clean package -DskipTests'

            }
        }

        stage('Run Unit Tests') {
            steps {
                script {
                    try {
                        // Run the unit tests
                        sh 'mvn clean test'

                        // Generate the JaCoCo report after tests pass
                        sh 'mvn jacoco:report'

                        // Ensure that JaCoCo report generation is recognized by Jenkins
                        jacoco execPattern: 'target/jacoco.exec'
                    } catch (Exception e) {
                        currentBuild.result = 'FAILURE'
                        error "Tests failed or JaCoCo report generation failed: ${e.message}"
                    }
                }
            }
        }

        stage('Deploy to Nexus') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'nexus-credentials', passwordVariable: 'NEXUS_PASSWORD', usernameVariable: 'NEXUS_USERNAME')]) {
                    // Deploy to Nexus Repository
                    sh 'mvn deploy -Dusername=$NEXUS_USERNAME -Dpassword=$NEXUS_PASSWORD'
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
                    def imageTag = "${DOCKER_IMAGE}:${BUILD_NUMBER}"
                    echo "Building Docker image ${imageTag}..."
                    sh "docker build -t ${imageTag} ."
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'dockerhubcredentials', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                        sh 'echo $DOCKER_PASSWORD | docker login --username $DOCKER_USERNAME --password-stdin || exit 1'
                        def imageTag = "${DOCKER_IMAGE}:${BUILD_NUMBER}"
                        sh "docker push ${imageTag}"
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
