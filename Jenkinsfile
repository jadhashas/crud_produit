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
                bat '.mvnw.cmd test'
            }
        }

        // Étape 4 : Package (générer un fichier exécutable .jar ou .war)
        stage('Package') {
            steps {
                bat 'mvnw.cmd package'
            }
        }
    }
}