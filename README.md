# Microserviço de Agendamento de Tarefas (ms-tarefas) - API REST

Este microserviço é uma API REST responsável pelo gerenciamento de tarefas de usuários, incluindo criação, atualização, exclusão, listagem e controle de status das tarefas. Ele se integra com o microserviço de usuários (ms-usuarios) para validação de autenticação e permissões via JWT.

## Responsabilidades

- Expor endpoints REST para gerenciamento de tarefas

- Cadastro, atualização e manutenção de tarefas

- Validação de permissões via JWT

- Controle de perfis e permissões

### Organização de tarefas 
- Nome 
- Descrição
- Data de criação
- Data de agendamento
- E-mail
- Data de alteração
- Status (PENDENTE, NOTIFICADO, CANCELADO)

## 🔐 Segurança

- API protegida com Spring Security

- Validação de autenticação via JWT obtido do microserviço ms-usuarios

- Controle de acesso por perfis e permissões

## Tecnologias

- Java 17
- Spring Boot
- Spring Web (REST)
- Spring Security + JWT
- Gradle
- Banco de Dados: MongoDB
- Postman (testes e validação dos endpoints)

## Arquitetura

### API REST organizada em camadas:

- Controller (REST Controllers)

- Service

- Business

- Infrastructure

### Segue boas práticas de Clean Code

### Preparada para arquitetura de microserviços

## Testes da API

### Os endpoints da API REST são testados utilizando o Postman, permitindo validar:

- Requisições HTTP (GET, POST, PUT, DELETE)

- Autenticação via JWT

- Fluxo de autorização

- Respostas e códigos HTTP

## Integração entre Microserviços

Esta API REST integra-se com o microserviço de usuários (ms-usuarios) para:

- Validação de tokens JWT

- Garantir que apenas usuários autenticados possam criar, atualizar ou alterar o status de tarefas

###  Autor

- Geisivan Vitena

### Contato

- Email: gsv1205@yahoo.com

- LinkedIn: https://www.linkedin.com/in/geisivan-vitena-a46168246/
