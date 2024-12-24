pipeline {
    agent any

    stages {
        // Étape 1 : Charger les variables d'environnement depuis env.properties
        stage('Charger les variables d\'environnement') {
            steps {
                script {
                    def props = readProperties file: 'env.properties'
                    env.DOCKERHUB_CREDENTIALS = props['DOCKERHUB_CREDENTIALS']
                    env.DOCKER_IMAGE = props['DOCKER_IMAGE']
                    env.DOCKER_TAG = props['DOCKER_TAG']
                }
            }
        }

        // Étape 2 : Cloner le dépôt Git
        stage('Cloner le dépôt') {
            steps {
                git branch: 'master', url: 'https://github.com/jadhashas/crud_produit'
            }
        }

        // Étape 3 : Construction du projet
        stage('Build') {
            steps {
                bat 'mvnw.cmd clean install' // Maven Wrapper
            }
        }

        // Étape 4 : Tests unitaires
        stage('Tests unitaires') {
            steps {
                bat 'mvnw.cmd test'
            }
        }

        // Étape 5 : Package
        stage('Package') {
            steps {
                bat 'mvnw.cmd package'
            }
        }

        // Étape 6 : Lister les fichiers dans target
        stage('Lister fichiers dans target') {
            steps {
                bat 'dir target'
            }
        }

        // Étape 7 : Construire une image Docker
        stage('Build Docker Image') {
            steps {
                script {
                    bat """
                        docker build -t ${env.DOCKER_IMAGE}:${env.DOCKER_TAG} .
                    """
                }
            }
        }

        // Étape 8 : Pousser l'image Docker vers Docker Hub
        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', "${env.DOCKERHUB_CREDENTIALS}") {
                        bat "docker push ${env.DOCKER_IMAGE}:${env.DOCKER_TAG}"
                    }
                }
            }
        }
    }
}