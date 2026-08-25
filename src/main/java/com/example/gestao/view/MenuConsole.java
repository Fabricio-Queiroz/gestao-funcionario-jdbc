package com.example.gestao.view;

import com.example.gestao.dao.FuncionarioDao;
import com.example.gestao.model.Funcionario;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MenuConsole {
    private final FuncionarioDao funcionarioDao;
    private final Scanner scanner = new Scanner(System.in);

    public MenuConsole(FuncionarioDao funcionarioDao) {
        this.funcionarioDao = funcionarioDao;
    }

    public void iniciar() {
        int opcao;

        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opcao: ");

            try {
                switch (opcao) {
                    case 1 -> cadastrar();
                    case 2 -> listar();
                    case 3 -> atualizar();
                    case 4 -> remover();
                    case 0 -> System.out.println("Encerrando...");
                    default -> System.out.println("Opcao invalida.");
                }
            } catch (SQLException exception) {
                System.out.println("Erro ao acessar o banco: " + exception.getMessage());
            }
        } while (opcao != 0);
    }

    private void exibirMenu() {
        System.out.println();
        System.out.println("=== Gestao de Funcionarios ===");
        System.out.println("1 - Cadastrar funcionario");
        System.out.println("2 - Listar funcionarios");
        System.out.println("3 - Atualizar funcionario");
        System.out.println("4 - Remover funcionario");
        System.out.println("0 - Sair");
    }

    private void cadastrar() throws SQLException {
        String nome = lerTextoObrigatorio("Nome completo: ");
        String cargo = lerTextoObrigatorio("Cargo: ");
        BigDecimal salario = lerDecimal("Salario: ");

        Funcionario funcionario = new Funcionario(nome, cargo, salario);
        funcionarioDao.cadastrar(funcionario);

        System.out.println("Funcionario cadastrado com ID " + funcionario.getId() + ".");
    }

    private void listar() throws SQLException {
        List<Funcionario> funcionarios = funcionarioDao.listar();

        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionario cadastrado.");
            return;
        }

        System.out.printf("%-5s %-30s %-20s %12s%n", "ID", "Nome", "Cargo", "Salario");
        for (Funcionario funcionario : funcionarios) {
            System.out.printf(
                    "%-5d %-30s %-20s %12.2f%n",
                    funcionario.getId(),
                    funcionario.getNomeCompleto(),
                    funcionario.getCargo(),
                    funcionario.getSalario()
            );
        }
    }

    private void atualizar() throws SQLException {
        int id = lerInteiro("ID do funcionario: ");
        String nome = lerTextoObrigatorio("Novo nome completo: ");
        String cargo = lerTextoObrigatorio("Novo cargo: ");
        BigDecimal salario = lerDecimal("Novo salario: ");

        Funcionario funcionario = new Funcionario(id, nome, cargo, salario);
        boolean atualizado = funcionarioDao.atualizar(funcionario);

        if (atualizado) {
            System.out.println("Funcionario atualizado.");
        } else {
            System.out.println("Funcionario nao encontrado.");
        }
    }

    private void remover() throws SQLException {
        int id = lerInteiro("ID do funcionario: ");
        boolean removido = funcionarioDao.remover(id);

        if (removido) {
            System.out.println("Funcionario removido.");
        } else {
            System.out.println("Funcionario nao encontrado.");
        }
    }

    private String lerTextoObrigatorio(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String texto = scanner.nextLine().trim();

            if (!texto.isBlank()) {
                return texto;
            }

            System.out.println("Digite um valor.");
        }
    }

    private int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String texto = scanner.nextLine().trim();

            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException exception) {
                System.out.println("Digite um numero inteiro.");
            }
        }
    }

    private BigDecimal lerDecimal(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String texto = scanner.nextLine().trim().replace(',', '.');

            try {
                BigDecimal valor = new BigDecimal(texto);
                if (valor.signum() >= 0) {
                    return valor;
                }
            } catch (NumberFormatException exception) {
                // A mensagem abaixo ja explica o erro para quem esta usando o menu.
            }

            System.out.println("Digite um valor decimal positivo.");
        }
    }
}
