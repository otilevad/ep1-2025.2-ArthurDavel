package Listas;

import java.util.ArrayList;

import Utilitarios.Calendario.*;

public class ConsultasLista{
    private ArrayList<Consulta> consultas;
    
    public ConsultasLista(){
        this.consultas=new ArrayList<Consulta>();
    }

    public ConsultasLista(ArrayList<Consulta> consultas){
        this.consultas=consultas;
    }

    public void setConsultas(ArrayList<Consulta> consultas){
        this.consultas=consultas;
    }

    public ArrayList<Consulta> getConsultas(){
        return this.consultas;
    }
}
