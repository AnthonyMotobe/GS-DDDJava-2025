public class Queimada {
    private final Localizacao localizacao;
    private final Sensor sensor;

    public Queimada(Localizacao localizacao, Sensor sensor) {
        this.localizacao = localizacao;
        this.sensor = sensor;
    }

    public double getRisco() {
        return sensor.getRisco();
    }

    public String gerarRelatorio() {
        return "Queimada em " + localizacao + "\n" +
                "Sensor: " + sensor.getDados() + "\n" +
                "Risco: " + getRisco();
    }
}

