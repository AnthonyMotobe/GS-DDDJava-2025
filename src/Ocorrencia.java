public class Ocorrencia {
    private final Queimada queimada;
    private Drone drone;
    private Equipe equipe;

    public Ocorrencia(Queimada queimada) {
        this.queimada = queimada;
    }

    public void alocarDrone(Drone drone) {
        if (drone.isDisponivel()) {
            this.drone = drone;
            drone.alocar();
        }
    }

    public void alocarEquipe(Equipe equipe) {
        if (equipe.isDisponivel()) {
            this.equipe = equipe;
            equipe.alocar();
        }
    }

    public String gerarRelatorio() {
        String relatorio = queimada.gerarRelatorio() + "\n";
        relatorio += (drone != null ? "Drone alocado: " + drone.getId() : "Sem drone alocado") + "\n";
        relatorio += (equipe != null ? "Equipe alocada: " + equipe.getNome() : "Sem equipe alocada");
        return relatorio;
    }
}
