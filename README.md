<p align=center> 
<img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white">
<img src="https://img.shields.io/badge/spring%20boot-%236DB33F.svg?style=for-the-badge&logo=springboot&logoColor=white">
<img src="https://img.shields.io/badge/spring%20security-%236DB33F.svg?style=for-the-badge&logo=springsecurity&logoColor=white">
<img src="https://img.shields.io/badge/openapi%20initiative-%236DB33F.svg?style=for-the-badge&logo=openapiinitiative&logoColor=white"> 
  <img src="https://img.shields.io/badge/MySQL-F2930E?style=for-the-badge&logo=mysql&logoColor=white">   
<img src="https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white">  
<img src="https://img.shields.io/badge/apache_maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white">
<img src="https://img.shields.io/badge/flyway-8EB573.svg?style=for-the-badge&logo=flyway&logoColor=white">
<img src="https://img.shields.io/badge/lombok-262425.svg?style=for-the-badge&logo=lumen&logoColor=white"> 
<img src="https://img.shields.io/badge/git-F05032.svg?style=for-the-badge&logo=git&logoColor=white">
<img src="https://img.shields.io/badge/github-181717.svg?style=for-the-badge&logo=git&logoColor=white">
<img src="https://img.shields.io/badge/json-000000.svg?style=for-the-badge&logo=json&logoColor=white">  

<br>
</p>

<!-- <p align="center">
  <img height="90px" src="./utils/logo1.jpeg">
</p> -->


<p align="center">
  <img height="110" src="./utils/logo3.jpeg"> 
  <img height="110" src="./utils/comite_alumnione.png">
</p>


<h1 align="center"><strong>One Community</strong></h1>

## Requisitos

+ **Java 21**
+ **Spring Boot**
+ **MySQL**



## Proposta do Projeto

A proposta visa desenvolver um projeto dentro da Comunidade Alumni ONE focado em proporcionar uma vivência real para os graduados pelo programa.

O projeto teria um ciclo de vida enquanto o programa existisse e tanto o seu desenvolvimento quanto a manutenção da aplicação serie feita pelos graduados.

O projeto seguiria a ideia de uma empresa júnior sem fiz lucrativos, com estruturas de lideranças, mentores e desenvolvedores em diferentes linhas, tais como, Frontend, Backend, QA, DevOps, Testes Unitários, Engenharia de banco de dados, etc.


## Projeto: One Community

A ideia para ser implementada seria um fórum para que os graduados possam postar  e responder dúvidas sobre tópicos diversos em tecnologia, além disso, o Backend e Frontend seriam desenvolvidos com as tecnologias aprendidas ao longo do programa One.

As funcionalidades da aplicação abrangeria, cadastro de usuário, criação de tópicos, responder tópicos, etc.


# Entidades do Projeto

O Banco de dados usado no projeto foi o MySQL, no arquivo `application.properties`
você deve configurar seu banco de dados.

```
  ❯ Nome do banco de dados: one_community
  ❯ Usuário do banco de dados : XXXXXXXX
  ❯ Senha do banco de dados: XXXXXXXX
```
---


## Entidade Users

Modelagem da tabela `users` para o banco de dados MySQL.

```sql 

1. Users
| Column        | Type         | Description                       |
|---------------|--------------|-----------------------------------|
| id            | BIGINT       | Primary Key (Auto Increment)      |
| username      | VARCHAR(255) | Unique username                   |
| email         | VARCHAR(255) | Users email address               |
| password      | VARCHAR(255) | Hashed password                   |
| role          | VARCHAR(50)  | Role of the user (admin, regular) |
| created_at    | TIMESTAMP    | Account creation timestamp        |
| status        | BOOLEAN      | deletion status                   |

```


+ ### Endpoit `http://localhost:8080/users`


Esses endpoints permitem o registro, login e gerenciamento de contas de usuário. O cadastro e o login são públicos, enquanto a exclusão de uma conta exige autenticação.


1. #### **Método POST - Registro de Usuário**

Endpoint público para registrar novos usuários no sistema. Qualquer pessoa pode se cadastrar.

