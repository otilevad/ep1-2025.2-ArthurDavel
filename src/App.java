import Menu.*;
import Utilitarios.*;
import Repositorios.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import Exceptions.*;

public class App {
    public static void main (String[] args) throws Exception {
        Scanner sc = new Scanner(System.in).useLocale(Locale.US);
        Misc.limpaTela();
        ArrayList<Menu> menus=MenuSetup.criaMenus();
        AllRep rep = new AllRep();
        int menuSelecionado=0; //id do menu
        int opt=0;
        Menu menuAtual=menus.get(MenuSetup.procuraMenu(menus, menuSelecionado));
        boolean skipInput=false;
        String obs="";
        String obsErro="";
        while(true){
            Misc.limpaTela();
            obs="";
            if(opt>=0){
                menuSelecionado=opt;
            }
            else if(opt==-2){
                System.out.println("Saindo...");
                break;
            }
            else if(opt==-1){
                obs=obsErro+"\n";
            }
            else{
                opt=MenuSetup.criaAcoes(opt, rep, sc);
                skipInput=true;
            }
            if(!skipInput){
                menuAtual=menus.get(MenuSetup.procuraMenu(menus, menuSelecionado)); //ordem do menu no arraylist
                menuAtual.mostraMenu(menuAtual.getOpts());
                System.out.print(obs);
                try{
                    opt = sc.nextInt();
                    sc.nextLine();
                    try{
                        InputCheck.intervaloCheck(opt,0,menuAtual.getOpts().size()-1);
                        opt=menuAtual.getOpts().get(opt).getDestino();
                    }
                    catch(IntervaloInvException e){
                        obsErro=e.getMessage();
                        opt=-1;
                    }
                }
                catch(InputMismatchException e){
                    obsErro="Por favor, digite um número.";
                    sc.next();
                    opt=-1;
                }
            }
            else{skipInput=false;}
        }
        sc.close();
    }
}
