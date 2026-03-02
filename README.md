# Microserviço de Tarefas (ms-tarefas)

### Contexto do Projeto

O ms-tarefas é uma API REST desenvolvida em Java com Spring Boot e faz parte do projeto Agendador de Tarefas, construído com base em arquitetura de microserviços.

Este microserviço é responsável pelo gerenciamento das tarefas dos usuários, permitindo a criação, atualização, exclusão e controle de status.

Ele atua como serviço de domínio da aplicação, concentrando exclusivamente as regras de negócio relacionadas às tarefas.

##

### Papel na Arquitetura de Microserviços

Na arquitetura do Agendador de Tarefas, cada microserviço possui responsabilidade única e bem definida.

O ms-tarefas é responsável por:

● Cadastro e manutenção de tarefas

● Atualização de status

● Persistência de dados no MongoDB

● Validação de autenticação via JWT

● Exposição de métricas e monitoramento da aplicação

A autenticação é centralizada no ms-usuarios. O ms-tarefas valida o token JWT antes de processar qualquer requisição protegida.

Além disso, a comunicação entre microserviços é realizada utilizando Spring Cloud OpenFeign, permitindo chamadas HTTP declarativas e desacopladas.

Fluxo arquitetural:

Cliente → ms-usuarios (gera JWT) → ms-tarefas (valida token) → Banco de Dados

Essa separação garante:

● Desacoplamento entre identidade e domínio de tarefas

● Segurança centralizada

● Escalabilidade independente

● Organização modular da aplicação

##

API REST

O ms-tarefas expõe endpoints REST stateless utilizando:

●  Métodos HTTP (GET, POST, PUT, DELETE)

●  Representação de recursos em formato JSON

●  Comunicação via HTTP dentro da arquitetura distribuída

A aplicação segue os princípios REST e não mantém estado de sessão no servidor, utilizando JWT como mecanismo de autenticação.

##

Segurança

A API é protegida com:

● Spring Security

● Validação de autenticação via JWT

● Controle de acesso baseado em perfis e permissões

● Somente usuários autenticados podem criar, atualizar ou alterar tarefas.

##

### Observabilidade

O microserviço utiliza Spring Boot Actuator para monitoramento e exposição de métricas operacionais.

Os endpoints de gerenciamento permitem:

● Healthcheck da aplicação

● Monitoramento de disponibilidade

● Exposição de métricas

● Informações do ambiente

Exemplo de endpoint:

http://localhost:8081/actuator/health

A utilização do Actuator permite acompanhar a saúde do serviço dentro da arquitetura distribuída.

##

### Tecnologias Utilizadas

● Java 17

● Spring Boot

● Spring Web

● Spring Security + JWT

● Spring Cloud OpenFeign

● Spring Boot Actuator

● Gradle

● MongoDB

● Docker

##

### Documentação da API

A documentação da API está disponível via Swagger:

http://localhost:8081/swagger-ui.html

##

### Execução do Projeto

1. Execução via Gradle
   
./gradlew bootRun

2. Execução via Docker

Build da imagem:

docker build -t ms-tarefas .

3. Executar o container:

docker run -p 8081:8081 ms-tarefas

##

### Endpoints Expostos

| Serviço    	| Porta |
|-------------|-------|
| Tarefas API |	8081  |

##

### Benefícios Arquiteturais

● Separação clara de responsabilidades

● Comunicação declarativa via OpenFeign

● Segurança centralizada via JWT

● Persistência orientada a documentos com MongoDB

● Escalabilidade independente

● Observabilidade integrada via Actuator

● Organização modular em arquitetura de microserviços

##

### Melhorias Futuras

● Implementação de paginação e filtros avançados

● Estratégias de resiliência

● Integração com mensageria

● Deploy em Cloud

##

### Autor

**Geisivan Vitena**

LinkedIn:  
https://www.linkedin.com/in/geisivan-vitena-a46168246/
