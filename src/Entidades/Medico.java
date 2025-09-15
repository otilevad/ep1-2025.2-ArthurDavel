package Entidades;
public class Medico extends Pessoa{
    private String crm;

    public Medico(){
        super();
        this.crm="";
    }

    public Medico(String nome, String crm){
        super();
        this.crm=crm;
    }

    public String getCrm() {
        return this.crm;
    }

    public void setCrm(String crm) {
        this.crm=crm;
    }

    @Override
    public void imprimeDados(){

    }
}
