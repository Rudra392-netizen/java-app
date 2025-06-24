def call(string IMAGE_NAME, string dockerHubCred) {
    stage("pushing to dockerhub") {
        withCrdentials([usernamePassword(
            credentialsId: dockerHubCred,
            usernameVariable: "DOCKER_USER",
            passwordVariable: "DOCKER_PASS"
        )])
        sh """
        docker login -u $DOCKER_USER -p $DOCKER_PASS
        docker push ${DOCKER_IMAGE}
        """
    }
}