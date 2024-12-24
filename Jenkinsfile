pipeline {
    agent any

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

        // Étape 6 : Simulation du déploiement
        stage('Deploy') {
            steps {
                script {
                    bat 'mkdir C:\\Deploy'
                    bat 'copy target\\crud_produit-1.0-SNAPSHOT.jar C:\\Deploy\\'
                }
            }
        }
    }
}