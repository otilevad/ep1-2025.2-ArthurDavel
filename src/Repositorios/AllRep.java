package Repositorios;

public class AllRep {
    private PacientesRep pacientesR;
    private MedicosRep medicosR;

    public AllRep(){
        pacientesR= new PacientesRep();
        medicosR= new MedicosRep();
    }

    public AllRep(PacientesRep pacientesR, MedicosRep medicosR){
        pacientesR=this.pacientesR;
        medicosR=this.medicosR;
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

}
