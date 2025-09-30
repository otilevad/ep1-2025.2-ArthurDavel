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
        this.opts=new ArrayList<Opcao>();
    }

    public Menu(String nome, int id) {
        this.nome=nome;
        this.id=id;
        this.opts=new ArrayList<Opcao>();
    }

    public Menu(String nome, int id, ArrayList<Opcao> opts) {
        this.nome=nome;
        this.id=id;
        this.opts=opts;
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
        int telaTam=Misc.getTamanhoTela();
        boolean skipDesenhaTabela=false;
        ArrayList<Comando> inputs=new ArrayList<Comando>(comandos);
        Misc.savePos();
        int tamTabela=0;
        for(Comando cmd : inputs){
            if(cmd.getComando().length()>tamTabela){
                tamTabela=cmd.getComando().length();
            }
        }
        int comandoPad=pad+tamTabela+2;
        Misc.gotoSavedPos();
        int num=0;
        String str="";
        String erro="";
        int extraWipe=0;
        for(Comando cmd : inputs){
            whileTrue: while(true){
                Misc.resetSetPos(comandoPad,1+2*(inputs.indexOf(cmd)));
                System.out.print(Misc.stringNum(" ",writePad)+"│"+Misc.stringNum(" ",extraWipe));
                extraWipe=0;
                if(!skipDesenhaTabela){
                    Misc.gotoSavedPos();
                    System.out.println( Misc.setCol(pad)+"┌"+Misc.stringNum("─",tamTabela)+"┬"+
                                        Misc.stringNum("─",writePad)+"┐");
                    for(Comando cmdTab : inputs){ //Desenha a tabela
                        System.out.println( Misc.setCol(pad)+"│"+cmdTab.getComando()+Misc.setCol(tamTabela-cmdTab.getComando().length())+
                                            "│"+Misc.setCol(writePad)+"│");
                        System.out.println( Misc.setCol(pad)+(inputs.indexOf(cmdTab)!=inputs.size()-1 ? "├" : "└")+
                                            Misc.stringNum("─",tamTabela)+
                                            (inputs.indexOf(cmdTab)!=inputs.size()-1 ? "┼" : "┴")+
                                            Misc.stringNum("─",writePad)+
                                            (inputs.indexOf(cmdTab)!=inputs.size()-1 ? "┤" : "┘"));
                    }
                    skipDesenhaTabela=true;
                }
                Misc.resetSetPos(pad,1+2*(inputs.size()));
                System.out.print(Misc.stringNum(" ",writePad*2));
                if(erro!=""){
                    Misc.resetSetPos(pad,1+2*(inputs.size()));
                    System.out.print(erro);
                }
                Misc.resetSetPos(comandoPad,1+2*(inputs.indexOf(cmd)));
                try{
                    str=sc.nextLine();
                    InputCheck.charLimitCheck(str,1,writePad);
                    switch(cmd.getDado()){
                        case "nome":
                            InputCheck.alphabeticCheck(str);
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
                        case "crm":
                            InputCheck.charLimitCheck(str,8,8);
                            InputCheck.crmCheck(str);
                            break;
                    }
                    cmd.setValorInt(num);
                    cmd.setValorStr(str);
                    erro="";
                    Misc.resetSetPos(comandoPad,1+2*(inputs.indexOf(cmd)));
                    System.out.print(Misc.setCol(writePad)+"│");
                    break whileTrue;
                }
                catch(CharLimitException e){
                    erro=e.getMessage();
                    if(str.length()>writePad){
                        extraWipe=str.length()-writePad;
                        if(telaTam!=0 && str.length()+pad+tamTabela>telaTam){
                            skipDesenhaTabela=false;
                            erro="Por favor, não ultrapasse os limites da tabela";
                        }
                    }
                    
                }
                catch(Exception e){
                    erro=e.getMessage();
                }
            }
        }
        return inputs;
    }
}