**URL:**

```bash
❯ POST http://localhost:8080/users
```

**Corpo da Requisição:**

Envie os dados do usuário no formato JSON, incluindo `userName`, `email`, `password`, e `role`.

Onde:
  + `userId`: ID do usuário;
  + `title`: Título da pergunta;
  + `body`: Corpo da pergunta, texto;
  + `role`: tipo de usuário [regular, admin]

```json
{
  "userName": "Fabio Pereira",
  "email": "fabio@gmail.com",
  "password": "senha1234",
  "role": "regular"
}
```

**Resposta:**

Retorna os detalhes do usuário criado, incluindo o `id`, `userName`, e `role`.

```json
{
  "id": 39,
  "userName": "Ana Pereira",
  "role": "regular"
}
```

---

2. #### **Método PUT - Atualizar Dados do Usuário**

Endpoint para atualizar os dados de um usuário. O usuário deve estar autenticado e pode atualizar apenas seus próprios dados.

**URL:**

```bash
❯ PUT http://localhost:8080/users
```

**Corpo da Requisição:**

Envie o `userName` do usuário e/ou `email` e/ou `password` no formato JSON, pode-se enviar nenhum, um ou mais parâmetros na requisição:

Onde:
  + `userName`: ID do usuário que se deseja atualizar os dados;
  + `email`: Email novo que se deseja usar;
  + `password`: Nova senha que se deseja usar.

  > OBS: *A senha deve conter pelo menos 8 caracteres, incluindo uma letra maiúscula, uma letra minúscula, um número e um caractere especial."*.


```json
{
  "userName": "XXXXXX XXXXx",
  "email": "XXXX@xmail.com",
  "password": "aaaa12@A"
}

OU

{
  "userName": "XXXXXX XXXXx",
  "email": "XXXX@xmail.com"
}

OU

{
  "email": "XXXX@xmail.com"
}
```



Entre outras combinações possíveis.


**Resposta:**

Retorna os seguintes dados: `id`, `userName` e `role`.

```json
{
  "id": 45,
  "userName": "Marcos Test",
  "role": "regular"
}
```


---

1. #### **Método DELETE - Deletar Usuário**

Endpoint para deletar a conta de um usuário. É necessário estar autenticado e fornecer o token Bearer. O usuário pode deletar apenas a própria conta.

**URL:**

```bash
❯ DELETE http://localhost:8080/users/{id}
```

**Parâmetros:**

- **`id`**: ID do usuário que será deletado. Deve corresponder ao ID do usuário logado.
`

**Exemplo de Requisição:**

```bash
❯ DELETE http://localhost:8080/users/9
```


---

+ ### Endpoit `http://localhost:8080/login`



1. #### **Método POST - Login de Usuário**

Endpoint público para login de usuários previamente cadastrados. O usuário recebe um token de autenticação necessário para acessar endpoints protegidos.

**URL:**

```
❯ POST http://localhost:8080/login
```

**Corpo da Requisição:**

Envie as credenciais de login no formato JSON, com os parâmetros `email` e `senha`:

Onde:
  + `email`: email cadastrado durante o registro do usuário;
  + `senha`: senha cadastrada durante o registro de usuário,

```json
{
  "email": "Fabio Pereira",
  "senha": "senha1234"
}
```

**Resposta:**

Retorna um **token JWT** no formato **Bearer Token**, necessário para autenticar chamadas a endpoints protegidos.

Exemplo de token retornado:

```json
{
  "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJGYWJpbyBwZXJlaXJhIiwiaXNzIjoiQVBJIE9uZUNvbW11bml0eS5jb20iLCJleHAiOjE3Mjk5ODM0OTV9.xvjRELtrwLsAjwWPV7FHF9pi1pHn71Q3hjIbhFTYBjw"
}
```

---


+ ## Entidade Questions

Modelagem da tabela `questions` para o banco de dados MySQL.

