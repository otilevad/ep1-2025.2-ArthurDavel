package Entidades.Medico;

import java.util.ArrayList;
import java.util.Collections;

import Entidades.PlanoSaude.Desconto;
import Exceptions.*;
import Utilitarios.Calendario.*;

public class Agenda {
    private ArrayList<Integer> inicioConsultas;
    private ArrayList<Integer> folga;
    private int duracao;

    public Agenda(){
        this.inicioConsultas=new ArrayList<Integer>();
        this.duracao=0;
        this.folga=new ArrayList<Integer>();
    }

    public Agenda(ArrayList<Integer> inicioConsultas,int duracao,ArrayList<Integer> folga){
        this.inicioConsultas=inicioConsultas;
        this.duracao=duracao;
        this.folga=folga;
    }
    
    public ArrayList<Integer> getInicioConsultas() {
        return this.inicioConsultas;
    }

    public void setInicioConsultas(ArrayList<Integer> inicioConsultas) {
        this.inicioConsultas=inicioConsultas;
    }

    public int getDuracao() {
        return this.duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public ArrayList<Integer> getFolga() {
        return this.folga;
    }

    public void setFolga(ArrayList<Integer> folga) {
        this.folga = folga;
    }


    public void addInicioConsultas(int horarioInicio, int horarioFim){
        for(int i=horarioInicio;i<horarioFim;i+=getDuracao()){
            if(!getInicioConsultas().contains(i)){
                getInicioConsultas().add(i);
            }
        }
        Collections.sort(getInicioConsultas());
    }

    public void removeInicioConsultas(int horarioInicio, int horarioFim){
        ArrayList<Integer> removeList=new ArrayList<Integer>();
        for(int i : getInicioConsultas()){
            if(i>=horarioInicio && i<=horarioFim){
                removeList.add(i);
            }
        }
        for(int i : removeList){
            getInicioConsultas().remove(getInicioConsultas().indexOf(i));
        }
    }
    
    public ArrayList<Integer> folgaStr(String str) throws Exception{
        ArrayList<Integer> folga=new ArrayList<Integer>();
        String strNova=str.replaceAll("\\s", "");
        String[] strs=strNova.split("/");
        for(String diaStr : strs){
            int dia=Integer.parseInt(diaStr);
            if(dia>6 || dia<0){
                throw new IntervaloInvException("Dias vão de 0 a 6, domingo sendo 0.");
            }else if(folga.contains(dia)){
                continue;
            }
            folga.add(dia);
        }
        return folga;
    }

    public ArrayList<Periodo> horarioStr(String str) throws Exception{
        ArrayList<Periodo> pers=new ArrayList<Periodo>();
        String strNova=str.replaceAll("\\s", "");
        String[] strs=strNova.split(",");
        for(String perStr : strs){
            String[] valores=perStr.split("/");
            String[] horaMinutoInicio=valores[0].split(":");
            String[] horaMinutoFim=valores[1].split(":");
            int inicio=Calendario.tempoMinuto(Integer.parseInt(horaMinutoInicio[0]),Integer.parseInt(horaMinutoInicio[1]));
            int fim=Calendario.tempoMinuto(Integer.parseInt(horaMinutoFim[0]),Integer.parseInt(horaMinutoFim[1]));
            if(fim<=inicio){
                throw new IntervaloInvException("O horário final deve ser maior que o inicial.");
            }
            pers.add(new Periodo(0,0,inicio,fim));
        }
        return pers;
    }

    public void agendaStr(ArrayList<Integer> folga,ArrayList<Periodo> trabalha, ArrayList<Periodo> intervalo){
        setFolga(folga);
        for(Periodo per : trabalha){
            addInicioConsultas(per.getHorarioInicio(),per.getHorarioFim());
        }
        for(Periodo per : intervalo){
            removeInicioConsultas(per.getHorarioInicio(),per.getHorarioFim());
        }
    }
}