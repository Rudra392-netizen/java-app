def call(String sonarQubeCredentialId, String projectKey, String sonarQubeEnv = 'SonarQube') {
    stage("Sonarqube analysis") {
        withSonarQubeEnv(sonarQubeEnv) {
            withCredentials([usernamePassword(
                credentialsId: sonarQubeCredentialId,
                usernameVariable: 'SONAR_USER',
                passwordVariable: 'SONAR_PASS'
            )]) {
                sh """
                    mvn sonar:sonar \
                    -Dsonar.login=$SONAR_USER \
                    -Dsonar.password=$SONAR_PASS \
                    -Dsonar.projectKey=${projectKey}
                """
            }
        }
    }
}
