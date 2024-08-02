# Usa a imagem oficial do Maven com OpenJDK 17 como base
FROM maven:3.8.5-openjdk-17-slim AS build

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o arquivo pom.xml para o diretório de trabalho
COPY pom.xml .

# Copia todo o código fonte para o diretório de trabalho
COPY src ./src

# Executa o comando 'mvn package' para compilar e empacotar o aplicativo
RUN mvn -B -DskipTests package

# Usa uma imagem mais leve do OpenJDK para o runtime
FROM openjdk:17-jdk-alpine

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o arquivo JAR gerado do estágio de build para o estágio de runtime
COPY --from=build /app/target/locationshop-0.0.1.jar /app/locationshop-0.0.1.jar

# Verifica o conteúdo do diretório
RUN ls -l /app

# Define o comando padrão a ser executado quando o contêiner for iniciado
CMD ["java", "-jar", "locationshop-0.0.1.jar"]
