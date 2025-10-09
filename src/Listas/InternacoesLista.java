package Listas;

import java.util.ArrayList;

import Entidades.Sala.SalaInternacao;
import Utilitarios.Calendario.Calendario;
import Utilitarios.Calendario.Consulta;
import Utilitarios.Calendario.Internacao;

public class InternacoesLista {
    private ArrayList<Internacao> internacoes;
    private ArrayList<SalaInternacao> salas;

    public InternacoesLista(){
        this.salas=new ArrayList<SalaInternacao>();
        this.internacoes=new ArrayList<Internacao>();
        addSalas(10);
    }

    public InternacoesLista(ArrayList<SalaInternacao> salas,ArrayList<Internacao> internacoes){
        this.internacoes=internacoes;
        this.salas=salas;
    }

    public ArrayList<SalaInternacao> getSalas() {
        return this.salas;
    }

    public void setSalas(ArrayList<SalaInternacao> salas) {
        this.salas = salas;
    }

    public ArrayList<Internacao> getInternacoes() {
        return this.internacoes;
    }

    public void setInternacoes(ArrayList<Internacao> internacoes) {
        this.internacoes = internacoes;
    }


    public void addSalas(int num){
        for(int i=1;i<=num;i++){
            SalaInternacao sala=new SalaInternacao();
            sala.setNum(i);
            getSalas().add(sala);
        }
    }

    public SalaInternacao buscaNum(int num) throws Exception{
        for(SalaInternacao i : getSalas()){
            if(i.getNum()==num){return i;}
        }
        throw new Exception("Este quarto não existe.");
    }

    public void listarInternacoes(Calendario cal){
        if(internacoes.size()>0){
            System.out.println("---Lista de internações---");
            for(Internacao i : internacoes){
                i.imprimeDados(cal);
                System.out.println("------------------------");
            }
        }
        else{
            System.out.println("Não há internações cadastradas");
        }
        System.out.println("Pressione Enter para retornar");
    }
}
