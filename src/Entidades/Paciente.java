package Entidades;
public class Paciente extends Pessoa{
    private String cpf;
    private int idade;

    public Paciente(){
        super();
        this.cpf="";
        this.idade=0;
    }

    public Paciente(String nome, String cpf, int idade){
        super();
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
    public void imprimeDados(){

    }
}
