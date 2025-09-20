package Calendario;

import java.util.Locale;
import java.util.Scanner;

public class RodaCalendario {
    public static void main (String[] args) throws Exception {
        Scanner sc = new Scanner(System.in).useLocale(Locale.US);
        Ano Cal = new Ano();
        int dia = sc.nextInt();
        sc.nextLine();
        System.out.println(Cal.diaCalendario(dia));
        sc.close();
    }
}
