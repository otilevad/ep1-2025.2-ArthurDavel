package Repositorios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Entidades.Paciente.*;
import Listas.AllLista;

public class PacientesRep{
    private static final String arquivo="src/Dados/pacientes.txt";

    public static void salvaPacientes(AllLista lista) throws IOException{
        try(PrintWriter escritor=new PrintWriter(new FileWriter(arquivo))){
            for(Paciente pac : lista.getPacientesL().getPacientes()){
                escritor.printf(pac.getNome()+";"+
                                pac.getCpf()+";"+
                                pac.getIdade()+"\n");
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void carregaPacientes(AllLista lista){
        ArrayList<Paciente> pacsL=new ArrayList<Paciente>();
        try{
            BufferedReader leitor=new BufferedReader(new FileReader(arquivo));
            String str;
            while((str=leitor.readLine()) != null){
                pacsL.add(pacStr(str));
            }
            leitor.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        if(!pacsL.isEmpty()){
            lista.getPacientesL().setPacientes(pacsL);
        }
    }

    public static Paciente pacStr(String str){
        String[] pacDados=str.split(";");
        Paciente pac=new Paciente();
        pac.setNome(pacDados[0]);
        pac.setCpf(pacDados[1]);
        pac.setIdade(Integer.parseInt(pacDados[2]));
        return pac;
    }
}
