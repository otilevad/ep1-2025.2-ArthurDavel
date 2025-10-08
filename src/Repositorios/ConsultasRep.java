package Repositorios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Entidades.Medico.Especialidade;
import Listas.AllLista;
import Utilitarios.Calendario.Consulta;
import Utilitarios.Calendario.Periodo;

public class ConsultasRep{
    private static final String arquivo="src/Dados/consultas.txt";

    public static void salvaConsultas(AllLista lista) throws IOException{
        try(PrintWriter escritor=new PrintWriter(new FileWriter(arquivo))){
            for(Consulta cons : lista.getConsultasL().getConsultas()){
                escritor.printf(cons.getPer().getDiaInicio()+";"+
                                cons.getPer().getHorarioInicio()+";"+
                                cons.getPer().getHorarioFim()+";"+
                                cons.getMed().getCrm()+";"+
                                (cons.getPacIsEsp() ? cons.getPacEsp().getCpf() : cons.getPac().getCpf())+";"+
                                cons.getEspec().getId()+";"+
                                cons.getValor()+";"+
                                cons.getStatus());
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void carregaConsultas(AllLista lista) throws Exception{
        ArrayList<Consulta> consL=new ArrayList<Consulta>();
        try{
            BufferedReader leitor=new BufferedReader(new FileReader(arquivo));
            String str;
            while((str=leitor.readLine()) != null){
                consL.add(consStr(str,lista));
            }
            leitor.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        if(!consL.isEmpty()){
            lista.getConsultasL().setConsultas(consL);
            addConsultaHistoricos(lista);
        }
    }

    public static Consulta consStr(String str, AllLista lista) throws Exception{
        String[] consDados=str.split(";");
        Consulta cons=new Consulta();
        cons.setPer(Periodo.periodoConsulta(Integer.parseInt(consDados[0]),Integer.parseInt(consDados[1]),Integer.parseInt(consDados[2])-Integer.parseInt(consDados[1])));
        cons.setMed(lista.getMedicosL().buscaCrm(consDados[3]));
        if(lista.getPacientesL().cpfEspecial(consDados[4])){
            cons.setPacEsp(lista.getPacientesL().buscaCpfEsp(consDados[4]));
            cons.setPacIsEsp(true);
        }
        else{
            cons.setPac(lista.getPacientesL().buscaCpf(consDados[4]));
            cons.setPacIsEsp(false);
        }
        cons.setEspec(Especialidade.buscaValorEspec(Integer.parseInt(consDados[5]),lista));
        cons.setValor(Double.parseDouble(consDados[6]));
        cons.setStatus(consDados[7]);
        return cons;
    }

    public static void addConsultaHistoricos(AllLista lista){
        for(Consulta cons : lista.getConsultasL().getConsultas()){
            cons.getMed().getHist().getConsultas().add(cons);
            cons.getPac().getHist().getConsultas().add(cons);
        }
    }
}
