package Repositorios;

import java.io.File;
import java.io.IOException;

import Listas.AllLista;

public class MiscRep{
    public static void carregaArquivos(AllLista lista) throws Exception{
        EspecialidadesRep.carregaEspecialidades(lista);
        PlanosRep.carregaPlanos(lista);
        PlanosEspRep.carregaPlanosEsp(lista);
        PacientesRep.carregaPacientes(lista);
        PacientesEspRep.carregaPacientesEsp(lista);
    }

    public static void salvaArquivos(AllLista lista) throws Exception{
        EspecialidadesRep.salvaEspecialidades(lista);
        PlanosRep.salvaPlanos(lista);
        PlanosEspRep.salvaPlanosEsp(lista);
        PacientesRep.salvaPacientes(lista);
        PacientesEspRep.salvaPacientesEsp(lista);
    }

    public static String[] setDir(){
        return new String[]{
            "src/Dados/especialidades.txt",
            "src/Dados/planos.txt",
            "src/Dados/planosEspeciais.txt",
            "src/Dados/pacientes.txt",
            "src/Dados/pacientesEspeciais.txt",
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
