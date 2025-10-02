package Entidades.Paciente;

import java.util.Scanner;
import java.util.ArrayList;

import Entidades.Pessoa;
import Entidades.Medico.Especialidade;
import Entidades.PlanoSaude.*;
import Repositorios.*;
import Menu.*;
import Utilitarios.Misc;
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
        getComandos().add(new Comando("plano de saúde", "int", "Digite o plano saúde: "));
    }

    @Override
    public void cadastrar(AllRep rep, Scanner sc) throws Exception{
        setComandos(Menu.inputMenu(getComandos(), false, 35, sc, rep));
        if(Comando.buscaPorDado("plano de saúde",getComandos()).getValorInt()>=0){
            PacienteEspecial pacienteEsp=new PacienteEspecial();
            pacienteEsp.setComandos(getComandos());
            pacienteEsp.setAtributosPaciente();
            if(Comando.buscaPorDado("plano de saúde",getComandos()).getValorInt()>=rep.getPlanosR().getPlanos().size()){
                pacienteEsp.setPlanoEsp(PlanoEspecial.buscaValorPlano(Comando.buscaPorDado("plano de saúde",getComandos()).getValorInt()-rep.getPlanosR().getPlanos().size(),rep));
                pacienteEsp.setIsEspecial(true);
                pacienteEsp.setPlano(null);
            }
            else{
                pacienteEsp.setPlano(PlanoSaude.buscaValorPlano(Comando.buscaPorDado("plano de saúde",getComandos()).getValorInt(),rep));
                pacienteEsp.setIsEspecial(false);
                pacienteEsp.setPlanoEsp(null);
            }
            rep.getPacientesR().adicionaPacienteEspecial(pacienteEsp);
        }
        else{
            setAtributosPaciente();
            rep.getPacientesR().adicionaPaciente(this);
        }
    }

    public void setAtributosPaciente(){
        setAtributosPessoa();
        setCpf(Comando.buscaPorDado("cpf",getComandos()).getValorStr());
        setIdade(Comando.buscaPorDado("idade",getComandos()).getValorInt());
    }

    @Override
    public void imprimeDados(){
        System.out.println("Nome: "+getNome());
        System.out.println("CPF: "+getCpf());
        System.out.println("Idade: "+getIdade());
    }
}
