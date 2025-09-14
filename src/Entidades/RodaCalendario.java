package Entidades;

import java.util.Locale;
import java.util.Scanner;

public class RodaCalendario {
    public static void main (String[] args) throws Exception {
        Scanner sc = new Scanner(System.in).useLocale(Locale.US);
        Calendario Cal = new Calendario();
        int dia = sc.nextInt();
        sc.nextLine();
        System.out.println(Cal.diaCalendario(dia));
        sc.close();
    }
}
