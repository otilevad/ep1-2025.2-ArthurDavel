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
        System.out.println(cal.diaSemana(dia)+", "+cal.diaCalendario(dia));
        sc.close();
    }
}
