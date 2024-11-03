<p align=center> 
<img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white">
<img src="https://img.shields.io/badge/spring%20boot-%236DB33F.svg?style=for-the-badge&logo=springboot&logoColor=white">  
<img src="https://img.shields.io/badge/MySQL-F2930E?style=for-the-badge&logo=mysql&logoColor=white">   
<img src="https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white">  
<img src="https://img.shields.io/badge/apache_maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white">
<img src="https://img.shields.io/badge/flyway-8EB573.svg?style=for-the-badge&logo=flyway&logoColor=white">
<img src="https://img.shields.io/badge/lombok-262425.svg?style=for-the-badge&logo=lumen&logoColor=white"> 
<img src="https://img.shields.io/badge/git-F05032.svg?style=for-the-badge&logo=git&logoColor=white">  
<img src="https://img.shields.io/badge/json-000000.svg?style=for-the-badge&logo=json&logoColor=white">  

<br>
</p>

<!-- <p align="center">
  <img height="90px" src="./utils/logo1.jpeg">
</p> -->


<p align="center">
  <img height="90" src="./utils/logo3.jpeg"> 
  <img height="90" src="./utils/comite_alumnione.png">
</p>


# One Community

## Requisitos

+ Java 21
+ Spring Boot
+ MySQL



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

> + Nome do banco de dados: one_community
> + Usuário do banco de dados : XXXXXXXX
> + Senha do banco de dados: XXXXXXXX


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


1. ### Método POST - Cadastro de Usuário

Endpoint público para registrar novos usuários no sistema. Qualquer pessoa pode se cadastrar.

**URL:**

```
POST http://localhost:8080/users
```

**Corpo da Requisição:**

Envie os dados do usuário no formato JSON, incluindo `userName`, `email`, `password`, e `role`.

```json
{
  "userName": "Fabio Pereira",
  "email": "fabio@gmail.com",
  "password": "senha1234",
  "role": "regular"
}
```

**Resposta:**

Retorna os detalhes do usuário criado, incluindo o `userId`, `userName`, e `role`.

---

2. ### Método POST - Login de Usuário

Endpoint público para login de usuários previamente cadastrados. O usuário recebe um token de autenticação necessário para acessar endpoints protegidos.

**URL:**

```
POST http://localhost:8080/login
```

**Corpo da Requisição:**

Envie as credenciais de login no formato JSON, com `login` (nome de usuário) e `senha`:

```json
{
  "login": "Fabio Pereira",
  "senha": "senha1234"
}
```

**Resposta:**

Retorna um token JWT no formato Bearer Token, necessário para autenticar chamadas a endpoints protegidos.

Exemplo de token retornado:

```json
{
  "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJGYWJpbyBwZXJlaXJhIiwiaXNzIjoiQVBJIE9uZUNvbW11bml0eS5jb20iLCJleHAiOjE3Mjk5ODM0OTV9.xvjRELtrwLsAjwWPV7FHF9pi1pHn71Q3hjIbhFTYBjw"
}
```

---

3. ### Método DELETE - Deletar Usuário

Endpoint para deletar a conta de um usuário. É necessário estar autenticado e fornecer o token Bearer. O usuário pode deletar apenas a própria conta.

**URL:**

```
DELETE http://localhost:8080/users/{id}
```

**Parâmetros:**

- **`id`**: ID do usuário que será deletado. Deve corresponder ao ID do usuário logado.
`

**Exemplo de Requisição:**

```
DELETE http://localhost:8080/users/9
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

Esses endpoints permitem o gerenciamento de perguntas de usuários autenticados, incluindo criação, listagem, atualização e exclusão de perguntas. Todos os endpoints requerem autenticação com Spring Security.


1. ### Método POST - Criar Pergunta

Endpoint para postar uma nova pergunta. O usuário deve estar autenticado para realizar essa ação.

**URL:**

```
POST http://localhost:8080/questions
```

**Corpo da Requisição:**

Envie os dados da pergunta no formato JSON, especificando o `userId`, `title`, e `body`:

```json
{
  "userId": 9,
  "title": "Teste em Python",
  "body": "Alguém conhece uma biblioteca Python para testes?"
}
```

**Resposta:**

Retorna a pergunta criada com um identificador único (`questionId`), título e corpo.

---

2. ### Método GET - Listar Perguntas

Endpoint que lista todas as perguntas do usuário autenticado. Somente o usuário logado pode visualizar suas próprias perguntas.

**URL:**

```
GET http://localhost:8080/questions
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

3. ### Método PUT - Atualizar Pergunta

Endpoint para atualizar uma pergunta existente. O usuário deve estar autenticado e pode atualizar apenas perguntas criadas por ele.

**URL:**

```
PUT http://localhost:8080/questions
```

**Corpo da Requisição:**

Envie o `id` da pergunta, `title`, e `body` no formato JSON:

```json
{
  "id": 33,
  "title": "Teste em Java Atualizado",
  "body": "Alguém conhece uma biblioteca Java para testes? (Atualizado)"
}
```

---

4. ### Método DELETE - Deletar Pergunta

Endpoint para deletar uma pergunta. Apenas perguntas criadas pelo usuário autenticado podem ser excluídas.

**URL:**

```
DELETE http://localhost:8080/questions/{ID}
```

**Parâmetros:**

- **`ID`**: ID da pergunta que será deletada.

**Exemplo de Uso:**

```
DELETE http://localhost:8080/questions/33
```


---
---

## EM DESENVLVIMENTO ...

<br>
<br>

<p align="center">
  <img height="100px" src="./utils/logo1.jpeg">
</p>