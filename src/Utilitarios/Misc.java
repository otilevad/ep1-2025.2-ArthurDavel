package Utilitarios;

public class Misc {
    public static boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
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
    
    public static String stringNum(String str,int num){
        String string="";
        for(int i=0;i<num;i++){
            string=string.concat(str);
        }
        return string;
    }

    public static String setCol(int col){
        if(col>0){return String.format("%c[%dC", 0x1B, col);}
        else{return "";}
    }

    public static String voltaCol(int col){
        if(col>0){return String.format("%c[%dD", 0x1B, col);}
        else{return "";}
    }

    public static String sobeLin(int lin){
        if(lin>0){return String.format("%c[%dA", 0x1B, lin);}
        else{return "";}
    }

    public static String setLin(int lin){
        if(lin>0){return String.format("%c[%dB", 0x1B, lin);}
        else{return "";}
    }

    public static void savePos(){
        System.out.print(String.format("%c[s", 0x1B));
    }

    public static void gotoSavedPos(){
        System.out.print(String.format("%c[u", 0x1B));
    }

    public static void gotoHome(){
        System.out.print(String.format("%c[H", 0x1B));
    }
}
