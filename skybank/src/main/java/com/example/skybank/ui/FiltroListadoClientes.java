package com.example.skybank.ui;

public class FiltroListadoClientes {
    private String texto;

    private boolean clientes;

    private boolean empresas;



    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean isClientes() {
        return clientes;
    }

    public void setClientes(boolean clientes) {
        this.clientes = clientes;
    }

    public boolean isEmpresas() {
        return empresas;
    }

    public void setEmpresas(boolean empresas) {
        this.empresas = empresas;
    }

    public FiltroListadoClientes(){

    }
}
