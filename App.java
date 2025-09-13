import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class App {
    public static void main (String[] args) throws Exception {
        ArrayList<Menu> menus=Setup.criaMenus();
        Scanner sc = new Scanner(System.in).useLocale(Locale.US);
        int menuSelecionado=0; //id do menu
        mainLoop: while(true){
            Menu menuAtual=menus.get(Setup.procuraMenu(menus, menuSelecionado)); //ordem do menu no arraylist
            int opt=menuAtual.mostraMenu(menuAtual.getOpts());
            if(opt>=0){
                menuSelecionado=opt;
            }
            else{
                switch(opt){
                    case -2:
                        System.out.println("Saindo...");
                    break mainLoop;
                    default:
                        System.out.println("Digite uma opção válida.");
                    break;
                }
            }
        }
    }
}
