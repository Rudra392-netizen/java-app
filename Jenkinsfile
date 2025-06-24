@Library("my-shared-library")_
    agent any 

    environment {
        DOCKER_IMAGE = "java-app"
        DOCKER_CREDENTIALS = "dockerHubCred"
        SONAR_CREDENTIALS = "SonarQube"
    }
    stages {
        stage("Cloning the code from github") {
            steps {
                git url: "https://github.com/Rudra392-netizen/java-app.git", branch: "main"
            }
        }
    }
    stage("Build with maven") {
        steps {
            buildwithMaven()
        }
    }
    stage("sonarqube analysis") {
        steps {
            runSonarQube(env.SONAR_CREDENTIALS)
        }
    }
    stage("Build Docker Image") {
        steps {
            sh "docker build -t ${env.IMAGE_NAME}"
        }
    }
    stage("Docker image scan using trivy") {
        steps {
            runTrivy(env.IMAGE_NAME)
        }
    }
    stage("Push docker image to dockerHub") {
        steps {
            pushToDockerhub(env.IMAGE_NAME, env.DOCKER_CREDENTIALS)
        }
    }
    post {
        always {
            echo "pipeline is successfully completed"
        }
        failure {
            always {
                echo "pipeline gets failed ! find the issue"
            }
            always {
                echo "cleaning up the resources"
                sh "docker rm -f $(docker ps -ef) || true"
            }
        }
    }
