/* Representa uma ocorrência de combate a incêndio, */
/* incluindo a queimada detectada, drone e equipe alocados. */

public class Ocorrencia {
    private Queimada queimada;
    private Drone drone;
    private Equipe equipe;

    /* Construtor da classe Ocorrencia. */
    /* @param queimada a queimada detectada */

    public Ocorrencia(Queimada queimada) {
        this.queimada = queimada;
    }

    /* Aloca um drone diretamente à ocorrência. */
    /* @param drone o drone a ser alocado */

    public void alocarDrone(Drone drone) {
        if (drone.isDisponivel()) {
            this.drone = drone;
            drone.alocar();
        }
    }

     /* Aloca um drone a partir de seu ID, sobrecarga do anterior */
     /* @param id identificador do drone */

    public void alocarDrone(String id) {
        Drone novoDrone = new Drone(id);
        alocarDrone(novoDrone);
    }

     /* Aloca uma equipe à ocorrência. */
     /* @param equipe a equipe disponível */

    public void alocarEquipe(Equipe equipe) {
        if (equipe.isDisponivel()) {
            this.equipe = equipe;
            equipe.alocar();
        }
    }

     /* Gera um relatório completo da ocorrência. */
     /* @return relatório em formato de string */

    public String gerarRelatorio() {
        String relatorio = queimada.gerarRelatorio() + "\n";
        relatorio += (drone != null ? "Drone alocado: " + drone.getId() : "Sem drone alocado") + "\n";
        relatorio += (equipe != null ? "Equipe alocada: " + equipe.getNome() : "Sem equipe alocada");
        return relatorio;
    }

     /* Sobrescrita do toString para retornar o relatório da ocorrência. */
     /* @return relatório da ocorrência */

    @Override
    public String toString() {
        return gerarRelatorio();
    }
}
