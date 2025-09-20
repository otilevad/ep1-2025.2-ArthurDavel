package Calendario;

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

    public String diaCalendario(int numCalendario){
        int dia=1;
        int mes=1;
        int anoOrd=0;
        Ano ano=getAnos().get(anoOrd);
        int diasTotais=ano.fatMes(12, ano.getMeses());
        int diaSemana=ano.getAnoInicia();
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
                    return "Data inválida";
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
            diaSemana++;
            diaSemana%=7;
        }
        String data=Semana.values()[diaSemana]+", "+String.format("%02d", dia)+"/"+String.format("%02d", mes)+"/"+ano.getAno();
        return data;
    }
}
