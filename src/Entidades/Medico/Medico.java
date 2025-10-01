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

    public Medico(){
        super();
        this.crm="";
        this.custoConsulta=0d;
        addComandos();
    }

    public Medico(String nome, String crm, ArrayList<Comando> comandos,double custoConsulta){
        super(nome,comandos);
        this.crm=crm;
        this.custoConsulta=custoConsulta;
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

    @Override
    public void addComandos(){
        getComandos().add(new Comando("crm", "String", "Digite o CRM: "));
        getComandos().add(new Comando("especialidade", "String", "Digite a especialidade: "));
        getComandos().add(new Comando("custo", "String", "Digite o custo padrão da consulta: "));
    }

    @Override
    public void cadastrar(AllRep rep, Scanner sc) throws Exception{
        setComandos(Menu.inputMenu(getComandos(), 0, 35, sc, rep));
        setAtributosPessoa();
        setCrm(Comando.buscaPorDado("crm",getComandos()).getValorStr());
        setEspec(Especialidade.buscaValorEspec(Comando.buscaPorDado("especialidade",getComandos()).getValorInt(),rep));
        setCustoConsulta(Double.parseDouble(Comando.buscaPorDado("custo",getComandos()).getValorStr()));
        rep.getMedicosR().adicionaMedico(this);
    }

    @Override
    public void imprimeDados(){
        System.out.println("Nome: "+getNome());
        System.out.println("CRM: "+getCrm());
        System.out.println("Especialidade: "+getEspec().getNome());
        System.out.println("Custo-padrão da consulta: R$ "+String.format("%.2f",getCustoConsulta()));
    }
}
