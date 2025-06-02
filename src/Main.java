import javax.swing.*;

public static void main(String[] args) {
    // Opção 1: Usando Scanner para entrada via console
    Scanner scanner = new Scanner(System.in);

    System.out.println("=== SISTEMA FIRE GUARDIAN ===");
    System.out.println("Informe os dados da ocorrência:");

    // Coletando dados da localização
    System.out.print("Latitude: ");
    double lat = scanner.nextDouble();

    System.out.print("Longitude: ");
    double lon = scanner.nextDouble();

    // Coletando dados ambientais
    System.out.print("Temperatura (°C): ");
    double temp = scanner.nextDouble();

    System.out.print("Umidade (%): ");
    double umid = scanner.nextDouble();

    System.out.print("Velocidade do vento (km/h): ");
    double vento = scanner.nextDouble();

    // Criando objetos
    Localizacao local = new Localizacao(lat, lon);
    Sensor sensor = new Sensor(1);

    // Validando parâmetros
    if (!sensor.validarParametros(temp, umid, vento)) {
        System.out.println("ERRO: Parâmetros inválidos!");
        return;
    }

    Queimada queimada = new Queimada(local, sensor, temp, umid, vento);
    Ocorrencia ocorrencia = new Ocorrencia(queimada);

    // Criando recursos
    Drone drone = new Drone(1);
    Equipe equipe = new Equipe(1, 6, "FLORESTAL", new Localizacao(lat - 0.05, lon - 0.05));

    ocorrencia.setDroneAlocado(drone);
    ocorrencia.setEquipeAlocada(equipe);

    // Exibindo informações
    System.out.println("\n=== ANÁLISE DA OCORRÊNCIA ===");
    System.out.println("Localização: " + local);
    System.out.println("Sensor: " + sensor);
    System.out.println("Queimada: " + queimada);
    System.out.println("Prioridade de Atendimento: " + local.calcularPrioridadeAtendimento(queimada.getIntensidade()));
    System.out.println("Urgência da Resposta: " + queimada.determinarUrgenciaResposta());

    // Cálculos operacionais
    double custoOperacao = ocorrencia.calcularCustoOperacao(200.0, 100.0, 3.0);
    double impactoAmbiental = queimada.avaliarImpactoAmbiental(2.0);
    double eficiencia = ocorrencia.avaliarEficienciaResposta(1.5, 2);

    System.out.println("\n=== RESULTADOS ===");
    System.out.println("Custo da Operação: R$ " + String.format("%.2f", custoOperacao));
    System.out.println("Impacto Ambiental: " + String.format("%.1f", impactoAmbiental) + "/100");
    System.out.println("Eficiência da Resposta: " + String.format("%.1f", eficiencia) + "%");

    // Usando JOptionPane como alternativa
    String opcao = JOptionPane.showInputDialog("Deseja ver relatório detalhado? (s/n)");
    if (opcao != null && opcao.equalsIgnoreCase("s")) {
        String relatorio = "RELATÓRIO DETALHADO\n" +
                "==================\n" +
                "Drone: " + drone + "\n" +
                "Equipe: " + equipe + "\n" +
                "Capacidade Operacional: " +
                equipe.calcularCapacidadeOperacional(queimada.getIntensidade(), queimada.getAreaAfetada()) +
                "/100\n" +
                "Recursos Necessários: " +
                equipe.determinarRecursosNecessarios(queimada.getIntensidade()) + "\n";

        JOptionPane.showMessageDialog(null, relatorio);
    }

    scanner.close();
    System.out.println("\nSistema finalizado com sucesso!");
}