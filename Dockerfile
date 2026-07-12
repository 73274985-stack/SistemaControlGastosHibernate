FROM maven:3.9.8-eclipse-temurin-21

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

CMD ["java","-jar","target/SistemaControlGastos-1.0.jar"]