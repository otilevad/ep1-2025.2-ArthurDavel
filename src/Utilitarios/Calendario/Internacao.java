package Utilitarios.Calendario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import Entidades.Medico.Medico;
import Entidades.Paciente.Paciente;
import Entidades.Paciente.PacienteEspecial;
import Entidades.Sala.SalaInternacao;
import Listas.AllLista;
import Listas.InternacoesLista;
import Menu.Comando;
import Menu.Menu;
import Utilitarios.InputCheck;

public class Internacao{
    private Paciente pac;
    private PacienteEspecial pacEsp;
    private boolean pacIsEsp;
    private Medico med;
    private SalaInternacao sala;
    private int dataEntrada;
    private int dataSaida;
    private double custo;
    private String status;
    private ArrayList<Comando> comandos;

    public Internacao(){
        this.pac=new Paciente();
        this.pacEsp=new PacienteEspecial();
        this.pacIsEsp=false;
        this.med=new Medico();
        this.sala=new SalaInternacao();
        this.dataEntrada=0;
        this.dataSaida=0;
        this.custo=0d;
        this.status="Agendada";
        this.comandos=new ArrayList<Comando>();
        addComandos();
    }

    public Internacao(Paciente pac,PacienteEspecial pacEsp,boolean pacIsEsp,Medico med,SalaInternacao sala,int dataEntrada,int dataSaida,double custo,String status,ArrayList<Comando> comandos){
        this.pac=pac;
        this.pacEsp=pacEsp;
        this.pacIsEsp=pacIsEsp;
        this.med=med;
        this.sala=sala;
        this.dataEntrada=dataEntrada;
        this.dataSaida=dataSaida;
        this.custo=custo;
        this.status=status;
        this.comandos=comandos;
    }

    public Paciente getPac() {
        return this.pac;
    }

    public void setPac(Paciente pac) {
        this.pac = pac;
        setPacIsEsp(false);
    }

    public PacienteEspecial getPacEsp() {
        return this.pacEsp;
    }

    public void setPacEsp(PacienteEspecial pacEsp) {
        this.pacEsp = pacEsp;
        setPacIsEsp(true);
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

    public SalaInternacao getSala() {
        return this.sala;
    }

    public void setSala(SalaInternacao sala) {
        this.sala = sala;
    }

    public int getDataEntrada() {
        return this.dataEntrada;
    }

    public void setDataEntrada(int dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public int getDataSaida() {
        return this.dataSaida;
    }

    public void setDataSaida(int dataSaida) {
        this.dataSaida = dataSaida;
    }

    public ArrayList<Comando> getComandos() {
        return this.comandos;
    }

    public void setComandos(ArrayList<Comando> comandos) {
        this.comandos = comandos;
    }

    public double getCusto() {
        return this.custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void addComandos(){
        getComandos().add(new Comando("cpf internacao", "String", "CPF do paciente: "));
        getComandos().add(new Comando("crm internacao", "String", "CRM do médico: "));
        getComandos().add(new Comando("data entrada", "String", "Data de entrada: "));
        getComandos().add(new Comando("data saida", "String", "Data de saída prevista: "));
        getComandos().add(new Comando("num quarto", "int", "Número do quarto: "));
        getComandos().add(new Comando("custo dia", "String", "Custo por dia: "));
    }

    public void agendar(Scanner sc, AllLista lista, Calendario cal) throws Exception{
        ArrayList<Comando> input=Menu.inputMenu(getComandos(), false, 35, sc, lista);
        if(input!=null){
            setComandos(input);
            String cpfPac=Comando.buscaPorDado("cpf internacao",getComandos()).getValorStr();
            if(lista.getPacientesL().cpfEspecial(cpfPac)){
                setPacIsEsp(true);
                setPacEsp(lista.getPacientesL().buscaCpfEsp(cpfPac)); 
            }
            else{
                setPacIsEsp(false);
                setPac(lista.getPacientesL().buscaCpf(cpfPac)); 
            }
            LocalDate dataSelecionada=InputCheck.dataCheck(Comando.buscaPorDado("data entrada",getComandos()).getValorStr());
            int dataEntradaNum=cal.dataDia(dataSelecionada.getDayOfMonth(),dataSelecionada.getMonthValue(),dataSelecionada.getYear());
            setDataEntrada(dataEntradaNum);
            dataSelecionada=InputCheck.dataCheck(Comando.buscaPorDado("data saida",getComandos()).getValorStr());
            int dataSaidaNum=cal.dataDia(dataSelecionada.getDayOfMonth(),dataSelecionada.getMonthValue(),dataSelecionada.getYear());
            setDataSaida(dataSaidaNum);
            setMed(lista.getMedicosL().buscaCrm(Comando.buscaPorDado("crm internacao",getComandos()).getValorStr()));
            SalaInternacao sala=lista.getInternacoesL().buscaNum(Comando.buscaPorDado("num quarto",getComandos()).getValorInt());
            setSala(sala);
            for(Internacao i : (getPacIsEsp() ? getPacEsp().getHist().getInternacoes() : getPac().getHist().getInternacoes())){
                if( (i.getDataEntrada()>=getDataEntrada() && i.getDataSaida()<=getDataSaida()) ||
                    (i.getDataEntrada()<=getDataEntrada() && i.getDataSaida()>=getDataSaida())){
                    throw new Exception("Paciente já internado nesta data.");
                }
            }
            for(Periodo i : sala.getOcupado()){
                if( (i.getDiaInicio()<=dataEntradaNum && i.getDiaFim()>=dataSaidaNum) || 
                    (i.getDiaInicio()>=dataEntradaNum && i.getDiaFim()<=dataSaidaNum)){
                    throw new Exception("O quarto está ocupado nesta data.");
                }
            }
            if(dataEntradaNum>=dataSaidaNum){
                throw new Exception("A data de entrda deve ser anterior à de saída.");
            }
            setCusto((dataSaidaNum-dataEntradaNum+1)*Double.parseDouble(Comando.buscaPorDado("custo dia",getComandos()).getValorStr()));
            if(getPacIsEsp()){
                if(getPacEsp().getIsEspecial()){
                    setCusto(0d);
                }
            }
            System.out.println("Custo: R$ "+String.format("%.2f",custo));
            lista.getInternacoesL().getInternacoes().add(this);
            getMed().getHist().getInternacoes().add(this);
            if(getPacIsEsp()){
                getPacEsp().getHist().getInternacoes().add(this);
            }
            else{
                getPac().getHist().getInternacoes().add(this);
            }
        }
    }
}
