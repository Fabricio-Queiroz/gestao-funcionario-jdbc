IF DB_ID(N'empresa_db') IS NULL
BEGIN
    CREATE DATABASE empresa_db;
END;
GO

USE empresa_db;
GO

IF OBJECT_ID(N'dbo.funcionarios', N'U') IS NULL
BEGIN
    CREATE TABLE dbo.funcionarios (
        id INT IDENTITY(1,1) PRIMARY KEY,
        nome_completo VARCHAR(100) NOT NULL,
        cargo VARCHAR(50) NOT NULL,
        salario DECIMAL(10, 2) NOT NULL
    );
END;
GO
