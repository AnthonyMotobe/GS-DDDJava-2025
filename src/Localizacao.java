public class Localizacao {
    private float latitude;
    private float longitude;

    public Localizacao(float latitude, float longitude) {
        if (latitude < -90.0f || latitude > 90.0f) {
            throw new IllegalArgumentException("Latitude deve estar entre -90 e 90 graus");
        }
        if (longitude < -180.0f || longitude > 180.0f) {
            throw new IllegalArgumentException("Longitude deve estar entre -180 e 180 graus");
        }

        this.latitude = latitude;
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return String.format("Localização: %.6f°, %.6f°", latitude, longitude);
    }

    // Método para calcular distância entre duas localizações (em km)
    public double calcularDistancia(Localizacao outraLocalizacao) {
        final double R = 6371.0; // Raio da Terra em km

        double lat1Rad = Math.toRadians(this.latitude);
        double lon1Rad = Math.toRadians(this.longitude);
        double lat2Rad = Math.toRadians(outraLocalizacao.latitude);
        double lon2Rad = Math.toRadians(outraLocalizacao.longitude);

        double deltaLat = lat2Rad - lat1Rad;
        double deltaLon = lon2Rad - lon1Rad;

        double a = Math.sin(deltaLat/2) * Math.sin(deltaLat/2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.sin(deltaLon/2) * Math.sin(deltaLon/2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        return R * c;
    }
}