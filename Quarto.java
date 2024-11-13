//classe que representa um quarto do hotel
public class Quarto {
    private int numero;
    private String tipo;
    private double preco;
    private boolean disponivel;

    //construtor da classe para iniciar os atributos
    public Quarto(int numero, String tipo, double preco, boolean disponivel){
        this.numero = numero;
        this.tipo = tipo;
        this.preco = preco;
        this.disponivel = disponivel;
    }

    //metodo para ter o numero do quarto
    public int getNumero(){
        return numero;
    }

    //metodo para verificar se o quarto esta disponivel
    public boolean isDisponivel() {
        return disponivel;
    }

    //metodo para definir a disponibilidade do quarto
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    //metodo para exibir as informações do quarto como uma string formatada
    public String toString() {
        return String.format("Quarto:\n" +
                        "Número: %d\n" +
                        "Tipo: %s\n" +
                        "Preço: R$ %.2f\n" +
                        "Disponível: %s\n\n",
                numero,
                tipo,
                preco,
                disponivel ? "Sim" : "Não");
    }
}
