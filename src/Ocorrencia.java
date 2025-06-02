public class Ocorrencia {
    private Drone droneAlocado;
    private Equipe equipeAlocada;
    private final Queimada queimada;
    private final String status;
    private double custoOperacional;

    public Ocorrencia(Queimada queimada) {
        this.queimada = queimada;
        this.status = "INICIADA";
        this.custoOperacional = 0.0;
    }

    /**
     * Calcula o custo total da operação
     * @param custoHoraDrone - custo por hora do drone
     * @param custoHoraEquipe - custo por hora da equipe
     * @param tempoOperacao - tempo total em horas
     * @return custo total em reais
     */
    public double calcularCustoOperacao(double custoHoraDrone, double custoHoraEquipe, double tempoOperacao) {
        double custoTotal = 0.0;

        if (droneAlocado != null) {
            custoTotal += custoHoraDrone * tempoOperacao;
        }

        if (equipeAlocada != null) {
            custoTotal += custoHoraEquipe * tempoOperacao * equipeAlocada.getNumeroMembros();
        }

        // Custo adicional baseado na intensidade
        switch (queimada.getIntensidade()) {
            case "CRÍTICO": custoTotal *= 1.8; break;
            case "ALTO": custoTotal *= 1.5; break;
            case "MÉDIO": custoTotal *= 1.2; break;
        }

        this.custoOperacional = custoTotal;
        return custoTotal;
    }

    /**
     * Avalia a eficiência da resposta
     * @param tempoResposta - tempo de resposta em horas
     * @param recursos - número de recursos utilizados
     * @return índice de eficiência (0-100)
     */
    public double avaliarEficienciaResposta(double tempoResposta, int recursos) {
        double eficiencia = 100.0;

        // Penalidade por tempo de resposta lento
        if (tempoResposta > 4) eficiencia -= 40;
        else if (tempoResposta > 2) eficiencia -= 20;
        else if (tempoResposta > 1) eficiencia -= 10;

        // Penalidade por uso excessivo de recursos
        int recursosNecessarios = queimada.determinarUrgenciaResposta();
        if (recursos > recursosNecessarios * 1.5) eficiencia -= 20;

        // Bônus por resposta rápida em casos críticos
        if (queimada.getIntensidade().equals("CRÍTICO") && tempoResposta <= 1) {
            eficiencia += 10;
        }

        return Math.min(100, eficiencia);
    }

    public void setDroneAlocado(Drone droneAlocado) { this.droneAlocado = droneAlocado; }

    public void setEquipeAlocada(Equipe equipeAlocada) { this.equipeAlocada = equipeAlocada; }

    @Override
    public String toString() {
        return String.format("Ocorrência - Status: %s, Custo: R$ %.2f, Urgência: %d",
                status, custoOperacional, queimada.determinarUrgenciaResposta());
    }
}