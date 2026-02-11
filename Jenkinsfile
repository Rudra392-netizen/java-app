@Library("my-shared-library") _

pipeline {
    agent any 

    environment {
        IMAGE_NAME = "aiml074/java-app"
        DOCKER_CREDENTIALS = "dockerHubCred"
        SONAR_CREDENTIALS = "sonarqube"
        SONAR_URL = "http://172.23.203.6:9000"
        PROJECT_KEY = "java-app"
        EMAIL_RECIPIENT = "rpsingh98188@gmail.com"
    }
    
    stages {

        stage("Clone Code") {
            steps {
                git url: "https://github.com/Rudra392-netizen/java-app.git", branch: "main"
            }
        }

        stage("Build with Maven") {
            steps {
                buildwithMaven()
            }
        }

        // ✅ Unit Test from shared library
        stage("Run Unit Tests") {
            steps {
                unitTest()
            }
        }

        stage("SonarQube Analysis") {
            steps {
                runSonarQube(env.SONAR_CREDENTIALS, env.SONAR_URL, env.PROJECT_KEY)
            }
        }

        // ✅ Quality Gate from shared library
        stage("Quality Gate Check") {
            steps {
                qualityGate()
            }
        }

        stage("Build Docker Image") {
            steps {
                sh "docker build -t ${env.IMAGE_NAME} ."
            }
        }

        stage("Push Docker Image") {
            steps {
                pushToDockerHub(env.IMAGE_NAME, env.DOCKER_CREDENTIALS)
            }
        }

        stage("Scan Image with Trivy") {
            steps {
                runTrivy(env.IMAGE_NAME)
            }
        }

        // ✅ Deploy from shared library
        stage("Deploy Application") {
            steps {
                k8deploy()
            }
        }
    }

    post {
        always {
            // ✅ Email Notification from shared library
            sendEmailNotification(env.EMAIL_RECIPIENT)
        }
    }
}
