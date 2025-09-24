package Utilitarios.Calendario;

import java.util.Locale;
import java.util.Scanner;

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
        sc.close();
    }
}
