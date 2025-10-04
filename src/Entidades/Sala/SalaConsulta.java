package Entidades.Sala;

import java.util.ArrayList;

import Entidades.Medico.Especialidade;
import Utilitarios.Calendario.Periodo;

public class SalaConsulta extends Sala{
    private Especialidade espec;

    public SalaConsulta(){
        super();
        this.espec=new Especialidade();
    }

    public SalaConsulta(int num, ArrayList<Periodo> ocupado, Especialidade espec){
        super(num,ocupado);
        this.espec=espec;
    }

    public Especialidade getEspec() {
        return this.espec;
    }

    public void setEspec(Especialidade espec) {
        this.espec = espec;
    }
}
