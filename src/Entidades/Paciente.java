package Entidades;

import java.util.Scanner;

public class Paciente extends Pessoa{
    private String cpf;
    private int idade;

    public Paciente(){
        super();
        this.cpf="";
        this.idade=0;
    }

    public Paciente(String nome, String cpf, int idade){
        super(nome);
        this.cpf=cpf;
        this.idade=idade;
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
    }

    @Override
    public void cadastrar(Lista listas, Scanner sc){
        int dado=0;
        String nome="";
        String cpf="";
        int idade=0;
        whileTrue: while(true){
            switch(dado){
                case 0:
                    System.out.print("Digite o nome: ");
                    nome=sc.nextLine();
                    break;
                case 1:
                    System.out.print("Digite o CPF: ");
                    cpf=sc.nextLine();
                    break;
                case 2:
                    idade=inputInt("Digite a idade: ", sc);
                    break;
                case 3:
                    int nada=inputInt("Digite o nada: ", sc);
                    break;
                default:
                    break whileTrue;
            }
            dado++;
        }
        listas.adicionaPaciente(new Paciente(nome, cpf, idade));
    }

    @Override
    public void imprimeDados(){
        System.out.println("Nome: "+getNome());
        System.out.println("CPF: "+getCpf());
        System.out.println("Idade: "+getIdade());
    }
}
