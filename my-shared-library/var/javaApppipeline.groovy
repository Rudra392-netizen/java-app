// vars/javaAppPipeline.groovy

def call(Map config = [:]) {
    pipeline {
        agent any

        environment {
            IMAGE = config.image ?: 'aiml074/java-app'
            CREDS = config.dockerCreds ?: 'dockerHubCred'
            SONAR = config.sonarEnv ?: 'SonarQube'
            PROJECT_KEY = config.projectKey ?: 'java-app'
            GIT_REPO = config.gitUrl ?: 'https://github.com/Rudra392-netizen/java-app.git'
        }

        stages {
            stage('Clone Code') {
                steps {
                    git url: GIT_REPO, branch: 'main'
                }
            }

            stage('Code Quality') {
                steps {
                    withSonarQubeEnv(SONAR) {
                        sh "mvn sonar:sonar -Dsonar.projectKey=${PROJECT_KEY}"
                    }
                }
            }

            stage('Build') {
                steps {
                    sh 'mvn clean package'
                }
            }

            stage('Docker Build') {
                steps {
                    sh "docker build -t ${IMAGE} ."
                }
            }

            stage('Scan Image') {
                steps {
                    sh "trivy image ${IMAGE}"
                }
            }

            stage('Run Container') {
                steps {
                    sh "docker run -d -p 8000:8000 ${IMAGE}"
                }
            }

            stage('Push Image') {
                steps {
                    withCredentials([usernamePassword(
                        credentialsId: CREDS,
                        usernameVariable: 'USER',
                        passwordVariable: 'PASS'
                    )]) {
                        sh """
                        echo $PASS | docker login -u $USER --password-stdin
                        docker push ${IMAGE}
                        """
                    }
                }
            }
        }

        post {
            always { cleanWs() }
            success { echo '✅ Done!' }
            failure { echo '❌ Something went wrong.' }
        }
    }
}
