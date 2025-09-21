package Entidades.Medico;

public class Especialidade {
    private String nome;
    private int valor;

    public Especialidade(){
        this.nome="";
        this.valor=0;
    }

    public Especialidade(String nome, int valor){
        this.nome=nome;
        this.valor=valor;
    }
    
    public void setNome(String nome){
        this.nome=nome;
    }

    public String getNome(){
        return this.nome;
    }

    public void setValor(int valor){
        this.valor=valor;
    }

    public int getValor(){
        return this.valor;
    }
}
