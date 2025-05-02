#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>

#define FILE_NAME "/tmp/pokemon.csv"
#define MAX_TYPES 2
#define MAX_ABILITIES 10

// Estrutura do Pokémon
typedef struct {
    char id[10];
    int generation;
    char name[50];
    char description[255];
    char types[MAX_TYPES][30];
    int typeCount;
    char abilities[MAX_ABILITIES][50];
    int abilityCount;
    double weight;
    double height;
    int captureRate;
    bool isLegendary;
    char captureDate[11];
} Pokemon;

// Nó da árvore AVL
typedef struct No {
    Pokemon elemento;
    struct No *esq, *dir;
    int altura;
} No;

No *raiz = NULL;

// Lista de Pokémon
typedef struct {
    Pokemon *lista;
    int tamanho;
    int capacidade;
} ListaPokemon;

ListaPokemon criarListaPokemon(int capacidade) {
    ListaPokemon lista;
    lista.lista = (Pokemon *)malloc(capacidade * sizeof(Pokemon));
    lista.tamanho = 0;
    lista.capacidade = capacidade;
    return lista;
}

void adicionarNaLista(ListaPokemon *lista, Pokemon p) {
    if (lista->tamanho >= lista->capacidade) {
        lista->capacidade *= 2;
        lista->lista = (Pokemon *)realloc(lista->lista, lista->capacidade * sizeof(Pokemon));
    }
    lista->lista[lista->tamanho++] = p;
}

// Funções auxiliares para manipular a árvore AVL
int altura(No *n) {
    return n == NULL ? 0 : n->altura;
}

int max(int a, int b) {
    return (a > b) ? a : b;
}

No *novoNo(Pokemon elemento) {
    No *novo = (No *)malloc(sizeof(No));
    novo->elemento = elemento;
    novo->esq = novo->dir = NULL;
    novo->altura = 1;
    return novo;
}

No *rotacaoDireita(No *y) {
    No *x = y->esq;
    No *T2 = x->dir;

    x->dir = y;
    y->esq = T2;

    y->altura = max(altura(y->esq), altura(y->dir)) + 1;
    x->altura = max(altura(x->esq), altura(x->dir)) + 1;

    return x;
}

No *rotacaoEsquerda(No *x) {
    No *y = x->dir;
    No *T2 = y->esq;

    y->esq = x;
    x->dir = T2;

    x->altura = max(altura(x->esq), altura(x->dir)) + 1;
    y->altura = max(altura(y->esq), altura(y->dir)) + 1;

    return y;
}

int getBalance(No *n) {
    return n == NULL ? 0 : altura(n->esq) - altura(n->dir);
}

No *inserir(No *raiz, Pokemon x) {
    if (raiz == NULL)
        return novoNo(x);

    if (strcmp(x.name, raiz->elemento.name) < 0)
        raiz->esq = inserir(raiz->esq, x);
    else if (strcmp(x.name, raiz->elemento.name) > 0)
        raiz->dir = inserir(raiz->dir, x);
    else {
        printf("ERRO: Elemento duplicado.\n");
        return raiz;
    }

    raiz->altura = 1 + max(altura(raiz->esq), altura(raiz->dir));

    int balance = getBalance(raiz);

    if (balance > 1 && strcmp(x.name, raiz->esq->elemento.name) < 0)
        return rotacaoDireita(raiz);

    if (balance < -1 && strcmp(x.name, raiz->dir->elemento.name) > 0)
        return rotacaoEsquerda(raiz);

    if (balance > 1 && strcmp(x.name, raiz->esq->elemento.name) > 0) {
        raiz->esq = rotacaoEsquerda(raiz->esq);
        return rotacaoDireita(raiz);
    }

    if (balance < -1 && strcmp(x.name, raiz->dir->elemento.name) < 0) {
        raiz->dir = rotacaoDireita(raiz->dir);
        return rotacaoEsquerda(raiz);
    }

    return raiz;
}

bool pesquisar(No *raiz, char *nome) {
    if (raiz == NULL) {
        printf("NAO\n");
        return false;
    } else if (strcmp(nome, raiz->elemento.name) == 0) {
        printf("SIM\n");
        return true;
    } else if (strcmp(nome, raiz->elemento.name) < 0) {
        printf("esq ");
        return pesquisar(raiz->esq, nome);
    } else {
        printf("dir ");
        return pesquisar(raiz->dir, nome);
    }
}

// Funções para manipular Pokémon
Pokemon parsePokemon(char *linha) {
    Pokemon p;
    char *token;

    token = strtok(linha, ",");
    strcpy(p.id, token);

    token = strtok(NULL, ",");
    p.generation = atoi(token);

    token = strtok(NULL, ",");
    strcpy(p.name, token);

    token = strtok(NULL, ",");
    strcpy(p.description, token);

    p.typeCount = 0;
    for (int i = 0; i < MAX_TYPES; i++) {
        token = strtok(NULL, ",");
        if (token != NULL && strlen(token) > 0) {
            strcpy(p.types[p.typeCount++], token);
        }
    }

    p.abilityCount = 0;
    token = strtok(NULL, ",");
    token = strtok(token, "[]"); // Remove colchetes
    while (token != NULL) {
        strcpy(p.abilities[p.abilityCount++], token);
        token = strtok(NULL, ",");
    }

    token = strtok(NULL, ",");
    p.weight = atof(token);

    token = strtok(NULL, ",");
    p.height = atof(token);

    token = strtok(NULL, ",");
    p.captureRate = atoi(token);

    token = strtok(NULL, ",");
    p.isLegendary = atoi(token);

    token = strtok(NULL, ",");
    strcpy(p.captureDate, token);

    return p;
}

void lerPokemons(ListaPokemon *lista) {
    FILE *file = fopen(FILE_NAME, "r");
    if (!file) {
        perror("Erro ao abrir o arquivo");
        exit(1);
    }

    char linha[1024];
    fgets(linha, sizeof(linha), file); // Ignorar cabeçalho

    while (fgets(linha, sizeof(linha), file) != NULL) {
        linha[strcspn(linha, "\r\n")] = 0; // Remove quebra de linha
        Pokemon p = parsePokemon(linha);
        adicionarNaLista(lista, p);
    }

    fclose(file);
}

// Função principal
int main() {
    char entrada[50];

    // Cria a lista de Pokémon
    ListaPokemon lista = criarListaPokemon(100);
    lerPokemons(&lista);

    // Leitura de IDs do usuário e inserção na árvore
    while (true) {
        scanf("%s", entrada);
        if (strcmp(entrada, "FIM") == 0) break;

        for (int i = 0; i < lista.tamanho; i++) {
            if (strcmp(lista.lista[i].id, entrada) == 0) {
                raiz = inserir(raiz, lista.lista[i]);
                break;
            }
        }
    }

    // Pesquisar Pokémon
    while (true) {
        scanf("%s", entrada);
        if (strcmp(entrada, "FIM") == 0) break;

        printf("%s\n", entrada);
        pesquisar(raiz, entrada);
    }

    // Liberação de memória
    free(lista.lista);
    return 0;
}
