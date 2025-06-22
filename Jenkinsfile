pipeline {
    agent any 

    environment {
        DOCKER_IMAGE = "aiml074/java-app"
        DOCKER_CREDENTIALS_ID = "dockerHubCred"
        SONARQUBE_ENV = 'sonarqube-api'  // Name from Jenkins config
        SONAR_PROJECT_KEY = 'java-app'
    }
    stages {
        stage ("Cloning the code") {
            steps {
                git url: "https://github.com/Rudratech-ui/java-app.git", branch: "main"
            }
        }
        stage ("Code Quality check") {
            steps {
                withSonarQubeEnv("${SONARQUBE_ENV}") {
                    sh """
                    mvn sonar:sonar \
                    -Dsonar.projectKey=${SONAR_PROJECT_KEY} \
                    -Dsonar.host.url=http://13.220.130.57:9000
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
                sh "docker build -t $DOCKER_IMAGE"
            }
        }
        stage ("Docker image scanning using Trivy") {
            steps {
                sh "trivy image ${DOCKER_IMAGE}"
            }
        }
        stage("Push to dockerhub") {
            steps {
                withCredentials([usernamePassword(
                    CredentialsId: "${DOCKER_CREDENTIALS_ID}",
                    usernameVariable: "DOCKER_USER",
                    passwordVariable: "DOCKER_PASS"
                )]){
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
            cleanWs() // ✅ FIX: correct spelling; 'clean Ws()' → 'cleanWs()'
        }
        success {
            echo "Pipeline completed successfully"
        }
        failure {
            echo "Pipeline failed!"
        }
    }
}