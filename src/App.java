import Menu.*;
import Utilitarios.*;
import Repositorios.*;

import java.lang.invoke.MethodHandle;
import java.lang.foreign.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;

import Exceptions.*;

public class App {
    public static void main (String[] args) throws Exception {
        if(System.getProperty("os.name").toLowerCase().startsWith("windows")){
            iniciaANSI(); 
        }
        Scanner sc = new Scanner(System.in, "Cp852").useLocale(Locale.US);
        Misc.limpaTela();
        ArrayList<Menu> menus=MenuSetup.criaMenus();
        AllRep rep = new AllRep();
        int menuSelecionado=0; //id do menu
        int opt=0;
        Menu menuAtual=menus.get(MenuSetup.procuraMenu(menus, menuSelecionado));
        boolean skipInput=false;
        String obs="";
        String obsErro="";
        while(true){
            Misc.limpaTela();
            obs="";
            if(opt>=0){
                menuSelecionado=opt;
            }
            else if(opt==-2){
                System.out.println("Saindo...");
                break;
            }
            else if(opt==-1){
                obs=obsErro+"\n";
            }
            else{
                opt=MenuSetup.criaAcoes(opt, rep, sc);
                skipInput=true;
            }
            if(!skipInput){
                menuAtual=menus.get(MenuSetup.procuraMenu(menus, menuSelecionado)); //ordem do menu no arraylist
                menuAtual.mostraMenu(menuAtual.getOpts());
                System.out.print(obs);
                try{
                    opt = sc.nextInt();
                    sc.nextLine();
                    try{
                        InputCheck.intervaloCheck(opt,0,menuAtual.getOpts().size()-1);
                        opt=menuAtual.getOpts().get(opt).getDestino();
                    }
                    catch(IntervaloInvException e){
                        obsErro=e.getMessage();
                        opt=-1;
                    }
                }
                catch(InputMismatchException e){
                    obsErro="Por favor, digite um número.";
                    sc.next();
                    opt=-1;
                }
            }
            else{skipInput=false;}
        }
        sc.close();
    }

    static boolean iniciaANSI(){ //Código que faz códigos ANSI funcionarem
        try(Arena arena=Arena.ofConfined()) {
            SymbolLookup sl=SymbolLookup.libraryLookup("kernel32.dll", arena);
            Linker linker=Linker.nativeLinker();
            MethodHandle GetStdHandle=linker.downcallHandle(sl.find("GetStdHandle").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
            );
            MethodHandle SetConsoleMode=linker.downcallHandle(sl.find("SetConsoleMode").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
            );
            MemorySegment a=(MemorySegment)GetStdHandle.invokeExact(STD_OUTPUT_HANDLE);
            return (boolean)SetConsoleMode.invokeExact(a,
                ENABLE_PROCESSED_OUTPUT|ENABLE_VIRTUAL_TERMINAL_PROCESSING);
        } catch (RuntimeException | Error unchecked) {
            throw unchecked;
        } catch(Throwable e) {
            throw new AssertionError(e);
        }
    }
    static final int STD_OUTPUT_HANDLE = -11,
    ENABLE_PROCESSED_OUTPUT = 0x0001, ENABLE_VIRTUAL_TERMINAL_PROCESSING = 0x0004;
}
