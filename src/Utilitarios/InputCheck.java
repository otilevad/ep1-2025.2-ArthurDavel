package Utilitarios;

import Exceptions.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputCheck {
    public static boolean isIntOrLong(String str) {
        if (str == null) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        }
        catch (NumberFormatException e) {
            try {
                Long.parseLong(str);
                return true;
            }
            catch (NumberFormatException f) {
                return false;
            }
        }
    }

    public static boolean isInt(String str) {
        if (str == null) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    public static int posIntCheck(Scanner sc) throws InputMismatchException, Exception{
        int input=-1;
        try{
            input = sc.nextInt();
            sc.nextLine();
        }
        catch(InputMismatchException e){
            sc.next();
        }
        return input;
    }

    public static void intervaloCheck(int num, int min, int max) throws Exception{
        if(!(num>=min && num<=max)){
            throw new IntervaloInvException("Digite um valor entre "+min+" e "+max+".");
        }
    }

    public static void numberCheck(String str) throws Exception{
        if(!isIntOrLong(str)){
            throw new NumberException("A resposta deve conter apenas números.");
        }
    }

    public static void intCheck(String str) throws Exception{
        if(!isInt(str)){
            throw new NumberException("A resposta deve ser um int.");
        }
    }

    public static void charLimitCheck(String str,int min,int max) throws Exception{
        if(min==max && str.length()!=min){
            throw new CharLimitException("A resposta deve conter exatamente "+max+" "+(max>1?"caracteres":"caracter")+".");
        }
        if(str.length()>max){
            throw new CharLimitException("A resposta deve ter no máximo "+max+" "+(max>1?"caracteres":"caracter")+".");
        }
        if(str.length()<min){
            throw new CharLimitException("A resposta deve ter no mínimo "+min+" "+(min>1?"caracteres":"caracter")+".");
        }
    }

    public static void crmCheck(String str) throws Exception{
        if(!str.matches("^\\d{6}[A-Z]{2}")){
            throw new CrmInvException("O CRM deve seguir o padrão 123456DF.");
        }
    }
}
