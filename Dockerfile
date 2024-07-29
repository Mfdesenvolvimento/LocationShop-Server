# Usa a imagem oficial do OpenJDK 17 como base
FROM openjdk:17-jdk-alpine

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o arquivo pom.xml para o diretório de trabalho
COPY pom.xml .

# Copia todo o código fonte para o diretório de trabalho
COPY src ./src

# Instala o Maven (opcional, pode não ser necessário dependendo da imagem base)
RUN apk add --no-cache maven

# Executa o comando 'mvn package' para compilar e empacotar o aplicativo
RUN mvn -B -DskipTests package

# Define o comando padrão a ser executado quando o contêiner for iniciado
CMD ["java", "-jar", "/app/target/locationshop-1.0.0.jar"]
