package Entidades.Sala;

import java.util.ArrayList;

import Utilitarios.Calendario.Periodo;

public class SalaInternacao extends Sala{
    public SalaInternacao(){
        super();
    }

    public SalaInternacao(int num, ArrayList<Periodo> ocupado){
        super(num,ocupado);
    }
}
