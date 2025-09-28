package Entidades;

import Utilitarios.*;
import Repositorios.*;
import Menu.*;
import java.util.InputMismatchException;
import java.util.Scanner;
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
        this.nome=nome;
        this.comandos=comandos;
        this.comandos.add(new Comando("nome", "String", "Digite o nome: "));
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome=nome;
    }

    public ArrayList<Comando> getComandos() {
        return this.comandos;
    }

    public void setComandos(ArrayList<Comando> comandos) {
        this.comandos = comandos;
    }

    abstract public void addComandos();
    abstract public void cadastrar(AllRep rep, Scanner sc) throws Exception;
    abstract public void imprimeDados();
    public int inputInt(String str, Scanner sc){
        boolean inputErrado=false; 
        int input=0;
        System.out.print(str);
        while(true){
            try{
                input=sc.nextInt();
                sc.nextLine();
                if(inputErrado){
                    Misc.limpaLinha(2);
                    System.out.println(str+input);
                }               
                break;
            }
            catch(InputMismatchException e){
                sc.next();
                if(inputErrado){
                    Misc.limpaLinha();
                }
                Misc.limpaLinha();
                System.out.println("Por favor, digite uma opção válida.");
                inputErrado=true;
            }
            System.out.print(str);
        }
        return input;
    }
}
