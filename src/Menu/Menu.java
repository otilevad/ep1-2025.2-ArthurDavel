package Menu;

import java.util.ArrayList;
import java.util.Scanner;

import Exceptions.*;
import Listas.*;
import Utilitarios.*;
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
    
    public void mostraMenu(ArrayList<Opcao> opts, int pad) throws Exception{
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

    public static ArrayList<Comando> inputMenu(ArrayList<Comando> comandos,boolean centered, int writePad,Scanner sc,AllLista lista) throws Exception{
        int telaTam=Misc.getTamanhoTela();
        int erroNum=0;
        int pad=0;
        ArrayList<String> opcoesStrings=stringArrayRep(lista,"");
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
        int prevOptsWipeCol=0;
        Misc.gotoSavedPos();
        for(Comando cmd : inputs){
            erroNum=0;
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
                opcoesStrings=stringArrayRep(lista,cmd.getDado());
                maiorOpcaoTam=tamMaiorString(opcoesStrings);
                if(!opcoesStrings.isEmpty()){
                    switch(cmd.getDado()){
                        case "plano de saúde":
                            minWrite=0;
                            avisoOpcoes="Caso não tenha, deixe vazio.";
                            break;
                        case "descontos":
                            avisoOpcoes="Siga o formato: especialidade(id)/desconto(%),";
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
                    prevOptsWipeCol=titleOpcoesFormatado.length();
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
                else{
                    Misc.resetSetPos(0,0);
                    Misc.limpaArea(pad+comandoPad+writePad+1,prevOptsWipeCol,inputs.size()*2+2," ");
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
                            InputCheck.cpfExistsCheck(str, lista);
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
                            InputCheck.crmExistsCheck(str,lista);
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
                        case "descontos":
                            InputCheck.descCheck(str,lista);
                            break;
                        case "internacaogratis":
                            InputCheck.simNaoCheck(str);
                            str.toLowerCase();
                            break;
                        case "tempo médio":
                            InputCheck.numberCheck(str);
                            InputCheck.intCheck(str);
                            num=Integer.parseInt(str);
                            InputCheck.intervaloCheck(num, 15, 120);
                            break;
                        case "folga":
                            InputCheck.folgaCheck(str);
                            break;
                        case "horario":
                        case "intervalo":
                            InputCheck.horarioCheck(str);
                            break;
                        case "cpf consulta":
                        case "cpf internacao":
                            InputCheck.charLimitCheck(str,11,11);
                            InputCheck.numberCheck(str);
                            InputCheck.cpfNaoExistsCheck(str,lista);
                            break;
                        case "crm internacao":
                            InputCheck.charLimitCheck(str,8,8);
                            InputCheck.crmCheck(str);
                            InputCheck.crmNaoExistsCheck(str,lista);
                            break;
                        case "data entrada":
                        case "data saida":
                            InputCheck.dataCheck(str);
                            break;
                        case "num quarto":
                            InputCheck.numberCheck(str);
                            InputCheck.intCheck(str);
                            num=Integer.parseInt(str);
                            InputCheck.salasInternacoesCheck(num,lista.getInternacoesL());
                            break;
                        case "custo dia":
                            InputCheck.doubleCheck(str);
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
                    erroNum++;
                    if(erroNum>=5){
                        return null;
                    }else if(erroNum>=4){erro="Apenas mais uma tentativa.";
                    }else{erro=e.getMessage();}
                    if(str.length()>writePad){
                        extraWipe=str.length()-writePad;
                        if(telaTam!=0 && str.length()+pad+tamTabela>telaTam){
                            skipDesenhaTabela=false;
                            erro="Por favor, não ultrapasse os limites da tabela";
                        }
                    }
                }
                catch(Exception e){
                    erroNum++;
                    if(erroNum>=5){
                        return null;
                    }else if(erroNum>=4){erro="Apenas mais uma tentativa.";
                    }else{erro=e.getMessage();}
                }
            }
        }
        Misc.resetSetPos(pad,1+2*(inputs.size()));
        System.out.print(Misc.stringNum(" ",tamTotal));
        Misc.resetSetPos(pad,1+2*(inputs.size()));
        String ultima="Pressione Enter para continuar.";
        System.out.println(ultima);
        sc.nextLine();
        Misc.resetSetPos(pad,1+2*(inputs.size()));
        System.out.println(Misc.stringNum(" ", ultima.length()));
        Misc.resetSetPos(pad,1+2*(inputs.size()));
        return inputs;
    }

    public static ArrayList<String> stringArrayOpts(ArrayList<Opcao> opts){
        ArrayList<String> strArray=new ArrayList<String>();
        for(Opcao i : opts){
            strArray.add(i.getNome());
        }
        return strArray;
    }

    public static ArrayList<String> stringArrayRep(AllLista lista,String str){
        ArrayList<String> strArray=new ArrayList<String>();
        switch(str){
            case "especialidade":
                for(Especialidade i : lista.getEspecialidadesL().getEspecialidades()){
                    strArray.add(i.getNome());
                }
                break;
            case "plano de saúde":
                for(PlanoSaude i : lista.getPlanosL().getPlanos()){
                    strArray.add(i.getNome());
                }
                for(PlanoEspecial i : lista.getPlanosL().getPlanosEsp()){
                    strArray.add(i.getNome());
                }
                break;
            case "descontos":
                for(Especialidade i : lista.getEspecialidadesL().getEspecialidades()){
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