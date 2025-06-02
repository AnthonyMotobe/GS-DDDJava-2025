public class Equipe {
    private final int id;
    private final boolean disponivel;
    private final int numeroMembros;
    private final String especialidade;

    public Equipe(int id, int numeroMembros, String especialidade, Localizacao ignoredBase) {
        this.id = id;
        this.disponivel = true;
        this.numeroMembros = numeroMembros;
        this.especialidade = especialidade;
    }

    /**
     * Calcula a capacidade operacional da equipe
     * @param nivelRisco - nível de risco da ocorrência
     * @param areaAfetada - área em hectares
     * @return valor de capacidade (0-100)
     */
    public int calcularCapacidadeOperacional(String nivelRisco, double areaAfetada) {
        int capacidade = numeroMembros * 10; // Base: 10 pontos por membro

        // Ajuste por especialidade
        if (especialidade.equals("FLORESTAL")) capacidade += 20;
        else if (especialidade.equals("URBANO")) capacidade += 10;

        // Penalidade por área grande
        if (areaAfetada > 10) capacidade -= 20;
        else if (areaAfetada > 5) capacidade -= 10;

        // Penalidade por risco alto
        if (nivelRisco.equals("CRÍTICO")) capacidade -= 15;
        else if (nivelRisco.equals("ALTO")) capacidade -= 10;

        return Math.max(0, Math.min(100, capacidade));
    }

    /**
     * Determina os recursos necessários baseado no cenário
     * @param nivelRisco - nível de risco
     * @return número de recursos extras necessários
     */
    public int determinarRecursosNecessarios(String nivelRisco) {
        return switch (nivelRisco) {
            case "CRÍTICO" -> 5;
            case "ALTO" -> 3;
            case "MÉDIO" -> 2;
            default -> 1;
        };
    }

    public int getNumeroMembros() { return numeroMembros; }

    @Override
    public String toString() {
        return String.format("Equipe ID: %d - %d membros, Especialidade: %s, Disponível: %s",
                id, numeroMembros, especialidade, disponivel ? "Sim" : "Não");
    }

}
