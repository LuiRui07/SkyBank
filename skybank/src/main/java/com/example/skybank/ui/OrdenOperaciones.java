/*
@author: Luis Ruiz Nuñez
*/
package com.example.skybank.ui;

public class OrdenOperaciones {

    private int idorden;

    private String orden;

    public OrdenOperaciones(){
        this.idorden = 0;
        this.orden = "";
    }

    public int getIdorden() {
        return idorden;
    }

    public void setIdorden(int idorden) {
        this.idorden = idorden;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public OrdenOperaciones(int idorden, String orden) {
        this.idorden = idorden;
        this.orden = orden;
    }
}
