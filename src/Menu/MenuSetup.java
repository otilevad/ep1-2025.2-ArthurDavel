package Menu;

import java.util.ArrayList;

public class MenuSetup {
    public static ArrayList<Menu> criaMenus() { 
        ArrayList<Menu> menus = new ArrayList<>();
        menus.add(new Menu("Menu Inicial", 0));
        menus.add(new Menu("Menu do Paciente", 1));
        menus.add(new Menu("Menu do Médico", 2));
        menus.add(new Menu("Menu do Plano de Saúde", 3));
        menus.add(new Menu("Cadastro de Pacientes", 4));
        menus.add(new Menu("Cadastro de Médicos", 5));
        for(int i = 0; i < menus.size(); i++) {
            Menu mn = menus.get(i);
            ArrayList<Opcao> opts = mn.iniciaOpcoes();
            switch(mn.getId()){
                case 0: //Menu Inicial
                    mn.adcionaOpcoes(opts, "Menu do paciente", 1);
                    mn.adcionaOpcoes(opts, "Menu do médico", 2);
                    mn.adcionaOpcoes(opts, "Menu do plano de saúde", 3);
                    break;
                case 1: //Menu do Paciente
                    mn.adcionaOpcoes(opts, "Cadastrar paciente", 4);
                    break;
                case 2: //Menu do Médico
                    mn.adcionaOpcoes(opts, "Cadastrar médico", 5);
                    break;
                case 3: //Menu do Plano de saúde
                    
                    break;
                case 4: //Cadastro de pacientes
                    
                    break;
                case 5: //Cadastro de médicos
                    
                    break;
            }
            if(mn.getId()!=0){ //Adiciona a opção retornar ao menu inicial, a menos que já esteja nele
                mn.adcionaOpcoes(opts, "Retornar ao menu inicial", 0);
            }
            mn.adcionaOpcoes(opts, "Sair", -2); //Adiciona a opção sair para todos os menus
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

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}