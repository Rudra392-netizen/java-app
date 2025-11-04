@Library("my-shared-library") _

pipeline {
    agent any 

    environment {
        IMAGE_NAME = "aiml40573/java-app"
        DOCKER_CREDENTIALS = "dockerHubCred"
        SONAR_CREDENTIALS = "SonarQube"
        SONAR_URL = "http://54.79.181.242:9000"
        PROJECT_KEY = "java-app"
    }
    
    stages {
        stage("cloning the code from github") {
            steps {
                git url: "https://github.com/Rudra392-netizen/java-app.git", branch: "main"
            }
        }

        stage("buid with maven") {
            steps {
                buildwithMaven()
            }
        }

        stage("sonarqube analysis") {
            steps {
                runSonarQube(env.SONAR_CREDENTIALS, env.SONAR_URL, env.PROJECT_KEY)
            }
        }
        stage("build docker image") {
            steps {
                sh "docker build -t ${env.IMAGE_NAME} ."
            }
        }

        stage("push docker image to dockerhub") {
            steps {
                pushToDockerHub(env.IMAGE_NAME, env.DOCKER_CREDENTIALS)
            }
        }

        stage("docker image sacn using trivy") {
            steps {
                runTrivy(env.IMAGE_NAME)
            }
        }
    }
    post {
        always {
            echo "pipeline has completed (success)"
        }
        failure {
            echo "pipeline has been failed"
        }
    }
}
