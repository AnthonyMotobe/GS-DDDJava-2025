public class Drone {
    private final int id;
    private final boolean disponivel;
    private final double autonomiaBateria;
    private final String status;

    public Drone(int id) {
        this.id = id;
        this.disponivel = true;
        this.autonomiaBateria = 100.0;
        this.status = "AGUARDANDO";
    }


    @Override
    public String toString() {
        return String.format("Drone ID: %d - Status: %s, Bateria: %.1f%%, Disponível: %s",
                id, status, autonomiaBateria, disponivel ? "Sim" : "Não");
    }

}