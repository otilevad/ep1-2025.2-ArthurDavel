package Entidades.Medico;

import java.util.ArrayList;
import Utilitarios.Calendario.*;

public class Agenda {
    private ArrayList<ArrayList<Periodo>> horarios;
    
    public Agenda(){
        horarios=new ArrayList<ArrayList<Periodo>>();
    }
}