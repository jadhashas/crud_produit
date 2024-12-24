pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = 'dockerhub-credentials' // ID des credentials Docker Hub dans Jenkins
        DOCKER_IMAGE = 'jadhashas48/crud_produit' // Nom de l'image Docker sur Docker Hub
        DOCKER_TAG = '1.0-SNAPSHOT' // Tag de l'image Docker
    }

    stages {
        // Étape 1 : Cloner le dépôt Git
        stage('Cloner le dépôt') {
            steps {
                git branch: 'master', url: 'https://github.com/jadhashas/crud_produit'
            }
        }

        // Étape 2 : Construction du projet
        stage('Build') {
            steps {
                bat 'mvnw.cmd clean install' // Maven Wrapper
            }
        }

        // Étape 3 : Tests unitaires
        stage('Tests unitaires') {
            steps {
                bat 'mvnw.cmd test'
            }
        }

        // Étape 4 : Package
        stage('Package') {
            steps {
                bat 'mvnw.cmd package'
            }
        }

        // Étape 5 : Lister les fichiers dans target
        stage('Lister fichiers dans target') {
            steps {
                bat 'dir target'
            }
        }

        // Étape 6 : Simulation du déploiement local
//         stage('Deploy') {
//             steps {
//                 script {
//                     bat 'mkdir C:\\Deploy'
//                     bat 'copy target\\crud_produit-1.0-SNAPSHOT.jar C:\\Deploy\\'
//                 }
//             }
//         }

        // Étape 7 : Construire une image Docker
        stage('Build Docker Image') {
            steps {
                script {
                    bat """
                        docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} .
                    """
                }
            }
        }

        // Étape 8 : Pousser l'image Docker vers Docker Hub
        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', "${DOCKERHUB_CREDENTIALS}") {
                        bat "docker push ${DOCKER_IMAGE}:${DOCKER_TAG}"
                    }
                }
            }
        }
    }
}