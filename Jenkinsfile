pipeline {
    agent none
    environment {
        NEXUS_HOST = 'nexus:8081'
    }
    stages {
        stage('artifact package') {
            steps {
                echo 'artifact package'
            }
        }
        stage('container runs') {
            steps {
            	echo 'container runs'
            }
        }
        stage('integration tests') {
            steps {
            	echo 'integration tests'
            }
        }
        stage('container stops') {
            steps {
            	echo 'container stops'
            }
        }
    }
}
