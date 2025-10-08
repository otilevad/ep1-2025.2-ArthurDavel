package Listas;

import java.util.ArrayList;

import Entidades.Paciente.Paciente;
import Entidades.Paciente.PacienteEspecial;
import Entidades.PlanoSaude.*;
import Exceptions.CpfExistsException;
import Exceptions.NumberException;

public class PlanosLista{
    private ArrayList<PlanoSaude> planos;
    private ArrayList<PlanoEspecial> planosEsp;

    public PlanosLista(){
        this.planos= new ArrayList<PlanoSaude>();
        this.planosEsp= new ArrayList<PlanoEspecial>();
        addPlanosPadrao();
    }

    public PlanosLista(ArrayList<PlanoSaude> planos, ArrayList<PlanoEspecial> planosEsp){
        this.planos=planos;
        this.planosEsp=planosEsp;
    }

    public ArrayList<PlanoSaude> getPlanos(){
        return this.planos;
    }

    public void setPlanos(ArrayList<PlanoSaude> planos){
        this.planos=planos;
    }

    public ArrayList<PlanoEspecial> getPlanosEsp() {
        return this.planosEsp;
    }

    public void setPlanosEsp(ArrayList<PlanoEspecial> planosEsp) {
        this.planosEsp = planosEsp;
    }

    public void adicionaPlano(PlanoSaude plano){
        planos.add(plano);
    }

    public void adicionaPlanoEsp(PlanoEspecial planoEsp){
        planosEsp.add(planoEsp);
    }

    public void listarPlanos(){
        if(planos.size()>0){
            System.out.println("---Lista de Planos de Saúde---");
            for(int i=0; i<planos.size(); i++){
                PlanoSaude plano=planos.get(i);
                plano.imprimeDados();
                System.out.println("------------------------");
            }
            for(int i=0; i<planosEsp.size(); i++){
                PlanoEspecial planoEsp=planosEsp.get(i);
                planoEsp.imprimeDados();
                System.out.println("------------------------");
            }
        }
        else{
            System.out.println("Não há Planos de Saúde cadastrados");
        }
        System.out.println("Pressione Enter para retornar");
    }

    public void addPlanosPadrao(){
        getPlanos().add(new PlanoSaude("Mais Saúde",new ArrayList<Desconto>(),0));
        getPlanos().add(new PlanoSaude("Mais ou Menos Saúde",new ArrayList<Desconto>(),1));
        getPlanos().add(new PlanoSaude("Tudo Saúde",new ArrayList<Desconto>(),2));
        getPlanos().add(new PlanoSaude("Mil Planos",new ArrayList<Desconto>(),3));
        getPlanos().add(new PlanoSaude("SauDAVEL",new ArrayList<Desconto>(),4));
    }

    public boolean isPlanoEspecial(int id) throws Exception{

            for(PlanoSaude i : getPlanos()){
                if(id==i.getId()){
                    return false;
                }
            }

            for(PlanoEspecial i : getPlanosEsp()){
                if(id==i.getId()){
                    return true;
                }
            }
        throw new NumberException("id não encontrado.");
    }

    public PlanoEspecial buscaIdEsp(int id){
        for(PlanoEspecial i : getPlanosEsp()){
            if(id==i.getId()){
                return i;
            }
        }
        return null;
    }

    public PlanoSaude buscaId(int id){
        for(PlanoSaude i : getPlanos()){
            if(id==i.getId()){
                return i;
            }
        }
        return null;
    }
}