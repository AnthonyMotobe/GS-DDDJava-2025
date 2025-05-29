public class Sensor {
    private final double temperatura;
    private final double umidade;

    public Sensor(double temperatura, double umidade) {
        this.temperatura = temperatura;
        this.umidade = umidade;
    }

    public double getRisco() {
        if (temperatura > 35 && umidade > 20) return 0.9;
        else if (temperatura > 30 && umidade > 30) return 0.7;
        return 0.3;
    }

    public String getDados() {
        return "Temperatura" + temperatura + "°C, Umidade" + umidade + '%';
    }
}
