package Entidades.Paciente;

import java.util.Scanner;
import java.util.ArrayList;

import Entidades.Pessoa;
import Repositorios.*;
import Menu.*;
import Utilitarios.Calendario.Historico;

public class Paciente extends Pessoa{
    private String cpf;
    private int idade;
    private boolean idoso;
    private HistoricoPaciente historicoPaciente;
    //private ArrayList<Comando> comandos;

    public Paciente(){
        super();
        this.cpf="";
        this.idade=0;
        this.idoso=false;
        this.historicoPaciente=new HistoricoPaciente();
        addComandos();
    }

    public Paciente(String nome, String cpf, int idade, HistoricoPaciente historicoPaciente, ArrayList<Comando> comandos){
        super(nome,comandos);
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
    public void addComandos(){
        getComandos().add(new Comando("cpf", "String", "Digite o CPF: "));
        getComandos().add(new Comando("idade", "int", "Digite a idade: "));
    }

    @Override
    public void cadastrar(AllRep rep, Scanner sc) throws Exception{
        setComandos(Menu.inputMenu(getComandos(), 0, 35, sc));
        setNome(Comando.buscaPorDado("nome",getComandos()).getValorStr());
        setCpf(Comando.buscaPorDado("cpf",getComandos()).getValorStr());
        setIdade(Comando.buscaPorDado("idade",getComandos()).getValorInt());
        rep.getPacientesR().adicionaPaciente(this);
    }

    @Override
    public void imprimeDados(){
        System.out.println("Nome: "+getNome());
        System.out.println("CPF: "+getCpf());
        System.out.println("Idade: "+getIdade());
    }
}
