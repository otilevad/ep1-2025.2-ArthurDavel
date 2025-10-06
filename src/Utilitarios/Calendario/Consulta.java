package Utilitarios.Calendario;

import Entidades.Paciente.*;
import Exceptions.*;
import Listas.AllLista;
import Menu.Comando;
import Menu.Menu;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.time.DateTimeException;

import Entidades.Medico.*;
import Entidades.PlanoSaude.*;
import Utilitarios.*;

public class Consulta {
    private Paciente pac;
    private PacienteEspecial pacEsp;
    private boolean pacIsEsp;
    private Medico med;
    private Especialidade espec;
    private Periodo per;
    private String status;
    private double valor;
    private ArrayList<Comando> comandos;
    
    public Consulta(){
        this.pac=new Paciente();
        this.pacEsp=new PacienteEspecial();
        this.pacIsEsp=false;
        this.med=new Medico();
        this.espec=new Especialidade();
        this.per=Periodo.periodoConsulta(0,0,0);
        this.status="Agendada";
        this.comandos=new ArrayList<Comando>();
        this.valor=0d;
        addComandos();
    }

    public Consulta(Paciente pac, Medico med, Especialidade espec, int dia, int horario, int duracao, String status, ArrayList<Comando> comandos, PacienteEspecial pacEsp, boolean pacIsEsp,double valor){
        this.pac=new Paciente();
        this.pacEsp=pacEsp;
        this.pacIsEsp=pacIsEsp;
        this.med=new Medico();
        this.espec=new Especialidade();
        this.per=Periodo.periodoConsulta(dia,horario,duracao);
        this.status=status;
        this.comandos=comandos;
        this.valor=valor;
    }

    public Paciente getPac() {
        return this.pac;
    }

    public void setPac(Paciente pac) {
        this.pac = pac;
    }

    public PacienteEspecial getPacEsp() {
        return this.pacEsp;
    }

    public void setPacEsp(PacienteEspecial pacEsp) {
        this.pacEsp = pacEsp;
    }

    public boolean getPacIsEsp() {
        return this.pacIsEsp;
    }

