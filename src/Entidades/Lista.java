package Entidades;

import java.util.ArrayList;

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

    public void adicionaMedico(Medico medico){
        medicos.add(medico);
    }

    public void listarMedicos(){
        System.out.println("----Lista de Médicos----");
        for(int i=0; i<medicos.size(); i++){
            Medico medico=medicos.get(i);
            medico.imprimeDados();
            System.out.println("------------------------");
        }
        System.out.println("Pressione Enter para retornar");
    }
}
