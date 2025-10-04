package Utilitarios.Calendario;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;
import java.util.Properties;
import Utilitarios.*;

public class RodaCalendario {
    public static void main (String[] args) throws Exception {
        Scanner sc = new Scanner(System.in).useLocale(Locale.US);
        Calendario cal = new Calendario();
        cal.calendarioSetup();
        int mesAgr=1;
        int anoAgr=2025;
        String input="";
        Misc.limpaTela();
        whileTrue: while(true){
            int calPad=0;
            int horPad=calPad+36;
            Misc.savePos();
            cal.mostraMes(mesAgr,anoAgr,calPad);
            Misc.gotoSavedPos();
            cal.mostraHorario(30,cal.tempoMinuto(00,00),cal.tempoMinuto(24,00),horPad,8);
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
                DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
                String dataString = sc.nextLine(); // Exemplo de data inválida
                LocalDate data=null;
                try {
                    if(LocalDate.parse(dataString, formatador).getYear()>2026 || LocalDate.parse(dataString, formatador).getYear()<2025){
                        throw new DateTimeException("Ano inválido");
                    }
                    data = LocalDate.parse(dataString, formatador);
                    cal.getDatasM().add(new DataMarcada(cal.dataDia(data.getDayOfMonth(),data.getMonthValue(),data.getYear()),0));
                    mesAgr=data.getMonthValue();
                    anoAgr=data.getYear();
                    System.out.println(data.format(formatador).toString() +" "+LocalDate.now().format(formatador).toString());
                } catch (DateTimeParseException e) {
                    System.out.println("Por favor, digite a data neste formato ==> dd/MM/yyyy: " + e.getMessage());
                } catch (DateTimeException e){
                    System.out.println(e.getMessage());
                }
                break;
            case "e":

                break whileTrue;
            }
        }
        sc.close();
    }
}
