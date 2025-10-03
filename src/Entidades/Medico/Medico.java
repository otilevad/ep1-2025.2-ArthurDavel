package Entidades.Medico;

import java.util.ArrayList;
import java.util.Scanner;

import Entidades.Pessoa;
import Menu.Comando;
import Menu.Menu;
import Repositorios.*;
import Utilitarios.Misc;

public class Medico extends Pessoa{
    private String crm;
    private Especialidade espec;
    private double custoConsulta;
    private int tempoMedio; //em minutos
    private Agenda agnd;

    public Medico(){
        super();
        this.crm="";
        this.custoConsulta=0d;
        this.tempoMedio=0;
        this.agnd=new Agenda();
        addComandos();
    }

    public Medico(String nome, String crm, ArrayList<Comando> comandos,double custoConsulta,int tempoMedio,Agenda agnd){
        super(nome,comandos);
        this.crm=crm;
        this.custoConsulta=custoConsulta;
        this.tempoMedio=tempoMedio;
        this.agnd=agnd;
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
        getAgnd().setDuracao(tempoMedio);
    }

    public Agenda getAgnd() {
        return this.agnd;
    }

    public void setAgnd(Agenda agnd) {
        this.agnd = agnd;
    }

    @Override
    public void addComandos(){
        getComandos().add(new Comando("crm", "String", "Digite o CRM: "));
        getComandos().add(new Comando("especialidade", "String", "Digite a especialidade: "));
        getComandos().add(new Comando("custo", "String", "Digite o custo padrão da consulta: "));
        getComandos().add(new Comando("tempo médio", "int", "Digite o tempo médio da consulta (min): "));
    }

    public ArrayList<Comando> inputAgenda(Scanner sc, AllRep rep) throws Exception{
        ArrayList<Comando> agenda=new ArrayList<Comando>();
        agenda.add(new Comando("folga", "String", "Digite os dias que não trabalha separados por \"/\": "));
        agenda.add(new Comando("horario", "String", "Horário (hh:mm) de início e fim do expediente separados por \"/\": "));
        agenda.add(new Comando("intervalo", "String", "Início e fim do intervalo separados por \"/\" e \",\" para adicionar: "));
        return Menu.inputMenu(agenda, false, 35, sc, rep);
    }

    @Override
    public void cadastrar(AllRep rep, Scanner sc) throws Exception{
        setComandos(Menu.inputMenu(getComandos(), false, 35, sc, rep));
        setAtributosPessoa();
        setCrm(Comando.buscaPorDado("crm",getComandos()).getValorStr());
        setEspec(Especialidade.buscaValorEspec(Comando.buscaPorDado("especialidade",getComandos()).getValorInt(),rep));
        setCustoConsulta(Double.parseDouble(Comando.buscaPorDado("custo",getComandos()).getValorStr()));
        setTempoMedio(Comando.buscaPorDado("tempo médio",getComandos()).getValorInt());
        Misc.limpaTela();
        ArrayList<Comando> agenda=inputAgenda(sc, rep);
        getAgnd().agendaStr(getAgnd().folgaStr(Comando.buscaPorDado("folga",agenda).getValorStr()),
                            getAgnd().horarioStr(Comando.buscaPorDado("horario",agenda).getValorStr()),
                            getAgnd().horarioStr(Comando.buscaPorDado("intervalo",agenda).getValorStr()));
        rep.getMedicosR().adicionaMedico(this);
    }

    @Override
    public void imprimeDados(){
        System.out.println("Nome: "+getNome());
        System.out.println("CRM: "+getCrm());
        System.out.println("Especialidade: "+getEspec().getNome());
        System.out.println("Custo padrão da consulta: R$ "+String.format("%.2f",getCustoConsulta()));
        System.out.println("Tempo médio da consulta: "+getTempoMedio()+" minutos");
        getAgnd().imprimeAgenda();
    }
}
