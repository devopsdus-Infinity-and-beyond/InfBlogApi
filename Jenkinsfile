pipeline {
    agent none
    environment {
        NEXUS_HOST = 'nexus:8081'
    }
    stages {
        stage('parallel') {
            parallel {
                stage('test') {
                    agent {
                        docker {
                            image 'maven:3.6.3-adoptopenjdk-14'
                        }
                    }
                    steps {
                        sh 'mvn test'
                    }
                 }
                stage('upload maven modules') {
                    agent {
                        docker {
                            image 'maven:3.6.3-adoptopenjdk-14'
                            args '--network tools'
                        }
                    }
                    steps {
                        withCredentials([usernamePassword(credentialsId: 'nexus', usernameVariable: 'NEXUS_USER', passwordVariable: 'NEXUS_PASSWORD')]) {
                            sh '''
                                mvn deploy -s settings.xml
                            '''
                        }
                    }
                }
                stage('build image') {
                    agent {
                        docker {
                            image 'maven:3.6.3-adoptopenjdk-14'
                        }
                    }
                    steps {
                        sh 'mvn install -DskipTests -P build-docker-image'
                    }
                }
            }
        }
        stage('run all containers') {
            agent {
                docker {
                    image 'docker/compose'
                }
            }
            steps {
                dir('app-mysql'){
                    sh 'docker-compose up --build -d'
                }
            }
        }
        stage('run integration test') {
            agent {
                docker {
                    image 'node:12-alpine'
                    args '--network app'
                }
            }
            steps {
                dir('integration-tests'){
                    catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                        sh 'npm install'
                        sh 'npm start'
                    }
                }
            }
        }
        stage('stop all containers') {
            agent {
                docker {
                    image 'docker/compose'
                }
            }
            steps {
                dir('app-mysql'){   
                    sh 'docker-compose down'
                }
            }
        }
    }
}