```sql 
2. Questions

| Column     | Type         | Description                  |
|------------|--------------|------------------------------|
| id         | BIGINT       | Primary Key (Auto Increment) |
| user_id    | BIGINT       | Foreign Key to Users         |
| title      | VARCHAR(255) | Question title               |
| body       | TEXT         | Detailed question content    |
| created_at | TIMESTAMP    | Question creation timestamp  |
| updated_at | TIMESTAMP    | Last update timestamp        |
| views      | INT          | Number of views              |
| status     | BOOLEAN      | deletion status              |

```

+ ### Endpoit `http://localhost:8080/questions`

Esses endpoints permitem o gerenciamento de perguntas de usuários autenticados, incluindo criação, listagem, atualização e exclusão de perguntas. Todos os endpoints requerem autenticação com Spring Security.


1. #### **Método POST - Criar Pergunta**

Endpoint para postar uma nova pergunta. O usuário deve estar autenticado para realizar essa ação.

**URL:**

```bash
❯ POST http://localhost:8080/questions
```

**Corpo da Requisição:**

Envie os dados da pergunta no formato JSON, especificando o `userId`, `title`, e `body`:

Onde:
  + `userId`: ID do usuário;
  + `title`: Título da pergunta;
  + `body`: Corpo da pergunta, texto.

```json
{
  "userId": 40,
	"title": "Teste em Python",
  "body": "Alguem conhece uma biblioteca Python para testes?"
}
```

**Resposta:**

Retorna os seguintes dados: `questionId`, `userId`, `title` e `body`.


```json
{
  "questionId": 13,
  "userId": 40,
  "title": "Teste em Python",
  "body": "Alguem conhece uma biblioteca Python para testes?"
}
```

---

2. #### **Método GET - Listar Perguntas**

Endpoint que lista todas as perguntas do usuário autenticado. Somente o usuário logado pode visualizar suas próprias perguntas.

**URL:**

```bash
❯ GET http://localhost:8080/questions
```

**Exemplo de Resposta:**

A resposta inclui uma lista paginada das perguntas do usuário, com detalhes como `questionId`, `userId`, `title`, e `body`.

```json
{
  "content": [
    {
      "questionId": 8,
      "userId": 31,
      "title": "Teste em C#",
      "body": "Alguém conhece uma biblioteca C# para testes?"
    },
    {
      "questionId": 2,
      "userId": 31,
      "title": "Teste em Java",
      "body": "Alguém conhece uma biblioteca Java para testes?"
    }
  ],
  "page": {
    "size": 5,
    "number": 0,
    "totalElements": 3,
    "totalPages": 1
  }
}
```

---

3. #### **Método PUT - Atualizar Pergunta**

Endpoint para atualizar uma pergunta existente. O usuário deve estar autenticado e pode atualizar apenas perguntas criadas por ele.

**URL:**

```bash
❯ PUT http://localhost:8080/questions
```

**Corpo da Requisição:**

Envie o `id` da pergunta, `title`, e `body` no formato JSON:

Onde:
  + `id`: ID da pergunta ao qual se deseja atualizar;
  + `title`: Título atualizado da pergunta que se deseja atualizar;
  + `body`: Texto atualizado da pergunta.


```json
{
  "id": 33,
  "title": "Teste em Java Atualizado",
  "body": "Alguém conhece uma biblioteca Java para testes? (Atualizado)"
}
```

**Resposta:**

Retorna os seguintes dados: `questionId`, `userId`, `title` e `body`.

```json
{
  "questionId": 13,
  "userId": 40,
  "title": "Teste em Java Atualizado",
  "body": "Alguém conhece uma biblioteca Java para testes? (Atualizado)"
}
```


4. #### **Método DELETE - Deletar Pergunta**

Endpoint para deletar uma pergunta. Apenas perguntas criadas pelo usuário autenticado podem ser excluídas.

**URL:**

```bash
❯ DELETE http://localhost:8080/questions/{ID}
```

**Parâmetros:**

- **`ID`**: ID da pergunta que será deletada.

**Exemplo de Uso:**

```bash
❯ DELETE http://localhost:8080/questions/33
```

---



## Entidade Answer

Modelagem da tabela `answer` para o banco de dados MySQL.

