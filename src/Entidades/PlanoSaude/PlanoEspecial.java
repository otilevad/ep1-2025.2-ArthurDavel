package Entidades.PlanoSaude;

import Listas.AllLista;

public class PlanoEspecial extends PlanoSaude{
    @Override
    public void imprimeDados(){
        System.out.println("Nome: "+getNome());
        System.out.println("Descontos: ");
        for(Desconto i : getDescontos()){
            System.out.println(" "+i.getEspec().getNome()+" - "+i.getPorcentagem()+"%;");
        }
        System.out.println("Internação de no máximo 1 semana gratuita!");
    }
    
    public static PlanoEspecial buscaValorPlano(int num, AllLista lista){
        return lista.getPlanosL().getPlanosEsp().get(num);
    }
}
