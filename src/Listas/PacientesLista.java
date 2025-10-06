package Listas;

import java.util.ArrayList;

import Entidades.Paciente.*;
import Exceptions.*;

public class PacientesLista {
    private ArrayList<Paciente> pacientes;
    private ArrayList<PacienteEspecial> pacientesEsp;
    
    public PacientesLista(){
        pacientes= new ArrayList<Paciente>();
        pacientesEsp= new ArrayList<PacienteEspecial>();
    }

    public PacientesLista(ArrayList<Paciente> pacientes, ArrayList<PacienteEspecial> pacientesEsp){
        this.pacientes=pacientes;
        this.pacientesEsp=pacientesEsp;
    }

    public ArrayList<Paciente> getPacientes(){
        return this.pacientes;
    }

    public void setPacientes(ArrayList<Paciente> pacientes){
        this.pacientes=pacientes;
    }

    public ArrayList<PacienteEspecial> getPacientesEsp() {
        return this.pacientesEsp;
    }

    public void setPacientesEsp(ArrayList<PacienteEspecial> pacientesEsp) {
        this.pacientesEsp = pacientesEsp;
    }

    public void adicionaPaciente(Paciente paciente){
        pacientes.add(paciente);
    }

    public void adicionaPacienteEspecial(PacienteEspecial pacienteEsp){
        pacientesEsp.add(pacienteEsp);
    }

    public boolean cpfEspecial(String str) throws Exception{
        if(getPacientes().isEmpty()){
            for(Paciente i : getPacientes()){
                if(str.equals(i.getCpf())){
                    return false;
                }
            }
        }
        if(!getPacientesEsp().isEmpty()){
            for(PacienteEspecial j : getPacientesEsp()){
                if(str.equals(j.getCpf())){
                    return true;
                }
            }
        }
        throw new CpfExistsException("CPF não cadastrado.");
    }

    public PacienteEspecial buscaCpfEsp(String str){
        for(PacienteEspecial i : getPacientesEsp()){
            if(str.equals(i.getCpf())){
                return i;
            }
        }
        return null;
    }

    public Paciente buscaCpf(String str){
        for(Paciente i : getPacientes()){
            if(str.equals(i.getCpf())){
                return i;
            }
        }
        return null;
    }

    public void listarPacientes(){
        if(pacientes.size()>0||pacientesEsp.size()>0){
            System.out.println("---Lista de Pacientes---");
            for(int i=0; i<pacientes.size(); i++){
                Paciente paciente=pacientes.get(i);
                paciente.imprimeDados();
                System.out.println("------------------------");
            }
            for(int i=0; i<pacientesEsp.size(); i++){
                PacienteEspecial pacienteEsp=pacientesEsp.get(i);
                pacienteEsp.imprimeDados();
                System.out.println("------------------------");
            }
        }
        else{
            System.out.println("Não há pacientes cadastrados");
        }
        System.out.println("Pressione Enter para retornar");
    }
}
