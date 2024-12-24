pipeline {
    agent any

    environment {
        SONARQUBE_SERVER = 'SonarQube' // Nom configuré pour SonarQube dans Jenkins
    }

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

        // Étape 3 : Analyse SonarQube
        stage('Analyse SonarQube') {
            steps {
                withSonarQubeEnv("${SONARQUBE_SERVER}") {
                    bat 'mvnw.cmd sonar:sonar'
                }
            }
        }

        // Étape 4 : Construction du projet
        stage('Build') {
            steps {
                bat 'mvnw.cmd clean install'
            }
        }

        // Étape 5 : Tests unitaires
        stage('Tests unitaires') {
            steps {
                bat 'mvnw.cmd test'
            }
        }

        // Étape 6 : Package
        stage('Package') {
            steps {
                bat 'mvnw.cmd package'
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