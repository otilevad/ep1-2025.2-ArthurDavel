package Utilitarios.Calendario;

public class DataMarcada{
    public static final String RESET = "\u001B[0m";
    public static final String VERMELHO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String AMARELO = "\u001B[33m";

    private int data;
    private String[] cores;
    private int cor;

    public DataMarcada(){
        this.data=1;
        this.cores=new String[] {VERDE, AMARELO, VERMELHO};
        this.cor=0;
    }

    public DataMarcada(int data, int cor){
        this.data=data;
        this.cores=new String[] {VERDE, AMARELO, VERMELHO};
        this.cor=cor;
    }

    public int getData() {
        return this.data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public String[] getCores() {
        return this.cores;
    }

    public void setCores(String[] cores) {
        this.cores = cores;
    }

    public int getCor() {
        return this.cor;
    }

    public void setCor(int cor) {
        this.cor = cor;
    }

    public void printaCor(){
        System.out.printf(cores[cor]);
    }

    public static void resetaCor(){
        System.out.printf("\u001B[0m");
    }
}
