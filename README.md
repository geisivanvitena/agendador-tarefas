# Microserviço - Agendador de Tarefas (ms-tarefas)
## Sobre o Projeto

O ms-tarefas é um microserviço responsável pelo gerenciamento de tarefas dos usuários do sistema.

Ele permite criar, atualizar, excluir e controlar o status das tarefas, garantindo segurança através da validação de tokens JWT emitidos pelo microserviço de usuários.

Este serviço integra-se à arquitetura de microserviços do sistema e pode ser executado individualmente ou via container Docker.

##

## Arquitetura do Sistema

### O microserviço é responsável por:

● Cadastro e manutenção de tarefas

● Atualização de status

● Validação de permissões via JWT

● Integração com ms-usuarios para autenticação

### Fluxo do sistema:

Cliente → ms-usuarios (gera JWT) → ms-tarefas (valida token) → Banco de Dados

##

## Segurança

### A API é protegida com:

● Spring Security

● Validação de autenticação via JWT

● Controle de acesso por perfis e permissões

● Somente usuários autenticados podem criar, atualizar ou alterar tarefas.

##

## Documentação da API (Swagger)

### A documentação da API pode ser acessada em:

● Tarefas API → http://localhost:8081/swagger-ui.html

##

## Tecnologias Utilizadas

● Java 17

● Spring Boot

● Spring Web (REST)

● Spring Security + JWT

● Gradle

● Docker

● MongoDB

## Pré-requisitos

### Antes de executar o projeto você precisa ter instalado:

● Java 17

● Docker

● MongoDB

● Gradle

##

## Como Executar o Projeto

### Executando via Gradle:

●  ./gradle bootRun

### Executando via Docker:

●  docker build -t ms-tarefas .

### Executar o container:

●  docker run -p 8081:8081 ms-tarefas

### Endpoints Expostos

●  Serviço	Porta

●  Tarefas API	8081

##

### Benefícios da Arquitetura

● Organização em camadas

● Segurança via JWT

● Escalabilidade

● Integração com arquitetura de microserviços

##

### Melhorias Futuras

● Implementar paginação e filtros

● Adicionar monitoramento com Actuator

● Integração com mensageria

● Deploy em Cloud

##

## Autor

Desenvolvido por **Geisivan Vitena**

LinkedIn:  
https://www.linkedin.com/in/geisivan-vitena-a46168246/