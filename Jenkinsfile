pipeline {
    agent any 

    environment {
        DOCKER_IMAGE = "aiml074/java-app"
        DOCKER_CREDENTIALS_ID = "dockerHubCred"
        SONARQUBE_ENV = 'SonarQube'  // Replace with actual name from Jenkins config
        SONAR_PROJECT_KEY = 'java-app'
    }

    stages {
        stage ("Cloning the code") {
            steps {
                git url: "https://github.com/Rudra392-netizen/java-app.git", branch: "main"
            }
        }

        stage ("Code Quality check") {
            steps {
                withSonarQubeEnv("${SONARQUBE_ENV}") {
                    sh """
                    mvn sonar:sonar \
                    -Dsonar.projectKey=${SONAR_PROJECT_KEY} \
                    -Dsonar.host.url=http://3.80.146.150:9000
                    """
                }
            }
        }

        stage ("Build with maven") {
            steps {
                sh "mvn clean package"
            }
        }

        stage ("Build docker image") {
            steps {
                sh "docker build -t $DOCKER_IMAGE ."
            }
        }

        stage ("Docker image scanning using Trivy") {
            steps {
                sh "trivy image ${DOCKER_IMAGE}"
            }
        }
        stage ("Run docker container") {
            steps {
                sh "docker run -d -p 8000:8000 ${DOCKER_IMAGE}"
            }
        }
        stage("Push to dockerhub") {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: "${DOCKER_CREDENTIALS_ID}",
                    usernameVariable: "DOCKER_USER",
                    passwordVariable: "DOCKER_PASS"
                )]) {
                    sh """
                    echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin
                    docker push $DOCKER_IMAGE
                    """
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
        success {
            echo "Pipeline completed successfully"
        }
        failure {
            echo "Pipeline failed!"
        }
    }
}
