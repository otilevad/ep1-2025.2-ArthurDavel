package Utilitarios.Calendario;

import java.util.ArrayList;

public class Historico {
    private ArrayList<Consulta> consultas;
    private ArrayList<Internacao> internacoes;
    
    public Historico(){
        this.consultas=new ArrayList<Consulta>();
        this.internacoes=new ArrayList<Internacao>();
    }

    public Historico(ArrayList<Consulta> consultas, ArrayList<Internacao> internacoes){
        this.consultas=consultas;
        this.internacoes=internacoes;
    }

    public void setConsultas(ArrayList<Consulta> consultas){
        this.consultas=consultas;
    }

    public ArrayList<Consulta> getConsultas(){
        return this.consultas;
    }

    public void setInternacoes(ArrayList<Internacao> internacoes){
        this.internacoes=internacoes;
    }

    public ArrayList<Internacao> getInternacoes(){
        return this.internacoes;
    }
}
