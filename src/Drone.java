/* Representa um drone que pode ser alocado para combate a incêndios. */

public class Drone {
    private final String id;
    private boolean disponivel;

     /* Construtor da classe Drone. */
     /* @param id identificador do drone */

    public Drone(String id) {
        this.id = id;
        this.disponivel = true;
    }

    /* Marca o drone como alocado. */

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

