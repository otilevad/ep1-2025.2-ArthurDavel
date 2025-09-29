package Utilitarios;

import java.lang.foreign.*;

public class Misc {
    public static void limpaTela(){
        System.out.print(String.format("%c[H\033[2J",0x1B));
        System.out.flush();
    }

    public static void limpaLinha(int qtd){
        for(int i=0;i<qtd;i++){
            System.out.print(String.format("%c[1F\33[K",0x1B));
        }
    }

    public static void limpaLinha(){
        System.out.print(String.format("%c[1F\33[K",0x1B));
    }
    
    public static String stringNum(String str,int num){
        String string="";
        for(int i=0;i<num;i++){
            string=string.concat(str);
        }
        return string;
    }

    public static String sobeLin(int lin){
        if(lin>0){return String.format("%c[%dA", 0x1B, lin);}
        else{return "";}
    }

    public static String setLin(int lin){
        if(lin>0){return String.format("%c[%dB", 0x1B, lin);}
        else{return "";}
    }

    public static String setCol(int col){
        if(col>0){return String.format("%c[%dC", 0x1B, col);}
        else{return "";}
    }

    public static String voltaCol(int col){
        if(col>0){return String.format("%c[%dD", 0x1B, col);}
        else{return "";}
    }

    public static void savePos(){
        System.out.print(String.format("%c[s", 0x1B));
    }

    public static void gotoSavedPos(){
        System.out.print(String.format("%c[u", 0x1B));
    }

    public static void gotoHome(){
        System.out.print(String.format("%c[H", 0x1B));
    }

    public static void resetSetPos(int col, int lin){
        Misc.gotoSavedPos();
        System.out.print(Misc.setCol(col)+Misc.setLin(lin));
    }

    public static int getTamanhoTela() throws Exception{
        int terminalTam=0;
        try{
            if(System.getProperty("os.name").toLowerCase().startsWith("windows")){
                Process process=Runtime.getRuntime().exec(new String[]{"powershell.exe", "-command", "(Get-Host).UI.RawUI.WindowSize.Width"});
                java.io.BufferedReader reader=new java.io.BufferedReader(new java.io.InputStreamReader(process.getInputStream()));
                String line=reader.readLine();
                if (line!=null){
                    terminalTam=Integer.parseInt(line.trim());
                }
                process.waitFor();
            }
            return terminalTam;
        }
        catch(Exception e){
            return 0;
        }
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
