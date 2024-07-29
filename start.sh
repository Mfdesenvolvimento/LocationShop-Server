#!/bin/bash

# Build da imagem da aplicação Spring Boot
docker build -t locationshop-service:latest .
  
  # Rodar o container do PostgreSQL
docker run -d \
--name postgres-db \
-e POSTGRES_DB=locationshop_db \
-e POSTGRES_USER=postgres \
-e POSTGRES_PASSWORD=Alegri@18 \
-p 5432:5432 \
postgres:latest
  
  # Aguardar alguns segundos para garantir que o PostgreSQL esteja pronto
sleep 10
  
  # Rodar o container da aplicação Spring Boot
docker run -d \
--name spring-boot \
--link postgres-db:db \
-e SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/locationshop_db \
-e SPRING_DATASOURCE_USERNAME=postgres \
-e SPRING_DATASOURCE_PASSWORD=Alegri@18 \
-p 8080:8080 \
locationshop-service:latest
