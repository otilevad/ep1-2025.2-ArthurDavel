package Entidades.Paciente;

import java.util.ArrayList;

import Entidades.PlanoSaude.*;
import Utilitarios.Calendario.*;
import Menu.Comando;

public class PacienteEspecial extends Paciente{
    private PlanoSaude plano;
    private PlanoEspecial planoEsp;
    private boolean isEspecial;

    public PacienteEspecial(){
        super();
        this.plano=new PlanoSaude();
        this.planoEsp=new PlanoEspecial();
        this.isEspecial=false;
    }

    public PacienteEspecial(String nome, String cpf, int idade, Historico hist, ArrayList<Comando> comandos, PlanoSaude plano, PlanoEspecial planoEsp, boolean isEspecial){
        super(nome, cpf, idade, hist, comandos);
        this.plano=plano;
        this.planoEsp=planoEsp;
        this.isEspecial=isEspecial;
    }

    public PlanoSaude getPlano() {
        return this.plano;
    }

    public void setPlano(PlanoSaude plano) {
        this.plano = plano;
    }

    public PlanoEspecial getPlanoEsp() {
        return this.planoEsp;
    }

    public void setPlanoEsp(PlanoEspecial planoEsp) {
        this.planoEsp = planoEsp;
    }

    public boolean getIsEspecial() {
        return this.isEspecial;
    }

    public void setIsEspecial(boolean isEspecial) {
        this.isEspecial = isEspecial;
    }

    @Override
    public void imprimeDados(){
        System.out.println("Nome: "+getNome());
        System.out.println("CPF: "+getCpf());
        System.out.println("Idade: "+getIdade());
        System.out.println("Plano de saúde: "+(getIsEspecial()==true ? getPlanoEsp().getNome() : getPlano().getNome()));
    }
}
