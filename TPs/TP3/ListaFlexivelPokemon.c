#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <ctype.h>

// Estruturas
typedef struct {
    char id[20];
    int generation;
    char name[100];
    char description[1000];
    char **types;
    int num_types;
    char **abilities;
    int num_abilities;
    double weight;
    double height;
    int captureRate;
    int isLegendary;
    struct tm captureDate;
} Pokemon;

typedef struct Celula {
    Pokemon elemento;
    struct Celula *prox;
} Celula;

// Variáveis globais da lista
Celula *primeiro;
Celula *ultimo;

// Funções de lista
Celula *novaCelula(Pokemon elemento) {
    Celula *nova = (Celula *)malloc(sizeof(Celula));
    nova->elemento = elemento;
    nova->prox = NULL;
    return nova;
}

void start() {
    primeiro = novaCelula((Pokemon){0}); // Celula cabeça com Pokémon vazio
    ultimo = primeiro;
}

void inserirInicio(Pokemon x) {
    Celula *tmp = novaCelula(x);
    tmp->prox = primeiro->prox;
    primeiro->prox = tmp;
    if (primeiro == ultimo) {
        ultimo = tmp;
    }
}

void inserirFim(Pokemon x) {
    ultimo->prox = novaCelula(x);
    ultimo = ultimo->prox;
}

void inserirEmPosicao(Pokemon x, int pos) {
    int tam = 0;
    Celula *i;
    for (i = primeiro->prox; i != NULL; i = i->prox) tam++;

    if (pos < 0 || pos > tam) {
        printf("Erro ao inserir posicao (%d/tamanho = %d) invalida!\n", pos, tam);
        exit(1);
    } else if (pos == 0) {
        inserirInicio(x);
    } else if (pos == tam) {
        inserirFim(x);
    } else {
        Celula *j = primeiro;
        for (int k = 0; k < pos; k++, j = j->prox);

        Celula *tmp = novaCelula(x);
        tmp->prox = j->prox;
        j->prox = tmp;
    }
}

Pokemon removerInicio() {
    if (primeiro == ultimo) {
        printf("Erro ao remover: lista vazia!\n");
        exit(1);
    }

    Celula *tmp = primeiro->prox;
    Pokemon resp = tmp->elemento;
    primeiro->prox = tmp->prox;
    if (tmp == ultimo) {
        ultimo = primeiro;
    }
    free(tmp);
    return resp;
}

Pokemon removerFim() {
    if (primeiro == ultimo) {
        printf("Erro ao remover: lista vazia!\n");
        exit(1);
    }

    Celula *i;
    for (i = primeiro; i->prox != ultimo; i = i->prox);

    Pokemon resp = ultimo->elemento;
    free(ultimo);
    ultimo = i;
    ultimo->prox = NULL;
    return resp;
}

Pokemon removerDePosicao(int pos) {
    int tam = 0;
    Celula *i;
    for (i = primeiro->prox; i != NULL; i = i->prox) tam++;

    if (pos < 0 || pos >= tam) {
        printf("Erro ao remover posicao (%d/tamanho = %d) invalida!\n", pos, tam);
        exit(1);
    } else if (pos == 0) {
        return removerInicio();
    } else if (pos == tam - 1) {
        return removerFim();
    } else {
        Celula *j = primeiro;
        for (int k = 0; k < pos; k++, j = j->prox);

        Celula *tmp = j->prox;
        Pokemon resp = tmp->elemento;
        j->prox = tmp->prox;
        free(tmp);
        return resp;
    }
}

// Função para imprimir o Pokémon
void imprimirPokemon(Pokemon *pokemon, int index) {
    char dateStr[20];
    strftime(dateStr, sizeof(dateStr), "%d/%m/%Y", &pokemon->captureDate);

    printf("[%d] [#%s -> %s: %s - [", index, pokemon->id, pokemon->name, pokemon->description);
    for (int i = 0; i < pokemon->num_types; i++) {
        printf("'%s'", pokemon->types[i]);
        if (i < pokemon->num_types - 1) {
            printf(", ");
        }
    }
    printf("] - [");
    for (int i = 0; i < pokemon->num_abilities; i++) {
        printf("%s", pokemon->abilities[i]);
        if (i < pokemon->num_abilities - 1) {
            printf(", ");
        }
    }
    printf("] - %.1fkg - %.1fm - %d%% - %s - %d gen] - %s\n",
           pokemon->weight, pokemon->height, pokemon->captureRate,
           pokemon->isLegendary ? "true" : "false",
           pokemon->generation, dateStr);
}

