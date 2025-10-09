package Repositorios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Calendario.*;
import Listas.AllLista;

public class InternacoesRep{
    private static final String arquivo="src/Dados/internacoes.txt";

    public static void salvaInternacoes(AllLista lista) throws IOException{
        try(PrintWriter escritor=new PrintWriter(new FileWriter(arquivo))){
            for(Internacao inter : lista.getInternacoesL().getInternacoes()){
                escritor.printf(inter.getDataEntrada()+";"+
                                inter.getDataSaida()+";"+
                                inter.getMed().getCrm()+";"+
                                (inter.getPacIsEsp() ? inter.getPacEsp().getCpf() : inter.getPac().getCpf())+";"+
                                inter.getSala().getNum()+";"+
                                inter.getCusto()+";"+
                                inter.getStatus()+"\n");
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void carregaInternacoes(AllLista lista) throws Exception{
        ArrayList<Internacao> interL=new ArrayList<Internacao>();
        try{
            BufferedReader leitor=new BufferedReader(new FileReader(arquivo));
            String str;
            while((str=leitor.readLine()) != null){
                interL.add(interStr(str,lista));
            }
            leitor.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        if(!interL.isEmpty()){
            lista.getInternacoesL().setInternacoes(interL);
            addInternacaoHistoricos(lista);
        }
    }

    public static Internacao interStr(String str, AllLista lista) throws Exception{
        String[] interDados=str.split(";");
        Internacao inter=new Internacao();
        inter.setDataEntrada(Integer.parseInt(interDados[0]));
        inter.setDataSaida(Integer.parseInt(interDados[1]));
        inter.setMed(lista.getMedicosL().buscaCrm(interDados[2]));
        if(lista.getPacientesL().cpfEspecial(interDados[3])){
            inter.setPacEsp(lista.getPacientesL().buscaCpfEsp(interDados[3]));
            inter.setPacIsEsp(true);
        }
        else{
            inter.setPac(lista.getPacientesL().buscaCpf(interDados[3]));
            inter.setPacIsEsp(false);
        }
        inter.setSala(lista.getInternacoesL().buscaNum(Integer.parseInt(interDados[4])));
        inter.setCusto(Double.parseDouble(interDados[5]));
        inter.setStatus(interDados[6]);
        return inter;
    }

    public static void addInternacaoHistoricos(AllLista lista){
        for(Internacao inter : lista.getInternacoesL().getInternacoes()){
            inter.getMed().getHist().getInternacoes().add(inter);
            inter.getPac().getHist().getInternacoes().add(inter);
        }
    }
}