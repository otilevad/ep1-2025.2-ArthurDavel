package Entidades.Medico;

import java.util.Scanner;

import Entidades.Pessoa;
import Repositorios.*;

public class Medico extends Pessoa{
    private String crm;

    public Medico(){
        super();
        this.crm="";
    }

    public Medico(String nome, String crm){
        super(nome);
        this.crm=crm;
    }

    public String getCrm() {
        return this.crm;
    }

    public void setCrm(String crm) {
        this.crm=crm;
    }

    @Override
    public void cadastrar(AllRep rep, Scanner sc) throws Exception{
        int dado=0;
        String nome="";
        String crm="";
        whileTrue: while(true){
            switch(dado){
                case 0:
                    System.out.print("Digite o nome: ");
                    nome=sc.nextLine();
                    dado++;
                    break;
                case 1:
                    System.out.print("Digite o CRM: ");
                    crm=sc.nextLine();
                    dado++;
                    break;
                default:
                    break whileTrue;
            }
        }
        setNome(nome);
        setCrm(crm);
        rep.getMedicosR().adicionaMedico(this);
    }

    @Override
    public void imprimeDados(){
        System.out.println("Nome: "+getNome());
        System.out.println("CRM: "+getCrm());
    }
}
