public class Sensor {
    private float temperatura;
    private float umidade;
    private String risco;

    public Sensor(float temperatura, float umidade) {
        this.temperatura = temperatura;
        this.umidade = umidade;
        this.risco = calcularRisco();
    }

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

    public float getTemperatura() {
        return temperatura;
    }

    public float getUmidade() {
        return umidade;
    }

    public String getRisco() {
        return risco;
    }

    @Override
    public String toString() {
        return String.format("Sensor - Temp: %.1f°C, Umidade: %.1f%%, Risco: %s",
                temperatura, umidade, risco);
    }
}
