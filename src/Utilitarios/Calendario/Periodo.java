package Utilitarios.Calendario;

public class Periodo {
    private int diaInicio;
    private int diaFim;
    private int horarioInicio;
    private int horarioFim;

    public Periodo(){
        this.diaInicio=0;
        this.diaFim=0;
        this.horarioInicio=0;
        this.horarioFim=0;
    }

    public Periodo(int diaInicio, int diaFim, int horarioInicio, int horarioFim){
        this.diaInicio=diaInicio;
        this.diaFim=diaFim;
        this.horarioInicio=horarioInicio;
        this.horarioFim=horarioFim;
    }

    public Periodo(int diaInicio, int diaFim){
        this.diaInicio=diaInicio;
        this.diaFim=diaFim;
        this.horarioInicio=1;
        this.horarioFim=1440;
    }

    public int getDiaInicio(){
        return this.diaInicio;
    }

    public void setInicio(int diaInicio){
        this.diaInicio=diaInicio;
    }

    public int getDiaFim(){
        return this.diaFim;
    }

    public void setDiaFim(int diaFim){
        this.diaFim=diaFim;
    }

    public int getHorarioInicio(){
        return this.horarioInicio;
    }

    public void setHorarioInicio(int horarioInicio){
        this.horarioInicio=horarioInicio;
    }

    public int getHorarioFim(){
        return this.horarioFim;
    }

    public void setHorarioFim(int horarioFim){
        this.horarioFim=horarioFim;
    }

    public static Periodo periodoConsulta(int dia, int horario, int duracao){
        int virouDia=horario+duracao>1440 ? 1 : 0;
        int horarioFim=virouDia==0 ? horario+duracao : horario+duracao-1440;
        return new Periodo(dia, dia+virouDia, horario, horarioFim);
    }
}
