package Entidades;

import java.util.Scanner;

public abstract class Pessoa {
    private String nome;
    
    public Pessoa(){
        this.nome="";
    }

    public Pessoa(String nome){
        this.nome=nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome=nome;
    }

    abstract public void cadastrar(Lista listas, Scanner sc);
    abstract public void imprimeDados();
}
