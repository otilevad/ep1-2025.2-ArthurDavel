package Entidades.Sala;

import java.util.ArrayList;

import Utilitarios.Calendario.Periodo;

public class Sala {
    private int num;
    private ArrayList<Periodo> ocupado;

    public Sala(){
        this.num=0;
        this.ocupado=new ArrayList<Periodo>();
    }

    public Sala(int num,ArrayList<Periodo> ocupado){
        this.num=num;
        this.ocupado=ocupado;
    }                           

    public int getNum(){
        return this.num;
    }

    public void setNum(int num){
        this.num = num;
    }

    public ArrayList<Periodo> getOcupado() {
        return this.ocupado;
    }

    public void setOcupado(ArrayList<Periodo> ocupado) {
        this.ocupado = ocupado;
    }

    public void addOcupado(Periodo per) {
        this.getOcupado().add(per);
    }
}
