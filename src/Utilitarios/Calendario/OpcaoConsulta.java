package Utilitarios.Calendario;

import Entidades.Medico.*; 

public class OpcaoConsulta {
    private String str;
    private double valor;
    private Medico med;

    public OpcaoConsulta(){
        this.str="";
        this.valor=0d;
        this.med=new Medico();
    }

    public OpcaoConsulta(String str, double valor, Medico med){
        this.str=str;
        this.valor=valor;
        this.med=med;
    }

    public String getStr() {
        return this.str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public double getValor() {
        return this.valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Medico getMed() {
        return this.med;
    }

    public void setMed(Medico med) {
        this.med = med;
    }

}
