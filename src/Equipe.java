public class Equipe {
    private final String nome;
    private boolean disponivel;

    public Equipe(String nome) {
        this.nome = nome;
        this.disponivel = true;
    }

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
