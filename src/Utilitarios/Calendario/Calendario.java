package Utilitarios.Calendario;

import java.util.ArrayList;

public class Calendario {
    private ArrayList<Ano> anos;

    public Calendario(){
        this.anos=new ArrayList<Ano>();
    }

    public Calendario(ArrayList<Ano> anos){
        this.anos=anos;
    }

    public ArrayList<Ano> getAnos(){
        return this.anos;
    }

    public void setAnos(ArrayList<Ano> anos){
        this.anos=anos;
    }

    enum Semana{
        Domingo,
        Segunda,
        Terça,
        Quarta,
        Quinta,
        Sexta,
        Sábado
    }

    enum Mes{
        Janeiro,
        Fevereiro,
        Março,
        Abril,
        Maio,
        Junho,
        Julho,
        Agosto,
        Setembro,
        Outubro,
        Novembro,
        Dezembro
    }

    public void addAno(int ano, int anoInicia, boolean anoBissexto){
        getAnos().add(new Ano(ano,anoInicia,anoBissexto));
    }

    public void calendarioSetup(){
        addAno(2025,3,false);
        addAno(2026,4,false);
    }

    public int diaData(int numCalendario, String diaMesAno){
        int dia=1;
        int mes=1;
        int anoOrd=0;
        Ano ano=getAnos().get(anoOrd);
        int diasTotais=ano.fatMes(12, ano.getMeses());
        int anoPassou=0;
        int mesCheck=anoPassou+ano.fatMes(mes, ano.getMeses());
        for(int i=1;i<numCalendario;i++){
            if(i<diasTotais){
                if(i>=mesCheck){
                    mes++;
                    dia=1;
                    mesCheck=anoPassou+ano.fatMes(mes, ano.getMeses());
                }
                else{
                    dia++;
                }
            }
            else{
                anoOrd++;
                if(anoOrd>=getAnos().size()){
                    return 0;
                }
                else{
                    mes=1;
                    dia=1;
                    anoPassou=ano.fatMes(12, ano.getMeses());
                    ano=getAnos().get(anoOrd);
                    diasTotais+=ano.fatMes(12, ano.getMeses());
                    mesCheck=anoPassou+ano.fatMes(mes, ano.getMeses());  
                }
            }
        }
        int data;
        switch(diaMesAno){
            case "dia":
                data=dia;
                break;
            case "mes":
                data=mes;
                break;
            case "ano":
                data=ano.getAno();
                break;
            default:
                data=0;
                break;
        }
        return data;
    }

    public String dataString(int dia, int mes, int ano){
        return String.format("%02d", dia)+"/"+String.format("%02d", mes)+"/"+ano;
    }

    public String diaSemana(int numCalendario){
        int dSem=numCalendario+getAnos().get(0).getAnoInicia()-1;
        dSem%=7;
        return Semana.values()[dSem].toString();
    }

    public int tempoMinuto(int hora, int min){
        return hora*60+min;
    }

    public String minutoTempo(int numTempo){
        int hora=0;
        int min=0;
        for(int i=0;i<numTempo;i++){
            min++;
            if(min>=60){
                min=0;
                hora++;
                if(hora>=24){
                    hora=0;
                }
            }
        }
        return String.format("%02d", hora)+":"+String.format("%02d", min);
    }
}
