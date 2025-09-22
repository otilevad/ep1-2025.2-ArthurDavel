import Menu.*;
import Utilitarios.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class App {
    public static void main (String[] args) throws Exception {
        Scanner sc = new Scanner(System.in).useLocale(Locale.US);
        Misc.limpaTela();
        ArrayList<Menu> menus=MenuSetup.criaMenus();
        Lista listas = new Lista();
        int menuSelecionado=0; //id do menu
        int opt=0;
        Menu menuAtual=menus.get(MenuSetup.procuraMenu(menus, menuSelecionado));
        boolean skipInput=false;
        while(true){
            Misc.limpaTela();
            if(opt>=0){
                menuSelecionado=opt;
            }
            else if(opt==-2){
                System.out.println("Saindo...");
                break;
            }
            else if(opt==-1){
                System.out.println("Por favor, digite uma opção válida.");
            }
            else{
                opt=MenuSetup.criaAcoes(opt, listas, sc);
                skipInput=true;
            }
            if(!skipInput){
                menuAtual=menus.get(MenuSetup.procuraMenu(menus, menuSelecionado)); //ordem do menu no arraylist
                menuAtual.mostraMenu(menuAtual.getOpts());
                try{
                    opt = sc.nextInt();
                    sc.nextLine();
                }
                catch(InputMismatchException e){
                    sc.next();
                    opt=-1;
                }
                if(opt>=0 && opt<menuAtual.getOpts().size()){
                    opt=menuAtual.getOpts().get(opt).getDestino();
                }
                else{opt=-1;}
            }
            else{skipInput=false;}
        }
        sc.close();
    }
}
