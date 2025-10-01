package Utilitarios;

import Exceptions.*;
import Repositorios.AllRep;
import Utilitarios.*;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Entidades.Paciente.*;

import java.util.ArrayList;

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

    public static boolean isDouble(String str) {
        if (str == null) {
            return false;
        }
        try {
            Double.parseDouble(str);
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

    public static void alphabeticCheck(String str) throws Exception{
        if(!str.matches("^[\\p{L}\\s]*$")){
            throw new AlphabeticException("A resposta deve conter apenas letras e espaços.");
        }
    }

    public static void vazioCheck(String str) throws Exception{
        if(str.matches("^\\s*$")){
            throw new CharLimitException("A resposta não pode estar vazia.");
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

    public static void cpfExistsCheck(String str, AllRep rep) throws Exception{
        if(!rep.getPacientesR().getPacientes().isEmpty()){
            for(Paciente i : rep.getPacientesR().getPacientes()){
                if(Long.parseLong(str)==Long.parseLong(i.getCpf())){
                    throw new CpfExistsException("Já existe um paciente com este CPF cadastrado.");
                }
            }
        }
        if(!rep.getPacientesR().getPacientesEsp().isEmpty()){
            for(PacienteEspecial j : rep.getPacientesR().getPacientesEsp()){
                if(Long.parseLong(str)==Long.parseLong(j.getCpf())){
                    throw new CpfExistsException("Já existe um paciente (especial) com este CPF cadastrado.");
                }
            }
        }
    }

    public static void doubleCheck(String str) throws Exception{
        if(!isDouble(str)){
            throw new NumberException("A resposta deve ser um número (. para separar a parte decimal).");
        }
    }

    public static int optionListCheck(String str, ArrayList<String> opcoesStrings) throws Exception{
        int numOpt=opcoesStrings.size();
        if(str.length()==0){
            return -1;
        }
        if(!((isInt(str) && Integer.parseInt(str)>=0 && Integer.parseInt(str)<=numOpt-1) || (opcoesStrings.contains(Misc.formataStrProprio(str))))){
            throw new OptionsInvException("Por favor, digite a opção ou o número da opção.");
        }
        else if(InputCheck.isInt(str)){
            return Integer.parseInt(str);
        }
        else{
            return opcoesStrings.indexOf(Misc.formataStrProprio(str));
        }
    }
}
