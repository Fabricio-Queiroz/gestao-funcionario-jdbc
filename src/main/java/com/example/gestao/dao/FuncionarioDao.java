package com.example.gestao.dao;

import com.example.gestao.db.ConnectionFactory;
import com.example.gestao.model.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDao {
    public void cadastrar(Funcionario funcionario) throws SQLException {
        String sql = """
                INSERT INTO funcionarios (nome_completo, cargo, salario)
                VALUES (?, ?, ?)
                """;

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, funcionario.getNomeCompleto());
            statement.setString(2, funcionario.getCargo());
            statement.setBigDecimal(3, funcionario.getSalario());
            statement.executeUpdate();

            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    funcionario.setId(resultSet.getInt(1));
                }
            }
        }
    }

    public List<Funcionario> listar() throws SQLException {
        String sql = """
                SELECT id, nome_completo, cargo, salario
                FROM funcionarios
                ORDER BY id
                """;
        List<Funcionario> funcionarios = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                funcionarios.add(mapearFuncionario(resultSet));
            }
        }

        return funcionarios;
    }

    public boolean atualizar(Funcionario funcionario) throws SQLException {
        String sql = """
                UPDATE funcionarios
                SET nome_completo = ?, cargo = ?, salario = ?
                WHERE id = ?
                """;

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, funcionario.getNomeCompleto());
            statement.setString(2, funcionario.getCargo());
            statement.setBigDecimal(3, funcionario.getSalario());
            statement.setInt(4, funcionario.getId());

            return statement.executeUpdate() > 0;
        }
    }

    public boolean remover(int id) throws SQLException {
        String sql = "DELETE FROM funcionarios WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        }
    }

    private Funcionario mapearFuncionario(ResultSet resultSet) throws SQLException {
        return new Funcionario(
                resultSet.getInt("id"),
                resultSet.getString("nome_completo"),
                resultSet.getString("cargo"),
                resultSet.getBigDecimal("salario")
        );
    }
}
