package Repositorios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Entidades.Medico.Especialidade;
import Entidades.PlanoSaude.Desconto;
import Entidades.PlanoSaude.PlanoSaude;
import Listas.AllLista;

public class PlanosRep{
    private static final String arquivo="src/Dados/planos.txt";

    public static void salvaPlanos(AllLista lista) throws IOException{
        try(PrintWriter escritor=new PrintWriter(new FileWriter(arquivo))){
            for(PlanoSaude plano : lista.getPlanosL().getPlanos()){
                escritor.printf(plano.getNome()+";"+
                                plano.descontosStr()+";"+
                                plano.getId()+"\n");
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void carregaPlanos(AllLista lista){
        ArrayList<PlanoSaude> planosL=new ArrayList<PlanoSaude>();
        try{
            BufferedReader leitor=new BufferedReader(new FileReader(arquivo));
            String str;
            while((str=leitor.readLine()) != null){
                planosL.add(planoStr(str,lista));
            }
            leitor.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        if(!planosL.isEmpty()){
            lista.getPlanosL().setPlanos(planosL);
        }
    }

    public static PlanoSaude planoStr(String str, AllLista lista){
        String[] planoDados=str.split(";");
        PlanoSaude plano=new PlanoSaude();
        plano.setNome(planoDados[0]);
        ArrayList<Desconto> descs=new ArrayList<Desconto>();
        if(planoDados[1].contains("/")){
            if(planoDados[1].contains(",")){
                String[] descsStr=planoDados[1].split(",");
                for(String i : descsStr){
                    String[] info=i.split("/");
                    descs.add(new Desconto(Double.parseDouble(info[1]),Especialidade.buscaValorEspec(Integer.parseInt(info[0]), lista)));
                }
            }
            else{
                String[] info=planoDados[1].split("/");
                descs.add(new Desconto(Double.parseDouble(info[1]),Especialidade.buscaValorEspec(Integer.parseInt(info[0]), lista)));
            }
        }
        plano.setDescontos(descs);
        plano.setId(Integer.parseInt(planoDados[2]));
        return plano;
    }
}
