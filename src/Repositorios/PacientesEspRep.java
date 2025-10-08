package Repositorios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Entidades.Paciente.*;
import Listas.AllLista;

public class PacientesEspRep{
    private static final String arquivo="src/Dados/pacientesEspeciais.txt";

    public static void salvaPacientesEsp(AllLista lista) throws IOException{
        try(PrintWriter escritor=new PrintWriter(new FileWriter(arquivo))){
            for(PacienteEspecial pac : lista.getPacientesL().getPacientesEsp()){
                escritor.printf(pac.getNome()+";"+
                                pac.getCpf()+";"+
                                pac.getIdade()+";"+
                                (pac.getIsEspecial() ? pac.getPlanoEsp().getId() : pac.getPlano().getId())+"\n");
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void carregaPacientesEsp(AllLista lista) throws Exception{
        ArrayList<PacienteEspecial> pacsL=new ArrayList<PacienteEspecial>();
        try{
            BufferedReader leitor=new BufferedReader(new FileReader(arquivo));
            String str;
            while((str=leitor.readLine()) != null){
                pacsL.add(pacEspStr(str,lista));
            }
            leitor.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        if(!pacsL.isEmpty()){
            lista.getPacientesL().setPacientesEsp(pacsL);
        }
    }

    public static PacienteEspecial pacEspStr(String str, AllLista lista) throws Exception{
        String[] pacDados=str.split(";");
        PacienteEspecial pac=new PacienteEspecial();
        pac.setNome(pacDados[0]);
        pac.setCpf(pacDados[1]);
        pac.setIdade(Integer.parseInt(pacDados[2]));
        if(lista.getPlanosL().isPlanoEspecial(Integer.parseInt(pacDados[3]))){
           pac.setPlanoEsp(lista.getPlanosL().buscaIdEsp(Integer.parseInt(pacDados[3]))); 
           pac.setIsEspecial(true);
        }
        else{
            pac.setPlano(lista.getPlanosL().buscaId(Integer.parseInt(pacDados[3]))); 
            pac.setIsEspecial(false);
        }
        return pac;
    }
}
