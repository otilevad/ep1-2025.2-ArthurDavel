package Listas;

import java.util.ArrayList;

import Entidades.Medico.Especialidade;
import Entidades.Sala.SalaConsulta;

public class EspecialidadesLista {
    private ArrayList<Especialidade> especialidades;

    public EspecialidadesLista(){
        this.especialidades=new ArrayList<Especialidade>();
        addEspecialidadesPadrao();
    }

    public EspecialidadesLista(ArrayList<Especialidade> especialidades){
        this.especialidades=especialidades;
    }

    public ArrayList<Especialidade> getEspecialidades() {
        return this.especialidades;
    }

    public void setEspecialidades(ArrayList<Especialidade> especialidades) {
        this.especialidades = especialidades;
    }

    public void addEspecialidadesPadrao(){
        getEspecialidades().add(new Especialidade("Cardiologia",1.07,new ArrayList<SalaConsulta>(),10));
        getEspecialidades().add(new Especialidade("Ortopedia",1.03,new ArrayList<SalaConsulta>(),10));
        getEspecialidades().add(new Especialidade("Pediatria",1.02,new ArrayList<SalaConsulta>(),10));
        getEspecialidades().add(new Especialidade("Neurologia",1.11,new ArrayList<SalaConsulta>(),10));
        getEspecialidades().add(new Especialidade("Otorrinolaringologia",1.09,new ArrayList<SalaConsulta>(),10));
        getEspecialidades().add(new Especialidade("Oftalmologia",1.01,new ArrayList<SalaConsulta>(),10));
        getEspecialidades().add(new Especialidade("Oncologia",1.12,new ArrayList<SalaConsulta>(),10));
        getEspecialidades().add(new Especialidade("Check-up",1,new ArrayList<SalaConsulta>(),10));
    }
}