    public void setPacIsEsp(boolean pacIsEsp) {
        this.pacIsEsp = pacIsEsp;
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

    public double getValor() {
        return this.valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void addComandos(){
        getComandos().add(new Comando("cpf consulta", "String", "CPF do paciente: "));
        getComandos().add(new Comando("especialidade", "String", "Especialidade da consulta: "));
    }

    public void agendar(Scanner sc, AllLista lista, Calendario cal) throws Exception{
        setComandos(Menu.inputMenu(getComandos(), false, 11, sc, lista));
        setEspec(Especialidade.buscaValorEspec(Comando.buscaPorDado("especialidade",getComandos()).getValorInt(),lista));
        String cpfPac=Comando.buscaPorDado("cpf consulta",getComandos()).getValorStr();
        if(lista.getPacientesL().cpfEspecial(cpfPac)){
            setPacIsEsp(true);
            setPacEsp(lista.getPacientesL().buscaCpfEsp(cpfPac)); 
        }
        else{
            setPacIsEsp(false);
            setPac(lista.getPacientesL().buscaCpf(cpfPac)); 
        }
        agendamentoCalendario(sc,lista,cal);
    }

    public void agendamentoCalendario(Scanner sc, AllLista lista, Calendario cal) throws Exception{
        int mesAgr=LocalDate.now().getMonthValue();
        int anoAgr=2025;
        String input="";
        String obs="";
        LocalDate dataSelecionada=null;
        int horarioSelecionado=-1;
        int opcaoSelecionada=-1;
        ArrayList<Integer> horarios=new ArrayList<Integer>();
        ArrayList<DataMarcada> datasM=datasDisponiveis(mesAgr,anoAgr,cal,lista);
        ArrayList<OpcaoConsulta> opcoes=new ArrayList<OpcaoConsulta>(); 
        Medico medicoSelecionado=new Medico();
        double valorSelecionado=0d;
        while(true){
            Misc.limpaTela();
            datasM=datasDisponiveis(mesAgr,anoAgr,cal,lista);
            cal.setDatasM(datasM);
            cal.mostraMes(mesAgr,anoAgr,0);
            if(dataSelecionada!=null && !horarios.isEmpty()){
                Misc.gotoHome();
                cal.mostraHorario(horarios,40,8);
            }
            if(horarioSelecionado>=0 && dataSelecionada!=null){
                opcoes=criaOpcoesMedicos(horarioSelecionado,dataSelecionada,cal,lista);
                for(OpcaoConsulta op : opcoes){
                    System.out.println(op.getStr());
                }
            }
            if(obs.length()>0){
                System.out.println(obs);
                obs="";
            }
            System.out.print("» ");
            input=sc.nextLine();
            try{
                if(input.length()==1){
                    if(horarioSelecionado>=0 && dataSelecionada!=null && InputCheck.isInt(input)){
                        opcaoSelecionada=Integer.parseInt(input);
                        if(opcaoSelecionada>=0 && opcaoSelecionada<opcoes.size()){
                            medicoSelecionado=opcoes.get(opcaoSelecionada).getMed();
                            valorSelecionado=opcoes.get(opcaoSelecionada).getValor();
                            setMed(medicoSelecionado);
                            setPer(Periodo.periodoConsulta(cal.dataDia(dataSelecionada.getDayOfMonth(),dataSelecionada.getMonthValue(),dataSelecionada.getYear()),horarioSelecionado,getMed().getTempoMedio()));
                            setValor(valorSelecionado);
                            setStatus("Agendada");
                            break;
                        }
                        else{
                            opcaoSelecionada=-1;
                            throw new OptionsInvException("Escolha entre 0 e "+(opcoes.size()-1)+".");
                        }
                    }
                    else{
                        switch(input){
                            case "a":
                                if(mesAgr-1<1){
                                    if(anoAgr-1>=cal.getAnos().get(0).getAno()){
                                        mesAgr=12;
                                        anoAgr--;
                                    }
                                }
                                else{mesAgr--;}
                                obs="";
                                dataSelecionada=null;
                                horarioSelecionado=-1;
                                break;
                            case "d":
                                if(mesAgr+1>12){
                                    if(anoAgr+1<=cal.getAnos().get(cal.getAnos().size()-1).getAno()){
                                        mesAgr=1;
                                        anoAgr++;
                                    }
                                }
                                else{mesAgr++;}
                                obs="";
                                dataSelecionada=null;
                                horarioSelecionado=-1;
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
                        mesAgr=dataSelecionada.getMonthValue();
                        anoAgr=dataSelecionada.getYear();
                        horarios=horariosDisponiveis(dataSelecionada,lista,cal);
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
        getMed().getHist().getConsultas().add(this);
        if(getPacIsEsp()){
            getPacEsp().getHistoricoPaciente().getConsultas().add(this);
        }
        else{
            getPac().getHistoricoPaciente().getConsultas().add(this);
        }
        System.out.println("\nConsulta agendada com sucesso, pressione Enter para prosseguir.");
    }

    public ArrayList<Integer> horariosDisponiveis(LocalDate data, AllLista lista, Calendario cal){
        ArrayList<Integer> horarios=new ArrayList<Integer>();
        ArrayList<Integer> horariosMedico=new ArrayList<Integer>();
        int diaNum=cal.dataDia(data.getDayOfMonth(),data.getMonthValue(),data.getYear());
        int diaSemana=cal.diaSemanaInt(diaNum);
        boolean medicoOcupado=false;
        for(Medico med : lista.getMedicosL().getMedicos()){
            horariosMedico=med.getAgnd().getInicioConsultas();
            if(med.getEspec().equals(espec) && !med.getAgnd().getFolga().contains(diaSemana)){
                for(int hor : horariosMedico){
                    for(Consulta i : med.getHist().getConsultas()){
                        medicoOcupado=false;
                        if(i.getPer().getDiaInicio()==diaNum && i.getPer().getHorarioInicio()==hor && i.getStatus()=="Agendada"){
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

    public ArrayList<OpcaoConsulta> criaOpcoesMedicos(int horario,LocalDate data,Calendario cal,AllLista lista){
        ArrayList<OpcaoConsulta> opcoes=new ArrayList<OpcaoConsulta>();
        ArrayList<Integer> horariosMedico=new ArrayList<Integer>();
        double custo=0d;
        double desconto=0d;
        for(Medico med : lista.getMedicosL().getMedicos()){
            if(!med.getEspec().equals(espec)){continue;}
            horariosMedico=med.getAgnd().getInicioConsultas();
            if(!med.getAgnd().getFolga().contains(cal.diaSemanaInt(cal.dataDia(data.getDayOfMonth(),data.getMonthValue(),data.getYear()))) && horariosMedico.contains(horario)){
                custo=med.getCustoConsulta()*getEspec().getMult();
                if(getPacIsEsp()){
                    if(getPacEsp().getIsEspecial()==false){
                        for(Desconto i : getPacEsp().getPlano().getDescontos()){
                            if(i.getEspec()==getEspec()){
                                desconto=custo*(i.getPorcentagem()/100d);
                                custo-=desconto;
                                break;
                            }
                        }
                    }
                    else{
                        for(Desconto i : getPacEsp().getPlanoEsp().getDescontos()){
                            if(i.getEspec()==getEspec()){
                                desconto=custo*(i.getPorcentagem()/100d);
                                custo-=desconto;
                                break;
                            }
                        }
                    }
                }
                opcoes.add(new OpcaoConsulta(opcoes.size()+" » "+med.getNome()+" - Custo: "+med.getCustoConsulta()+" × "+getEspec().getMult()+ (desconto!=0 ? " - "+desconto : "") +" = R$ "+String.format("%.2f",custo), custo, med));
            }
        }
        return opcoes;
    }

    public ArrayList<DataMarcada> datasDisponiveis(int mes, int ano,Calendario cal,AllLista lista){
        ArrayList<DataMarcada> disp=new ArrayList<DataMarcada>();
        ArrayList<Integer> horariosMed=new ArrayList<Integer>(); 
        Ano anoObj=cal.numAno(ano);
        int mesDuracao=anoObj.getMeses()[mes-1];
        int dia=0;
        int consultaNoDia=0;
        for(int i=1;i<=mesDuracao;i++){
            dia=cal.dataDia(i,mes,ano);
            medLoop: for(Medico med : lista.getMedicosL().getMedicos()){
                horariosMed=med.getAgnd().getInicioConsultas();
                if(!med.getEspec().equals(espec)){continue;}
                if(med.getAgnd().getFolga().contains(cal.diaSemanaInt(dia))){continue;}
                if(med.getHist().getConsultas().size()==0 && horariosMed.size()>0){
                    disp.add(new DataMarcada(dia,0));
                    break;
                }
                consultaNoDia=0;
                for(int j : horariosMed){
                    for(Consulta cons : med.getHist().getConsultas()){
                        if(cons.getPer().getDiaInicio()!=dia){continue;}
                        if(j==cons.getPer().getHorarioInicio() && cons.getStatus()=="Agendada"){
                            consultaNoDia++;
                        }
                    }
                }
                if(consultaNoDia<horariosMed.size()){
                    disp.add(new DataMarcada(dia,0));
                    break;
                }
            }
        }
        return disp;
    }
}
