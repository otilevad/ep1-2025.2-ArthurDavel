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
        MedicosRep.carregaMedicos(lista);
        ConsultasRep.carregaConsultas(lista);
        InternacoesRep.carregaInternacoes(lista);
    }

    public static void salvaArquivos(AllLista lista) throws Exception{
        EspecialidadesRep.salvaEspecialidades(lista);
        PlanosRep.salvaPlanos(lista);
        PlanosEspRep.salvaPlanosEsp(lista);
        PacientesRep.salvaPacientes(lista);
        PacientesEspRep.salvaPacientesEsp(lista);
        MedicosRep.salvaMedicos(lista);
        ConsultasRep.salvaConsultas(lista);
        InternacoesRep.salvaInternacoes(lista);
    }

    public static String[] setDir(){
        return new String[]{
            "Dados/especialidades.txt",
            "Dados/planos.txt",
            "Dados/planosEspeciais.txt",
            "Dados/pacientes.txt",
            "Dados/pacientesEspeciais.txt",
            "Dados/medicos.txt",
            "Dados/consultas.txt",
            "Dados/internacoes.txt"
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
