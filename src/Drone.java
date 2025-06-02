// Classe Drone
public class Drone {
    private int id;
    private boolean disponivel;
    private Localizacao localizacaoAtual;

    public Drone(int id, Localizacao localizacaoInicial) {
        this.id = id;
        this.disponivel = true;
        this.localizacaoAtual = localizacaoInicial;
    }

    public void alocar() {
        if (!disponivel) {
            throw new IllegalStateException("Drone já está alocado");
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

    public Localizacao getLocalizacaoAtual() {
        return localizacaoAtual;
    }

    public void moverPara(Localizacao novaLocalizacao) {
        this.localizacaoAtual = novaLocalizacao;
    }

    @Override
    public String toString() {
        return String.format("Drone %d - %s - %s",
                id,
                disponivel ? "Disponível" : "Em missão",
                localizacaoAtual.toString()
        );
    }
}