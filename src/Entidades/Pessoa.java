package Entidades;

import Utilitarios.*;
import Menu.*;
import java.util.Scanner;

import Listas.*;

import java.util.ArrayList;

public abstract class Pessoa {
    private String nome;
    private ArrayList<Comando> comandos;
    
    public Pessoa(){
        this.nome="";
        this.comandos=new ArrayList<Comando>();
        this.comandos.add(new Comando("nome", "String", "Digite o nome: "));
    }

    public Pessoa(String nome, ArrayList<Comando> comandos){
        this.nome=Misc.formataStrProprio(nome);
        this.comandos=comandos;
        this.comandos.add(new Comando("nome", "String", "Digite o nome: "));
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome=Misc.formataStrProprio(nome);
    }

    public ArrayList<Comando> getComandos() {
        return this.comandos;
    }

    public void setComandos(ArrayList<Comando> comandos) {
        this.comandos = comandos;
    }

    abstract public void addComandos();
    public void setAtributosPessoa(){
        setNome(Comando.buscaPorDado("nome",getComandos()).getValorStr());
    }
    abstract public void cadastrar(AllLista lista, Scanner sc) throws Exception;
    abstract public void imprimeDados();
}
