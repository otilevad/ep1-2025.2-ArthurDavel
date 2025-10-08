import Menu.*;
import Utilitarios.*;
import Utilitarios.Calendario.*;
import Repositorios.*;

import java.lang.invoke.MethodHandle;
import java.lang.foreign.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import Exceptions.*;
import Listas.*;

public class App {
    public static void main (String[] args) throws Exception {
        if(System.getProperty("os.name").toLowerCase().startsWith("windows")){
            iniciaANSI(); 
        }
        AllLista lista = new AllLista();
        MiscRep.criaArquivos(MiscRep.setDir());
        MiscRep.carregaArquivos(lista);
        Scanner sc = new Scanner(System.in, "Cp852").useLocale(Locale.US);
        Calendario cal = new Calendario();
        cal.calendarioSetup();
        Misc.limpaTela();
        ArrayList<Menu> menus=MenuSetup.criaMenus();
        int menuSelecionado=0; //id do menu
        int opt=0;
        Menu menuAtual=menus.get(MenuSetup.procuraMenu(menus, menuSelecionado));
        boolean skipInput=false;
        String obs="";
        String obsErro="";
        mainWhile: while(true){
            Misc.limpaTela();
            obs="";
            switch(opt){
                case -1:
                    obs=obsErro+"\n";
                    break;
                case -2:
                    System.out.println("Saindo...");
                    MiscRep.salvaArquivos(lista);
                    break mainWhile;
                default:
                    if(opt>=0){menuSelecionado=opt;}
                    else{
                        opt=MenuSetup.criaAcoes(opt, lista, sc, cal);
                        skipInput=true;
                    }
                    break;
            }
            if(!skipInput){
                menuAtual=menus.get(MenuSetup.procuraMenu(menus, menuSelecionado)); //ordem do menu no arraylist
                menuAtual.mostraMenu(menuAtual.getOpts(),0);
                Misc.savePos();
                System.out.print(obs);
                Misc.gotoSavedPos();
                System.out.print((obs!="" ? Misc.setLin(1) : "") +"» ");
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