// Função para encontrar Pokémon por ID
int encontrarPokemonPorId(Celula *pokedex, char *id, Pokemon *resultado) {
    Celula *i;
    for (i = pokedex->prox; i != NULL; i = i->prox) {
        if (strcmp(i->elemento.id, id) == 0) {
            *resultado = i->elemento;
            return 1;
        }
    }
    return 0;
}

// Função para ler os dados do arquivo
void lerDadosDoArquivo() {
    const char *FILE_NAME = "/tmp/pokemon.csv";
    FILE *file = fopen(FILE_NAME, "r");
    if (!file) {
        perror("Erro ao abrir o arquivo");
        exit(1);
    }

    char line[2048];
    char *result;

    // Skip header line
    result = fgets(line, sizeof(line), file);

    while (fgets(line, sizeof(line), file)) {
        line[strcspn(line, "\r\n")] = 0; // Remove newline character

        Pokemon pokemon;
        memset(&pokemon, 0, sizeof(Pokemon));

        // Código de parsing dos atributos (adaptar conforme necessário)
        // Adicione a lógica completa de parsing aqui conforme o arquivo

        // Exemplo: Adicione o Pokémon na lista ligada
        inserirFim(pokemon);
    }

    fclose(file);
}

int split_at_char(char *str, char delimiter, char **tokens, int max_tokens) {
    int count = 0;
    char *start = str;
    while (*str) {
        if (*str == delimiter) {
            *str = '\0';
            tokens[count++] = start;
            start = str + 1;
            if (count >= max_tokens)
                return count;
        }
        str++;
    }
    tokens[count++] = start;
    return count;
}

int main() {
    start();

    // Inicializa a Pokédex completa e lê os dados do arquivo
    lerDadosDoArquivo();

    char entrada[100];

    // Leitura de IDs de Pokémon do usuário
    while (fgets(entrada, sizeof(entrada), stdin)) {
        entrada[strcspn(entrada, "\r\n")] = 0;
        if (strcmp(entrada, "FIM") == 0) {
            break;
        }

        Pokemon pokemonEncontrado;
        if (encontrarPokemonPorId(primeiro, entrada, &pokemonEncontrado)) {
            inserirFim(pokemonEncontrado);
        } else {
            printf("Pokemon com ID %s não encontrado\n", entrada);
        }
    }

    int numComandos;
    scanf("%d", &numComandos);
    getchar();

    // Processar comandos de manipulação
    while (numComandos > 0) {
        fgets(entrada, sizeof(entrada), stdin);
        entrada[strcspn(entrada, "\r\n")] = 0;

        char *partes[3];
        int num_partes = split_at_char(entrada, ' ', partes, 3);
        char *comando = partes[0];

        if (strcmp(comando, "II") == 0) { // Inserir no início
            Pokemon pokemon;
            if (encontrarPokemonPorId(primeiro, partes[1], &pokemon)) {
                inserirInicio(pokemon);
            }
        } else if (strcmp(comando, "IF") == 0) { // Inserir no fim
            Pokemon pokemon;
            if (encontrarPokemonPorId(primeiro, partes[1], &pokemon)) {
                inserirFim(pokemon);
            }
        } else if (strcmp(comando, "I*") == 0) { // Inserir em posição específica
            int posicao = atoi(partes[1]);
            Pokemon pokemon;
            if (encontrarPokemonPorId(primeiro, partes[2], &pokemon)) {
                inserirEmPosicao(pokemon, posicao);
            }
        } else if (strcmp(comando, "RI") == 0) { // Remover do início
            Pokemon removido = removerInicio();
            printf("(R) %s\n", removido.name);
        } else if (strcmp(comando, "RF") == 0) { // Remover do fim
            Pokemon removido = removerFim();
            printf("(R) %s\n", removido.name);
        } else if (strcmp(comando, "R*") == 0) { // Remover de posição específica
            int posicao = atoi(partes[1]);
            Pokemon removido = removerDePosicao(posicao);
            printf("(R) %s\n", removido.name);
        } else {
            printf("Comando inválido: %s\n", comando);
        }
        numComandos--;
    }

    // Mostrar a Pokédex do usuário com índices
    int index = 0;
    Celula *i;
    for (i = primeiro->prox; i != NULL; i = i->prox) {
        imprimirPokemon(&i->elemento, index++);
    }

    // Limpeza de memória
    while (primeiro != ultimo) {
        removerInicio();
    }
    free(primeiro);

    return 0;
}
