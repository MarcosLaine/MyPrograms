#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

typedef struct Jogador {
    int id;
    char nome[100];
    char altura[100];
    char peso[100];
    char universidade[100];
    char anoNascimento[100];
    char cidadeNascimento[100];
    char estadoNascimento[100];
} Jogador;

int inicio, fim, tamanho;

Jogador clonarJogador(Jogador *jogador) {
    Jogador novo = *jogador;
    return novo;
}

void lerJogador(Jogador *j, char *frase) {}

void imprimirJogador(Jogador jogador, int pos) {
    int altura = atoi(jogador.altura);
    int peso = atoi(jogador.peso);
    int anoNascimento = atoi(jogador.anoNascimento);

    printf("[%d] ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n", pos, jogador.nome,
           altura, peso, anoNascimento, jogador.universidade,
           jogador.cidadeNascimento, jogador.estadoNascimento);
}

char *tratarFrase(char *frase) {
    char data[] = "nao informado";
    char *newFrase = malloc(sizeof(char) * 200);
    if (newFrase == NULL) {
        return NULL;
    }

    strcpy(newFrase, "");

    for (int i = 0; frase[i] != '\0'; i++) {
        if (frase[i] == ',' && frase[i + 1] == ',') {
            strcat(newFrase, ",");
            strcat(newFrase, data);
        } else if (frase[i] == ',' && frase[i + 2] == '\0') {
            strcat(newFrase, ",");
            strcat(newFrase, data);
        } else {
            char temp[2] = {frase[i], '\0'};
            strcat(newFrase, temp);
        }
    }

    return newFrase;
}

void adicionarJogador(Jogador *player, char tokens[8][100]) {
    player->id = atoi(tokens[0]);
    strcpy(player->nome, tokens[1]);
    strcpy(player->altura, tokens[2]);
    strcpy(player->peso, tokens[3]);
    strcpy(player->universidade, tokens[4]);
    strcpy(player->anoNascimento, tokens[5]);
    strcpy(player->cidadeNascimento, tokens[6]);
    strcpy(player->estadoNascimento, tokens[7]);
}

void dividirString(const char *str, char delimiter, char tokens[8][100]) {
    int linha = 0;
    int index = 0;
    while (linha < 8) {
        int i = 0;
        while (1) {
            if (str[index] == delimiter || str[index] == '\0' || str[index] == '\n') {
                tokens[linha][i] = '\0';
                break;
            }

            tokens[linha][i] = str[index];
            i++;
            index++;
        }

        index++;
        linha++;
    }
}

void trocarInteiros(int *a, int *b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

void calcularMediaAltura(Jogador *lista) {
    int mediaAltura;
    int soma = 0;
    int quantidade = 0;
    for (int i = inicio; i != fim; i = (i + 1) % 1000) {
        int altura = atoi(lista[i].altura);
        soma += altura;
        quantidade++;
    }
    if (quantidade != 0) {
        mediaAltura = soma / quantidade;
        printf("%d\n", mediaAltura);
    } else {
        printf("0\n");
    }
}

void inserirJogadorFila(Jogador player, Jogador *fila) {
    if (tamanho == 5) {
        removerJogadorSemPrint(fila);
    }
    tamanho++;
    if ((fim + 1) % 1000 == inicio) { // Fila cheia
        return;
    }

    fila[fim] = player;
    fim = (fim + 1) % 1000;
}

void removerJogadorFila(Jogador *fila) {
    tamanho--;
    printf("(R) %s\n", fila[inicio].nome);

    inicio = (inicio + 1) % 1000;
}

void removerJogadorSemPrint(Jogador *fila) {
    tamanho--;

    inicio = (inicio + 1) % 1000;
}

int main() {
    long start = clock();
    char leraq[600];

    Jogador time[3922];

    FILE *arq = fopen("/tmp/players.csv", "r");

    fgets(leraq, sizeof(leraq), arq);

    for (int i = 0; fgets(leraq, 600, arq) != NULL; i++) {
        char *frase = tratarFrase(leraq);

        char dados[8][100];
        dividirString(frase, ',', dados);
        free(frase);

        adicionarJogador(&time[i], dados);
    }

    // Fila circular aqui
    Jogador fila[1000];
    // FILA CIRCULAR
    tamanho = fim = inicio = 0;

    for (int i = 0; 1; i++) {
        char entrada[100];
        scanf("%s", entrada);
        if (strcmp(entrada, "FIM") == 0) {
            break;
        }
        int id = atoi(entrada);
        inserirJogadorFila(time[id], fila);
        calcularMediaAltura(fila);
    }

    int quantidade;
    scanf("%d", &quantidade);
    for (int i = 0; i < quantidade; i++) {
        char entrada[10];
        scanf("%s", entrada);

        if (strcmp(entrada, "I") == 0) {
            int id;
            scanf("%d", &id);
            inserirJogadorFila(time[id], fila);
            calcularMediaAltura(fila);
        } else if (strcmp(entrada, "R") == 0) {
            removerJogadorFila(fila);
            calcularMediaAltura(fila);
        }
    }
    int count = 0;
    for (int i = inicio; i != fim; i = (i + 1) % 1000, count++) {
        imprimirJogador(fila[i], count);
    }

    fclose(arq);
    return 0;
}
