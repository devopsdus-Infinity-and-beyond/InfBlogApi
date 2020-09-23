pipeline {
    agent none
    environment {
        NEXUS_HOST = 'nexus:8081'
    }
    stages {
        stage('unit tests') {
           agent {
                docker {
                    image 'maven:3.6.3-adoptopenjdk-14'
                }
            }
            steps {
                sh 'mvn test'
            }
         }
        stage('nexus upload') {
           agent {
                docker {
                    image 'maven:3.6.3-adoptopenjdk-14'
                    args '--network tools'
                }
            }
            steps {
                withCredentials([usernamePassword(credentialsId: 'nexus', usernameVariable: 'NEXUS_USER', passwordVariable: 'NEXUS_PASSWORD')]) {
                    sh 'mvn deploy -s settings.xml'
                }
            }
        }
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
