package Repositorios;

public class AllRep {
    private PacientesRep pacientesR;
    private MedicosRep medicosR;
    private EspecialidadesRep especialidadesR;
    private PlanosRep planosR;

    public AllRep(){
        this.pacientesR= new PacientesRep();
        this.medicosR= new MedicosRep();
        this.especialidadesR= new EspecialidadesRep();
        this.planosR= new PlanosRep();
    }

    public AllRep(PacientesRep pacientesR, MedicosRep medicosR, EspecialidadesRep especialidadesR, PlanosRep planosR){
        this.pacientesR=pacientesR;
        this.medicosR=medicosR;
        this.especialidadesR=especialidadesR;
        this.planosR=planosR;
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
    
    public PlanosRep getPlanosR() {
        return this.planosR;
    }

    public void setPlanosR(PlanosRep planosR) {
        this.planosR = planosR;
    }
}
