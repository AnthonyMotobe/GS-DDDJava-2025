public class Localizacao {
    private final Double latitude;
    private final Double longitude;

    public Localizacao(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Localizacao{" + "latitude=" + latitude + ", longitude=" + longitude + '}';
    }
}
