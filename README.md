# Projeto
<h1 align="center"> Desafio empresa Caju</h1>

### 

1. Java 17 instalado
2. Postgres 16+
3. Docker

### Como executar projeto:

1. Build da raiz do projeto:

   mvn clean package

2. Configurar variável docker
   DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD

3. Run

    Na pasta raiz do projeto executar:
    sudo docker-compose up -d

<h5>Se ocorrer um erro ao criar a imagem devido ao OpenJDK, execute o seguinte comando: </h5>
docker pull openjdk:17-jdk-slim-buster



## Url acesso
  http://localhost:8080/transactions

## Payload
<h5>Transaction</h5>
{
"account": "123",
"totalAmount": "30.00",
"mcc": "5811",
"merchant": "UBER TRIP                   SAO PAULO BR"
}

