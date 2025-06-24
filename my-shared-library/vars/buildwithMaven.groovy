def call() {
    stage("Build with maven") {
        sh "maven clean package"
    }
}