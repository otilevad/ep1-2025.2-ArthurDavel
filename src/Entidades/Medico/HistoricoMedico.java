package Entidades.Medico;

import java.util.ArrayList;

import Utilitarios.Calendario.*;

public class HistoricoMedico extends Historico{
    public HistoricoMedico(){
        super();
    }

    public HistoricoMedico(ArrayList<Consulta> consultas){
        super(consultas);
    }
}
