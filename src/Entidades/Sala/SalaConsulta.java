package Entidades.Sala;

import java.util.ArrayList;

import Calendario.Periodo;
import Entidades.Medico.Especialidade;

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
