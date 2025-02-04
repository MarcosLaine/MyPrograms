#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <ctype.h>

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

typedef struct {
    Pokemon *fila;
    int front;
    int rear;
    int size;
    int capacity;
} FilaCircular;

char *trim(char *str) {
    while (isspace((unsigned char)*str))
        str++;
    if (*str == 0)
        return str;
    char *end = str + strlen(str) - 1;
    while (end > str && isspace((unsigned char)*end))
        end--;
    end[1] = '\0';
    return str;
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

void initFilaCircular(FilaCircular *fila) {
    fila->capacity = 5; // Tamanho da fila circular
    fila->fila = malloc(fila->capacity * sizeof(Pokemon));
    fila->front = 0;
    fila->rear = -1;
    fila->size = 0;
}

int isFull(FilaCircular *fila) {
    return fila->size == fila->capacity;
}

int isEmpty(FilaCircular *fila) {
    return fila->size == 0;
}

void enqueue(FilaCircular *fila, Pokemon *pokemon) {
    if (isFull(fila)) {
        Pokemon removido = fila->fila[fila->front]; // Remove o Pokémon da frente
        printf("(R) %s\n", removido.name); // Imprime os detalhes do Pokémon removido
        fila->front = (fila->front + 1) % fila->capacity; // Move a frente para o próximo
    }

    fila->rear = (fila->rear + 1) % fila->capacity; // Move o rear para a próxima posição
    fila->fila[fila->rear] = *pokemon; // Insere o Pokémon na fila
    fila->size++;

    // Calcula a média do captureRate
    double totalCaptureRate = 0;
    for (int i = 0; i < fila->size; i++) {
        totalCaptureRate += fila->fila[(fila->front + i) % fila->capacity].captureRate;
    }
    int mediaCaptureRate = (int)(totalCaptureRate / fila->size + 0.5); // Média arredondada
    printf("Média: %d\n", mediaCaptureRate);
}

void mostrarFila(FilaCircular *fila) {
    for (int i = 0; i < fila->size; i++) {
        Pokemon *pokemon = &fila->fila[(fila->front + i) % fila->capacity];
        char dateStr[20];
        strftime(dateStr, sizeof(dateStr), "%d/%m/%Y", &pokemon->captureDate);
        
        printf("[%d] [#%s -> %s: %s - [", i, pokemon->id, pokemon->name, pokemon->description);
        for (int j = 0; j < pokemon->num_types; j++) {
            printf("%s", pokemon->types[j]);
            if (j < pokemon->num_types - 1) {
                printf(", ");
            }
        }
        printf("] - [");
        for (int j = 0; j < pokemon->num_abilities; j++) {
            printf("'%s'", pokemon->abilities[j]);
            if (j < pokemon->num_abilities - 1) {
                printf(", ");
            }
        }
        printf("] - %.1fkg - %.1fm - %d%% - %s - %d gen] - %s\n",
               pokemon->weight, pokemon->height, pokemon->captureRate,
               pokemon->isLegendary ? "true" : "false",
               pokemon->generation, dateStr);
    }
}

int encontrarPokemonPorId(FilaCircular *fila, char *id, Pokemon *resultado) {
    for (int i = 0; i < fila->size; i++) {
        if (strcmp(fila->fila[(fila->front + i) % fila->capacity].id, id) == 0) {
            *resultado = fila->fila[(fila->front + i) % fila->capacity];
            return 1;
        }
    }
    return 0;
}

void lerDadosDoArquivo(FilaCircular *fila) {
    const char *FILE_NAME = "/tmp/pokemon.csv";
    // const char *FILE_NAME = "/home/marcoslaine/Área de trabalho/Programacao/MyPrograms/TPs/TP3/tmp/pokemon.csv"; //my directory in linux
    FILE *file = fopen(FILE_NAME, "r");
    if (!file) {
        perror("Erro ao abrir o arquivo");
        exit(1);
    }

    char line[2048];
    fgets(line, sizeof(line), file); // Skip header line

    while (fgets(line, sizeof(line), file)) {
        line[strcspn(line, "\r\n")] = 0; // Remove newline character

        Pokemon pokemon;
        memset(&pokemon, 0, sizeof(Pokemon));

        char *blocos[10];
        int num_blocos = split_at_char(line, '"', blocos, 10);

        if (num_blocos < 3) {
            printf("Linha mal formatada ou incompleta: %s\n", line);
            continue;
        }

        char *atributo[20];
        int num_atributo = split_at_char(blocos[0], ',', atributo, 20);
        for (int i = 0; i < num_atributo; i++) {
            atributo[i] = trim(atributo[i]);
        }

        if (num_atributo < 6) {
            printf("Linha mal formatada ou incompleta: %s\n", line);
            continue;
        }

        strcpy(pokemon.id, atributo[0]);
        pokemon.generation = atoi(atributo[1]);
        strcpy(pokemon.name, atributo[2]);
        strcpy(pokemon.description, atributo[3]);

        pokemon.types = malloc(2 * sizeof(char *));
        pokemon.num_types = 0;
        if (strlen(trim(atributo[4])) > 0) {
            pokemon.types[pokemon.num_types++] = strdup(trim(atributo[4]));
        }
        if (num_atributo > 5 && strlen(trim(atributo[5])) > 0) {
            pokemon.types[pokemon.num_types++] = strdup(trim(atributo[5]));
        }

        char *abilities_str = blocos[1];
        char *abilities_cleaned = (char *)malloc(strlen(abilities_str) + 1);
        int idx = 0;
        for (int i = 0; abilities_str[i] != '\0'; i++) {
            if (abilities_str[i] != '[' && abilities_str[i] != ']') {
                abilities_cleaned[idx++] = abilities_str[i];
            }
        }
        abilities_cleaned[idx] = '\0';

        char *abilities_tokens[20];
        int num_abilities_tokens = split_at_char(abilities_cleaned, ',', abilities_tokens, 20);
        for (int i = 0; i < num_abilities_tokens; i++) {
            abilities_tokens[i] = trim(abilities_tokens[i]);
        }

        pokemon.abilities = malloc(num_abilities_tokens * sizeof(char *));
        pokemon.num_abilities = num_abilities_tokens;
        for (int i = 0; i < num_abilities_tokens; i++) {
            pokemon.abilities[i] = strdup(abilities_tokens[i]);
        }

        free(abilities_cleaned);

        char *resto = blocos[2];
        if (resto[0] == ',') {
            resto++;
        }

        char *atributo3[20];
        int num_atributo3 = split_at_char(resto, ',', atributo3, 20);
        for (int i = 0; i < num_atributo3; i++) {
            atributo3[i] = trim(atributo3[i]);
        }

        if (num_atributo3 > 0) {
            pokemon.weight = atof(atributo3[0]);
        }
        if (num_atributo3 > 1) {
            pokemon.height = atof(atributo3[1]);
        }
        if (num_atributo3 > 2) {
            pokemon.captureRate = atoi(atributo3[2]);
        }
        if (num_atributo3 > 3) {
            pokemon.isLegendary = atoi(atributo3[3]);
        }
        if (num_atributo3 > 4) {
            char *captureDateStr = atributo3[4];
            if (strptime(captureDateStr, "%d/%m/%Y", &pokemon.captureDate) == NULL) {
                printf("Erro ao parsear a data: %s\n", captureDateStr);
            }
        }

        enqueue(fila, &pokemon); // Insere o Pokémon na fila
    }

    fclose(file);
}

int main() {
    FilaCircular fila;
    initFilaCircular(&fila);
    lerDadosDoArquivo(&fila);

    char entrada[100];

    // Leitura de IDs de Pokémon do usuário
    while (1) {
        scanf("%s", entrada); // Lê a entrada como uma string

        if (strcmp(entrada, "FIM") == 0) {
            break; // Encerra a leitura
        }

        Pokemon pokemonEncontrado;
        if (encontrarPokemonPorId(&fila, entrada, &pokemonEncontrado)) {
            enqueue(&fila, &pokemonEncontrado); // Insere na fila
        } else {
            printf("Pokemon com ID %s não encontrado\n", entrada);
        }
    }

    // Mostrar a fila após inserções
    mostrarFila(&fila);

    // Liberar memória alocada
    for (int i = 0; i < fila.size; i++) {
        free(fila.fila[(fila.front + i) % fila.capacity].types);
        free(fila.fila[(fila.front + i) % fila.capacity].abilities);
    }
    free(fila.fila);

    return 0;
}
