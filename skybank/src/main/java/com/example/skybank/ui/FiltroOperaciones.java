package com.example.skybank.ui;

import java.util.Date;

public class FiltroOperaciones {
    private int tipo;

    private Double Max;

    private Double Min;

    private Date desde;

    private Date Hasta;

    public FiltroOperaciones(int tipo, Double max, Double min, Date desde, Date hasta) {
        this.tipo = tipo;
        Max = max;
        Min = min;
        this.desde = desde;
        Hasta = hasta;
    }

    public FiltroOperaciones() {
        tipo = 0;
    }

    public int getTipo(){
        return this.tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Double getMax() {
        return Max;
    }

    public void setMax(Double max) {
        Max = max;
    }

    public Double getMin() {
        return Min;
    }

    public void setMin(Double min) {
        Min = min;
    }

    public Date getDesde() {
        return desde;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public Date getHasta() {
        return Hasta;
    }

    public void setHasta(Date hasta) {
        Hasta = hasta;
    }
}
