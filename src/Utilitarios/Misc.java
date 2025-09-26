package Utilitarios;

public class Misc {
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
    
    public static String stringNum(String str,int num){
        String string="";
        for(int i=0;i<num;i++){
            string=string.concat(str);
        }
        return string;
    }
}
