/*
    @autor: José Luis López Ruiz
 */

package com.example.skybank.ui;

public class FiltroSociosAutorizados {
    private String texto;

    private boolean socios;

    private boolean autorizados;



    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean isSocios() {
        return socios;
    }

    public void setSocios(boolean socios) {
        this.socios = socios;
    }

    public boolean isAutorizados() {
        return autorizados;
    }

    public void setAutorizados(boolean autorizados) {
        this.autorizados = autorizados;
    }

    public FiltroSociosAutorizados(){

    }

}
