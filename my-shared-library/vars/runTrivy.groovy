def call(string IMAGE_NAME) {
    stage("scanning the docker image") {
        sh "trivy image ${IMAGE_NAME}>trivy.report.txt"
        archiveArtifacts artifacts: "trivy-report.txt" allowEmptyArchive:false
    }
}