package Menu;

import java.util.ArrayList;
import java.util.Scanner;

import Exceptions.*;
import Utilitarios.*;
import Repositorios.*;
import Entidades.Medico.*;
import Entidades.PlanoSaude.*;

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
    
    public void mostraMenu(ArrayList<Opcao> opts, int pad) {
        Misc.savePos();
        ArrayList<String> strArray=stringArrayOpts(opts);
        String menuTitle=("["+getNome()+"]");
        String menuSubtitle=("["+"Digite apenas o número"+"]");
        strArray.add(menuTitle);
        strArray.add(menuSubtitle);
        int tamMaior=tamMaiorString(strArray)+8;
        String totalTitleStr="┌"+Misc.stringNum("─",(tamMaior-menuTitle.length())/2)+menuTitle+Misc.stringNum("─",(tamMaior-menuTitle.length())/2)+"┐";
        System.out.println(Misc.setCol(pad)+totalTitleStr);
        String prevSubtitleStr="├"+Misc.stringNum("─",(tamMaior-menuSubtitle.length())/2)+menuSubtitle;
        System.out.println(Misc.setCol(pad)+prevSubtitleStr+Misc.stringNum("─",totalTitleStr.length()-prevSubtitleStr.length()-1)+"┤");
        for(int i = 0; i < opts.size(); i++) {
            String prevOpt="│ "+i+" » "+opts.get(i).getNome();
            System.out.println(Misc.setCol(pad)+prevOpt+Misc.setCol(totalTitleStr.length()-prevOpt.length()-1)+"│");
        }
        System.out.println(Misc.setCol(pad)+"└"+Misc.stringNum("─",totalTitleStr.length()-2)+"┘");
        Misc.resetSetPos(pad,3+opts.size());
    }

    public static ArrayList<Comando> inputMenu(ArrayList<Comando> comandos,int pad, int writePad,Scanner sc,AllRep rep) throws Exception{
        int telaTam=Misc.getTamanhoTela();
        ArrayList<String> opcoesStrings=stringArrayRep(rep,"");
        int maiorOpcaoTam=0;
        int opcaoPad=0;
        int minWrite=1;
        String avisoOpcoes="";
        boolean skipDesenhaTabela=false;
        ArrayList<Comando> inputs=new ArrayList<Comando>(comandos);
        int num=0;
        String str="";
        String erro="";
        int extraWipe=0;
        Misc.savePos();
        int tamTabela=0;
        for(Comando cmd : inputs){
            if(cmd.getComando().length()>tamTabela){
                tamTabela=cmd.getComando().length();
            }
        }
        int comandoPad=pad+tamTabela+2;
        int tamTotal=tamTabela+2+writePad+1;
        Misc.gotoSavedPos();
        for(Comando cmd : inputs){
            whileTrue: while(true){
                minWrite=1;
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
                System.out.print(Misc.stringNum(" ",tamTotal));
                if(erro!=""){
                    Misc.resetSetPos(pad,1+2*(inputs.size()));
                    System.out.print(erro);
                }

                //Imprime as opções, caso o comando seja compatível com opções
                opcoesStrings=stringArrayRep(rep,cmd.getDado());
                maiorOpcaoTam=tamMaiorString(opcoesStrings);
                if(!opcoesStrings.isEmpty()){
                    switch(cmd.getDado()){
                        case "plano de saúde":
                            minWrite=0;
                            avisoOpcoes="Caso não tenha, deixe vazio.";
                            break;
                        default:
                            avisoOpcoes="";
                            break;
                    }
                    int j=0;
                    int cols=1;
                    opcaoPad=0;
                    for(String i : opcoesStrings){
                        Misc.resetSetPos(pad+tamTotal+1+opcaoPad,j+1);
                        System.out.println(opcoesStrings.indexOf(i)+" » "+i);
                        j++;
                        if(j==inputs.size()*2-1){
                            opcaoPad+=maiorOpcaoTam+5;
                            j=0;
                            cols++;
                        }
                    }
                    String strTitleOpcoes="[Opções de "+cmd.getDado()+"]";
                    int meioTitleOpcoes=cols*((maiorOpcaoTam+5)/2)-strTitleOpcoes.length()/2;
                    Misc.resetSetPos(pad+comandoPad+writePad+1,0);
                    String titleOpcoesFormatado="┌"+Misc.stringNum("─",meioTitleOpcoes)+strTitleOpcoes+Misc.stringNum("─",meioTitleOpcoes)+"┐";
                    System.out.println(titleOpcoesFormatado);
                    for(int i=1;i<=inputs.size()*2-1;i++){
                        Misc.resetSetPos(pad+comandoPad+writePad+1,i);
                        System.out.println("│"+Misc.setCol(titleOpcoesFormatado.length()-2)+"│");
                    }
                    Misc.resetSetPos(pad+comandoPad+writePad+1,inputs.size()*2);
                    System.out.println("└"+Misc.stringNum("─",meioTitleOpcoes)+Misc.stringNum("─",strTitleOpcoes.length())+Misc.stringNum("─",meioTitleOpcoes)+"┘");
                    if(avisoOpcoes.length()>0){
                        Misc.resetSetPos(pad+comandoPad+writePad+1,inputs.size()*2+1);
                        System.out.println(avisoOpcoes);
                    }
                    else{
                        Misc.resetSetPos(pad+comandoPad+writePad+1,inputs.size()*2+1);
                        System.out.println(Misc.stringNum(" ", num));
                    }
                    Misc.gotoSavedPos();
                }

                Misc.resetSetPos(comandoPad,1+2*(inputs.indexOf(cmd)));
                try{
                    str=sc.nextLine();
                    if(minWrite!=0){InputCheck.vazioCheck(str);}
                    InputCheck.charLimitCheck(str,minWrite,writePad);
                    switch(cmd.getDado()){
                        case "nome":
                            InputCheck.alphabeticCheck(str);
                            break;
                        case "cpf":
                            InputCheck.charLimitCheck(str,11,11);
                            InputCheck.numberCheck(str);
                            InputCheck.cpfExistsCheck(str, rep);
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
                        case "especialidade":
                            num=InputCheck.optionListCheck(str,opcoesStrings);
                            break;
                        case "custo":
                            InputCheck.doubleCheck(str);
                            break;
                        case "plano de saúde":
                            num=InputCheck.optionListCheck(str,opcoesStrings);
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
        Misc.resetSetPos(pad,1+2*(inputs.size()));
        System.out.print(Misc.stringNum(" ",tamTotal));
        Misc.resetSetPos(pad,1+2*(inputs.size()));
        System.out.println("Pressione Enter para continuar.");
        sc.nextLine();
        return inputs;
    }

    public static ArrayList<String> stringArrayOpts(ArrayList<Opcao> opts){
        ArrayList<String> strArray=new ArrayList<String>();
        for(Opcao i : opts){
            strArray.add(i.getNome());
        }
        return strArray;
    }

    public static ArrayList<String> stringArrayRep(AllRep rep,String str){
        ArrayList<String> strArray=new ArrayList<String>();
        switch(str){
            case "especialidade":
                for(Especialidade i : rep.getEspecialidadesR().getEspecialidades()){
                    strArray.add(i.getNome());
                }
                break;
            case "plano de saúde":
                for(PlanoSaude i : rep.getPlanosR().getPlanos()){
                    strArray.add(i.getNome());
                }
                break;
            default:
                break;
        }
        return strArray;
    }

    public static int tamMaiorString(ArrayList<String> strs){
        int tam=0;
        if(!strs.isEmpty()){
            for(String i : strs){
                if(i.length()>tam){tam=i.length();}
            }
        }
        return tam;
    }
}