public class Queimada {
    private final Localizacao localizacao;
    private final String intensidade;
    private final double areaAfetada;
    private final double indiceRisco;

    public Queimada(Localizacao localizacao, Sensor sensor, double temp, double umid, double vento) {
        this.localizacao = localizacao;
        this.indiceRisco = sensor.calcularIndiceRisco(temp, umid, vento);
        this.intensidade = sensor.determinarNivelAlerta(indiceRisco);
        this.areaAfetada = calcularAreaAfetada();
    }

    /**
     * Calcula a área afetada baseada no risco e condições
     * @return área em hectares
     */
    private double calcularAreaAfetada() {
        double baseArea = 1.0;

        return switch (intensidade) {
            case "CRÍTICO" -> baseArea * 8.0;
            case "ALTO" -> baseArea * 5.0;
            case "MÉDIO" -> baseArea * 3.0;
            default -> baseArea * 1.5;
        };
    }

    /**
     * Avalia o impacto ambiental da queimada
     * @param tempoResposta - tempo de resposta em horas
     * @return valor do impacto (0-100)
     */
    public double avaliarImpactoAmbiental(double tempoResposta) {
        double impacto = areaAfetada * 5; // Base: 5 pontos por hectare

        // Penalidade por tempo de resposta
        impacto += tempoResposta * 3;

        // Multiplicador por intensidade
        switch (intensidade) {
            case "CRÍTICO": impacto *= 2.0; break;
            case "ALTO": impacto *= 1.5; break;
            case "MÉDIO": impacto *= 1.2; break;
        }

        // Área de risco aumenta impacto
        if (localizacao.verificarAreaRisco()) {
            impacto *= 1.3;
        }

        return Math.min(100, impacto);
    }

    /**
     * Determina a urgência da resposta (0-10)
     * @return nível de urgência
     */
    public int determinarUrgenciaResposta() {
        int urgencia = 1;

        // Baseado na intensidade
        switch (intensidade) {
            case "CRÍTICO": urgencia += 4; break;
            case "ALTO": urgencia += 3; break;
            case "MÉDIO": urgencia += 2; break;
            case "BAIXO": urgencia += 1; break;
        }

        // Baseado na área
        if (areaAfetada > 10) urgencia += 3;
        else if (areaAfetada > 5) urgencia += 2;
        else urgencia += 1;

        // Prioridade da localização
        urgencia += localizacao.calcularPrioridadeAtendimento(intensidade) / 2;

        return Math.min(10, urgencia);
    }

    // Getters
    public String getIntensidade() { return intensidade; }
    public double getAreaAfetada() { return areaAfetada; }

    @Override
    public String toString() {
        return String.format("Queimada - Intensidade: %s, Área: %.2f hectares, Índice Risco: %.2f",
                intensidade, areaAfetada, indiceRisco);
    }
}
