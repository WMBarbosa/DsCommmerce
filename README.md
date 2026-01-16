# 🛒 API REST de E-commerce

Este repositório tem como objetivo servir como um **ambiente de estudo, prática e consolidação de conhecimentos em desenvolvimento backend**, por meio da construção de uma **API REST de e-commerce** utilizando **Java e Spring Boot**.

Aqui são explorados conceitos, técnicas, boas práticas e padrões amplamente utilizados no mercado, com foco no **ecossistema Spring**, **arquitetura em camadas** e **desenvolvimento de APIs REST seguras e bem estruturadas**.

---

## 🎯 Objetivo do Repositório

* Consolidar o aprendizado em **Java e Spring Boot**
* Aplicar conceitos de **arquitetura em camadas**
* Desenvolver **APIs REST seguindo boas práticas**
* Praticar **CRUD de entidades reais de um e-commerce**
* Implementar autenticação e autorização utilizando **OAuth2 (Resource Server) com JWT**
* Evoluir o projeto de forma incremental, do básico ao avançado

Este repositório será **constantemente evoluído** com novas implementações, melhorias de código e aprimoramentos técnicos.

---

## 🧩 Visão Geral do Projeto

O **DSCommerce** é um back-end de e-commerce simples que permite:

* Gerenciar **produtos**
* Gerenciar **categorias**
* Criar e consultar **pedidos**
* Realizar **cadastro e autenticação de usuários**
* Controlar **perfis e permissões de acesso** (ex.: usuário e administrador)

O projeto serve como base prática para estudo de **Java 17+**, **Spring Boot**, **Spring Data JPA** e padrões de **APIs REST**.

---

## 🗂️ Estrutura do Projeto

O projeto segue a estrutura padrão de aplicações Spring Boot, organizada em camadas:

```
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.seuprojeto.dscommerce
│   │   │       ├── controllers   # Endpoints REST
│   │   │       ├── services      # Regras de negócio
│   │   │       ├── repositories  # Acesso ao banco de dados
│   │   │       ├── entities      # Entidades JPA
│   │   │       ├── dto           # Objetos de transferência de dados
│   │   │       └── config        # Configurações (segurança, CORS, etc.)
│   │   └── resources
│   │       └── application.properties
├── pom.xml
├── mvnw / mvnw.cmd
└── README.md
```

💡 Essa organização facilita a **manutenção**, **legibilidade** e **escalabilidade** do projeto.

---

## 🚀 Funcionalidades Implementadas

### 🔹 Produtos

* Listagem de produtos
* Busca de produto por ID
* Criação, atualização e exclusão de produtos

### 🔹 Categorias

* Listagem de categorias
* Cadastro de novas categorias
* Consulta de produtos por categoria

### 🔹 Usuários & Autenticação

* Cadastro de usuários
* Autenticação com **JWT**
* Proteção de rotas privadas
* Controle de permissões por perfil

### 🔹 Pedidos

* Criação de pedidos
* Consulta de pedidos por ID (restrita ao usuário autenticado)

⚠️ Algumas funcionalidades ainda podem estar em desenvolvimento ou sofrer ajustes conforme a evolução do projeto.

---

## 🛠️ Tecnologias e Ferramentas Utilizadas

Este projeto utiliza as seguintes tecnologias:

* **Java 17+**
* **Spring Boot 3**
* **Spring Data JPA**
* **Spring Security**
* **JWT (JSON Web Token)**
* **Banco de dados H2 (em memória)**
* **Maven**

Além disso, são aplicados conceitos de **boas práticas**, **separação de responsabilidades** e **arquitetura em camadas**.

---

## ▶️ Executando o Projeto

### Pré-requisitos

* JDK 17 ou superior
* Maven (opcional — o projeto utiliza Maven Wrapper)
* IDE de sua preferência (IntelliJ, Eclipse ou VS Code)

### Passos

1. Clone o repositório:

```bash
git clone https://github.com/WMBarbosa/DsCommmerce.git
```

2. Acesse o diretório do projeto:

```bash
cd DsCommmerce
```

3. Execute a aplicação:

```bash
mvn spring-boot:run
```

Ou utilizando o Maven Wrapper:

```bash
./mvnw spring-boot:run
```

4. A aplicação estará disponível em:

```
http://localhost:8080
```

---

## 📘 Endpoints Principais

| Método | Endpoint         | Descrição                       |
| ------ | ---------------- | ------------------------------- |
| GET    | `/products`      | Lista todos os produtos         |
| GET    | `/products/{id}` | Retorna produto por ID          |
| POST   | `/products`      | Cria um novo produto            |
| GET    | `/categories`    | Lista categorias                |
| POST   | `/users/signup`  | Cadastro de usuário             |
| POST   | `/login`         | Autenticação e geração de token |

> Os endpoints podem sofrer alterações conforme novas implementações forem adicionadas.

---

## 📈 Evolução Contínua

Este repositório **não é um projeto final**, mas sim um **ambiente vivo de aprendizado**.
Novas funcionalidades, melhorias de segurança, ajustes de arquitetura e testes automatizados ainda estão sendo implementados.

---

## 🤝 Contribuições

Sugestões, melhorias e feedbacks são sempre bem-vindos!
Este repositório também pode servir como referência para desenvolvedores que estejam estudando **backend com Java e Spring Boot**.

---

## 📌 Observação

Todo o conteúdo aqui presente possui **finalidade educacional**, com foco no **desenvolvimento técnico e profissional** em backend e arquitetura de APIs REST.

