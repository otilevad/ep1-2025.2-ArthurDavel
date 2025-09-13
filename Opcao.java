public class Opcao {
    private String nome;
    private int destino;

    public Opcao() {
        this.nome="";
        this.destino=0;
    }

    public Opcao(String nome, int destino) {
        this.nome=nome;
        this.destino=destino;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome=nome;
    }

    public int getDestino() {
        return this.destino;
    }

    public void setDestino(int destino) {
        this.destino=destino;
    }
}
