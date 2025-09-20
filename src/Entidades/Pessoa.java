package Entidades;

import Menu.*;
import java.util.InputMismatchException;
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
    public int inputInt(String str, Scanner sc){
        boolean inputErrado=false; 
        int input=0;
        System.out.print(str);
        while(true){
            try{
                input=sc.nextInt();
                sc.nextLine();
                if(inputErrado){
                    MenuSetup.limpaLinha(2);
                    System.out.println(str+input);
                }               
                break;
            }
            catch(InputMismatchException e){
                sc.next();
                if(inputErrado){
                    MenuSetup.limpaLinha();
                }
                MenuSetup.limpaLinha();
                System.out.println("Por favor, digite uma opção válida.");
                inputErrado=true;
            }
            System.out.print(str);
        }
        return input;
    }
}
