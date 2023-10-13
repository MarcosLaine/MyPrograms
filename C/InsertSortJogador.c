#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

// Definindo a estrutura para representar um jogador.
typedef struct Player {
    int player_id;
    char player_name[100];
    int player_height;
    int player_weight;
    char player_university[100];
    int birth_year;
    char birth_city[100];
    char birth_state[100];
} Player;

// Função para validar strings vazias.
char* validateString(char str[]) {
    if (!str || strcmp(str, "\0") == 0) {
        return "nao informado";
    }
    return str;
}

// Função para clonar um jogador (não é usada no código principal).
void clonePlayer(Player* original, Player* copy) {
    copy = (Player*) malloc(sizeof(Player));
    copy->player_id = original->player_id;
    strcpy(copy->player_name, original->player_name);
    copy->player_height = original->player_height;
    copy->player_weight = original->player_weight;
    strcpy(copy->player_university, original->player_university);
    copy->birth_year = original->birth_year;
    strcpy(copy->birth_city, original->birth_city);
    strcpy(copy->birth_state, original->birth_state);
}

// Função para imprimir os detalhes de um jogador.
void printPlayer(Player* player) {
    printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n",
           player->player_id, player->player_name, player->player_height, player->player_weight,
           player->birth_year, player->player_university, player->birth_city, player->birth_state);
}

// Função para ler os dados de um jogador a partir de uma string formatada.
void readPlayerData(char* str, Player* player) {
    char* token = strsep(&str, ",");
    if (token != NULL) {
        player->player_id = atoi(token);
    } else {
        player->player_id = -1;
    }
    token = strsep(&str, ",");
    strcpy(player->player_name, validateString(token));
    token = strsep(&str, ",");
    player->player_height = atoi(token);
    token = strsep(&str, ",");
    player->player_weight = atoi(token);
    token = strsep(&str, ",");
    strcpy(player->player_university, validateString(token));
    token = strsep(&str, ",");
    player->birth_year = atoi(token);
    token = strsep(&str, ",");
    strcpy(player->birth_city, validateString(token));
    token = strsep(&str, "\n");
    strcpy(player->birth_state, validateString(token));
}

// Função para ler os dados de jogadores a partir de um arquivo CSV.
void readPlayersFromFile(Player* players[], int num_players) {
    FILE *file = NULL;
    file = fopen("/tmp/players.csv", "r");
    char *data = NULL;
    data = (char*) malloc(sizeof(char) * 1000);
    fgets(data, 1000, file);
    for (int i = 0; i < num_players; i++) {
        fgets(data, 1000, file);
        players[i] = (Player*) malloc(sizeof(Player));
        readPlayerData(data, players[i]);
    }
    free(data);
}

// Função para realizar a ordenação dos jogadores por nome usando o algoritmo de inserção.
void insertionSort(Player* players[], int num_players) {
    for (int i = 1; i < num_players; i++) {
        Player* key = players[i];
        int j = i - 1;
        while (j >= 0 && strcmp(players[j]->player_name, key->player_name) > 0) {
            players[j + 1] = players[j];
            j = j - 1;
        }
        players[j + 1] = key;
    }
}

// Função para realizar a pesquisa binária em jogadores ordenados por nome.
int binarySearch(Player* players[], int num_players, char* name) {
    int left = 0;
    int right = num_players - 1;
    while (left <= right) {
        int mid = (right - left) / 2;
        if (strcmp(players[mid]->player_name, name) == 0)
            return mid;
        if (strcmp(players[mid]->player_name, name) < 0)
            left = mid + 1;
        else
            right = mid - 1;
    }
    return -1; // Não encontrado
}

int main() {
    int num_players = 3922;
    Player* players[3922];
    readPlayersFromFile(players, num_players);
    insertionSort(players, num_players); // Ordena o vetor

    // Abre um arquivo de log para registrar as informações.
    FILE *logFile = fopen("matricula_binaria.txt", "w");
    char input[100];
    int comparisons = 0;

    while (1) {
        scanf("%s", input);

        if (strcmp(input, "FIM") == 0)
            break;

        // Realiza a pesquisa binária.
        int result = binarySearch(players, num_players, input);

        if (result != -1)
            printf("SIM\n");
        else
            printf("NAO\n");

        comparisons++; // Incrementa o número de comparações
    }

    // Registra informações no arquivo de log.
    fprintf(logFile, "802627\tTempoDeExecucao\t%d\n", comparisons);
    fclose(logFile);

    return 0;
}
