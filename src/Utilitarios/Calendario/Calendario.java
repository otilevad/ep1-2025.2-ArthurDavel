package Utilitarios.Calendario;

import java.util.ArrayList;

import Utilitarios.Misc;

public class Calendario {
    private ArrayList<Ano> anos;
    private ArrayList<DataMarcada> datasM;

    public Calendario(){
        this.anos=new ArrayList<Ano>();
        this.datasM=new ArrayList<DataMarcada>();
    }

    public Calendario(ArrayList<Ano> anos, ArrayList<DataMarcada> datasM){
        this.anos=anos;
        this.datasM=datasM;
    }

    public ArrayList<Ano> getAnos(){
        return this.anos;
    }

    public void setAnos(ArrayList<Ano> anos){
        this.anos=anos;
    }
    
    public ArrayList<DataMarcada> getDatasM() {
		return this.datasM;
	}

	public void setDatasM(ArrayList<DataMarcada> datasM) {
		this.datasM = datasM;
	}

    public enum Semana{
        Domingo,
        Segunda,
        Terça,
        Quarta,
        Quinta,
        Sexta,
        Sábado
    }

    public enum Mes{
        Janeiro,
        Fevereiro,
        Março,
        Abril,
        Maio,
        Junho,
        Julho,
        Agosto,
        Setembro,
        Outubro,
        Novembro,
        Dezembro
    }

    public void addAno(int ano, int anoInicia, boolean anoBissexto){
        getAnos().add(new Ano(ano,anoInicia,anoBissexto));
    }

    public void calendarioSetup(){
        addAno(2025,3,false);
        addAno(2026,4,false);
    }

    public int diaData(int numCalendario, String diaMesAno){
        int dia=1;
        int mes=1;
        int anoOrd=0;
        Ano ano=getAnos().get(anoOrd);
        int diasTotais=ano.fatMes(12, ano.getMeses());
        int anoPassou=0;
        int mesCheck=anoPassou+ano.fatMes(mes, ano.getMeses());
        for(int i=1;i<numCalendario;i++){
            if(i<diasTotais){
                if(i>=mesCheck){
                    mes++;
                    dia=1;
                    mesCheck=anoPassou+ano.fatMes(mes, ano.getMeses());
                }
                else{
                    dia++;
                }
            }
            else{
                anoOrd++;
                if(anoOrd>=getAnos().size()){
                    return 0;
                }
                else{
                    mes=1;
                    dia=1;
                    anoPassou=ano.fatMes(12, ano.getMeses());
                    ano=getAnos().get(anoOrd);
                    diasTotais+=ano.fatMes(12, ano.getMeses());
                    mesCheck=anoPassou+ano.fatMes(mes, ano.getMeses());  
                }
            }
        }
        int data;
        switch(diaMesAno){
            case "dia":
                data=dia;
                break;
            case "mes":
                data=mes;
                break;
            case "ano":
                data=ano.getAno();
                break;
            default:
                data=0;
                break;
        }
        return data;
    }

    public String dataString(int dia, int mes, int ano){
        return String.format("%02d", dia)+"/"+String.format("%02d", mes)+"/"+ano;
    }

    public String diaSemana(int numCalendario){
        int dSem=numCalendario+getAnos().get(0).getAnoInicia()-1;
        dSem%=7;
        return Semana.values()[dSem].toString();
    }

    public int diaSemanaInt(int numCalendario){
        int dSem=numCalendario+getAnos().get(0).getAnoInicia()-1;
        dSem%=7;
        return dSem;
    }

    public static int tempoMinuto(int hora, int min){
        return hora*60+min;
    }

    public static String minutoTempo(int numTempo){
        int hora=0;
        int min=0;
        for(int i=0;i<numTempo;i++){
            min++;
            if(min>=60){
                min=0;
                hora++;
                if(hora>=24){
                    hora=0;
                }
            }
        }
        return String.format("%02d", hora)+":"+String.format("%02d", min);
    }

