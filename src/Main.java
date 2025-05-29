public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    double lat = lerDouble(sc, "Digite a latitude: ");
    double lon = lerDouble(sc, "Digite a longitude: ");
    double temp = lerDouble(sc, "Digite a temperatura: ");
    double umid = lerDouble(sc, "Digite a umidade: ");

    Localizacao local = new Localizacao(lat, lon);
    Sensor sensor = new Sensor(temp, umid);
    Queimada queimada = new Queimada(local, sensor);
    Ocorrencia ocorrencia = new Ocorrencia(queimada);

    Drone drone1 = new Drone("DR-001");
    Equipe equipe1 = new Equipe("Brigada A");

    ocorrencia.alocarDrone(drone1);
    ocorrencia.alocarEquipe(equipe1);

    System.out.println("\n=== RELATÓRIO DA OCORRÊNCIA ===");
    System.out.println(ocorrencia.gerarRelatorio());
}

public static double lerDouble(Scanner sc, String mensagem) {
    double valor = 0;
    boolean valido = false;

    while (!valido) {
        System.out.print(mensagem);
        String entrada = sc.nextLine().replace(",", ".");
        try {
            valor = Double.parseDouble(entrada);
            valido = true;
        } catch (NumberFormatException e) {
            System.out.println("Erro: Digite um número válido.");
        }
    }

    return valor;
}

