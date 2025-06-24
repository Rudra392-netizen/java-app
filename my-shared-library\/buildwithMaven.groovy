def call() {
    stage("build with maven") {
        sh "mvn clean package"
    }
}