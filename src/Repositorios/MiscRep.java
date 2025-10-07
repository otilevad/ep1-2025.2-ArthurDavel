package Repositorios;

import java.io.File;
import java.io.IOException;

public class MiscRep{
    public static String[] setDir(){
        return new String[]{
            "src/Dados/especialidades.txt",
            "src/Dados/pacientes.txt"
        };
    }

    public static void criaArquivos(String[] arquivos){
        File arquivo;
        for(String str : arquivos){
            arquivo=new File(str);
            try{arquivo.createNewFile();}
            catch (IOException e){
                System.err.println("Erro ao criar "+arquivo+":"+ e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
