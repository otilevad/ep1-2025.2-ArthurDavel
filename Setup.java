import java.util.ArrayList;

public class Setup {
    public static ArrayList<Menu> criaMenus() { 
        ArrayList<Menu> menus = new ArrayList<>();
        menus.add(new Menu("Menu Inicial", 0));
        menus.add(new Menu("Menu do Paciente", 1));
        for(int i = 0; i < menus.size(); i++) {
            Menu mn = menus.get(i);
            ArrayList<Opcao> opts = mn.iniciaOpcoes();
            switch(mn.getId()){
                case 0: //Menu Inicial
                mn.adcionaOpcoes(opts, "Sair", -2);
                mn.adcionaOpcoes(opts, "Menu do paciente", 1);
                break;
                case 1: //Menu do Paciente
                mn.adcionaOpcoes(opts, "Retornar ao menu inicial", 0);
                break;
            }
        }
        return menus;
    }
    public static int procuraMenu(ArrayList<Menu> menus, int menuId){
        int menuOrder=0;
        for(int i = 0; i < menus.size(); i++) {
            Menu mn = menus.get(i);
            if(mn.getId()==menuId){
                menuOrder=i;
                break;
            }
        }
        return menuOrder;
    }
}
