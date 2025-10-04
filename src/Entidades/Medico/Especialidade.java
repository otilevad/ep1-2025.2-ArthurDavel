package Entidades.Medico;

import java.util.ArrayList;

import Entidades.Sala.*;
import Repositorios.*;
import Utilitarios.Misc;
import Utilitarios.Calendario.Periodo;


public class Especialidade {
    private String nome;
    private double mult;
    private ArrayList<SalaConsulta> salas;

    public Especialidade(){
        this.nome="";
        this.mult=0;
        this.salas=new ArrayList<SalaConsulta>();
    }

    public Especialidade(String nome, double mult, ArrayList<SalaConsulta> salas, int qtdSalas){
        this.nome=nome;
        this.mult=mult;
        this.salas=salas;
        addSalasConsulta(qtdSalas);
    }
    
    public void setNome(String nome){
        this.nome=nome;
    }

    public String getNome(){
        return this.nome;
    }

    public void setMult(double mult){
        this.mult=mult;
    }

    public double getMult(){
        return this.mult;
    }

    public ArrayList<SalaConsulta> getSalas() {
        return this.salas;
    }

    public void setSalas(ArrayList<SalaConsulta> salas) {
        this.salas=salas;
    }

    public ArrayList<SalaConsulta> addSalasConsulta(int qtd){
        ArrayList<SalaConsulta> arrayPrev=getSalas();
        for(int i=0;i<qtd;i++){
            arrayPrev.add(new SalaConsulta(getSalas().size()+1,new ArrayList<Periodo>(),this));
        }
        return arrayPrev;
    }

    public static Especialidade buscaValorEspec(int num, AllRep rep){
        return rep.getEspecialidadesR().getEspecialidades().get(num);
    }
}
