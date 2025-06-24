@Library("my-shared-library") _

pipeline {
    agent any

    environment {
        IMAGE_NAME = "java-app"
        DOCKER_CREDENTIALS = "dockerHubCred"
        SONAR_CREDENTIALS = "SonarQube"
    }

    stages {
        stage("Cloning the code from GitHub") {
            steps {
                git url: "https://github.com/Rudra392-netizen/java-app.git", branch: "main"
            }
        }

        stage("Build with Maven") {
            steps {
                buildWithMaven() // Assuming this is defined in your shared library
            }
        }

        stage("SonarQube Analysis") {
            steps {
                runSonarQube(env.SONAR_CREDENTIALS, "http://44.201.86.107:9000", "java-app")
            }
        }

        stage("Build Docker Image") {
            steps {
                sh "docker build -t ${env.IMAGE_NAME} ."
            }
        }

        stage("Docker Image Scan using Trivy") {
            steps {
                runTrivy(env.IMAGE_NAME) // Assuming this is defined in your shared library
            }
        }

        stage("Push Docker Image to DockerHub") {
            steps {
                pushToDockerhub(env.IMAGE_NAME, env.DOCKER_CREDENTIALS) // Assuming this is defined in your shared library
            }
        }
    }

    post {
        always {
            echo "Pipeline has completed (success or failure)."
        }

        failure {
            echo "Pipeline failed. Please investigate the issue."

            echo "Cleaning up Docker containers..."
            // Use triple quotes or escape $ for shell compatibility
            sh '''docker rm -f $(docker ps -aq) || true'''
        }
    }
}
