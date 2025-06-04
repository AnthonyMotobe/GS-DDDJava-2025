/**
 * Classe principal do sistema FireGuardian para detecção e monitoramento de incêndios florestais.
 * Esta classe contém a interface de usuário via console para entrada de dados de ocorrências
 * e demonstração do funcionamento do sistema com dados fixos.
 *
 * @author Anthony Motobe
 * @version 1.0
 * @since 2025
 */
public class Main {

    /**
     * Método utilitário para leitura segura de valores float do console com validação.
     * Garante que o valor inserido esteja dentro do range especificado e trata
     * erros de formatação e entrada inválida.
     *
     * @param scanner o objeto Scanner para leitura do console
     * @param mensagem a mensagem a ser exibida para o usuário
     * @param min valor mínimo aceito (inclusive)
     * @param max valor máximo aceito (inclusive)
     * @return o valor float validado inserido pelo usuário
     */
    private static float lerFloat(java.util.Scanner scanner, String mensagem, float min, float max) {
        float valor;
        while (true) {
            try {
                System.out.print(mensagem);
                String input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    System.out.println("Por favor, digite um valor válido.");
                    continue;
                }

                // Substitui vírgula por ponto para compatibilidade
                input = input.replace(",", ".");
                valor = Float.parseFloat(input);

                if (valor < min || valor > max) {
                    System.out.printf("Valor deve estar entre %.2f e %.2f\n", min, max);
                    continue;
                }

                break;

            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido (use ponto para decimais).");
            }
        }
        return valor;
    }

    /**
     * Método principal que executa a aplicação FireGuardian.
     * Inicializa os recursos do sistema (drones e equipes), coleta dados do usuário
     * via console, processa uma ocorrência de incêndio e exibe o relatório completo.
     *
     * @param args argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        scanner.useLocale(java.util.Locale.US); // Para usar ponto como separador decimal

        // Criando recursos disponíveis com coordenadas float
        java.util.List<Drone> drones = new java.util.ArrayList<>();
        drones.add(new Drone(1, new Localizacao(-23.5505f, -46.6333f))); // São Paulo
        drones.add(new Drone(2, new Localizacao(-22.9068f, -43.1729f))); // Rio de Janeiro
        drones.add(new Drone(3, new Localizacao(-15.7801f, -47.9292f))); // Brasília

        java.util.List<Equipe> equipes = new java.util.ArrayList<>();
        equipes.add(new Equipe(1, 5, new Localizacao(-23.5505f, -46.6333f))); // São Paulo
        equipes.add(new Equipe(2, 7, new Localizacao(-22.9068f, -43.1729f))); // Rio de Janeiro
        equipes.add(new Equipe(3, 4, new Localizacao(-15.7801f, -47.9292f))); // Brasília

        System.out.println("=== SISTEMA FIREGUARDIAN ===");
        System.out.println("Digite as informações da ocorrência:");
        System.out.println("(Use ponto como separador decimal, ex: -23.5505)");
        System.out.println();

        try {
            // Lendo dados com validação
            float latitude = lerFloat(scanner, "Latitude (-90 a 90): ", -90.0f, 90.0f);
            float longitude = lerFloat(scanner, "Longitude (-180 a 180): ", -180.0f, 180.0f);
            float temperatura = lerFloat(scanner, "Temperatura em °C (0 a 60): ", 0.0f, 60.0f);
            float umidade = lerFloat(scanner, "Umidade em % (0 a 100): ", 0.0f, 100.0f);

            System.out.println("\nProcessando dados...");

            // Criando objetos do sistema
            Localizacao localizacao = new Localizacao(latitude, longitude);
            Sensor sensor = new Sensor(temperatura, umidade);
            Queimada queimada = new Queimada(localizacao, sensor);
            Ocorrencia ocorrencia = new Ocorrencia(queimada, drones, equipes);

            // Exibindo relatório
            System.out.println("\n" + ocorrencia.gerarRelatorioCompleto());

            ocorrencia.finalizar();
            System.out.println("Ocorrência finalizada. Recursos liberados.");

        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + (e.getMessage() != null ? e.getMessage() : "Erro desconhecido"));
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}