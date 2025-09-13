import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Menu {
    private String nome;
    private int id;
    private ArrayList<Opcao> opts;

    public Menu() {
        this.nome="";
        this.id=0;
    }

    public Menu(String nome, int id) {
        this.nome=nome;
        this.id=id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome=nome;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id=id;
    }

    public ArrayList<Opcao> getOpts() {
        return this.opts;
    }

    public void setOpts(ArrayList<Opcao> opts) {
        this.opts=opts;
    }

    public ArrayList<Opcao> iniciaOpcoes() {
        ArrayList<Opcao> novoOpts = new ArrayList<>();
        setOpts(novoOpts);
        return opts;
    }

    public void adcionaOpcoes(ArrayList<Opcao> opts, String nome, int destino) {
        opts.add(new Opcao(nome, destino));
    }
    
    public int mostraMenu(ArrayList<Opcao> opts) {
        Scanner sc = new Scanner(System.in).useLocale(Locale.US);

        System.out.println("-----"+getNome()+"-----");
        System.out.println("Selecione a opção desejada:");
        for(int i = 0; i < opts.size(); i++) {
            System.out.println(i+" ===> "+opts.get(i).getNome());
        }
        
        int opt = sc.nextInt();
        sc.nextLine();

        int destinoId=opts.get(opt).getDestino();

        return destinoId;
    }
}