package Utilitarios.Calendario;

public class Ano {
    private int ano;
    private int anoInicia; //de 0 a 6, de domingo a sábado
    private boolean anoBissexto;
    private int[] meses; //duração dos meses em dias

    public Ano(){
        this.ano=2025;
        this.anoInicia=3;
        this.anoBissexto=false;
        this.meses=new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    }
    public Ano(int ano, int anoInicia, boolean anoBissexto){
        this.ano=ano;
        this.anoInicia=anoInicia;
        this.anoBissexto=anoBissexto;
        if(anoBissexto){
            this.meses = new int[] {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        }
        else{
            this.meses = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        }
    }

    //region get e set
    public int getAno(){
        return this.ano;
    }
    public void setAno(int ano){
        this.ano=ano;
    }

    public int getAnoInicia(){
        return this.anoInicia;
    }
    public void setAnoInicia(int anoInicia){
        this.anoInicia=anoInicia;
    }

    public boolean getAnoBissexto(){
        return this.anoBissexto;
    }
    public void setAnoBissexto(boolean anoBissexto){
        this.anoBissexto=anoBissexto;
    }

    public int[] getMeses(){
        return this.meses;
    }
    public void setMeses(int[] meses){
        this.meses=meses;
    }
    //endregion

    public int fatMes(int mes, int[] meses){
        int fator=0;
        int mesCount=0;
        for(int i : getMeses()){
            mesCount++;
            fator+=i;
            if(mesCount>=mes){
                break;
            }
        }
        return fator;
    }
}
