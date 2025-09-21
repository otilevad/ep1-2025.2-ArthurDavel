package Utilitarios.Calendario;

public class Periodo {
    private int inicio;
    private int fim;

    public Periodo(){
        this.inicio=0;
        this.fim=0;
    }

    public Periodo(int inicio, int fim){
        this.inicio=inicio;
        this.fim=fim;
    }

    public int getInicio(){
        return this.inicio;
    }

    public void setInicio(int inicio){
        this.inicio=inicio;
    }

    public int getFim(){
        return this.fim;
    }

    public void setFim(int fim){
        this.fim=fim;
    }
}
