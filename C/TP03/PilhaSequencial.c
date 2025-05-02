#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

typedef struct Player {
    int id;
    char name[100];
    char height[100];
    char weight[100];
    char university[100];
    char birthYear[100];
    char birthCity[100];
    char birthState[100];
} Player;

int start, end, size;

Player clone(Player *player) {
    Player newPlayer = *player;
    return newPlayer;
}

void read(Player *player, char *line) {
    // Implemente a função de leitura aqui, se necessário.
}

void print(Player player, int pos) {
    int height = atoi(player.height);
    int weight = atoi(player.weight);
    int birthYear = atoi(player.birthYear);

    printf("[%d] ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n", pos, player.name,
           height, weight, birthYear, player.university,
           player.birthCity, player.birthState);
}

char *processLine(char *line) {
    char data[] = "nao informado";
    char *newLine = (char *)malloc(sizeof(char) * 200);
    if (newLine == NULL) {
        return NULL;
    }

    strcpy(newLine, "");

    for (int i = 0; line[i] != '\0'; i++) {
        if (line[i] == ',' && line[i + 1] == ',') {
            strcat(newLine, ",");
            strcat(newLine, data);
        } else if (line[i] == ',' && line[i + 2] == '\0') {
            strcat(newLine, ",");
            strcat(newLine, data);
        } else {
            char temp[2] = {line[i], '\0'};
            strcat(newLine, temp);
        }
    }

    return newLine;
}

void addPlayer(Player *player, char tokens[8][100]) {
    player->id = atoi(tokens[0]);
    strcpy(player->name, tokens[1]);
    strcpy(player->height, tokens[2]);
    strcpy(player->weight, tokens[3]);
    strcpy(player->university, tokens[4]);
    strcpy(player->birthYear, tokens[5]);
    strcpy(player->birthCity, tokens[6]);
    strcpy(player->birthState, tokens[7]);
}

void split(const char *str, char delimiter, char tokens[8][100]) {
    int line = 0;
    int index = 0;
    while (line < 8) {
        int i = 0;
        while (1) {
            if (str[index] == delimiter || str[index] == '\0' || str[index] == '\n') {
                tokens[line][i] = '\0';
                break;
            }

            tokens[line][i] = str[index];
            i++;
            index++;
        }

        index++;
        line++;
    }
}

void swap(int *a, int *b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

void calcularMedia(Player *queue) {
    int mediaAltura;
    int soma = 0;
    int quantidade = 0;
    for (int i = start; i != end; i = (i + 1) % 1000) {
        int altura = atoi(queue[i].height);
        soma += altura;
        quantidade++;
    }
    mediaAltura = soma / quantidade;
    printf("%d\n", mediaAltura);
}

void insert(Player player, Player *queue) {
    if (size == 5) {
        removeWithoutPrint(queue);
    }
    size++;
    if ((end + 1) % 1000 == start) {
        return; // Fila cheia
    }

    queue[end] = player;
    end = (end + 1) % 1000;
}

void remove(Player *queue) {
    size--;
    printf("(R) %s\n", queue[start].name);

    start = (start + 1) % 1000;
}

void removeWithoutPrint(Player *queue) {
    size--;

    start = (start + 1) % 1000;
}

int main() {
    long startTime = clock();
    char line[600];

    Player players[3922];

    FILE *file = fopen("/tmp/players.csv", "r");

    fgets(line, sizeof(line), file);

    for (int i = 0; fgets(line, 600, file) != NULL; i++) {
        char *processedLine = processLine(line);

        char data[8][100];
        split(processedLine, ',', data);
        free(processedLine);

        addPlayer(&players[i], data);
    }

    // Fila circular aqui
    Player queue[1000];
    // FILA CIRCULAR
    size = end = start = 0;

    for (int i = 0; 1; i++) {
        char input[100];
        scanf("%s", input);
        if (strcmp(input, "FIM") == 0) {
            break;
        }
        int id = atoi(input);
        insert(players[id], queue);
        calcularMedia(queue);
    }

    int quantity;
    scanf("%d", &quantity);
    for (int i = 0; i < quantity; i++) {
        char input[10];
        scanf("%s", input);

        if (strcmp(input, "I") == 0) {
            int id;
            scanf("%d", &id);
            insert(players[id], queue);
            calcularMedia(queue);
        } else if (strcmp(input, "R") == 0) {
            remove(queue);
        }
    }
    int count = 0;
    for (int i = start; i != end; i = (i + 1) % 1000, count++) {
        print(queue[i], count);
    }

    fclose(file);
    return 0;
}
