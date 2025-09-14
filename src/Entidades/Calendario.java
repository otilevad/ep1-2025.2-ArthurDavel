package Entidades;

import Menu.Opcao;

public class Calendario {
    private int ano;
    private int anoInicia; //de 0 a 6, de domingo a sábado
    private boolean anoBissexto;
    private int[] meses; //duração dos meses em dias

    public Calendario(){
        this.ano=2025;
        this.anoInicia=3;
        this.anoBissexto=false;
        this.meses=new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    }
    public Calendario(int ano, int anoInicia, boolean anoBissexto){
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

    public String diaCalendario(int numAno){
        int dia=1;
        int mes=1;
        int diaSemana=anoInicia;
        int mesCheck=fatMes(mes, getMeses());
        String diaSemanaS="";
        for(int i=1;i<numAno;i++){
            if(i>=mesCheck){
                mes++;
                dia=1;
                mesCheck=fatMes(mes, getMeses());
            }
            else{
                dia++;
            }
            diaSemana++;
            diaSemana%=7;
        }
        switch(diaSemana){
            case 0:
                diaSemanaS="Domingo";
                break;
            case 1:
                diaSemanaS="Segunda";
                break;
            case 2:
                diaSemanaS="Terça";
                break;
            case 3:
                diaSemanaS="Quarta";
                break;
            case 4:
                diaSemanaS="Quinta";
                break;
            case 5:
                diaSemanaS="Sexta";
                break;
            case 6:
                diaSemanaS="Sábado";
                break;
            
        }
        String data=dia+"/"+mes+"/"+getAno()+", "+diaSemanaS;
        return data;
    }

    public int fatMes(int mes, int[] meses){
        int fat=0;
        int mesCount=0;
        for(int i : getMeses()){
            mesCount++;
            fat+=i;
            if(mesCount>=mes){
                break;
            }
        }
        System.out.println(fat);
        return fat;
    }
}
