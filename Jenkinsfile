pipeline {
    agent any

    environment {
        DOCKER_CREDENTIALS_ID = 'docker-hub-credentials'
        DOCKER_IMAGE = 'ahmedharleyy/ski' // Replace with your image name
    }

    stages {
        stage('Main') {
            steps {
                echo "Echo Test of Ahmed Branch"
            }
        }

        stage('Compile') {
            steps {
                // Check out the code from the repository
                checkout scm
                // Run Maven compile
                sh 'mvn compile'
            }
        }

        stage('Test') {
            steps {
                // Check out the code from the repository
                checkout scm
                // Run Maven tests specifically for GestionStationSkiApplicationTests
                sh 'mvn -Dtest=GestionStationSkiApplicationTests test'
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
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Deploy to Nexus') {
            steps {
                sh 'mvn clean deploy -DskipTests'
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
                
                // Wait for MySQL to be ready by checking the logs or running a command
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
                    }
                }
            }
        }

//         stage('Integration Test') {
//     steps {
//         script {
//             echo 'Running database integration tests in the app container...'
//             // Run only DatabaseIntegrationTest inside the container without TTY
//             sh 'docker exec $(docker ps -q -f ancestor=ahmedharleyy/ski-image:1.0.0) mvn -Dtest=DatabaseIntegrationTest test'
//         }
//     }
// }
    }
}
