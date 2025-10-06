package Listas;

import java.util.ArrayList;

import Entidades.Medico.Medico;

public class MedicosLista {
    private ArrayList<Medico> medicos;
    
    public MedicosLista(){
        medicos = new ArrayList<Medico>();
    }

    public MedicosLista(ArrayList<Medico> medicos){
        this.medicos = medicos;
    }

    public ArrayList<Medico> getMedicos(){
        return this.medicos;
    }

    public void setMedicos(ArrayList<Medico> medicos){
        this.medicos=medicos;
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
