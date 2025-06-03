/**
 * Representa uma equipe de resposta a emergências no sistema FireGuardian.
 * Esta classe modela as brigadas ou equipes especializadas em combate a incêndios florestais,
 * incluindo bombeiros, especialistas em meio ambiente e outros profissionais de emergência.
 *
 * @author Anthony Motobe
 * @version 1.0
 * @since 2025
 */
public class Equipe {

    /**
     * Identificador único da equipe.
     */
    private final int id;

    /**
     * Indica se a equipe está disponível para atendimento.
     * {@code true} se disponível, {@code false} caso contrário.
     */
    private boolean disponivel;

    /**
     * Número de membros que compõem a equipe.
     */
    private final int numeroMembros;

    /**
     * Localização da base onde a equipe está estacionada.
     */
    private final Localizacao localizacaoBase;

    /**
     * Construtor que inicializa uma equipe com ID, número de membros e localização base.
     * A equipe é criada com estado disponível por padrão.
     *
     * @param id o identificador único da equipe
     * @param numeroMembros o número de membros que compõem a equipe
     * @param localizacaoBase a localização da base onde a equipe está estacionada
     */
    public Equipe(int id, int numeroMembros, Localizacao localizacaoBase) {
        this.id = id;
        this.disponivel = true;
        this.numeroMembros = numeroMembros;
        this.localizacaoBase = localizacaoBase;
    }

    /**
     * Aloca a equipe para uma operação, tornando-a indisponível.
     *
     * @throws IllegalStateException se a equipe já estiver alocada
     */
    public void alocar() {
        if (!disponivel) {
            throw new IllegalStateException("Equipe já está alocada");
        }
        this.disponivel = false;
    }

    /**
     * Libera a equipe, tornando-a disponível novamente para operações.
     */
    public void liberar() {
        this.disponivel = true;
    }

    /**
     * Verifica se a equipe está disponível para alocação.
     *
     * @return true se a equipe estiver disponível, false caso contrário
     */
    public boolean isDisponivel() {
        return disponivel;
    }

    /**
     * Obtém a localização da base onde a equipe está estacionada.
     *
     * @return a localização da base da equipe
     */
    public Localizacao getLocalizacaoBase() {
        return localizacaoBase;
    }

    /**
     * Retorna uma representação textual da equipe.
     *
     * @return string contendo ID, número de membros, status e localização da base
     */
    @Override
    public String toString() {
        return String.format("Equipe %d - %d membros - %s - Base: %s",
                id,
                numeroMembros,
                disponivel ? "Disponível" : "Em missão",
                localizacaoBase.toString()
        );
    }
}