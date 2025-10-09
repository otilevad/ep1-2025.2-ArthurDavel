package Entidades.Medico;

import java.util.ArrayList;

import Calendario.Periodo;
import Entidades.Sala.*;
import Listas.*;


public class Especialidade {
    private String nome;
    private double mult;
    private ArrayList<SalaConsulta> salas;
    private int id;

    public Especialidade(){
        this.nome="";
        this.mult=0;
        this.salas=new ArrayList<SalaConsulta>();
        this.id=-1;
    }

    public Especialidade(String nome, double mult, ArrayList<SalaConsulta> salas, int qtdSalas,int id){
        this.nome=nome;
        this.mult=mult;
        this.salas=salas;
        this.id=id;
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
    
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQtdSalas(){
        return getSalas().size();
    }
    
    public ArrayList<SalaConsulta> addSalasConsulta(int qtd){
        ArrayList<SalaConsulta> arrayPrev=getSalas();
        for(int i=0;i<qtd;i++){
            arrayPrev.add(new SalaConsulta(getSalas().size()+1,new ArrayList<Periodo>(),this));
        }
        return arrayPrev;
    }

    public static Especialidade buscaValorEspec(int num, AllLista lista){
        return lista.getEspecialidadesL().getEspecialidades().get(num);
    }

    public SalaConsulta salaByNum(int num){
        for(SalaConsulta sala : salas){
            if(sala.getNum()==num){
                return sala;
            }
        }
        return null;
    }
}
