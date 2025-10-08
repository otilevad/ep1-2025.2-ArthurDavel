package Listas;

public class AllLista {
    private PacientesLista pacientesL;
    private MedicosLista medicosL;
    private EspecialidadesLista especialidadesL;
    private PlanosLista planosL;
    private ConsultasLista consultasL;

    public AllLista(){
        this.pacientesL= new PacientesLista();
        this.medicosL= new MedicosLista();
        this.especialidadesL= new EspecialidadesLista();
        this.planosL= new PlanosLista();
        this.consultasL= new ConsultasLista();
    }

    public AllLista(PacientesLista pacientesL, MedicosLista medicosL, EspecialidadesLista especialidadesL, PlanosLista planosL, ConsultasLista consultasL){
        this.pacientesL=pacientesL;
        this.medicosL=medicosL;
        this.especialidadesL=especialidadesL;
        this.planosL=planosL;
        this.consultasL=consultasL;
    }

    public PacientesLista getPacientesL() {
        return this.pacientesL;
    }

    public void setPacientesL(PacientesLista pacientesL) {
        this.pacientesL = pacientesL;
    }

    public MedicosLista getMedicosL() {
        return this.medicosL;
    }

    public void setMedicosL(MedicosLista medicosL) {
        this.medicosL = medicosL;
    }

    public EspecialidadesLista getEspecialidadesL() {
        return this.especialidadesL;
    }

    public void setEspecialidadesL(EspecialidadesLista especialidadesL) {
        this.especialidadesL = especialidadesL;
    }

    public PlanosLista getPlanosL() {
        return this.planosL;
    }

    public void setPlanosL(PlanosLista planosL) {
        this.planosL = planosL;
    }

    public ConsultasLista getConsultasL() {
        return this.consultasL;
    }

    public void setConsultasL(ConsultasLista consultasL) {
        this.consultasL = consultasL;
    }
}
