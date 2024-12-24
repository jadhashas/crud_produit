# Utiliser une image de base Java
FROM openjdk:11-jre-slim

# Copier le fichier JAR généré par Maven dans le conteneur
COPY target/crud_produit-1.0-SNAPSHOT.jar app.jar

# Définir la commande d'exécution par défaut
ENTRYPOINT ["java", "-jar", "/app.jar"]