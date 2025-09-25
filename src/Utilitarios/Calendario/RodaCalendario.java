package Utilitarios.Calendario;

import java.util.Locale;
import java.util.Scanner;

import Utilitarios.Calendario.Calendario.Mes;

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
        for(int i=1;i<13;i++){
            System.out.println(Mes.values()[i-1].toString());
            cal.mostraMes(i,2025);
        }
        sc.close();
    }
}
