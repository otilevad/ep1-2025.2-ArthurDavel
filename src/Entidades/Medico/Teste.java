package Entidades.Medico;

import Utilitarios.Calendario.*;

public class Teste {
    public static void main (String[] args) throws Exception {
        HorarioMedico horario=new HorarioMedico();
        horario.setDuracao(5);
        horario.addInicioConsultas(12*60,24*60);
        horario.addInicioConsultas(8*60,9*60);
        horario.removeInicioConsultas(13*60,16*60);
        for(int i : horario.getInicioConsultas()){
            System.out.println(Calendario.minutoTempo(i));
        }
    }
}
