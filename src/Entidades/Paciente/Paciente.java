package Entidades.Paciente;

import java.util.Scanner;

import Entidades.Pessoa;
import Repositorios.*;
import Utilitarios.Calendario.Historico;

public class Paciente extends Pessoa{
    private String cpf;
    private int idade;
    private boolean idoso;
    private HistoricoPaciente historicoPaciente;

    public Paciente(){
        super();
        this.cpf="";
        this.idade=0;
        this.idoso=false;
        this.historicoPaciente=new HistoricoPaciente();
    }

    public Paciente(String nome, String cpf, int idade, HistoricoPaciente historicoPaciente){
        super(nome);
        this.cpf=cpf;
        this.idade=idade;
        this.idoso=(this.idade>=60);
        this.historicoPaciente=historicoPaciente;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf=cpf;
    }

    public int getIdade() {
        return this.idade;
    }

    public void setIdade(int idade) {
        this.idade=idade;
        this.idoso=(this.idade>=60);
    }

    public boolean getIdoso(){
        return this.idoso;
    }

    public void setHistoricoPaciente(HistoricoPaciente historicoPaciente){
        this.historicoPaciente=historicoPaciente;
    }

    public Historico getHistoricoPaciente(){
        return this.historicoPaciente;
    }

    @Override
    public void cadastrar(AllRep rep, Scanner sc){
        int dado=0;
        String nome="";
        String cpf="";
        int idade=0;
        whileTrue: while(true){
            switch(dado){
                case 0:
                    System.out.print("Digite o nome: ");
                    nome=sc.nextLine();
                    break;
                case 1:
                    System.out.print("Digite o CPF: ");
                    cpf=sc.nextLine();
                    break;
                case 2:
                    idade=inputInt("Digite a idade: ", sc);
                    break;
                default:
                    break whileTrue;
            }
            dado++;
        }
        setNome(nome);
        setCpf(cpf);
        setIdade(idade);
        rep.getPacientesR().adicionaPaciente(this);
    }

    @Override
    public void imprimeDados(){
        System.out.println("Nome: "+getNome());
        System.out.println("CPF: "+getCpf());
        System.out.println("Idade: "+getIdade());
    }
}
