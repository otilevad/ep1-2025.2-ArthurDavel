package Repositorios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import Entidades.Medico.Especialidade;
import Entidades.Sala.SalaConsulta;
import Listas.AllLista;

public class EspecialidadesRep{
    private static final String arquivo="src/Dados/especialidades.txt";

    public static void salvaEspecialidades(AllLista lista) throws IOException{
        try(PrintWriter escritor=new PrintWriter(new FileWriter(arquivo))){
            for(Especialidade espec : lista.getEspecialidadesL().getEspecialidades()){
                escritor.printf(espec.getNome()+";"+
                                espec.getMult()+";"+
                                espec.getQtdSalas()+";"+
                                espec.getId()+"\n");
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void carregaEspecialidades(AllLista lista){
        ArrayList<Especialidade> especsL=new ArrayList<Especialidade>();
        try{
            BufferedReader leitor=new BufferedReader(new FileReader(arquivo));
            String str;
            while((str=leitor.readLine()) != null){
                especsL.add(especStr(str));
            }
            leitor.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        if(!especsL.isEmpty()){
            lista.getEspecialidadesL().setEspecialidades(especsL);
        }
    }

    public static Especialidade especStr(String str){
        String[] especDados=str.split(";");
        Especialidade espec=new Especialidade(especDados[0],Double.parseDouble(especDados[1]),new ArrayList<SalaConsulta>(),Integer.parseInt(especDados[2]),Integer.parseInt(especDados[3]));
        return espec;
    }
}
