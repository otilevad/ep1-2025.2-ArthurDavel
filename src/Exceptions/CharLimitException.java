package Exceptions;

public class CharLimitException extends Exception{
    public CharLimitException(String mensagem){
        super(mensagem);
    }
}
