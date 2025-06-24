def call(string IMAGE_NAME, string DockerHubCred ) {
    stage("Push to Dockerhub") {
        withCredentials([usernamePassword(
            credentialsId: DockerHubCred,
            usernameVariable: "DOCKER_USER",
            passwordVariable: "DOCKER_PASS"
        )])
        sh """
        docker login -u $DOCKER_USER -p $DOCKER_PASS 
        docker push ${IMAGE_NAME}
        """

    }
}