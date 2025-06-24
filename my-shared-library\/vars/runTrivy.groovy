def call(string IMAGE_NAME) {
    stage("Trivy scane") {
        sh "trivy image ${IMAGE_NAME}>trivy.report.txt",
        archiveArtifacts artifacts: "trivy-report.txt", allowEmptyArchive:false

    }
}