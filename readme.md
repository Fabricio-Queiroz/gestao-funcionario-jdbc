# 🏢 Sistema de Gestão de Funcionários (CRUD JDBC)

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![SQL Server](https://img.shields.io/badge/SQL%20Server-CC2927?style=for-the-badge&logo=microsoft-sql-server&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)

## 📌 Sobre o Projeto

Este projeto é uma aplicação Java Console desenvolvida para demonstrar o domínio sobre operações de **CRUD** (Create, Read, Update, Delete) utilizando **JDBC (Java Database Connectivity)** puro, sem o uso de frameworks ORM (como Hibernate), para fins de estudo aprofundado sobre persistência de dados.

O banco de dados utilizado é o **Microsoft SQL Server**, rodando em um container **Docker** para garantir portabilidade e isolamento do ambiente.

## 🚀 Tecnologias Utilizadas

* **Linguagem:** Java (JDK 21)
* **Banco de Dados:** SQL Server 2019/2022
* **Infraestrutura:** Docker (Containerização)
* **Persistência:** JDBC (Driver `mssql-jdbc`)
* **Arquitetura:** MVC simplificado (Model, Service, View)
* **IDE:** IntelliJ IDEA

## ⚙️ Arquitetura do Projeto

O código foi organizado seguindo boas práticas de Orientação a Objetos e separação de responsabilidades:

```text
src/
└── br/com/fabricio/gestao/
    ├── model/      # Representação dos dados (POJO - Funcionario)
    ├── service/    # Lógica de negócios e acesso ao BD (DAO Pattern)
    └── view/       # Interface com o usuário (Menu Console)

🛠️ Como Executar
Pré-requisitos
Java JDK 17+ instalado.

Docker instalado e rodando.

Maven ou Gradle (para dependências) ou Jar do driver JDBC.

Passo 1: Configurar o Banco de Dados (Docker)
Execute o seguinte comando no terminal para subir o container do SQL Server:

Bash

sudo docker run -e "ACCEPT_EULA=Y" -e "MSSQL_SA_PASSWORD=Fabricio123!" -p 1433:1433 --name sql_server_container -d [mcr.microsoft.com/mssql/server:2019-latest](https://mcr.microsoft.com/mssql/server:2019-latest)
Passo 2: Criar a Tabela
Conecte-se ao banco (via DBeaver ou Terminal) e execute o script SQL inicial:

SQL

CREATE DATABASE empresa_db;
GO

USE empresa_db;
GO

CREATE TABLE funcionarios (
    id INT PRIMARY KEY IDENTITY(1,1),
    nome_completo VARCHAR(100),
    cargo VARCHAR(50),
    salario DECIMAL(10, 2)
);
Passo 3: Executar a Aplicação
Clone este repositório.

Abra o projeto na sua IDE de preferência.

Verifique a string de conexão em FuncionarioService.java para garantir que a senha bate com a do container.

Execute a classe Main.java.

📸 Funcionalidades
O sistema possui um menu interativo no console com as seguintes opções:

Cadastrar: Insere um novo funcionário no banco.

Listar: Exibe todos os funcionários formatados em tabela.

Atualizar: Permite alterar cargo e salário via ID.

Demitir: Remove um registro do banco via ID.

📝 Autor
Fabrício Queiroz

Estudante de Ciência da Computação - UNIP

Desenvolvido para fins acadêmicos e portfólio pessoal.rm