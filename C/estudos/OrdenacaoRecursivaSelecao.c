#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>

int comparacoes = 0; // Variável para contar o número de comparações
int movimentacoes = 0; // Variável para contar o número de movimentações

// Definição da estrutura de dados Jogador
typedef struct Player {
    char id[100];
    char name[100];
    char weight[100];
    char height[100];
    char university[100];
    char birthYear[100];
    char birthCity[100];
    char birthState[100];
} Player;

// Função para clonar um jogador
Player clone(Player *player) {
    Player newPlayer;
    strcpy(newPlayer.id, player->id);
    strcpy(newPlayer.name, player->name);
    strcpy(newPlayer.height, player->height);
    strcpy(newPlayer.weight, player->weight);
    strcpy(newPlayer.birthYear, player->birthYear);
    strcpy(newPlayer.birthCity, player->birthCity);
    strcpy(newPlayer.birthState, player->birthState);
    strcpy(newPlayer.university, player->university);
    return newPlayer;
}

// Função para imprimir os detalhes de um jogador
void print(Player *player) {
    printf("[%s ## %s ## %s ## %s ## %s ## %s ## %s ## %s]\n",
        player->id, player->name, player->height, player->weight, player->birthYear,
        player->university, player->birthCity, player->birthState);
}

// Função para calcular um valor numérico a partir de uma string
int stringToNumber(char *string) {
    int number = 0;
    for (int i = 0; string[i] != '\0'; i++) {
        number += (int)string[i];
    }
    return number;
}

// Função de seleção recursiva
void recursiveSelection(Player *players, int start, int end) {
    if (start >= end) {
        comparacoes++;
        return;
    }
    int smallest = start;
    for (int i = start + 1; i <= end; i++) {
        comparacoes++; // Incrementa o contador de comparações
        if (strcmp(players[i].name, players[smallest].name) < 0) {
            smallest = i;
        }
    }
    Player temp = players[start];
    players[start] = players[smallest];
    players[smallest] = temp;
    movimentacoes += 3; // Incrementa o contador de movimentações

    recursiveSelection(players, start + 1, end);
}

// Função para ler os dados de um jogador a partir de uma linha
void read(Player *player, char line[1000]) {
    int posicao[7];
    int virgulas = 0;
    for (int i = 0; i < strlen(line); i++) {
        if (line[i] == ',') {
            posicao[virgulas] = i;
            virgulas++;
        }
    }

    int count = 0;
    char id[100];
    char name[100];
    char weight[100];
    char height[100];
    char university[100];
    char birthYear[100];
    char birthCity[100];
    char birthState[100];

    if (posicao[0] - 0 != 0) {
        for (int i = 0; i < posicao[0]; i++) {
            id[count++] = line[i];
        }
        id[count] = '\0';
        strcpy(player->id, id);
    } else {
        strcpy(player->id, "nao informado");
    }

    count = 0;

    if (posicao[1] - (posicao[0]) != 1) {
        for (int j = posicao[0] + 1; j < posicao[1]; j++) {
            name[count++] = line[j];
        }
        name[count] = '\0';
        strcpy(player->name, name);
    } else {
        strcpy(player->name, "nao informado");
    }
    count = 0;

    if (posicao[2] - (posicao[1]) != 1) {
        for (int k = posicao[1] + 1; k < posicao[2]; k++) {
            height[count++] = line[k];
        }
        height[count] = '\0';
        strcpy(player->height, height);
    } else {
        strcpy(player->height, "nao informado");
    }
    count = 0;

    if (posicao[3] - (posicao[2]) != 1) {
        for (int l = posicao[2] + 1; l < posicao[3]; l++) {
            weight[count++] = line[l];
        }
        weight[count] = '\0';
        strcpy(player->weight, weight);
    } else {
        strcpy(player->weight, "nao informado");
    }

    count = 0;
    if (posicao[4] - (posicao[3]) != 1) {
        for (int m = posicao[3] + 1; m < posicao[4]; m++) {
            university[count++] = line[m];
        }
        university[count] = '\0';
        strcpy(player->university, university);
    } else {
        strcpy(player->university, "nao informado");
    }

    count = 0;

    if (posicao[5] - (posicao[4]) != 1) {
        for (int n = posicao[4] + 1; n < posicao[5]; n++) {
            birthYear[count++] = line[n];
        }
        birthYear[count] = '\0';
        strcpy(player->birthYear, birthYear);
    } else {
        strcpy(player->birthYear, "nao informado");
    }

    count = 0;

    if (posicao[6] - (posicao[5]) != 1) {
        for (int o = posicao[5] + 1; o < posicao[6]; o++) {
            birthCity[count++] = line[o];
        }
        birthCity[count] = '\0';

        strcpy(player->birthCity, birthCity);
    } else {
        strcpy(player->birthCity, "nao informado");
    }

    count = 0;

    if ((strlen(line) - 1) - (posicao[6]) != 1) {
        for (int p = posicao[6] + 1; p < strlen(line) - 1; p++) {
            birthState[count++] = line[p];
        }
        birthState[count] = '\0';
        strcpy(player->birthState, birthState);
    } else {
        strcpy(player->birthState, "nao informado");
    }
    count = 0;
}

int main() {
    clock_t start, end;
    char data[1000];
    FILE *file = fopen("/tmp/playersAtualizado.csv", "r");
    Player players[3922];
    char id[100];
    char name[100];
    Player searchResults[1000];
    int count = 0;

    // Lê o cabeçalho do arquivo CSV
    fgets(data, sizeof(data), file);
    int i = 0;

    // Lê os dados dos jogadores do arquivo e armazena na estrutura de dados
    while (fgets(data, 1000, file)) {
        read(&players[i], data);
        i++;
    }

    // Solicita a entrada do ID do jogador e realiza uma busca
    scanf("%s", id);
    while (strcmp(id, "FIM") != 0) {
        for (int j = 0; j < 3922; j++) {
            if (strcmp(players[j].id, id) == 0) {
                searchResults[count] = clone(&players[j]);
                count++;
            }
        }
        scanf("%s", id);
    }

    start = clock();
    recursiveSelection(searchResults, 0, count - 1);

    for (int i = 0; i < count; i++) {
        print(&searchResults[i]);
    }

    end = clock();

    FILE *logFile;
    char logFileName[] = "matricula_selection_recursive.txt";

    logFile = fopen(logFileName, "w");

    if (logFile == NULL) {
        perror("Erro ao abrir o arquivo");
        return 1;
    }

    int studentID = 803627;
    double executionTime = (double)(end - start) / CLOCKS_PER_SEC;

    fprintf(logFile, "Matricula: %d\tTempo: %.2f\tComparacoes: %d\tMovimentacoes: %d\n",
        studentID, executionTime, comparacoes, movimentacoes);

    fclose(logFile);

    fclose(file);
    return 0;
}
