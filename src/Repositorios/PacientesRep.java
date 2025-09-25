package Repositorios;

import java.util.ArrayList;

import Entidades.Paciente.Paciente;

public class PacientesRep {
    private ArrayList<Paciente> pacientes;
    
    public PacientesRep(){
        pacientes = new ArrayList<Paciente>();
    }

    public PacientesRep(ArrayList<Paciente> pacientes){
        this.pacientes = pacientes;
    }

    public ArrayList<Paciente> getPacientes(){
        return this.pacientes;
    }

    public void setPacientes(ArrayList<Paciente> pacientes){
        this.pacientes=pacientes;
    }

    public void adicionaPaciente(Paciente paciente){
        pacientes.add(paciente);
    }

    public void listarPacientes(){
        if(pacientes.size()>0){
            System.out.println("---Lista de Pacientes---");
            for(int i=0; i<pacientes.size(); i++){
                Paciente paciente=pacientes.get(i);
                paciente.imprimeDados();
                System.out.println("------------------------");
            }
        }
        else{
            System.out.println("Não há pacientes cadastrados");
        }
        System.out.println("Pressione Enter para retornar");
    }
}
