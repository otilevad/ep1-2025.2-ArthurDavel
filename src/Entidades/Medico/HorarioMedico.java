package Entidades.Medico;

import java.util.ArrayList;
import java.util.Collections;

import Utilitarios.Calendario.Periodo;

public class HorarioMedico {
    private ArrayList<Integer> inicioConsultas;

    public HorarioMedico(){
        this.inicioConsultas=new ArrayList<Integer>();
    }

    public HorarioMedico(ArrayList<Integer> inicioConsultas){
        this.inicioConsultas=inicioConsultas;
    }
    
    public ArrayList<Integer> getInicioConsultas() {
        return this.inicioConsultas;
    }

    public void setInicioConsultas(ArrayList<Integer> inicioConsultas) {
        this.inicioConsultas=inicioConsultas;
    }

    public void addInicioConsultas(int horarioInicio, int horarioFim, int duracao){
        for(int i=horarioInicio;i<horarioFim;i+=duracao){
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
