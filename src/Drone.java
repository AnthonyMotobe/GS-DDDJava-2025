/**
 * Representa um drone utilizado no sistema FireGuardian para detecção e monitoramento de incêndios florestais.
 * Esta classe encapsula as informações básicas de um drone, incluindo seu identificador único
 * e seu estado de disponibilidade para operações de campo.
 *
 * @author Anthony Motobe
 * @version 1.0
 * @since 2025
 */
public class Drone {

    /**
     * Identificador único do drone.
     */
    private final int id;

    /**
     * Indica se o drone está disponível para uso.
     * {@code true} se disponível, {@code false} caso contrário.
     */
    private boolean disponivel;

    /**
     * Localização atual do drone no sistema de coordenadas.
     */
    private Localizacao localizacaoAtual;

    /**
     * Construtor que inicializa um drone com ID e localização inicial.
     * O drone é criado com estado disponível por padrão.
     *
     * @param id o identificador único do drone
     * @param localizacaoInicial a localização inicial onde o drone será posicionado
     */
    public Drone(int id, Localizacao localizacaoInicial) {
        this.id = id;
        this.disponivel = true;
        this.localizacaoAtual = localizacaoInicial;
    }

    /**
     * Aloca o drone para uma operação, tornando-o indisponível.
     * <p>
     * Este método deve ser chamado quando o drone for designado para uma missão específica.
     * </p>
     *
     * @throws IllegalStateException se o drone já estiver alocado
     */
    public void alocar() {
        if (!disponivel) {
            throw new IllegalStateException("Drone já está alocado");
        }
        this.disponivel = false;
    }

    /**
     * Libera o drone, tornando-o disponível novamente para operações.
     */
    public void liberar() {
        this.disponivel = true;
    }

    /**
     * Verifica se o drone está disponível para alocação.
     *
     * @return {@code true} se o drone estiver disponível, {@code false} caso contrário
     */
    public boolean isDisponivel() {
        return disponivel;
    }

    /**
     * Obtém a localização atual do drone.
     *
     * @return a localização atual do drone
     */
    public Localizacao getLocalizacaoAtual() {
        return localizacaoAtual;
    }

    /**
     * Move o drone para uma nova localização.
     * Atualiza a posição atual do drone no sistema de coordenadas.
     *
     * @param novaLocalizacao a nova localização para onde o drone deve se mover
     */
    public void moverPara(Localizacao novaLocalizacao) {
        this.localizacaoAtual = novaLocalizacao;
    }

    /**
     * Retorna uma representação textual do drone.
     *
     * @return string contendo ID e status de disponibilidade do drone
     */
    @Override
    public String toString() {
        return String.format("Drone %d - %s - %s",
                id,
                disponivel ? "Disponível" : "Em missão",
                localizacaoAtual.toString()
        );
    }
}