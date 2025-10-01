package Exceptions;

public class CpfExistsException extends Exception{
    public CpfExistsException(String mensagem){
        super(mensagem);
    }
}