package Entidades.PlanoSaude;

import Entidades.Medico.Especialidade;

public class Desconto {
    private double porcentagem;
    private Especialidade espec;

    public Desconto(){
        this.porcentagem=0;
        this.espec=new Especialidade();
    }

    public Desconto(double porcentagem, Especialidade espec){
        this.porcentagem=porcentagem;
        this.espec=espec;
    }

    public void setPorcentagem(double porcentagem) {
        this.porcentagem = porcentagem;
    }

    public double getPorcentagem() {
        return this.porcentagem;
    }
    
    public void setEspec(Especialidade espec) {
        this.espec = espec;
    }

    public Especialidade getEspec() {
        return this.espec;
    }
}
