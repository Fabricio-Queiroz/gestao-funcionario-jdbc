package com.example.gestao.model;

import java.math.BigDecimal;

public class Funcionario {
    private Integer id;
    private String nomeCompleto;
    private String cargo;
    private BigDecimal salario;

    public Funcionario(String nomeCompleto, String cargo, BigDecimal salario) {
        this(null, nomeCompleto, cargo, salario);
    }

    public Funcionario(Integer id, String nomeCompleto, String cargo, BigDecimal salario) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.cargo = cargo;
        this.salario = salario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }
}
