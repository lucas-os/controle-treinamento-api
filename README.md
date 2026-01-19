# API de Controle de Treinamentos

API REST desenvolvida em Java com Spring Boot para controle de cursos, turmas de treinamento e participantes.

## Tecnologias utilizadas
- Java 17
- Spring Boot
- JDBC (JdbcTemplate)
- Banco de dados relacional
- Maven

## Funcionalidades
- Cadastro, alteração, exclusão e listagem de cursos
- Cadastro, alteração e exclusão de turmas
- Listagem de turmas por curso
- Inclusão, exclusão e listagem de participantes por turma
- Aplicação das regras de negócio descritas no enunciado

## Observações
- Não foi utilizado JPA, Hibernate ou ORM
- Todas as consultas SQL foram escritas manualmente
- Arquitetura separada em Controller, Service, Repository e Model

## Importante
A tabela `Funcionario` é considerada previamente existente e populada,
conforme descrito no enunciado do exercício. Não há endpoints de cadastro
de funcionários neste projeto.

## Como executar
1. Configurar o banco de dados no `application.properties`
2. Executar a aplicação via Spring Boot
3. Testar os endpoints via Postman ou ferramenta similar
