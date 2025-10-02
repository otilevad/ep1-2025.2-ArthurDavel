package Entidades.PlanoSaude;

import java.util.ArrayList;
import java.util.Scanner;

import Entidades.Medico.Especialidade;
import Menu.Comando;
import Menu.Menu;
import Repositorios.*;

public class PlanoSaude {
    private String nome;
    private ArrayList<Desconto> descontos;
    private ArrayList<Comando> comandos;

    public PlanoSaude(){
        this.nome="";
        this.descontos=new ArrayList<Desconto>();
        this.comandos=new ArrayList<Comando>();
        addComandos();
    }

    public PlanoSaude(String nome, ArrayList<Desconto> descontos){
        this.nome=nome;
        this.descontos=descontos;
        this.comandos=new ArrayList<Comando>();
        addComandos();
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

    public ArrayList<Comando> getComandos() {
        return this.comandos;
    }

    public void setComandos(ArrayList<Comando> comandos) {
        this.comandos = comandos;
    }

    public void imprimeDados(){
        System.out.println("Nome: "+getNome());
        System.out.println("Descontos: ");
        for(Desconto i : getDescontos()){
            System.out.println(" "+i.getEspec().getNome()+" - "+i.getPorcentagem()+"%;");
        }
    }

    public void addComandos(){
        getComandos().add(new Comando("nome", "String", "Digite o nome: "));
        getComandos().add(new Comando("descontos", "String", "Digite os descontos: "));
    }

    public void cadastrar(AllRep rep, Scanner sc) throws Exception{
        setComandos(Menu.inputMenu(getComandos(), false, 35, sc, rep));
        setNome(Comando.buscaPorDado("nome",getComandos()).getValorStr());
        setDescontos(stringSetDescontos(Comando.buscaPorDado("descontos",getComandos()).getValorStr(),rep));
        rep.getPlanosR().adicionaPlano(this);
    }

    public static ArrayList<Desconto> stringSetDescontos(String str, AllRep rep){
        ArrayList<Desconto> descs=new ArrayList<Desconto>();
        String strNova=str.replaceAll("\\s", "").replaceAll(";", "");
        String[] strs=strNova.split(",");
        for(String descontoStr : strs){
            String[] valores=descontoStr.split("/");
            descs.add(new Desconto(Double.parseDouble(valores[0]),Especialidade.buscaValorEspec(Integer.parseInt(valores[1]), rep)));
        }
        return descs;
    }

    public static PlanoSaude buscaValorPlano(int num, AllRep rep){
        return rep.getPlanosR().getPlanos().get(num);
    }
}
