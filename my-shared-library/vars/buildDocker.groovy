def call(string IMAGE_NAME) {
    stage("build with docker") {
        sh "docker build -t ${IMAGE_NAME}"
    }
}