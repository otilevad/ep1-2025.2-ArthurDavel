package Repositorios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Entidades.Medico.Especialidade;
import Entidades.PlanoSaude.Desconto;
import Entidades.PlanoSaude.PlanoEspecial;
import Listas.AllLista;

public class PlanosEspRep{
    private static final String arquivo="src/Dados/planosEspeciais.txt";

    public static void salvaPlanosEsp(AllLista lista) throws IOException{
        try(PrintWriter escritor=new PrintWriter(new FileWriter(arquivo))){
            for(PlanoEspecial planoEsp : lista.getPlanosL().getPlanosEsp()){
                escritor.printf(planoEsp.getNome()+";"+
                                planoEsp.descontosStr()+";"+
                                planoEsp.getId()+"\n");
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void carregaPlanosEsp(AllLista lista){
        ArrayList<PlanoEspecial> planosEspL=new ArrayList<PlanoEspecial>();
        try{
            BufferedReader leitor=new BufferedReader(new FileReader(arquivo));
            String str;
            while((str=leitor.readLine()) != null){
                planosEspL.add(planoEspStr(str,lista));
            }
            leitor.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        if(!planosEspL.isEmpty()){
            lista.getPlanosL().setPlanosEsp(planosEspL);
        }
    }

    public static PlanoEspecial planoEspStr(String str, AllLista lista){
        String[] planoDados=str.split(";");
        PlanoEspecial plano=new PlanoEspecial();
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
