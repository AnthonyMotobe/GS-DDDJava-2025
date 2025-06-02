public class Ocorrencia {
    private Queimada queimada;
    private Drone droneAlocado;
    private Equipe equipeAlocada;
    private java.time.LocalDateTime dataResposta;

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

    public String gerarRelatorioCompleto() {
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("=== RELATÓRIO COMPLETO DE OCORRÊNCIA ===\n");
        relatorio.append("Data/Hora da Resposta: ")
                .append(dataResposta.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")))
                .append("\n\n");

        relatorio.append(queimada.gerarRelatorio()).append("\n\n");

        if (droneAlocado != null) {
            relatorio.append("DRONE ALOCADO:\n").append(droneAlocado.toString()).append("\n\n");
        } else {
            relatorio.append("NENHUM DRONE DISPONÍVEL\n\n");
        }

        if (equipeAlocada != null) {
            relatorio.append("EQUIPE ALOCADA:\n").append(equipeAlocada.toString()).append("\n\n");
        } else {
            relatorio.append("NENHUMA EQUIPE DISPONÍVEL\n\n");
        }

        relatorio.append("=====================================");

        return relatorio.toString();
    }

    public void finalizar() {
        if (droneAlocado != null) {
            droneAlocado.liberar();
        }
        if (equipeAlocada != null) {
            equipeAlocada.liberar();
        }
    }

    public Queimada getQueimada() {
        return queimada;
    }

    public Drone getDroneAlocado() {
        return droneAlocado;
    }

    public Equipe getEquipeAlocada() {
        return equipeAlocada;
    }
}
