def call(string DOCKER_IMAGE) {
    satge("Build docker image") {
        sh "docker build -t ${DOCKER_IMAGE}"
    }
}