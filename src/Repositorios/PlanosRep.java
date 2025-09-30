package Repositorios;

import java.util.ArrayList;

import Entidades.PlanoSaude.*;

public class PlanosRep{
    private ArrayList<PlanoSaude> planos;
    
    public PlanosRep(){
        this.planos = new ArrayList<PlanoSaude>();
    }

    public PlanosRep(ArrayList<PlanoSaude> planos){
        this.planos = planos;
    }

    public ArrayList<PlanoSaude> getPlanos(){
        return this.planos;
    }

    public void setPlanos(ArrayList<PlanoSaude> planos){
        this.planos=planos;
    }

    public void adicionaPlano(PlanoSaude plano){
        planos.add(plano);
    }

    public void listarPlanos(){
        if(planos.size()>0){
            System.out.println("---Lista de Planos de Saúde---");
            for(int i=0; i<planos.size(); i++){
                PlanoSaude plano=planos.get(i);
                plano.imprimeDados();
                System.out.println("------------------------");
            }
        }
        else{
            System.out.println("Não há Planos de Saúde cadastrados");
        }
        System.out.println("Pressione Enter para retornar");
    }
}