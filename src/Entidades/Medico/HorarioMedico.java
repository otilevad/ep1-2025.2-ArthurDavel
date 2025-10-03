package Entidades.Medico;

import java.util.ArrayList;

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

    public ArrayList<Integer> addInicioConsultas(Periodo per, int duracao){
        ArrayList<Integer> prevArray=new ArrayList<Integer>();
        for(int i=per.getHorarioInicio();i<per.getHorarioFim();i+=duracao){
            prevArray.add(i);
        }
        return prevArray;
    }

    public ArrayList<Integer> removeInicioConsultas(Periodo per){
        ArrayList<Integer> prevArray=getInicioConsultas();
        for(int i : prevArray){
            if(i>=per.getHorarioInicio() && i<=per.getHorarioFim()){
                prevArray.remove(i);
            }
        }
        return prevArray;
    }
}
