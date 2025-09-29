package Repositorios;

public class AllRep {
    private PacientesRep pacientesR;
    private MedicosRep medicosR;
    private EspecialidadesRep especialidadesR;

    public AllRep(){
        this.pacientesR= new PacientesRep();
        this.medicosR= new MedicosRep();
        this.especialidadesR= new EspecialidadesRep();
    }

    public AllRep(PacientesRep pacientesR, MedicosRep medicosR, EspecialidadesRep especialidadesR){
        this.pacientesR=pacientesR;
        this.medicosR=medicosR;
        this.especialidadesR=especialidadesR;
    }

    public PacientesRep getPacientesR() {
        return this.pacientesR;
    }

    public void setPacientesR(PacientesRep pacientesR) {
        this.pacientesR = pacientesR;
    }

    public MedicosRep getMedicosR() {
        return this.medicosR;
    }

    public void setMedicosR(MedicosRep medicosR) {
        this.medicosR = medicosR;
    }

    public EspecialidadesRep getEspecialidadesR() {
        return this.especialidadesR;
    }

    public void setEspecialidadesR(EspecialidadesRep especialidadesR) {
        this.especialidadesR = especialidadesR;
    }
}
