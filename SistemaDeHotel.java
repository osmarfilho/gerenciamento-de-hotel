import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class SistemaDeHotel {
    private Hotel hotel;
    private Scanner scanner;

    public SistemaDeHotel() {
        this.hotel = new Hotel();
        this.scanner = new Scanner(System.in);
    }

    // Método principal para iniciar o sistema
    public void iniciar() {
        int opcao;
        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine();  // Limpa o buffer
            switch (opcao) {
                case 1 -> adicionarQuarto();
                case 2 -> fazerReserva();
                case 3 -> cancelarReserva();
                case 4 -> exibirQuartos();
                case 5 -> exibirReservas();
                case 0 -> System.out.println("Saindo do sistema.");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    // Exibe o menu principal
    private void exibirMenu() {
        System.out.println("\nMenu do Sistema de Hotel:");
        System.out.println("1. Adicionar Quarto");
        System.out.println("2. Fazer Reserva");
        System.out.println("3. Cancelar Reserva");
        System.out.println("4. Exibir Quartos");
        System.out.println("5. Exibir Reservas");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    // Método para adicionar um novo quarto ao hotel
    private void adicionarQuarto() {
        System.out.print("Número do quarto: ");
        int numero = scanner.nextInt();
        scanner.nextLine();  // Limpa o buffer

        System.out.print("Tipo do quarto: ");
        String tipo = scanner.nextLine();

        System.out.print("Preço do quarto: ");
        double preco = scanner.nextDouble();
        scanner.nextLine();  // Limpa o buffer

        boolean disponivel = true;  // Define o quarto como disponível inicialmente

        Quarto novoQuarto = new Quarto(numero, tipo, preco, disponivel);  // Passando os quatro parâmetros necessários
        hotel.AdicionarQuarto(novoQuarto);
    }

    // Método para fazer uma reserva
    private void fazerReserva() {
        try {
            System.out.print("Número do quarto para reserva: ");
            int numeroQuarto = scanner.nextInt();
            scanner.nextLine();  // Limpa o buffer

            System.out.print("Nome do cliente: ");
            String nomeCliente = scanner.nextLine();
            Cliente cliente = new Cliente(nomeCliente);

            System.out.print("Data de chegada (formato AAAA-MM-DD): ");
            LocalDate dataChegada = lerData();

            System.out.print("Data de saída (formato AAAA-MM-DD): ");
            LocalDate dataSaida = lerData();

            hotel.ReservarQuarto(numeroQuarto, cliente, dataChegada, dataSaida);

            // Criando o arquivo de texto com as informações da reserva
            criarArquivoReserva(cliente, numeroQuarto, dataChegada, dataSaida);

        } catch (QuartoIndisponivelException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("Erro: Data inválida. Tente novamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    // Método para cancelar uma reserva
    private void cancelarReserva() {
        System.out.print("Número do quarto para cancelar reserva: ");
        int numeroQuarto = scanner.nextInt();
        scanner.nextLine();  // Limpa o buffer

        hotel.CancelarReserva(numeroQuarto);
    }

    // Método para exibir todos os quartos cadastrados
    private void exibirQuartos() {
        hotel.ExibirQuartos();
    }

    // Método para exibir todas as reservas feitas
    private void exibirReservas() {
        hotel.ExibirReservas();
    }

    // Método auxiliar para ler uma data no formato AAAA-MM-DD
    private LocalDate lerData() {
        String dataString = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dataString, formatter);
    }

    // Método para criar um arquivo de texto com as informações da reserva
    private void criarArquivoReserva(Cliente cliente, int numeroQuarto, LocalDate dataChegada, LocalDate dataSaida) {
        String nomeArquivo = "reserva_" + cliente.getNome().replace(" ", "_") + ".txt";
        String conteudo = "Reserva para: " + cliente.getNome() + "\n" +
                "Número do Quarto: " + numeroQuarto + "\n" +
                "Data de Chegada: " + dataChegada.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "\n" +
                "Data de Saída: " + dataSaida.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "\n";

        try (FileWriter escritor = new FileWriter(nomeArquivo)) {
            escritor.write(conteudo);
            System.out.println("Reserva registrada no arquivo: " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao criar o arquivo de reserva: " + e.getMessage());
        }
    }

    // Método principal para executar o sistema
    public static void main(String[] args) {
        SistemaDeHotel sistema = new SistemaDeHotel();
        sistema.iniciar();
    }
}
