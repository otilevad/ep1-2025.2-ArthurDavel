package Listas;

import java.util.ArrayList;

import Calendario.*;
import Entidades.Paciente.Paciente;
import Entidades.Paciente.PacienteEspecial;

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

    public void listarConsultas(Calendario cal){
        if(consultas.size()>0){
            System.out.println("---Lista de consultas---");
            for(Consulta i : consultas){
                i.imprimeDados(cal);
                System.out.println("------------------------");
            }
        }
        else{
            System.out.println("Não há consultas cadastradas");
        }
        System.out.println("Pressione Enter para retornar");
    }
}
