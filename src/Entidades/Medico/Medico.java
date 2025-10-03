package Entidades.Medico;

import java.util.ArrayList;
import java.util.Scanner;

import Entidades.Pessoa;
import Menu.Comando;
import Menu.Menu;
import Repositorios.*;

public class Medico extends Pessoa{
    private String crm;
    private Especialidade espec;
    private double custoConsulta;
    private int tempoMedio; //em minutos

    public Medico(){
        super();
        this.crm="";
        this.custoConsulta=0d;
        this.tempoMedio=0;
        addComandos();
    }

    public Medico(String nome, String crm, ArrayList<Comando> comandos,double custoConsulta,int tempoMedio){
        super(nome,comandos);
        this.crm=crm;
        this.custoConsulta=custoConsulta;
        this.tempoMedio=tempoMedio;
    }

    public String getCrm() {
        return this.crm;
    }

    public void setCrm(String crm) {
        this.crm=crm;
    }

	public Especialidade getEspec() {
		return this.espec;
	}

	public void setEspec(Especialidade espec) {
		this.espec = espec;
	}

    public double getCustoConsulta() {
        return this.custoConsulta;
    }

    public void setCustoConsulta(double custoConsulta) {
        this.custoConsulta = custoConsulta;
    }

    public int getTempoMedio() {
        return this.tempoMedio;
    }

    public void setTempoMedio(int tempoMedio) {
        this.tempoMedio = tempoMedio;
    }

    @Override
    public void addComandos(){
        getComandos().add(new Comando("crm", "String", "Digite o CRM: "));
        getComandos().add(new Comando("especialidade", "String", "Digite a especialidade: "));
        getComandos().add(new Comando("custo", "String", "Digite o custo padrão da consulta: "));
        getComandos().add(new Comando("tempo médio", "int", "Digite o tempo médio da consulta (min): "));
    }

    @Override
    public void cadastrar(AllRep rep, Scanner sc) throws Exception{
        setComandos(Menu.inputMenu(getComandos(), false, 35, sc, rep));
        setAtributosPessoa();
        setCrm(Comando.buscaPorDado("crm",getComandos()).getValorStr());
        setEspec(Especialidade.buscaValorEspec(Comando.buscaPorDado("especialidade",getComandos()).getValorInt(),rep));
        setCustoConsulta(Double.parseDouble(Comando.buscaPorDado("custo",getComandos()).getValorStr()));
        setTempoMedio(Comando.buscaPorDado("tempo médio",getComandos()).getValorInt());
        rep.getMedicosR().adicionaMedico(this);
    }

    @Override
    public void imprimeDados(){
        System.out.println("Nome: "+getNome());
        System.out.println("CRM: "+getCrm());
        System.out.println("Especialidade: "+getEspec().getNome());
        System.out.println("Custo padrão da consulta: R$ "+String.format("%.2f",getCustoConsulta()));
        System.out.println("Tempo médio da consulta: "+getTempoMedio()+" minutos");
    }
}
