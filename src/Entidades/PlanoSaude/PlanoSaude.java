package Entidades.PlanoSaude;

import java.util.ArrayList;

public class PlanoSaude {
    private String nome;
    private ArrayList<Desconto> descontos;

    public PlanoSaude(){
        this.nome="";
        this.descontos=new ArrayList<Desconto>();
    }

    public PlanoSaude(String nome, ArrayList<Desconto> descontos){
        this.nome=nome;
        this.descontos=descontos;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }
    
    public void setDescontos(ArrayList<Desconto> descontos) {
        this.descontos = descontos;
    }

    public ArrayList<Desconto> getDescontos() {
        return this.descontos;
    }

    public void imprimeDados(){
        System.out.println("Nome: "+getNome());
    }
}
