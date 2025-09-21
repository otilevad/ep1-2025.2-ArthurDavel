package Utilitarios.Calendario;

import java.util.ArrayList;

abstract public class Historico {
    private ArrayList<Consulta> consultas;
    
    public Historico(){
        this.consultas=new ArrayList<Consulta>();
    }

    public Historico(ArrayList<Consulta> consultas){
        this.consultas=consultas;
    }

    public void setConsultas(ArrayList<Consulta> consultas){
        this.consultas=consultas;
    }

    public ArrayList<Consulta> getConsultas(){
        return this.consultas;
    }
}
