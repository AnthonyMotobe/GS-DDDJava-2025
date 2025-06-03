/**
 * Representa uma localização geográfica no sistema FireGuardian.
 * Esta classe armazena as coordenadas geográficas (latitude e longitude)
 * onde uma ocorrência de incêndio foi detectada.
 *
 * @author Anthony Motobe
 * @version 1.0
 * @since 2025
 */
public class Localizacao {

    /** Coordenada de latitude */
    private final float latitude;

    /** Coordenada de longitude */
    private final float longitude;

    /**
     * Construtor que inicializa uma localização com coordenadas específicas.
     * Valida se as coordenadas estão dentro dos limites geográficos válidos.
     *
     * @param latitude a coordenada de latitude (deve estar entre -90 e 90 graus)
     * @param longitude a coordenada de longitude (deve estar entre -180 e 180 graus)
     * @throws IllegalArgumentException se latitude ou longitude estiverem fora dos limites válidos
     */
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

    /**
     * Retorna uma representação textual da localização.
     *
     * @return string formatada com as coordenadas de latitude e longitude
     */
    @Override
    public String toString() {
        return String.format("Localização: %.6f°, %.6f°", latitude, longitude);
    }

    /**
     * Calcula a distância entre esta localização e outra localização usando a fórmula de Haversine.
     * A fórmula de Haversine determina a distância do círculo máximo entre dois pontos
     * na superfície de uma esfera dados sua latitude e longitude.
     *
     * @param outraLocalizacao a outra localização para calcular a distância
     * @return a distância em quilômetros entre as duas localizações
     * @throws IllegalArgumentException se outraLocalizacao for null
     */
    public double calcularDistancia(Localizacao outraLocalizacao) {
        if (outraLocalizacao == null) {
            throw new IllegalArgumentException("Outra localização não pode ser null");
        }

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