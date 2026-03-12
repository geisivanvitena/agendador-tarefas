# Microserviço de Tarefas (ms-tarefas)

<div align="center">

![Build Status](https://img.shields.io/badge/build-passing-brightgreen)
![Java 17](https://img.shields.io/badge/java-17-brightgreen)
![Spring Boot 3.3.5](https://img.shields.io/badge/spring_boot-3.3.5-brightgreen)
![Gradle 8.3](https://img.shields.io/badge/gradle-8.3-blue)
![Dependências Seguras](https://img.shields.io/badge/dependencies-secure-brightgreen)
![GitHub Actions](https://github.com/geisivanvitena/agendador-tarefas/actions/workflows/gradle.yml/badge.svg)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=geisivanvitena_agendador-tarefas&metric=alert_status)](https://sonarcloud.io/dashboard?id=geisivanvitena_agendador-tarefas)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=geisivanvitena_agendador-tarefas&metric=bugs)](https://sonarcloud.io/dashboard?id=geisivanvitena_agendador-tarefas)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=geisivanvitena_agendador-tarefas&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=geisivanvitena_agendador-tarefas)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=geisivanvitena_agendador-tarefas&metric=code_smells)](https://sonarcloud.io/dashboard?id=geisivanvitena_agendador-tarefas)
[![Duplications](https://sonarcloud.io/api/project_badges/measure?project=geisivanvitena_agendador-tarefas&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=geisivanvitena_agendador-tarefas)
</div>

---

### Contexto do Projeto

O **ms-tarefas** é uma API REST desenvolvida em **Java com Spring Boot** e faz parte do projeto **Agendador de Tarefas**, construído com base em arquitetura de microserviços.

**Este microserviço é responsável por:** 

- Criar e gerenciar tarefas dos usuários
- Atualizar status das tarefas
- Persistir dados no MongoDB
- Validar autenticação via **JWT** emitido pelo **ms-usuario**
- Integrar-se com outros microserviços

O ms-tarefas **depende do ms-usuarios** para autenticação. Todos os endpoints protegidos exigem que o cliente envie o token JWT obtido do ms-usuarios.

O microserviço está **dockerizado**, permitindo execução isolada, portabilidade e integração rápida com o ecossistema de microserviços.

---

**Arquitetura do Sistema**

O sistema **Agendador de Tarefas** é composto por múltiplos microserviços especializados que trabalham de forma independente.

O **ms-tarefas** é responsável pelo gerenciamento das tarefas dos usuários e se integra com outros serviços da arquitetura.

**Diagrama da Arquitetura**

<div align="center">
  <img src="images/diagrama-arquitetura.png" alt="Diagrama da Arquitetura" width="700px">
</div>

**Descrição do fluxo**

1. Cliente realiza autenticação através do **ms-usuarios**.
2. **ms-usuarios** gera um token JWT.
3. Cliente envia requisições autenticadas para o **BFF**.
4. BFF encaminha requisições para o **ms-tarefas**.
5. **ms-tarefas** valida o token JWT junto ao ms-usuarios.
6. Tarefas são persistidas no **MongoDB**.
7. **ms-notificacao** é acionado para envio de notificações, se necessário.

**Benefícios dessa arquitetura:**

- Separação clara de responsabilidades
- Segurança centralizada
- Escalabilidade independente
- Organização modular da aplicação

---

#### Integração com OpenFeign

O **ms-tarefas** utiliza **Spring Cloud OpenFeign** para comunicação declarativa entre microserviços.

**Essa abordagem garante:**

- Redução de código boilerplate
- Padronização de chamadas HTTP
- Facilidade na manutenção de endpoints remotos
- Comunicação direta com o **ms-usuarios** para validação de token

---

### Segurança

A API utiliza **Spring Security** com autenticação baseada em **JWT** fornecido pelo ms-usuarios.

**Principais mecanismos de segurança:**

- Autenticação baseada em JWT do ms-usuarios
- Validação de token antes de processar requisições
- Controle de acesso baseado em autenticação
- Proteção de endpoints sensíveis

Somente usuários autenticados podem gerenciar tarefas.

---

### Observabilidade

O microserviço utiliza **Spring Boot Actuator** para monitoramento e exposição de métricas.

**Endpoints disponíveis:**

- Healthcheck da aplicação
- Monitoramento de disponibilidade
- Exposição de métricas
- Informações do ambiente

**Exemplo:**

    http://localhost:8081/actuator/health

A utilização do Actuator permite acompanhar a saúde do serviço dentro da arquitetura distribuída.

---

### API REST

O **ms-tarefas** expõe endpoints REST **stateless**:

- Métodos HTTP: GET, POST, PUT, PATCH, DELETE
- Representação de recursos em JSON
- Comunicação HTTP dentro da arquitetura distribuída

**Endpoints principais:**

| Método | Endpoint                                   | Descrição                                 |
|--------|--------------------------------------------|-------------------------------------------|
| POST   | /tarefas                                   | Criar nova tarefa                          |
| GET    | /tarefas                                   | Listar tarefas do usuário autenticado      |
| GET    | /tarefas/periodo                           | Listar tarefas por período                 |
| PUT    | /tarefas?id={id}                           | Atualizar dados da tarefa                  |
| PATCH  | /tarefas?id={id}&status={status}           | Atualizar status da tarefa                 |
| DELETE | /tarefas?id={id}                           | Deletar tarefa                             |

---

### Documentação da API

A documentação da API está disponível via **Swagger:**

    http://localhost:8081/swagger-ui.html

---

### Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Web
- Spring Security + JWT
- Spring Cloud OpenFeign
- Spring Boot Actuator
- Gradle
- MongoDB
- Docker

---

### Execução do projeto

**Docker**

    docker build -t agendador-api .

    docker run -p 8081:8081 agendador-api

**Gradle**

    ./gradlew bootRun

---

### Benefícios Arquiteturais

- Separação clara de responsabilidades
- Comunicação declarativa via OpenFeign
- Segurança centralizada via JWT emitido pelo ms-usuarios
- Persistência orientada a documentos com MongoDB
- Escalabilidade independente
- Containerização com Docker
- Observabilidade integrada via Actuator
- Organização modular em arquitetura de microserviços

---

### Melhorias Futuras

- Implementação de paginação e filtros avançados
- Estratégias de resiliência
- Integração com mensageria (RabbitMQ ou Kafka)
- Implementação de testes automatizados (unitários e de integração)
- Deploy em ambiente Cloud

---

### Autor

**Geisivan Vitena**

LinkedIn:  
https://www.linkedin.com/in/geisivan-vitena-a46168246/
