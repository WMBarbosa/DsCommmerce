#🛒 API REST de E-commerce

Este repositório tem como objetivo servir como um ambiente de estudo, prática e consolidação de conhecimentos em desenvolvimento backend, utilizando Java e Spring Boot, com foco na construção de uma API REST para e-commerce.

O projeto aborda conceitos fundamentais e avançados de desenvolvimento de software, aplicando boas práticas, arquitetura em camadas e padrões utilizados no mercado, além de evoluir continuamente com novas funcionalidades.

##🎯 Objetivo do Repositório

Consolidar o aprendizado em desenvolvimento backend com Java

Aplicar Spring Boot e Spring Data JPA em um projeto real

Praticar a criação de APIs REST bem estruturadas

Trabalhar conceitos de autenticação, autorização e segurança

Evoluir o projeto de forma incremental, do básico ao avançado

Este repositório será constantemente evoluído, recebendo novas implementações, melhorias de arquitetura e refinamentos técnicos.

###🧩 Visão Geral do Projeto

O DSCommerce é um back-end de e-commerce que permite:

Gerenciamento de produtos

Gerenciamento de categorias

Criação e consulta de pedidos

Cadastro e autenticação de usuários

Controle de autorização baseado em perfis (ex.: usuário e administrador)

O projeto serve como base prática para estudo de Java 17+, Spring Boot, Spring Data JPA e padrões de APIs REST.

##🗂️ Estrutura do Projeto

O projeto segue a estrutura padrão do ecossistema Spring Boot, organizada em camadas:

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


💡 Essa organização facilita a manutenção, legibilidade e escalabilidade do projeto.

##🚀 Funcionalidades Implementadas
🔹 Produtos

Listagem de produtos

Busca de produto por ID

Criação, atualização e exclusão

🔹 Categorias

Listagem de categorias

Cadastro de novas categorias

Consulta de produtos por categoria

🔹 Usuários & Autenticação

Cadastro de usuários

Autenticação com JWT

Proteção de rotas privadas

Controle de permissões por perfil

🔹 Pedidos

Criação de pedidos

Consulta de pedidos por ID (restrita ao usuário autenticado)

##⚠️ Algumas funcionalidades ainda podem estar em desenvolvimento ou sujeitas a ajustes conforme a evolução do projeto.

##🛠️ Tecnologias e Ferramentas

O projeto utiliza as seguintes tecnologias:

Java 17+

Spring Boot 3

Spring Data JPA

Spring Security

JWT (JSON Web Token)

Banco de dados H2 (em memória)

Maven

Além disso, conceitos de boas práticas, separação de responsabilidades e arquitetura em camadas são aplicados em todo o projeto.

##▶️ Executando o Projeto
Pré-requisitos

JDK 17 ou superior

Maven (opcional — o projeto utiliza Maven Wrapper)

IDE de sua preferência (IntelliJ, Eclipse ou VS Code)

Passos

Clone o repositório:

git clone https://github.com/WMBarbosa/DsCommmerce.git


Acesse o diretório do projeto:

cd DsCommmerce


Execute a aplicação:

mvn spring-boot:run


Ou utilizando o Maven Wrapper:

./mvnw spring-boot:run


A aplicação estará disponível em:

http://localhost:8080

##📘 Endpoints Principais
Método	Endpoint	Descrição
GET	/products	Lista todos os produtos
GET	/products/{id}	Retorna produto por ID
POST	/products	Cria um novo produto
GET	/categories	Lista categorias
POST	/users/signup	Cadastro de usuário
POST	/login	Autenticação e geração de token

Os endpoints podem sofrer alterações conforme novas implementações forem adicionadas.

##📈 Evolução Contínua

Este repositório não representa um projeto finalizado, mas sim um projeto em constante evolução.

Novas funcionalidades, melhorias de segurança, ajustes de arquitetura, testes automatizados e boas práticas adicionais ainda estão sendo implementados ao longo do tempo.

##🤝 Contribuições

Sugestões, melhorias e feedbacks são sempre bem-vindos!
Este repositório também pode servir como referência para outros desenvolvedores que estejam estudando backend com Java e Spring.

##📌 Observação Final

Todo o conteúdo deste repositório possui finalidade educacional, com foco no desenvolvimento técnico e profissional em backend e arquitetura de APIs REST.
