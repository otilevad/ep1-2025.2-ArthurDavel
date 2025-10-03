package Entidades.Medico;

import java.util.ArrayList;
import java.util.Collections;

import Utilitarios.Calendario.Periodo;

public class HorarioMedico {
    private ArrayList<Integer> inicioConsultas;
    private int duracao;

    public HorarioMedico(){
        this.inicioConsultas=new ArrayList<Integer>();
        this.duracao=0;
    }

    public HorarioMedico(ArrayList<Integer> inicioConsultas,int duracao){
        this.inicioConsultas=inicioConsultas;
        this.duracao=duracao;
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

    public void addInicioConsultas(int horarioInicio, int horarioFim){
        for(int i=horarioInicio;i<horarioFim;i+=getDuracao()){
            getInicioConsultas().add(i);
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
}
