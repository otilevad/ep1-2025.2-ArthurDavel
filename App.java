import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class App {
    public static void main (String[] args) throws Exception {
        ArrayList<Menu> menus=Setup.criaMenus();
        Scanner sc = new Scanner(System.in).useLocale(Locale.US);
        int menuSelecionado=0; //id do menu
        while(true){
            Menu menuAtual=menus.get(Setup.procuraMenu(menus, menuSelecionado)); //ordem do menu no arraylist
            int opt=menuAtual.mostraMenu(menuAtual.getOpts());
        }
    }
}
