#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

// Definindo a estrutura do Jogador
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

// Função para validar uma string
char* validateString(char str[]) {
    if (!str || strcmp(str, "\0") == 0) {
        return "nao informado";
    }
    return str;
}

// Função para clonar um jogador
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

// Função para imprimir os dados de um jogador
void printPlayer(Player* player) {
    printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n",
           player->player_id, player->player_name, player->player_height, player->player_weight,
           player->birth_year, player->player_university, player->birth_city, player->birth_state);
}

// Função para ler os dados de um jogador a partir de uma string
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

// Função para ler dados de jogadores a partir de um arquivo
void readPlayersFromFile(Player* players[]) {
    FILE *file = NULL;
    file = fopen("/tmp/players.csv", "r");
    char *data = NULL;
    data = (char*) malloc(sizeof(char) * 1000);
    fgets(data, 1000, file);
    for (int i = 0; i < 3922; i++) {
        fgets(data, 1000, file);
        players[i] = (Player*) malloc(sizeof(Player));
        readPlayerData(data, players[i]);
    }
    free(data);
}

// Função para verificar se a entrada é "FIM" para encerrar o programa
bool isEnd(char *str) {
    if (str[0] == 'F' && str[1] == 'I' && str[2] == 'M')
        return false;
    else
        return true;
}

int main() {
    int num_players = 3922;
    Player* players[3922];
    readPlayersFromFile(players);
    int position;
    char input[4];
    scanf("%s", input);
    while (isEnd(input)) {
        position = atoi(input);
        printPlayer(players[position]);
        scanf("%s", input);
    }
}
