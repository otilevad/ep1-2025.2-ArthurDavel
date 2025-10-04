package Utilitarios.Calendario;

import Entidades.Paciente.*;
import Exceptions.*;
import Menu.Comando;
import Menu.Menu;
import Repositorios.AllRep;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.time.DateTimeException;

import Entidades.Medico.*;
import Utilitarios.*;

public class Consulta {
    private Paciente pac;
    private Medico med;
    private Especialidade espec;
    private Periodo per;
    private String status;
    private ArrayList<Comando> comandos;
    
    public Consulta(){
        this.pac=new Paciente();
        this.med=new Medico();
        this.espec=new Especialidade();
        this.per=Periodo.periodoConsulta(0,0,0);
        this.status="Agendada";
        this.comandos=new ArrayList<Comando>();
        addComandos();
    }

    public Consulta(Paciente pac, Medico med, Especialidade espec, int dia, int horario, int duracao, String status, ArrayList<Comando> comandos){
        this.pac=new Paciente();
        this.med=new Medico();
        this.espec=new Especialidade();
        this.per=Periodo.periodoConsulta(dia,horario,duracao);
        this.status=status;
        this.comandos=comandos;
    }

    public Paciente getPac() {
        return this.pac;
    }

    public void setPac(Paciente pac) {
        this.pac = pac;
    }

    public Medico getMed() {
        return this.med;
    }

    public void setMed(Medico med) {
        this.med = med;
    }

    public Especialidade getEspec() {
        return this.espec;
    }

    public void setEspec(Especialidade espec) {
        this.espec = espec;
    }

    public Periodo getPer() {
        return this.per;
    }

    public void setPer(Periodo per) {
        this.per = per;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Comando> getComandos() {
        return this.comandos;
    }

    public void setComandos(ArrayList<Comando> comandos) {
        this.comandos = comandos;
    }

    public void addComandos(){
        getComandos().add(new Comando("cpf consulta", "String", "CPF do paciente: "));
        getComandos().add(new Comando("especialidade", "String", "Especialidade da consulta: "));
    }

    public void agendar(Scanner sc, AllRep rep, Calendario cal) throws Exception{
        setComandos(Menu.inputMenu(getComandos(), false, 11, sc, rep));
        agendamentoCalendario(sc,rep,cal);
    }

    public void agendamentoCalendario(Scanner sc, AllRep rep, Calendario cal) throws Exception{
        int mesAgr=LocalDate.now().getMonthValue();
        int anoAgr=2025;
        String input="";
        String obs="";
        LocalDate dataSelecionada=null;
        int horarioSelecionado=-1;
        ArrayList<Integer> horarios=new ArrayList<Integer>();
        while(true){
            Misc.limpaTela();
            cal.mostraMes(mesAgr,anoAgr,0);
            if(dataSelecionada!=null && !horarios.isEmpty()){
                Misc.gotoHome();
                cal.mostraHorario(horarios,40,8);
            }
            if(horarioSelecionado>=0){mostraOpcoesMedicos(horarioSelecionado,dataSelecionada,cal,rep);}
            if(obs.length()>0){
                System.out.println(obs);
                obs="";
            }
            System.out.print("» ");
            input=sc.nextLine();
            try{
                if(input.length()==1){
                    switch(input){
                        case "a":
                            if(mesAgr-1<1){
                                if(anoAgr-1>=cal.getAnos().get(0).getAno()){
                                    mesAgr=12;
                                    anoAgr--;
                                }
                            }
                            else{mesAgr--;}
                            break;
                        case "d":
                            if(mesAgr+1>12){
                                if(anoAgr+1<=cal.getAnos().get(cal.getAnos().size()-1).getAno()){
                                    mesAgr=1;
                                    anoAgr++;
                                }
                            }
                            else{mesAgr++;}
                            break;
                        case "r":
                            mesAgr=LocalDate.now().getMonthValue();
                            anoAgr=2025;
                            input="";
                            obs="";
                            dataSelecionada=null;
                            break;
                        case "z":
                            obs="==>"+Calendario.minutoTempo(horarioSelecionado)+", "+dataSelecionada;
                            break;
                        default:
                            throw new OptionsInvException("Opção inválida.");
                    }
                }
                else{
                    if(input.contains(":")){
                        if(dataSelecionada==null){
                            throw new DateTimeException("Antes de selecionar um horário, selecione uma data.");
                        }
                        horarioSelecionado=InputCheck.horarioSelecionadoCheck(input);
                        if(!horarios.contains(horarioSelecionado)){
                            horarioSelecionado=-1;
                            throw new DateTimeException("Horário indisponível.");
                        }
                    }
                    else if(input.contains("/")){
                        dataSelecionada=InputCheck.dataCheck(input);
                        horarios=horariosDisponiveis(getEspec(),dataSelecionada,rep,cal);
                    }
                    else{
                        throw new OptionsInvException("Comando inválido.");
                    }
                }
            }
            catch(Exception e){
                obs=e.getMessage();
            }
        }
    }

    public ArrayList<Integer> horariosDisponiveis(Especialidade espec,LocalDate data, AllRep rep, Calendario cal){
        ArrayList<Integer> horarios=new ArrayList<Integer>();
        ArrayList<Integer> horariosMedico=new ArrayList<Integer>();
        int diaNum=cal.dataDia(data.getDayOfMonth(),data.getMonthValue(),data.getYear());
        int diaSemana=cal.diaSemanaInt(diaNum);
        boolean medicoOcupado=false;
        for(Medico med : rep.getMedicosR().getMedicos()){
            horariosMedico=med.getAgnd().getInicioConsultas();
            if(med.getEspec().getNome()==espec.getNome() && !med.getAgnd().getFolga().contains(diaSemana)){
                for(int hor : horariosMedico){
                    for(Consulta i : med.getHist().getConsultas()){
                        medicoOcupado=false;
                        if(i.getPer().getDiaInicio()==diaNum && i.getPer().getDiaInicio()==hor && i.getStatus()=="Agendada"){
                            medicoOcupado=true;
                            break;
                        }
                    }
                    if(!horarios.contains(hor) && !medicoOcupado){
                        horarios.add(hor);
                        Collections.sort(horarios);
                    }
                }
            }
        }
        return horarios;
    }

    public void mostraOpcoesMedicos(int horario,LocalDate data,Calendario cal,AllRep rep){
        ArrayList<String> opcoes=new ArrayList<String>();
        ArrayList<Integer> horariosMedico=new ArrayList<Integer>();
        for(Medico med : rep.getMedicosR().getMedicos()){
            if(med.getEspec()!=getEspec()){continue;}
            horariosMedico=med.getAgnd().getInicioConsultas();
            if(!med.getAgnd().getFolga().contains(cal.diaSemanaInt(cal.dataDia(data.getDayOfMonth(),data.getMonthValue(),data.getYear())))){
                if(horariosMedico.contains(horario)){
                    opcoes.add(med.getNome());
                }
            }
        }
        for(String str : opcoes){
            System.out.println(str);
        }
    }
}
