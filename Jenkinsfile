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
    }
}


    }
}