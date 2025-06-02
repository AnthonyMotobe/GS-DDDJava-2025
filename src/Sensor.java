/* Representa um sensor que mede temperatura e umidade, calculando risco de incêndio. */
public class Sensor {
    private double temperatura;
    private double umidade;

    public Sensor(double temperatura, double umidade) {
        this.temperatura = temperatura;
        this.umidade = umidade;
    }

     /* Calcula o risco de incêndio com base na temperatura e umidade. */
     /* @return risco entre 0.0 (baixo) e 1.0 (alto) */

    public double calcularRisco() {
        if (temperatura > 35 && umidade < 20) return 0.9;
        if (temperatura > 30 && umidade < 30) return 0.7;
        return 0.3;
    }


     /* Retorna os dados do sensor formatados. */
     /* @return string com temperatura e umidade */
    public String getDados() {
        return "Temp: " + temperatura + "°C, Umid: " + umidade + "%";
    }

     /* Retorna dados do calcularRisco para getRisco */
    public double getRisco() {
        return calcularRisco();
    }
}
