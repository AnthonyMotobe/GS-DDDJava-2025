/* Representa uma equipe de resposta a incêndios. */

public class Equipe {
    private final String nome;
    private boolean disponivel;

    /* Construtor da classe Equipe. */
    /* @param nome nome da equipe */
    public Equipe(String nome) {
        this.nome = nome;
        this.disponivel = true;
    }

    /* Marca a equipe como alocada. */
    public void alocar() {
        disponivel = false;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public String getNome() {
        return nome;
    }
}