```sql 

3. Answers
| Column        | Type         | Description                         |
|---------------|--------------|-------------------------------------|
| id            | BIGINT       | Primary Key (Auto Increment)        |
| question_id   | BIGINT       | Foreign Key to Questions            |
| user_id       | BIGINT       | Foreign Key to Users                |
| body          | TEXT         | Answer content                      |
| created_at    | TIMESTAMP    | Answer creation timestamp           |
| updated_at    | TIMESTAMP    | Last update timestamp               |
| is_accepted   | BOOLEAN      | Indicates if the answer is accepted |

```


+ ### Endpoit `http://localhost:8080/answers`


Esses endpoints permitem o gerenciamento de respostas de usuários autenticados, incluindo criação, listagem, atualização e exclusão de respostas. Todos os endpoints requerem autenticação com Spring Security.


1. #### **Método POST - Criar Resposta**

Endpoint para postar uma nova resposta a uma pergunta. O usuário deve estar autenticado para realizar essa ação.

**URL:**

```bash
❯ POST http://localhost:8080/answers
```

**Corpo da Requisição:**

Envie os dados da pergunta no formato JSON, especificando o `questionId` e `body`:

Onde:
  + `questionId`: ID de uma pergunta ao qual se deseja responder.
  + `body`: Texto de resposta a pergunta.


```json
{
  "questionId": 6,
  "body": "JUnit - É BOA."
}
```

**Resposta:**

Retorna os seguintes dados: `answerId`, `userId`, `questionId` e `body`.


```json
{
  "answerId": 7,
  "userId": 40,
  "questionId": 6,
  "body": "JUnit - É BOA."
}
```


2. #### **Método GET - Listar Respostas de uma Pergunta**

Endpoint que lista todas as respostas de uma determinada pergunta.

**URL:**

```bash
❯ GET http://localhost:8080/answers/list-answers-question/{id}
```

**Exemplo de Resposta:**

A resposta inclui uma lista paginada das resposta de uma pergunta, com detalhes como `answerId`, `userId`, `questionId`, e `body`.

```json
{
  "content": [
      {
          "answerId": 3,
          "userId": 31,
          "questionId": 1,
          "body": "JUnit é uma biblioteca muito usada no java para testes unitários."
      },
      {
          "answerId": 4,
          "userId": 31,
          "questionId": 1,
          "body": "JUnit é uma boa opção e, java para testes unitários."
      }
  ],
  "page": {
      "size": 5,
      "number": 0,
      "totalElements": 2,
      "totalPages": 1
  }
}
```


3. #### **Método PUT - Atualizar Resposta**

Endpoint para atualizar uma resposta existente. O usuário deve estar autenticado e pode atualizar apenas resposta criadas por ele.

**URL:**

```bash
❯ PUT http://localhost:8080/answers
```

**Corpo da Requisição:**

Envie o `id` da pergunta, `title`, e `body` no formato JSON:

Onde:
  + `answerId`: ID da pergunta ao qual se deseja atualizar;
  + `body`: Texto atualizado da pergunta.


```json
{
  "answerId": 7,
  "body": "JUnit - É BOA. (PUT TEST)"
}
```

**Resposta:**

Retorna os seguintes dados: `answerId`, `userId`, `questionId` e `body`.

```json
{
  "answerId": 7,
  "userId": 40,
  "questionId": 6,
  "body": "JUnit - É BOA. (PUT TEST)"
}
```


4. #### **Método DELETE - Deletar Resposta**

Endpoint para deletar uma resposta feita pelo usuário. Apenas respostas criadas pelo usuário autenticado podem ser excluídas por ele.

**URL:**

```bash
❯ DELETE http://localhost:8080/answers/{ID}
```

**Parâmetros:**

- **`ID`**: ID da resposta que será deletada.

**Exemplo de Uso:**

```bash
❯ DELETE http://localhost:8080/answers/33
```



---
---


# **EM DESENVLVIMENTO ...**

<br>
<br>

<p align="center">
  <img height="100px" src="./utils/logo1.jpeg">
</p>