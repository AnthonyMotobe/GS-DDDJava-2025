public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

     /* inicia a aplicação e gerencia o fluxo da ocorrência. */
     /* @param args argumentos de linha de comando (não utilizados) */

    /* Leitura de dados */
    double lat = lerDouble(sc, "Digite a latitude: ");
    double lon = lerDouble(sc, "Digite a longitude: ");
    double temp = lerDouble(sc, "Digite a temperatura: ");
    double umid = lerDouble(sc, "Digite a umidade: ");

    /* Instancia os objetos principais */
    Localizacao local = new Localizacao(lat, lon);
    Sensor sensor = new Sensor(temp, umid);
    Queimada queimada = new Queimada(local, sensor);
    Ocorrencia ocorrencia = new Ocorrencia(queimada);

    /* Alocação de recursos */
    Drone drone1 = new Drone("DR-001");
    Equipe equipe1 = new Equipe("Brigada A");

    ocorrencia.alocarDrone(drone1);
    ocorrencia.alocarEquipe(equipe1);

    /* Geração de relatório */
    System.out.println("\n=== RELATÓRIO DA OCORRÊNCIA ===");
    System.out.println(ocorrencia.gerarRelatorio());
}

/* Lê um valor double do usuário com validação. */
/* Aceita vírgula ou ponto como separador decimal. */
/* @param sc objeto Scanner usado para leitura */
/* @param mensagem mensagem exibida ao usuário */
/* @return valor decimal inserido corretamente */

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

