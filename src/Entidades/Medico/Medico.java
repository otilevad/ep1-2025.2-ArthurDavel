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

    public Medico(){
        super();
        this.crm="";
        addComandos();
    }

    public Medico(String nome, String crm, ArrayList<Comando> comandos){
        super(nome,comandos);
        this.crm=crm;
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

    @Override
    public void addComandos(){
        getComandos().add(new Comando("crm", "String", "Digite o CRM: "));
        getComandos().add(new Comando("especialidade", "String", "Digite a especialidade: "));
    }

    @Override
    public void cadastrar(AllRep rep, Scanner sc) throws Exception{
        setComandos(Menu.inputMenu(getComandos(), 0, 35, sc, rep));
        setAtributosPessoa();
        setCrm(Comando.buscaPorDado("crm",getComandos()).getValorStr());
        setEspec(Especialidade.buscaNomeEspec(Comando.buscaPorDado("especialidade",getComandos()).getValorStr(),rep));
        rep.getMedicosR().adicionaMedico(this);
    }

    @Override
    public void imprimeDados(){
        System.out.println("Nome: "+getNome());
        System.out.println("CRM: "+getCrm());
    }
}
