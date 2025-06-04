/**
 * Representa uma queimada detectada pelo sistema FireGuardian, contendo informações
 * sobre localização, dados dos sensores e timestamp de detecção.
 * Esta classe armazena todos os dados relevantes de uma ocorrência de incêndio
 * e fornece funcionalidades para geração de relatórios detalhados.
 *
 * @author Sistema de Combate a Incêndios
 * @version 1.0
 * @since 1.0
 */
public class Queimada {
    private final Localizacao localizacao;
    private final Sensor sensor;
    private final java.time.LocalDateTime dataHora;

    /**
     * Constrói uma nova instância de queimada com localização e dados do sensor.
     * A data e hora da detecção são automaticamente registradas no momento da criação.
     *
     * @param localizacao a localização geográfica onde a queimada foi detectada
     * @param sensor o sensor que detectou a queimada com dados de temperatura e umidade
     */
    public Queimada(Localizacao localizacao, Sensor sensor) {
        this.localizacao = localizacao;
        this.sensor = sensor;
        this.dataHora = java.time.LocalDateTime.now();
    }

    /**
     * Gera um relatório formatado contendo todas as informações da queimada.
     * O relatório inclui data/hora de detecção, informações de localização
     * e dados dos sensores organizados em formato legível.
     *
     * @return string formatada contendo o relatório completo da queimada
     */
    public String gerarRelatorio() {
        return String.format(
                """
                        === RELATÓRIO DE QUEIMADA ===
                        Data/Hora: %s
                        %s
                        %s
                        ========================""",
                dataHora.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                localizacao.toString(),
                sensor.toString()
        );
    }

    /**
     * Retorna a localização geográfica da queimada.
     *
     * @return a localização onde a queimada foi detectada
     */
    public Localizacao getLocalizacao() {
        return localizacao;
    }
}