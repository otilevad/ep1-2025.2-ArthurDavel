package Repositorios;

import java.util.ArrayList;

import Entidades.Medico.Especialidade;

public class EspecialidadesRep {
    private ArrayList<Especialidade> especialidades;

    public EspecialidadesRep(){
        this.especialidades=new ArrayList<Especialidade>();
    }

    public EspecialidadesRep(ArrayList<Especialidade> especialidades){
        this.especialidades=especialidades;
    }

    public ArrayList<Especialidade> getEspecialidades() {
        return this.especialidades;
    }

    public void setEspecialidades(ArrayList<Especialidade> especialidades) {
        this.especialidades = especialidades;
    }

    public void addEspecialidadesPadrao(){
        getEspecialidades().add(new Especialidade("Cardiologia",1.07));
        getEspecialidades().add(new Especialidade("Ortopedia",1.03));
        getEspecialidades().add(new Especialidade("Pediatria",1.02));
        getEspecialidades().add(new Especialidade("Neurologia",1.11));
        getEspecialidades().add(new Especialidade("Otorrinolaringologia",1.09));
        getEspecialidades().add(new Especialidade("Oftalmologia",1.01));
        getEspecialidades().add(new Especialidade("Oncologia",1.12));
        getEspecialidades().add(new Especialidade("Check-up",1));
    }
}
