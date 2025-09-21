package Utilitarios;

import java.util.ArrayList;

import Entidades.Medico.Medico;
import Entidades.Paciente.Paciente;

public class Lista {
    private ArrayList<Medico> medicos;
    private ArrayList<Paciente> pacientes;
    
    public Lista(){
        medicos = new ArrayList<Medico>();
        pacientes = new ArrayList<Paciente>();
    }

    public ArrayList<Medico> getMedicos(){
        return this.medicos;
    }

    public void setMedicos(ArrayList<Medico> medicos){
        this.medicos=medicos;
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

    public void adicionaMedico(Medico medico){
        medicos.add(medico);
    }

    public void listarMedicos(){
        if(medicos.size()>0){
            System.out.println("----Lista de Médicos----");
            for(int i=0; i<medicos.size(); i++){
                Medico medico=medicos.get(i);
                medico.imprimeDados();
                System.out.println("------------------------");
            }
        }
        else{
            System.out.println("Não há médicos cadastrados");
        }
        System.out.println("Pressione Enter para retornar");
    }
}
