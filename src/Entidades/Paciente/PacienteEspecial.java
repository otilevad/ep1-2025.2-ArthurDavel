package Entidades.Paciente;

import java.util.ArrayList;

import Entidades.PlanoSaude.*;
import Menu.Comando;

public class PacienteEspecial extends Paciente{
    private PlanoSaude plano;

    public PacienteEspecial(){
        super();
        this.plano=new PlanoSaude();
    }

    public PacienteEspecial(String nome, String cpf, int idade, HistoricoPaciente historicoPaciente, ArrayList<Comando> comandos, PlanoSaude plano){
        super(nome, cpf, idade, historicoPaciente, comandos);
        this.plano=plano;
    }

    public PlanoSaude getPlano() {
        return this.plano;
    }

    public void setPlano(PlanoSaude plano) {
        this.plano = plano;
    }

    @Override
    public void imprimeDados(){
        System.out.println("Nome: "+getNome());
        System.out.println("CPF: "+getCpf());
        System.out.println("Idade: "+getIdade());
        System.out.println("Plano de saúde: "+getPlano().getNome());
    }
}
