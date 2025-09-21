package Entidades.Paciente;

import java.util.ArrayList;

import Utilitarios.Calendario.*;

public class HistoricoPaciente extends Historico{
    private ArrayList<Internacao> internacoes;

    public HistoricoPaciente(){
        super();
        this.internacoes=new ArrayList<Internacao>();
    }

    public HistoricoPaciente(ArrayList<Consulta> consultas, ArrayList<Internacao> internacoes){
        super(consultas);
        this.internacoes=internacoes;
    }

    public void setInternacoes(ArrayList<Internacao> internacoes){
        this.internacoes=internacoes;
    }

    public ArrayList<Internacao> getInternacoes(){
        return this.internacoes;
    }
}
