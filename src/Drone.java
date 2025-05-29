public class Drone {
    private final String id;
    private boolean disponivel;

    public Drone(String id) {
        this.id = id;
        this.disponivel = true;
    }

    public void alocar() {
        disponivel = false;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public String getId() {
        return id;
    }
}