    public int dataDia(int dia, int mes, int ano){
        int total=dia;
        Ano anoObj=anos.get(0);
        for(int i=0;i<anos.size();i++){
            anoObj=anos.get(i);
            if(anos.get(i).getAno()==ano){
                if(mes!=1){
                    total+=anoObj.fatMes(mes-1,anoObj.getMeses());
                }
                break;
            }
            total+=anoObj.fatMes(12,anoObj.getMeses());
        }
        return total;
    }

    public Ano numAno(int anoNum){
        Ano anoObj=anos.get(0);
        for(int i=0;i<anos.size();i++){
            anoObj=anos.get(i);
            if(anos.get(i).getAno()==anoNum){
                break;
            }
        }
        return anoObj;
    }

    public void mostraMes(int mes,int ano,int pad){
        Ano anoObj=numAno(ano);
        String mesStr=Mes.values()[mes-1].toString();
        double strPad=Math.floor((25-mesStr.length())/2);
        System.out.println(Misc.setCol(pad)+"┌────────┬─────────────────────────┐");
        System.out.print(Misc.setCol(pad)+"│  "+ano+"  │");
        System.out.print(Misc.stringNum(" ", (int) strPad));
        System.out.print(mesStr);
        strPad=mesStr.length()%2==0 ? strPad+1 : strPad;
        System.out.print(Misc.stringNum(" ", (int) strPad));
        System.out.println("│");
        System.out.println(Misc.setCol(pad)+"├────┬───┴┬────┬────┬────┬────┬────┤");
        System.out.println(Misc.setCol(pad)+"│ D  │ S  │ T  │ Q  │ Q  │ S  │ S  │");
        System.out.println(Misc.setCol(pad)+"├────┼────┼────┼────┼────┼────┼────┤");
        System.out.print(Misc.setCol(pad));
        if(diaSemanaInt(dataDia(1,mes,ano))!=0){System.out.print("│");}
        for(int i=0;i<diaSemanaInt(dataDia(1,mes,ano));i++){
            System.out.print("    ");
            System.out.print("│");
        }
        for(int i=1;i<=anoObj.getMeses()[mes-1];i++){
            if(diaSemanaInt(dataDia(i,mes,ano))==0){System.out.print("│");}
            for(DataMarcada j : datasM){
                if(dataDia(i,mes,ano)==j.getData()){
                    j.printaCor();
                    break;
                }
            }
            System.out.print(" "+String.format("%02d", i)+" ");
            DataMarcada.resetaCor();
            if(diaSemanaInt(dataDia(i,mes,ano))==6){
                System.out.print("│");
                if(i!=anoObj.getMeses()[mes-1]){
                    System.out.println("");
                    System.out.print(Misc.setCol(pad));
                    //System.out.println("├────┼────┼────┼────┼────┼────┼────┤");
                }
            }
            else{System.out.print("│");}
        }
        for(int i=diaSemanaInt(dataDia(anoObj.getMeses()[mes-1],mes,ano));i<6;i++){
            System.out.print("    ");
            System.out.print("│");
        }
        System.out.println("\n"+Misc.setCol(pad)+"└────┴────┴────┴────┴────┴────┴────┘");
        Misc.gotoSavedPos();
        System.out.print(Misc.setLin(12)); 
    }

    public void mostraHorario(int tempoConsulta,int inicia, int acaba, int pad, int qtdLin){
        //ArrayList<String> horarios=new ArrayList<String>();
        int tempPad=pad+1;
        int cols=0;
        int ordCol=0;
        System.out.print(Misc.setLin(2));
        Misc.savePos();
        for(int i=inicia;i<acaba;i+=tempoConsulta){
            System.out.println(Misc.setCol(tempPad)+minutoTempo(i));
            ordCol++;
            if(ordCol==qtdLin){
                tempPad+=6;
                cols++;
                ordCol=0;
                Misc.gotoSavedPos();
            }
        }
        Misc.gotoSavedPos();
        System.out.print(Misc.sobeLin(2));
        tempPad=pad;
        String title="│Horários│";
        int titlePad=((cols*6)-title.length())/2+(ordCol!=0 ? 4 : 1);
        System.out.println(Misc.setCol(titlePad+tempPad)+title);
        if(ordCol==0){cols--;}
        Misc.savePos();
        for(int i=0;i<=cols;i++){
            System.out.println(Misc.setCol(tempPad)+(i==0 ? "┌─────" : "┬─────")+(i==cols ? "┐" : ""));
            for(int j=0;j<qtdLin;j++){
                System.out.println(Misc.setCol(tempPad)+"│"+Misc.setCol(5)+(i==cols ? "│" : ""));
            }
            System.out.println(Misc.setCol(tempPad)+(i==0 ? "└─────" : "┴─────")+(i==cols ? "┘" : ""));
            tempPad+=6;
            Misc.gotoSavedPos();
        }
        System.out.print(Misc.setLin(10));        
    }

