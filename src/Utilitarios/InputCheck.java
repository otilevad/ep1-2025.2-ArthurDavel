package Utilitarios;

import Exceptions.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputCheck {
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

    public static void idadeCheck(int idade) throws Exception{

        if(idade<0){

        }
    }
}
