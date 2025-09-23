package Utilitarios.Calendario;

import Entidades.Paciente.*;
import Entidades.Medico.*;

public class Consulta {
    private Paciente pac;
    private Medico med;
    private Especialidade espec;
    private Periodo per;
    
    public Consulta(){
        this.pac=new Paciente();
        this.med=new Medico();
        this.espec=new Especialidade();
        this.per=Periodo.periodoConsulta(0,0,0);
    }

    public Consulta(Paciente pac, Medico med, Especialidade espec, int dia, int horario, int duracao){
        this.pac=new Paciente();
        this.med=new Medico();
        this.espec=new Especialidade();
        this.per=Periodo.periodoConsulta(dia,horario,duracao);
    }

    public Paciente getPac() {
        return this.pac;
    }

    public void setPac(Paciente pac) {
        this.pac = pac;
    }

    public Medico getMed() {
        return this.med;
    }

    public void setMed(Medico med) {
        this.med = med;
    }

    public Especialidade getEspec() {
        return this.espec;
    }

    public void setEspec(Especialidade espec) {
        this.espec = espec;
    }

    public Periodo getPer() {
        return this.per;
    }

    public void setPer(Periodo per) {
        this.per = per;
    }
}
