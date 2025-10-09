package Calendario;

import Entidades.Medico.*;
import Entidades.Sala.SalaConsulta; 

public class OpcaoConsulta {
    private String str;
    private double valor;
    private Medico med;
    private SalaConsulta sala;

    public OpcaoConsulta(){
        this.str="";
        this.valor=0d;
        this.med=new Medico();
        this.sala=new SalaConsulta();
    }

    public OpcaoConsulta(String str, double valor, Medico med, SalaConsulta sala){
        this.str=str;
        this.valor=valor;
        this.med=med;
        this.sala=sala;
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

    public SalaConsulta getSala() {
        return this.sala;
    }

    public void setSala(SalaConsulta sala) {
        this.sala = sala;
    }
}
