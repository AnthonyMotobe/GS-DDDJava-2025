public class Sensor {
    private final int id;
    private double temperatura;
    private double umidade;
    private double velocidadeVento;

    public Sensor(int id) {
        this.id = id;
    }

    /**
     * Calcula o índice de risco baseado nas condições ambientais
     * @param temp - temperatura atual
     * @param umid - umidade relativa
     * @param vento - velocidade do vento
     * @return índice numérico de risco (0-100)
     */
    public double calcularIndiceRisco(double temp, double umid, double vento) {
        this.temperatura = temp;
        this.umidade = umid;
        this.velocidadeVento = vento;

        return (temperatura * 0.4) + ((100 - umidade) * 0.3) + (velocidadeVento * 0.3);
    }

    /**
     * Determina o nível de alerta baseado no índice de risco
     * @param indiceRisco - valor do índice calculado
     * @return string com nível de alerta
     */
    public String determinarNivelAlerta(double indiceRisco) {
        if (indiceRisco >= 70) return "CRÍTICO";
        else if (indiceRisco >= 50) return "ALTO";
        else if (indiceRisco >= 30) return "MÉDIO";
        else return "BAIXO";
    }

    /**
     * Valida se os parâmetros estão dentro dos limites seguros
     * @param temp - temperatura a validar
     * @param umid - umidade a validar
     * @param vento - velocidade do vento a validar
     * @return true se valores são válidos, false caso contrário
     */
    public boolean validarParametros(double temp, double umid, double vento) {
        return temp >= -10 && temp <= 60 &&
                umid >= 0 && umid <= 100 &&
                vento >= 0 && vento <= 200;
    }

    // Método com sobrescrita
    @Override
    public String toString() {
        return String.format("Sensor ID: %d - Temp: %.1f°C, Umidade: %.1f%%, Vento: %.1f km/h",
                id, temperatura, umidade, velocidadeVento);
    }

}