// Classe Localizacao
public class Localizacao {
    private final double latitude;
    private final double longitude;
    private final String regiao;

    public Localizacao(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.regiao = determinarRegiao();
    }

    /**
     * Verifica se a localização está em área de risco elevado
     * @return true se área de risco, false caso contrário
     */
    public boolean verificarAreaRisco() {
        return latitude < -20.0 && longitude < -45.0;
    }

    /**
     * Calcula a prioridade de atendimento baseada na região e risco
     * @param nivelRisco - nível de risco detectado
     * @return valor numérico de prioridade (1-10, sendo 10 mais urgente)
     */
    public int calcularPrioridadeAtendimento(String nivelRisco) {
        int prioridade = 1;

        // Prioridade baseada na região
        switch (regiao) {
            case "Norte": prioridade += 3; break;
            case "Centro": prioridade += 2; break;
            case "Sul": prioridade += 1; break;
        }

        // Prioridade baseada no risco
        switch (nivelRisco) {
            case "CRÍTICO": prioridade += 5; break;
            case "ALTO": prioridade += 3; break;
            case "MÉDIO": prioridade += 2; break;
            case "BAIXO": prioridade += 1; break;
        }

        // Área de risco adiciona prioridade
        if (verificarAreaRisco()) {
            prioridade += 2;
        }

        return Math.min(prioridade, 10); // Máximo 10
    }

    private String determinarRegiao() {
        if (latitude >= -10) return "Norte";
        else if (latitude >= -20) return "Centro";
        else return "Sul";
    }

    @Override
    public String toString() {
        return String.format("Lat: %.4f, Lon: %.4f, Região: %s", latitude, longitude, regiao);
    }
}
