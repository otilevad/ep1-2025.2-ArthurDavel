package Menu;

public class Comando {
    private String dado;
    private String type;
    private String comando;
    private int valorInt;
    private String valorStr;

    public Comando(){
        this.dado="";
        this.type="String";
        this.comando="Isto é um comando!";
        this.valorInt=0;
        this.valorStr="";
    }

    public Comando(String dado, String type, String comando, int valorInt, String valorStr){
        this.dado=dado;
        this.type=type;
        this.comando=comando;
        this.valorInt=valorInt;
        this.valorStr=valorStr;
    }

    public Comando(String dado, String type, String comando){
        this.dado=dado;
        this.type=type;
        this.comando=comando;
        this.valorInt=0;
        this.valorStr="";
    }

    public String getDado() {
        return this.dado;
    }

    public void setDado(String dado) {
        this.dado = dado;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
    
    public void setComando(String comando) {
        this.comando = comando;
    }

    public String getComando() {
        return this.comando;
    }
    
    public void setValorInt(int valorInt) {
        this.valorInt = valorInt;
    }

    public int getValorInt() {
        return this.valorInt;
    }
    
    public void setValorStr(String valorStr) {
        this.valorStr = valorStr;
    }

    public String getValorStr() {
        return this.valorStr;
    }
}
