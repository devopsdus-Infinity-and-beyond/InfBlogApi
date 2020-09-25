pipeline {
    agent none
    environment {
        NEXUS_HOST = 'nexus:8081'
        DOCKER_NETWORK = 'tools'
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
                            args '--network $DOCKER_NETWORK'
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
                stage('build image - docker cli from host') {
                    agent {
                        docker {
                            image 'maven:3.6.3-adoptopenjdk-14'
                        }
                    }
                    steps {
                        sh 'mvn install -DskipTests -P build-docker-image'
                         withCredentials([usernamePassword(credentialsId: 'docker-registry', usernameVariable: 'DOCKER_REGISTRY_USER', passwordVariable: 'DOCKER_REGISTRY_PASSWORD')]) {
                            catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                                sh '''
                                    docker login -p $DOCKER_REGISTRY_PASSWORD -u $DOCKER_REGISTRY_USER localhost:5000
                                '''
                            }
                        }

                    }
                }
                stage('build image - docker cli from container') {
                    agent {
                        docker {
                            image 'maven:3.6.3-adoptopenjdk-14'
                            args '--network $DOCKER_NETWORK'
                        }
                    }
                    steps {
                        sh 'mvn install -DskipTests -P build-docker-image'
                         withCredentials([usernamePassword(credentialsId: 'docker-registry', usernameVariable: 'DOCKER_REGISTRY_USER', passwordVariable: 'DOCKER_REGISTRY_PASSWORD')]) {
                            catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                                sh '''
                                    docker login -p $DOCKER_REGISTRY_PASSWORD -u $DOCKER_REGISTRY_USER registry:5000
                                '''
                            }
                        }

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
