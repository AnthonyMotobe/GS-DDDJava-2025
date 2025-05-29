# GS-DDDJava-2025
Global Solution da disciplina Domain Drive Design (Java)

🖥️ Descrição do Código
O código desenvolvido simula a estrutura lógica do sistema FireGuardian, modelando os principais componentes envolvidos na detecção e resposta a focos de incêndio florestal.

Principais Componentes:
Drone: Representa os drones disponíveis, com um identificador (id) e um estado de disponibilidade. Possui métodos para alocação e verificação de status.

Equipe: Modela as brigadas ou equipes de resposta, com funcionamento semelhante ao da classe Drone.

Localizacao: Armazena as coordenadas geográficas (latitude e longitude) onde a ocorrência foi detectada.

Sensor: Responsável por coletar informações ambientais como temperatura e umidade. Também calcula automaticamente o risco de incêndio, com base em uma regra simples de decisão.

Queimada: Combina a localização e os dados do sensor, encapsulando as informações sobre a queimada e permitindo gerar relatórios.

Ocorrencia: Coordena a resposta à queimada, realizando a alocação de um drone e de uma equipe disponíveis. Também consolida os dados, permitindo a geração de um relatório completo da ocorrência.

Fluxo de Funcionamento:
O usuário informa dados ambientais e de localização via entrada no console.

O sistema cria instâncias de Localizacao e Sensor.

Com base nesses dados, uma Queimada é registrada.

A Ocorrencia é criada, alocando automaticamente um Drone e uma Equipe disponíveis.

Ao final, é gerado um relatório com todas as informações da ocorrência, incluindo o risco calculado e os recursos alocados.

Este código exemplifica os fundamentos da modelagem orientada a objetos aplicada ao contexto do FireGuardian, e serve como base para futuras expansões do sistema.