    public void mostraHorario(ArrayList<Integer> horarios,int pad, int qtdLin){
        //ArrayList<String> horarios=new ArrayList<String>();
        int tempPad=pad+1;
        int cols=0;
        int ordCol=0;
        System.out.print(Misc.setLin(2));
        Misc.savePos();
        for(int i : horarios){
            System.out.println(Misc.setCol(tempPad)+minutoTempo(i));
            ordCol++;
            if(ordCol==qtdLin){
                tempPad+=6;
                cols++;
                ordCol=0;
                Misc.gotoSavedPos();
            }
        }
        Misc.gotoSavedPos();
        System.out.print(Misc.sobeLin(2));
        tempPad=pad;
        String title="│Horários│";
        int titlePad=((cols*6)-title.length())/2+(ordCol!=0 ? 4 : 1);
        System.out.println(Misc.setCol(titlePad+tempPad)+title);
        if(ordCol==0){cols--;}
        Misc.savePos();
        for(int i=0;i<=cols;i++){
            System.out.println(Misc.setCol(tempPad)+(i==0 ? "┌─────" : "┬─────")+(i==cols ? "┐" : ""));
            for(int j=0;j<qtdLin;j++){
                System.out.println(Misc.setCol(tempPad)+"│"+Misc.setCol(5)+(i==cols ? "│" : ""));
            }
            System.out.println(Misc.setCol(tempPad)+(i==0 ? "└─────" : "┴─────")+(i==cols ? "┘" : ""));
            tempPad+=6;
            Misc.gotoSavedPos();
        }
        System.out.print(Misc.setLin(11));        
    }

    public void mostraMesData(int data){
        int mes=diaData(data,"mes");
        int ano=diaData(data, "ano");
        int dia=diaData(data,"dia");
        Ano anoObj=numAno(ano);
        String mesStr=Mes.values()[mes-1].toString();
        double strPad=Math.floor((25-mesStr.length())/2);
        System.out.println("┌────────┬─────────────────────────┐");
        System.out.print("│  "+ano+"  │");
        System.out.print(Misc.stringNum(" ", (int) strPad));
        System.out.print(mesStr);
        strPad=25-mesStr.length()%2==1 ? strPad+1 : strPad;
        System.out.print(Misc.stringNum(" ", (int) strPad));
        System.out.println("│");
        System.out.println("├────┬───┴┬────┬────┬────┬────┬────┤");
        System.out.println("│dom │seg │ter │qua │qui │sex │sab │");
        System.out.println("├────┼────┼────┼────┼────┼────┼────┤");
        if(diaSemanaInt(dataDia(1,mes,ano))!=0){System.out.print("│");}
        for(int i=0;i<diaSemanaInt(dataDia(1,mes,ano));i++){
            System.out.print("    ");
            System.out.print("│");
        }
        for(int i=1;i<=anoObj.getMeses()[mes-1];i++){
            if(diaSemanaInt(dataDia(i,mes,ano))==0){System.out.print("│");}
            if(i==dia){
                System.out.print("\u001B[32m");
            }
            System.out.print(" "+String.format("%02d", i)+" ");
            System.out.print("\u001B[0m");
            if(diaSemanaInt(dataDia(i,mes,ano))==6){
                System.out.print("│");
                if(i!=anoObj.getMeses()[mes-1]){System.out.println("");}
            }
            else{System.out.print("│");}
        }
        for(int i=diaSemanaInt(dataDia(anoObj.getMeses()[mes-1],mes,ano));i<6;i++){
            System.out.print("    ");
            System.out.print("│");
        }
        System.out.println("\n└────┴────┴────┴────┴────┴────┴────┘");
    }
}
