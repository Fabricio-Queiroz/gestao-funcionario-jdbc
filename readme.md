# Gestao de Funcionarios com Java e JDBC

Projeto simples para aprender Java, banco de dados relacional e JDBC sem framework.

## O que voce pratica aqui

- Classes, objetos, construtores, getters e setters
- Entrada de dados pelo console com `Scanner`
- Conexao com SQL Server usando JDBC
- CRUD com `INSERT`, `SELECT`, `UPDATE` e `DELETE`
- `PreparedStatement` para enviar parametros com seguranca
- Organizacao basica de projeto Maven

## Estrutura

```text
src/main/java/com/example/gestao/
  Main.java                  # Ponto de entrada da aplicacao
  db/ConnectionFactory.java  # Abre conexoes com o banco
  model/Funcionario.java     # Representa os dados de um funcionario
  dao/FuncionarioDao.java    # Executa SQL no banco
  view/MenuConsole.java      # Menu e leitura de dados no console

src/main/resources/schema.sql # Script para criar banco e tabela
docker-compose.yml            # SQL Server local para estudo
pom.xml                       # Configuracao Maven e driver JDBC
```

## Como executar

### 1. Subir o SQL Server

```bash
docker compose up -d
```

### 2. Criar o banco e a tabela

Execute o arquivo `src/main/resources/schema.sql` em uma ferramenta como DBeaver,
Azure Data Studio ou IntelliJ Database.

Dados padrao de conexao:

- Host: `localhost`
- Porta: `1433`
- Usuario: `sa`
- Senha: `LocalDev123!`
- Banco: `empresa_db`

### 3. Rodar o projeto

Pela IDE, execute a classe:

```text
com.example.gestao.Main
```

Se tiver Maven instalado:

```bash
mvn compile exec:java
```

## Ordem sugerida de estudo

1. Abra `Funcionario.java` e entenda como um objeto guarda dados.
2. Abra `ConnectionFactory.java` e entenda como o Java conecta no banco.
3. Abra `FuncionarioDao.java` e leia um metodo por vez junto com o SQL.
4. Abra `MenuConsole.java` e veja como cada opcao chama o DAO.
5. Altere uma coluna da tabela e tente ajustar o Java para acompanhar.

## Variaveis de ambiente opcionais

Se quiser mudar a conexao sem editar codigo:

```bash
DB_URL=jdbc:sqlserver://localhost:1433;databaseName=empresa_db;encrypt=true;trustServerCertificate=true
DB_USER=sa
DB_PASSWORD=LocalDev123!
```
