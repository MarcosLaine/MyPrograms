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
    Pokemon *stack;
    int top;
    int capacity;
} Stack;

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

void lerDadosDoArquivo(Stack *stack) {
    const char *FILE_NAME = "/tmp/pokemon.csv";
    // const char *FILE_NAME = "/home/marcoslaine/Área de trabalho/Programacao/MyPrograms/TPs/TP3/tmp/pokemon.csv"; //my directory in linux
    FILE *file = fopen(FILE_NAME, "r");
    if (!file) {
        perror("Erro ao abrir o arquivo");
        exit(1);
    }

    char line[2048];
    fgets(line, sizeof(line), file); // Skip header line

    stack->capacity = 1000; // Initial capacity
    stack->stack = (Pokemon *)malloc(stack->capacity * sizeof(Pokemon));
    stack->top = -1; // Initial top of the stack

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

        if (stack->top + 1 >= stack->capacity) {
            stack->capacity *= 2;
            stack->stack = (Pokemon *)realloc(stack->stack, stack->capacity * sizeof(Pokemon));
        }

        stack->stack[++stack->top] = pokemon; // Push onto the stack
    }

    fclose(file);
}

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

void push(Stack *stack, Pokemon *pokemon) {
    if (stack->top + 1 >= stack->capacity) {
        stack->capacity *= 2;
        stack->stack = (Pokemon *)realloc(stack->stack, stack->capacity * sizeof(Pokemon));
    }
    stack->stack[++stack->top] = *pokemon; // Push onto the stack
}

Pokemon pop(Stack *stack) {
    if (stack->top == -1) {
        printf("Erro ao remover: pilha vazia!\n");
        exit(1);
    }
    return stack->stack[stack->top--]; // Pop from the stack
}

int encontrarPokemonPorId(Stack *stack, char *id, Pokemon *resultado) {
    for (int i = 0; i <= stack->top; i++) {
        if (strcmp(stack->stack[i].id, id) == 0) {
            *resultado = stack->stack[i];
            return 1;
        }
    }
    return 0;
}

int main() {
    Stack pokedexCompleto;
    lerDadosDoArquivo(&pokedexCompleto);

    Stack pokedexUsuario;
    pokedexUsuario.stack = malloc(100 * sizeof(Pokemon)); // Capacidade inicial
    pokedexUsuario.top = -1; // Inicializa a pilha vazia
    pokedexUsuario.capacity = 100;

    char entrada[100];

    // Leitura de IDs de Pokémon do usuário
    while (fgets(entrada, sizeof(entrada), stdin)) {
        entrada[strcspn(entrada, "\r\n")] = 0;

        if (strcmp(entrada, "FIM") == 0) {
            break;
        }

        Pokemon pokemonEncontrado;
        if (encontrarPokemonPorId(&pokedexCompleto, entrada, &pokemonEncontrado)) {
            push(&pokedexUsuario, &pokemonEncontrado);
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

        if (strcmp(comando, "I") == 0) { // Inserir
            Pokemon pokemon;
            if (encontrarPokemonPorId(&pokedexCompleto, partes[1], &pokemon)) {
                push(&pokedexUsuario, &pokemon);
            }
        } else if (strcmp(comando, "R") == 0) { // Remover
            Pokemon removido = pop(&pokedexUsuario);
            printf("(R) %s\n", removido.name);
        } else {
            printf("Comando inválido: %s\n", comando);
        }
        numComandos--;
    }

    // Mostrar a Pokedex do usuário com índices
    for (int i = 0; i <= pokedexUsuario.top; i++) {
        imprimirPokemon(&pokedexUsuario.stack[i], i);
    }

    // Liberar memória alocada
    for (int i = 0; i < pokedexCompleto.top; i++) {
        Pokemon *pokemon = &pokedexCompleto.stack[i];
        for (int j = 0; j < pokemon->num_types; j++) {
            free(pokemon->types[j]);
        }
        free(pokemon->types);
        for (int j = 0; j < pokemon->num_abilities; j++) {
            free(pokemon->abilities[j]);
        }
        free(pokemon->abilities);
    }
    free(pokedexCompleto.stack);
    free(pokedexUsuario.stack);

    return 0;
}
