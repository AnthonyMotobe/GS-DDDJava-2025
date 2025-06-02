public class Equipe {
    private int id;
    private boolean disponivel;
    private int numeroMembros;
    private Localizacao localizacaoBase;

    public Equipe(int id, int numeroMembros, Localizacao localizacaoBase) {
        this.id = id;
        this.disponivel = true;
        this.numeroMembros = numeroMembros;
        this.localizacaoBase = localizacaoBase;
    }

    public void alocar() {
        if (!disponivel) {
            throw new IllegalStateException("Equipe já está alocada");
        }
        this.disponivel = false;
    }

    public void liberar() {
        this.disponivel = true;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public int getId() {
        return id;
    }

    public int getNumeroMembros() {
        return numeroMembros;
    }

    public Localizacao getLocalizacaoBase() {
        return localizacaoBase;
    }

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
