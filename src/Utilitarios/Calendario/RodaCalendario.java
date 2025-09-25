package Utilitarios.Calendario;

import java.util.Locale;
import java.util.Scanner;
import Utilitarios.*;

public class RodaCalendario {
    public static void main (String[] args) throws Exception {
        Scanner sc = new Scanner(System.in).useLocale(Locale.US);
        Calendario cal = new Calendario();
        cal.calendarioSetup();
        int dia = sc.nextInt();
        sc.nextLine();
        int day=cal.diaData(dia,"dia");
        int mon=cal.diaData(dia,"mes");
        int year=cal.diaData(dia,"ano");
        System.out.println(cal.diaSemana(dia)+", "+cal.dataString(day,mon,year));
        System.out.println(cal.dataDia(day,mon,year));
        cal.mostraMesData(dia);
        System.out.println("");
        int mesAgr=1;
        int anoAgr=2025;
        String input="";
        Misc.limpaTela();
        whileTrue: while(true){
            cal.mostraMes(mesAgr,anoAgr);
            input=sc.nextLine();
            Misc.limpaTela();
            switch(input){
            case "a":
                if(mesAgr-1<1){
                    if(anoAgr-1>=cal.getAnos().get(0).getAno()){
                        mesAgr=12;
                        anoAgr--;
                    }
                }
                else{mesAgr--;}
                break;
            case "d":
                if(mesAgr+1>12){
                    if(anoAgr+1<=cal.getAnos().get(cal.getAnos().size()-1).getAno()){
                        mesAgr=1;
                        anoAgr++;
                    }
                }
                else{mesAgr++;}
                break;
            case "s":
                break whileTrue;
            }
        }
        sc.close();
    }
}
