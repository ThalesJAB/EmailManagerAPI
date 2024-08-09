# 📧 EmailManagerAPI

## 📝 Descrição
O EmailManagerAPI é uma aplicação Java baseada em Spring Boot que permite o envio e leitura de emails utilizando os protocolos IMAP/SMTP. A aplicação fornece endpoints REST para gerenciar a comunicação por email de maneira eficiente.

## 🔧 Funcionalidades
- Envio de emails via SMTP
- Leitura de emails via IMAP
- Configuração de múltiplas contas de email
- Suporte a formatos de email complexos

## 🗂️ Estrutura do Projeto
```plaintext
EmailManagerAPI
├── src
│   ├── main
│   │   ├── java
│   │   │   └── br
│   │   │       └── com
│   │   │           └── emailmanagerapi
│   │   │               ├── controllers
│   │   │               │   └── EmailController.java
│   │   │               ├── entities
│   │   │               │   ├── EmailConfig.java
│   │   │               │   ├── EmailMessage.java
│   │   │               │   ├── EmailRequestRead.java
│   │   │               │   └── EmailRequestSend.java
│   │   │               └── services
│   │   │                   └── EmailService.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── application-dev.properties
│   └── test
│       └── java
│           └── br
│               └── com
│                   └── emailmanagerapi
│                       └── EmailManagerApiApplicationTests.java
├── HELP.md
├── mvnw
├── mvnw.cmd
└── pom.xml
```

## 🚀 Pré-requisitos
- Java 11 ou superior
- Maven 3.6.3 ou superior

## ⚙️ Configuração
### 🛠️ Configuração de Propriedades
As configurações de conexão com servidores de email e outras propriedades podem ser definidas no arquivo `application.properties` ou `application-dev.properties`.

### 📦 Dependências
O projeto utiliza Maven para gerenciar as dependências. As principais dependências estão listadas no arquivo `pom.xml`.

## ▶️ Execução
Para executar a aplicação localmente, siga os passos abaixo:

1. Clone o repositório:
```sh
git clone https://github.com/thalesjab/EmailManagerAPI.git
cd EmailManagerAPI
```

2. Compile e execute a aplicação:
```sh
./mvnw spring-boot:run
```

3. A aplicação estará disponível em `http://localhost:8080`.

## 🔗 Endpoints
A seguir estão alguns dos principais endpoints fornecidos pela API:

### **Enviar Email**
- **POST** `/emails/send`
- **Request Body**: `EmailRequestSend`
- **Descrição**: Envia um email utilizando as configurações fornecidas.
- **Exemplo de curl**:
  ```sh
   curl --location 'http://localhost:8080/api/email/send' \
   --header 'Content-Type: application/json' \
   --data-raw '{
     "emailConfig": {
       "smtpHost": "smtp-mail.outlook.com",
       "smtpPort": 587,
       "imapHost": "outlook.office365.com",
       "imapPort": 993,
       "username": "example@outlook.com",
       "password": "yourpassword",
       "smtpStartTlsEnable": true,
       "smtpSslEnable": false
     },
     "to": "recipient@example.com",
     "subject": "Assunto do Email",
     "text": "Conteúdo do email em texto simples",
     "displayName": null
   }'
  ```

### **Ler Emails**
- **GET** `/emails/read`
- **Request Body**: `EmailRequestRead`
- **Descrição**: Lê emails de uma conta configurada.
- **Exemplo de curl**:
```sh
  curl --location 'http://localhost:8080/api/email/read' \
  --header 'Content-Type: application/json' \
  --data-raw '{
  "emailConfig": {
  "smtpHost": "smtp-mail.outlook.com",
  "smtpPort": 587,
  "imapHost": "outlook.office365.com",
  "imapPort": 993,
  "username": "example@outlook.com",
  "password": "yourpassword",
  "smtpStartTlsEnable": false,
  "smtpSslEnable": true
  },
  "folder": "INBOX"
  }'
  ```

## 🤝 Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou enviar pull requests.

