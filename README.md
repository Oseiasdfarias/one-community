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
  <img height="80" src="./utils/logo1.jpeg"> 
  <img height="80" src="./utils/comite_alumnione.png">
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
```


### Endpoit Registro de usuário

Esse Endpoit é público para que qualquer pessoa possa se cadastrar no sistema.

```
POST http://localhost:8080/users
```

Json para envio dos dados:

```json
{
  "userName": "Fabio pereira",
  "email": "fabio@gmail.com",
  "password": "senha1234",
	"role": "regular"
}
```

Esse Endpoit é público para que qualquer pessoa que tenha um cadastro possa
fazer o login no sistema.


```
POST http://localhost:8080/login
```

Json para envio dos dados:

```json
{
  "login": "Fabio pereira",
  "senha": "senha1234"
}
```

Para esse endpoit é necessário efetuar o login e obter o Token para acessa-lo,
ele recebe no final da `url` número de `id` para identificar o usuário que se
deseja deletar, além disso, a pessoa logada pode deletar apenas seu próprio usuário.



```
DELETE http://localhost:8080/users/{id}
```

O tipo de Token é Bearer Token, exemplo de Token retornado é mostrado logo abaixo.


```
{
	"token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJGYWJpbyBwZXJlaXJhIiwiaXNzIjoiQVBJIE9uZUNvbW11bml0eS5jb20iLCJleHAiOjE3Mjk5ODM0OTV9.xvjRELtrwLsAjwWPV7FHF9pi1pHn71Q3hjIbhFTYBjw"
}
```


<p align="center">
  <img height="90px" src="./utils/logo2.jpeg">
</p>