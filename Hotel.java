import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Classe hotel que inicializa as listas de quartos e reservas
public class Hotel {
    private List<Quarto> quartos; // Lista de quartos disponíveis
    private List<Reserva> reservas; // Lista de reservas feitas no hotel

    // Construtor da classe hotel que inicializa as listas de quartos e reservas
    public Hotel() {
        this.quartos = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    // Método para adicionar um quarto à lista de quartos do hotel
    public void AdicionarQuarto(Quarto quarto) {
        quartos.add(quarto);
        System.out.println("Quarto: " + quarto.getNumero() + " adicionado com sucesso.");
    }

    // Método para reservar um quarto específico
    public void ReservarQuarto(int NumeroQuarto, Cliente cliente, LocalDate DataChegada, LocalDate DataSaida) throws QuartoIndisponivelException {
        if (DataChegada.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("A data de chegada não pode ser anterior à data atual.");
        }

        Quarto quartoReserva = BuscarQuartoPorNumero(NumeroQuarto); // Busca o quarto pelo número informado
        if (quartoReserva == null) {
            throw new IllegalArgumentException("Erro: Quarto " + NumeroQuarto + " não encontrado.");
        }

        // Lança uma exceção se o quarto já estiver reservado
        if (!quartoReserva.isDisponivel()) {
            throw new QuartoIndisponivelException("O quarto " + NumeroQuarto + " já está reservado.");
        }

        // Cria uma nova reserva com os dados fornecidos
        Reserva novaReserva = new Reserva(cliente, quartoReserva, DataChegada, DataSaida);
        reservas.add(novaReserva); // Adiciona a nova reserva à lista de reservas
        quartoReserva.setDisponivel(false); // Marca o quarto como indisponível
        System.out.println("Nova reserva feita com sucesso!\n" + novaReserva);
    }

    // Método para cancelar uma reserva específica
    public void CancelarReserva(int NumeroQuarto) {
        Reserva ReservaParaCancelar = null;
        for (Reserva reserva : reservas) {
            if (reserva.getQuarto().getNumero() == NumeroQuarto) { // Procura a reserva pelo número do quarto
                ReservaParaCancelar = reserva;
                break;
            }
        }

        // Verifica se a reserva foi encontrada
        if (ReservaParaCancelar != null) {
            reservas.remove(ReservaParaCancelar); // Remove a reserva
            ReservaParaCancelar.getQuarto().setDisponivel(true); // Define o quarto como disponível
            System.out.println("Reserva para o quarto " + NumeroQuarto + " foi cancelada.");
        } else {
            System.out.println("Nenhuma reserva encontrada para o quarto " + NumeroQuarto + ".");
        }
    }

    // Método para buscar um quarto pelo seu número
    public Quarto BuscarQuartoPorNumero(int numero) {
        for (Quarto quarto : quartos) {
            if (quarto.getNumero() == numero) {
                return quarto;
            }
        }
        return null; // Retorna null se o quarto não for encontrado
    }

    // Método para exibir quartos cadastrados
    public void ExibirQuartos() {
        if (quartos.isEmpty()) { // Verifica se a lista de quartos está vazia
            System.out.println("Nenhum quarto cadastrado.");
        } else {
            for (Quarto quarto : quartos) {
                System.out.println(quarto); // Exibe cada quarto da lista
            }
        }
    }

    // Método para exibir todas as reservas feitas
    public void ExibirReservas() {
        if (reservas.isEmpty()) { // Verifica se a lista de reservas está vazia
            System.out.println("Nenhuma reserva encontrada.");
        } else {
            for (Reserva reserva : reservas) {
                System.out.println(reserva); // Exibe cada reserva da lista
            }
        }
    }
}
