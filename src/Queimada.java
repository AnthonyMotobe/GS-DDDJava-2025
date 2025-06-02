public class Queimada {
    private Localizacao localizacao;
    private Sensor sensor;
    private java.time.LocalDateTime dataHora;

    public Queimada(Localizacao localizacao, Sensor sensor) {
        this.localizacao = localizacao;
        this.sensor = sensor;
        this.dataHora = java.time.LocalDateTime.now();
    }

    public String gerarRelatorio() {
        return String.format(
                "=== RELATÓRIO DE QUEIMADA ===\n" +
                        "Data/Hora: %s\n" +
                        "%s\n" +
                        "%s\n" +
                        "========================",
                dataHora.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                localizacao.toString(),
                sensor.toString()
        );
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public java.time.LocalDateTime getDataHora() {
        return dataHora;
    }
}