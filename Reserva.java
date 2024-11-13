import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//classe que representa uma reserva de hotel
public class Reserva {
    private Cliente cliente;
    private Quarto quarto;
    private LocalDate DataChegada;
    private LocalDate DataSaida;

   //construtor da classe reserva que inicia os atributos cliente, quarto, data de chegada e data de saida
    public Reserva(Cliente cliente, Quarto quarto, LocalDate DataChegada, LocalDate DataSaida){
        this.cliente = cliente;
        this.quarto = quarto;
        this.DataChegada = DataChegada;
        this.DataSaida = DataSaida;
    }

    //Metodo para obter o quarto da reserva
    public Quarto getQuarto() {
        return quarto;
    }

    //Metodo para exibir a reserva com uma string formatada
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //DateTimeFormatter para definir o formato da data
        return String.format( "Reserva:\n" +
                "CLIENTE: %s\n" +
                "QUARTO: %d\n"+
                "DATA DE CHEGADA: %s\n" +
                "DATA DE SAIDA: %s",
                cliente.getNome(),
                quarto.getNumero(),
                DataChegada.format(formatter),
                DataSaida.format(formatter));

    }
}
