package Menu;

import Entidades.*;
import java.util.ArrayList;
import java.util.Scanner;

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
                    mn.adcionaOpcoes(opts, "Cadastrar paciente", -3);
                    mn.adcionaOpcoes(opts, "Listar pacientes cadastrados", -4);
                    break;
                case 2: //Menu do Médico
                    mn.adcionaOpcoes(opts, "Cadastrar médico", -5);
                    mn.adcionaOpcoes(opts, "Listar médicos cadastrados", -6);
                    break;
                case 3: //Menu do Plano de saúde
                    mn.adcionaOpcoes(opts, "Cadastrar plano de saúde", -7);
                    mn.adcionaOpcoes(opts, "Listar plano de saúde", -8);
                    break;
            }
            if(mn.getId()!=0){ //Adiciona a opção retornar ao menu inicial, a menos que já esteja nele
                mn.adcionaOpcoes(opts, "Retornar ao menu inicial", 0);
            }
            mn.adcionaOpcoes(opts, "Sair", -2); //Adiciona a opção sair para todos os menus
        }
        return menus;
    }

    public static int criaAcoes(int opt, Lista listas, Scanner sc){
        int destino=0;
        switch(opt){
            case -3: //Cadastro de pacientes
                new Paciente().cadastrar(listas, sc);
                MenuSetup.limpaTela();
                destino=1;
                break;
            case -4: //Listar Pacientes
                listas.listarPacientes();
                sc.nextLine();
                destino=1;
                break;
            case -5: //Cadastro de médico
                new Medico().cadastrar(listas, sc);
                MenuSetup.limpaTela();
                destino=2;
                break;
            case -6: //Listar médicos
                listas.listarMedicos();
                sc.nextLine();
                destino=2;
                break;
            case -7: //Cadastro de plano de saúde
                destino=3;
                break;
            default:
                break;
        }
        return destino;
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

    public static void limpaTela(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void limpaLinha(int qtd){
        for(int i=0;i<qtd;i++){
            System.out.print("\033[1F\33[K");
        }
    }

    public static void limpaLinha(){
        System.out.print("\033[1F\33[K");
    }
}