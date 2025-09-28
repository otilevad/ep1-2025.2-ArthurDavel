package Menu;

import java.util.ArrayList;
import java.util.Scanner;

import Exceptions.*;
import Utilitarios.*;

public class Menu {
    private String nome;
    private int id;
    private ArrayList<Opcao> opts;

    public Menu() {
        this.nome="";
        this.id=0;
    }
    public Menu(String nome, int id) {
        this.nome=nome;
        this.id=id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome=nome;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id=id;
    }

    public ArrayList<Opcao> getOpts() {
        return this.opts;
    }

    public void setOpts(ArrayList<Opcao> opts) {
        this.opts=opts;
    }

    public ArrayList<Opcao> iniciaOpcoes() {
        ArrayList<Opcao> novoOpts = new ArrayList<>();
        setOpts(novoOpts);
        return opts;
    }

    public void adcionaOpcoes(ArrayList<Opcao> opts, String nome, int destino) {
        opts.add(new Opcao(nome, destino));
    }
    
    public void mostraMenu(ArrayList<Opcao> opts) {
        System.out.println("-----"+getNome()+"-----");
        System.out.println("Selecione a opção desejada:");
        for(int i = 0; i < opts.size(); i++) {
            System.out.println(i+" ===> "+opts.get(i).getNome());
        }
    }

    public static ArrayList<Comando> inputMenu(ArrayList<Comando> comandos,int pad, int writePad,Scanner sc) throws Exception{
        ArrayList<Comando> inputs=new ArrayList<Comando>(comandos);
        Misc.savePos();
        int tamTabela=0;
        for(Comando cmd : inputs){
            if(cmd.getComando().length()>tamTabela){
                tamTabela=cmd.getComando().length();
            }
        }
        int comandoPad=pad+tamTabela+2;
        System.out.println( Misc.setCol(pad)+"┌"+Misc.stringNum("─",tamTabela)+"┬"+
                            Misc.stringNum("─",writePad)+"┐");
        for(Comando cmd : inputs){ //Desenha a tabela
            System.out.println( Misc.setCol(pad)+"│"+cmd.getComando()+Misc.setCol(tamTabela-cmd.getComando().length())+
                                "│"+Misc.setCol(writePad)+"│");
            System.out.println( Misc.setCol(pad)+(inputs.indexOf(cmd)!=inputs.size()-1 ? "├" : "└")+
                                Misc.stringNum("─",tamTabela)+
                                (inputs.indexOf(cmd)!=inputs.size()-1 ? "┼" : "┴")+
                                Misc.stringNum("─",writePad)+
                                (inputs.indexOf(cmd)!=inputs.size()-1 ? "┤" : "┘"));
        }
        Misc.gotoSavedPos();
        int num=0;
        String str="";
        String erro="";
        for(Comando cmd : inputs){
            whileTrue: while(true){
                Misc.resetSetPos(comandoPad,1+2*(inputs.indexOf(cmd)));
                System.out.print(Misc.stringNum(" ",writePad)+"│");
                Misc.resetSetPos(pad,1+2*(inputs.size()));
                System.out.print(Misc.stringNum(" ",writePad*2));
                if(erro!=""){
                    Misc.resetSetPos(pad,1+2*(inputs.size()));
                    System.out.print(erro);
                }
                Misc.resetSetPos(comandoPad,1+2*(inputs.indexOf(cmd)));
                try{
                    str=sc.nextLine();
                    switch(cmd.getDado()){
                        case "nome":
                            InputCheck.charLimitCheck(str,1,writePad);
                            break;
                        case "cpf":
                            InputCheck.charLimitCheck(str,11,11);
                            InputCheck.numberCheck(str);
                            break;
                        case "idade":
                            InputCheck.numberCheck(str);
                            InputCheck.intCheck(str);
                            num=Integer.parseInt(str);
                            InputCheck.intervaloCheck(num, 0, 120);
                            break;
                    }
                    cmd.setValorInt(num);
                    cmd.setValorStr(str);
                    erro="";
                    Misc.resetSetPos(comandoPad,1+2*(inputs.indexOf(cmd)));
                    System.out.print(Misc.setCol(writePad)+"│");
                    break whileTrue;
                }
                catch(Exception e){
                    erro=e.getMessage();
                }
            }
        }
        return inputs;
    }
}