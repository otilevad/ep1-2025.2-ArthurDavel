package Entidades.Medico;

import java.util.ArrayList;

public class Especialidade {
    private String nome;
    private double mult;

    public Especialidade(){
        this.nome="";
        this.mult=0;
    }

    public Especialidade(String nome, double mult){
        this.nome=nome;
        this.mult=mult;
    }
    
    public void setNome(String nome){
        this.nome=nome;
    }

    public String getNome(){
        return this.nome;
    }

    public void setValor(double mult){
        this.mult=mult;
    }

    public double getValor(){
        return this.mult;
    }
}
