//classe cliente, representando um cliente do hotel
public class Cliente {
    private String nome;

    //Construtor da classe cliente que inicia o nome do cliente
    public Cliente(String nome){
        this.nome = nome;
    }

    // Método para obter o nome do cliente
    public String getNome(){
        return nome;
    }
}
