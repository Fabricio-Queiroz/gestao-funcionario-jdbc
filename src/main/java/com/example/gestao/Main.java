package com.example.gestao;

import com.example.gestao.dao.FuncionarioDao;
import com.example.gestao.view.MenuConsole;

public class Main {
    public static void main(String[] args) {
        FuncionarioDao funcionarioDao = new FuncionarioDao();
        MenuConsole menu = new MenuConsole(funcionarioDao);

        menu.iniciar();
    }
}
