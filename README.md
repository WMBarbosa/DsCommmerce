API REST de e-commerce desenvolvida em Java com Spring Boot, com foco em boas práticas de desenvolvimento, arquitetura em camadas e implementação de CRUD de produtos, categorias, pedidos e autenticação de usuários.
Este projeto foi iniciado como parte de estudos e continuará evoluindo com novas funcionalidades e melhorias contínuas.

📌 Visão Geral

O DSCommerce é um back-end para um e-commerce simples que permite:

Gerenciar produtos

Gerenciar categorias

Realizar pedidos

Cadastro e autenticação de usuários

Controle de autorização (ex.: operações restritas a administradores)

O projeto serve como base prática para estudo e aplicação de Spring Boot, Spring Data JPA, Java 17+ e padrões de APIs REST.

🗂️ Estrutura do Projeto

O projeto segue a estrutura padrão de aplicações Spring Boot, organizada em camadas:

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
├── .gitignore
├── mvnw / mvnw.cmd
├── pom.xml
└── README.md


💡 Essa estrutura modularizada facilita a manutenção, testes e escalabilidade do projeto.

🚀 Funcionalidades Atuais
✔️ Produtos

Listar todos os produtos

Obter produto por ID

Criar, atualizar e excluir produtos

✔️ Categorias

Listar categorias

Criar nova categoria

Listar produtos por categoria

✔️ Usuários & Autenticação

Registro de usuários

Login com retorno de token JWT

Autorização de rotas privadas conforme o perfil do usuário

✔️ Pedidos

Criar pedidos

Buscar pedido por ID (restrito ao usuário autenticado)

⚠️ Algumas funcionalidades ainda podem estar em desenvolvimento ou sofrer alterações. Consulte os endpoints diretamente no código para mais detalhes.

🛠️ Tecnologias Utilizadas
Camada	Tecnologia
Linguagem	Java
Framework	Spring Boot 3
Persistência	Spring Data JPA
Banco de Dados	H2 (em memória)
Segurança	Spring Security + JWT
Build	Maven
📦 Pré-requisitos

Antes de executar o projeto, é necessário ter instalado:

JDK 17 ou superior

Maven (opcional, pois o projeto utiliza Maven Wrapper)

Uma IDE como IntelliJ IDEA, Eclipse ou VS Code

▶️ Como Executar o Projeto

Clone o repositório:

git clone https://github.com/WMBarbosa/DsCommmerce.git


Acesse a pasta do projeto:

cd DsCommmerce


Execute a aplicação:

mvn spring-boot:run


Ou utilizando o Maven Wrapper:

./mvnw spring-boot:run


A API estará disponível em:

http://localhost:8080

📘 Endpoints Principais
Método	Endpoint	Descrição
GET	/products	Lista todos os produtos
GET	/products/{id}	Retorna produto por ID
POST	/products	Cria um novo produto
GET	/categories	Lista categorias
POST	/users/signup	Registro de usuário
POST	/login	Autenticação e geração de token

Os endpoints podem ser alterados ou expandidos conforme a evolução do projeto.

🚧 Status do Projeto

🔄 Projeto em desenvolvimento ativo.
Novas funcionalidades, melhorias de arquitetura, testes automatizados e ajustes de segurança ainda estão sendo implementados como parte do processo contínuo de aprendizado e evolução técnica.
