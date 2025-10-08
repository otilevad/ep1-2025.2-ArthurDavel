package Utilitarios.Calendario;

import java.util.ArrayList;

import Entidades.Medico.Medico;
import Entidades.Paciente.Paciente;
import Entidades.Paciente.PacienteEspecial;
import Entidades.Sala.SalaInternacao;
import Menu.Comando;

public class Internacao{
    private Paciente pac;
    private PacienteEspecial pacEsp;
    private boolean pacIsEsp;
    private Medico med;
    private SalaInternacao sala;
    private ArrayList<Comando> comandos;
}
