#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <math.h>

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

Jogador clonarJogador(Jogador *jogador) {
    Jogador novo = *jogador;
    return novo;
}

void lerJogador(Jogador *jogador, char *frase) {}

void imprimirJogador(Jogador jogador) {
    int altura = atoi(jogador.altura);
    int peso = atoi(jogador.peso);
    int anoNascimento = atoi(jogador.anoNascimento);

    printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n", jogador.id,
        jogador.nome, altura, peso, anoNascimento, jogador.universidade,
        jogador.cidadeNascimento, jogador.estadoNascimento);
}

char *tratarFrase(char *frase) {
    char data[] = "nao informado";
    char *novaFrase = malloc(sizeof(char) * 200);
    if (novaFrase == NULL) {
        return NULL;
    }

    strcpy(novaFrase, "");

    for (int i = 0; frase[i] != '\0'; i++) {
        if (frase[i] == ',' && frase[i + 1] == ',') {
            strcat(novaFrase, ",");
            strcat(novaFrase, data);
        } else if (frase[i] == ',' && frase[i + 2] == '\0') {
            strcat(novaFrase, ",");
            strcat(novaFrase, data);
        } else {
            char temp[2] = {frase[i], '\0'};
            strcat(novaFrase, temp);
        }
    }

    return novaFrase;
}

void adicionarJogador(Jogador *jogador, char tokens[8][100]) {
    jogador->id = atoi(tokens[0]);
    strcpy(jogador->nome, tokens[1]);
    strcpy(jogador->altura, tokens[2]);
    strcpy(jogador->peso, tokens[3]);
    strcpy(jogador->universidade, tokens[4]);
    strcpy(jogador->anoNascimento, tokens[5]);
    strcpy(jogador->cidadeNascimento, tokens[6]);
    strcpy(jogador->estadoNascimento, tokens[7]);
}

void dividirString(const char *str, char delimitador, char tokens[8][100]) {
    int linha = 0;
    int index = 0;
    while (linha < 8) {
        int i = 0;
        while (1) {
            if (str[index] == delimitador || str[index] == '\0' || str[index] == '\n') {
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

///////////////////////// Lista Ligada /////////////////////////

// Definindo a estrutura Node
typedef struct Node {
    struct Node* proximo;
    struct Node* anterior;
    struct Jogador Valor;
} Node;

// Definindo a estrutura Lista
typedef struct Lista {
    struct Node* primeiro;
    struct Node* ultimo;
} Lista;

void inicializarLista(Lista* lista) {
    lista->primeiro = lista->ultimo = NULL;
}

// Funções para manipulação da lista e ordenação
struct Node* criarNode(struct Jogador x) {
    struct Node* novoNode = (struct Node*)malloc(sizeof(struct Node));
    novoNode->Valor = x;
    novoNode->proximo = novoNode->anterior = NULL;
    return novoNode;
}

void inserirNaLista(Lista* lista, Jogador x) {
    struct Node* temp = criarNode(x);

    if (lista->primeiro == NULL) {
        lista->primeiro = lista->ultimo = temp;
    } else {
        temp->anterior = lista->ultimo;
        lista->ultimo->proximo = temp;
        lista->ultimo = temp;
    }
}

void imprimirLista(Lista* lista) {
    struct Node* temp = lista->primeiro;
    while (temp != NULL) {
        imprimirJogador(temp->Valor);
        temp = temp->proximo;
    }
}

int tamanho(struct Lista* lista) {
    int count = 0;
    struct Node* temp = lista->primeiro;
    while (temp != NULL) {
        count++;
        temp = temp->proximo;
    }
    return count;
}

void parseArray(struct Lista* lista, struct Jogador array[]) {
    struct Node* temp = lista->primeiro;
    int i = 0;
    while (temp != NULL) {
        array[i] = temp->Valor;
        i++;
        temp = temp->proximo;
    }
}

void arrayParaLista(struct Jogador array[], int tamanho, struct Lista* lista) {
    Lista* novaLista = malloc(sizeof(Lista));

    for (int i = 0; i < tamanho; i++) {
        inserirNaLista(novaLista, array[i]);
    }
    lista->primeiro = novaLista->primeiro;
    lista->ultimo = novaLista->ultimo;
}

int comparar(struct Jogador vet[], int i, struct Jogador pivot) {
    int resultado = strcmp(vet[i].estadoNascimento, pivot.estadoNascimento);
    if (resultado != 0) {
        return resultado;
    }
    return strcmp(vet[i].nome, pivot.nome);
}

void trocar(Jogador array[], int i, int j) {
    struct Jogador temp = array[i];
    array[i] = array[j];
    array[j] = temp;
}

void QuickSort(struct Jogador array[], int esq, int dir) {
    for (int i = 0; i < dir; i++) {
        for (int j = i + 1; j <= dir; j++) {
            if (comparar(array, i, array[j]) > 0) {
                trocar(array, i, j);
            }
        }
    }
}

int main() {
    char linha[600];

    Jogador time[3922];

    FILE* arq = fopen("/tmp/players.csv", "r");

    fgets(linha, sizeof(linha), arq);

    for (int i = 0; fgets(linha, 600, arq) != NULL; i++) {
        char* frase = tratarFrase(linha);

        char dados[8][100];
        dividirString(frase, ',', dados);
        free(frase);

        adicionarJogador(&time[i], dados);
    }

    // Lista circular aqui
    Lista* lista = malloc(sizeof(Lista));
    inicializarLista(lista);

    for (int i = 0; 1; i++) {
        char entrada[100];
        scanf("%s", entrada);
        if (strcmp(entrada, "FIM") == 0) {
            break;
        }
        int id = atoi(entrada);
        inserirNaLista(lista, time[id]);
    }
    int tamanhoLista = tamanho(lista);
    Jogador array[tamanhoLista];
    parseArray(lista, array);
    QuickSort(array, 0, tamanhoLista - 1);
    arrayParaLista(array, tamanhoLista, lista);
    imprimirLista(lista);

    fclose(arq);
    return 0;
}
