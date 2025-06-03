/**
 * Representa uma ocorrência de queimada que aloca automaticamente recursos disponíveis
 * (drones e equipes) para resposta a incêndios florestais.
 * Esta classe é responsável por alocar o drone mais próximo disponível para monitoramento,
 * alocar a equipe mais próxima disponível para combate, gerar relatórios completos da
 * ocorrência e liberar recursos quando a ocorrência for finalizada.
 *
 * @author Sistema de Combate a Incêndios
 * @version 1.0
 * @since 1.0
 */
public class Ocorrencia {
    private final Queimada queimada;
    private final Drone droneAlocado;
    private final Equipe equipeAlocada;
    private final java.time.LocalDateTime dataResposta;

    /**
     * Constrói uma nova ocorrência, alocando automaticamente os recursos mais próximos disponíveis.
     * Durante a construção, este método registra o horário atual como data de resposta,
     * encontra e aloca o drone mais próximo da queimada, move o drone alocado para a
     * localização da queimada e encontra e aloca a equipe mais próxima da queimada.
     *
     * @param queimada a queimada que gerou esta ocorrência
     * @param dronesDisponiveis lista de drones disponíveis para alocação
     * @param equipesDisponiveis lista de equipes disponíveis para alocação
     * @throws NullPointerException se queimada for null
     */
    public Ocorrencia(Queimada queimada, java.util.List<Drone> dronesDisponiveis,
                      java.util.List<Equipe> equipesDisponiveis) {
        this.queimada = queimada;
        this.dataResposta = java.time.LocalDateTime.now();

        // Encontra o drone mais próximo disponível
        this.droneAlocado = encontrarDroneMaisProximo(dronesDisponiveis);
        if (this.droneAlocado != null) {
            this.droneAlocado.alocar();
            this.droneAlocado.moverPara(queimada.getLocalizacao());
        }

        // Encontra a equipe mais próxima disponível
        this.equipeAlocada = encontrarEquipeMaisProxima(equipesDisponiveis);
        if (this.equipeAlocada != null) {
            this.equipeAlocada.alocar();
        }
    }

    /**
     * Encontra o drone mais próximo da queimada dentre os drones disponíveis.
     * Este método percorre a lista de drones, verifica quais estão disponíveis
     * e calcula a distância de cada um até a localização da queimada,
     * retornando o drone com menor distância.
     *
     * @param drones lista de drones para verificação
     * @return o drone mais próximo disponível, ou null se nenhum drone estiver disponível
     */
    private Drone encontrarDroneMaisProximo(java.util.List<Drone> drones) {
        Drone maisProximo = null;
        double menorDistancia = Double.MAX_VALUE;

        for (Drone drone : drones) {
            if (drone.isDisponivel()) {
                double distancia = drone.getLocalizacaoAtual().calcularDistancia(queimada.getLocalizacao());
                if (distancia < menorDistancia) {
                    menorDistancia = distancia;
                    maisProximo = drone;
                }
            }
        }

        return maisProximo;
    }

    /**
     * Encontra a equipe mais próxima da queimada dentre as equipes disponíveis.
     * Este método percorre a lista de equipes, verifica quais estão disponíveis
     * e calcula a distância da base de cada uma até a localização da queimada,
     * retornando a equipe com menor distância.
     *
     * @param equipes lista de equipes para verificação
     * @return a equipe mais próxima disponível, ou null se nenhuma equipe estiver disponível
     */
    private Equipe encontrarEquipeMaisProxima(java.util.List<Equipe> equipes) {
        Equipe maisProxima = null;
        double menorDistancia = Double.MAX_VALUE;

        for (Equipe equipe : equipes) {
            if (equipe.isDisponivel()) {
                double distancia = equipe.getLocalizacaoBase().calcularDistancia(queimada.getLocalizacao());
                if (distancia < menorDistancia) {
                    menorDistancia = distancia;
                    maisProxima = equipe;
                }
            }
        }

        return maisProxima;
    }

    /**
     * Gera um relatório completo da ocorrência contendo todas as informações relevantes.
     * O relatório inclui data e hora da resposta à ocorrência, informações detalhadas
     * da queimada, dados do drone alocado (se houver) e dados da equipe alocada (se houver).
     *
     * @return string formatada contendo o relatório completo da ocorrência
     */
    public String gerarRelatorioCompleto() {
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("=== RELATÓRIO COMPLETO DE OCORRÊNCIA ===\n");
        relatorio.append("Data/Hora da Resposta: ")
                .append(dataResposta.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")))
                .append("\n\n");

        relatorio.append(queimada.gerarRelatorio()).append("\n\n");

        if (droneAlocado != null) {
            relatorio.append("DRONE ALOCADO:\n").append(droneAlocado).append("\n\n");
        } else {
            relatorio.append("NENHUM DRONE DISPONÍVEL\n\n");
        }

        if (equipeAlocada != null) {
            relatorio.append("EQUIPE ALOCADA:\n").append(equipeAlocada).append("\n\n");
        } else {
            relatorio.append("NENHUMA EQUIPE DISPONÍVEL\n\n");
        }

        relatorio.append("=====================================");

        return relatorio.toString();
    }

    /**
     * Finaliza a ocorrência liberando todos os recursos alocados.
     * Este método libera o drone e a equipe que foram alocados para esta ocorrência,
     * tornando-os disponíveis novamente para outras ocorrências.
     * Recursos que não foram alocados (null) são ignorados de forma segura.
     */
    public void finalizar() {
        if (droneAlocado != null) {
            droneAlocado.liberar();
        }
        if (equipeAlocada != null) {
            equipeAlocada.liberar();
        }
    }

}