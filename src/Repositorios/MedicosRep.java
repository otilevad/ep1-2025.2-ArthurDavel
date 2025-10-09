package Repositorios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Entidades.Medico.Agenda;
import Entidades.Medico.Especialidade;
import Entidades.Medico.Medico;
import Listas.AllLista;

public class MedicosRep{
    private static final String arquivo="Dados/medicos.txt";

    public static void salvaMedicos(AllLista lista) throws IOException{
        try(PrintWriter escritor=new PrintWriter(new FileWriter(arquivo))){
            for(Medico med : lista.getMedicosL().getMedicos()){
                escritor.printf(med.getNome()+";"+
                                med.getCrm()+";"+
                                med.getEspec().getId()+";"+
                                med.getCustoConsulta()+";"+
                                med.getTempoMedio()+";"+
                                med.getAgnd().inicioConsultasStr()+";"+
                                med.getAgnd().folgaStr()+"\n");
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void carregaMedicos(AllLista lista) throws Exception{
        ArrayList<Medico> medL=new ArrayList<Medico>();
        try{
            BufferedReader leitor=new BufferedReader(new FileReader(arquivo));
            String str;
            while((str=leitor.readLine()) != null){
                medL.add(medStr(str,lista));
            }
            leitor.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        if(!medL.isEmpty()){
            lista.getMedicosL().setMedicos(medL);
        }
    }

    public static Medico medStr(String str, AllLista lista) throws Exception{
        String[] medDados=str.split(";");
        Medico med=new Medico();
        med.setNome(medDados[0]);
        med.setCrm(medDados[1]);
        med.setEspec(Especialidade.buscaValorEspec(Integer.parseInt(medDados[2]), lista));
        med.setCustoConsulta(Double.parseDouble(medDados[3]));
        med.setTempoMedio(Integer.parseInt(medDados[4]));
        Agenda agnd=new Agenda();
        if(medDados[5].contains(",")){
            String[] agndStr=medDados[5].split(",");
            for(String i : agndStr){
                agnd.getInicioConsultas().add(Integer.parseInt(i));
            }
        }
        if(medDados[6].contains(",")){
            String[] folgaStr=medDados[6].split(",");
            for(String i : folgaStr){
                agnd.getFolga().add(Integer.parseInt(i));
            }
        }
        agnd.setDuracao(med.getTempoMedio());
        med.setAgnd(agnd);
        return med;
    }
}
