/**
 * Representa um sensor de monitoramento ambiental que coleta dados de temperatura
 * e umidade, calculando automaticamente o nível de risco de incêndio baseado
 * nas condições ambientais detectadas.
 *
 * @author Sistema de Combate a Incêndios
 * @version 1.0
 * @since 1.0
 */
public class Sensor {
    private final float temperatura;
    private final float umidade;
    private final String risco;

    /**
     * Constrói um novo sensor com os valores de temperatura e umidade especificados.
     * O nível de risco é calculado automaticamente baseado nos valores fornecidos.
     *
     * @param temperatura a temperatura em graus Celsius
     * @param umidade a umidade relativa em percentual
     */
    public Sensor(float temperatura, float umidade) {
        this.temperatura = temperatura;
        this.umidade = umidade;
        this.risco = calcularRisco();
    }

    /**
     * Calcula o nível de risco de incêndio baseado na temperatura e umidade.
     * A classificação segue os critérios: CRÍTICO (temp > 35°C e umidade < 30%),
     * ALTO (temp > 30°C e umidade < 50%), MÉDIO (temp > 25°C e umidade < 70%)
     * e BAIXO para demais condições.
     *
     * @return string representando o nível de risco calculado
     */
    private String calcularRisco() {
        // Lógica simples para calcular risco
        if (temperatura > 35.0f && umidade < 30.0f) {
            return "CRÍTICO";
        } else if (temperatura > 30.0f && umidade < 50.0f) {
            return "ALTO";
        } else if (temperatura > 25.0f && umidade < 70.0f) {
            return "MÉDIO";
        } else {
            return "BAIXO";
        }
    }

    /**
     * Retorna uma representação textual formatada dos dados do sensor.
     * Inclui temperatura, umidade e nível de risco em formato legível.
     *
     * @return string formatada com todos os dados do sensor
     */
    @Override
    public String toString() {
        return String.format("Sensor - Temp: %.1f°C, Umidade: %.1f%%, Risco: %s",
                temperatura, umidade, risco);
    }
}