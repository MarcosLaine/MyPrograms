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
    Pokemon *listaDePokemons;
    int numPokemons;
} Pokedex;

char *trim(char *str) {
    while (isspace((unsigned char)*str)) str++;
    if (*str == 0) return str;
    char *end = str + strlen(str) - 1;
    while (end > str && isspace((unsigned char)*end)) end--;
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
            if (count >= max_tokens) return count;
        }
        str++;
    }
    tokens[count++] = start;
    return count;
}

void lerDadosDoArquivo(Pokedex *pokedex) {
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

    int capacity = 1000; // Initial capacity for the array
    pokedex->listaDePokemons = (Pokemon *)malloc(capacity * sizeof(Pokemon));
    pokedex->numPokemons = 0;

    while (fgets(line, sizeof(line), file)) {
        // Remove newline character at end of line
        line[strcspn(line, "\r\n")] = 0;

        // Parse the line into a Pokemon struct
        Pokemon pokemon;
        memset(&pokemon, 0, sizeof(Pokemon));

        // Split the line at double quotes
        char *blocos[10];
        int num_blocos = split_at_char(line, '"', blocos, 10);

        if (num_blocos < 3) {
            printf("Linha mal formatada ou incompleta: %s\n", line);
            continue;
        }

        // Split blocos[0] at commas
        char *atributo[20];
        int num_atributo = split_at_char(blocos[0], ',', atributo, 20);

        // Trim leading/trailing spaces from atributo
        for (int i = 0; i < num_atributo; i++) {
            atributo[i] = trim(atributo[i]);
        }

        // Parse id
        if (num_atributo < 6) {
            printf("Linha mal formatada ou incompleta: %s\n", line);
            continue;
        }

        char *id = atributo[0];
        strcpy(pokemon.id, id);

        // Parse generation
        pokemon.generation = atoi(atributo[1]);

        // Parse name
        strcpy(pokemon.name, atributo[2]);

        // Parse description
        strcpy(pokemon.description, atributo[3]);

        // Parse types
        pokemon.types = malloc(2 * sizeof(char *));
        pokemon.num_types = 0;
        if (strlen(trim(atributo[4])) > 0) {
            pokemon.types[pokemon.num_types++] = strdup(trim(atributo[4]));
        }
        if (num_atributo > 5 && strlen(trim(atributo[5])) > 0) {
            pokemon.types[pokemon.num_types++] = strdup(trim(atributo[5]));
        }

        // Now parse abilities from blocos[1], which is enclosed in quotes
        char *abilities_str = blocos[1];

        // Remove '[' and ']' from abilities_str
        char *abilities_cleaned = (char *)malloc(strlen(abilities_str) + 1);
        int idx = 0;
        for (int i = 0; abilities_str[i] != '\0'; i++) {
            if (abilities_str[i] != '[' && abilities_str[i] != ']') {
                abilities_cleaned[idx++] = abilities_str[i];
            }
        }
        abilities_cleaned[idx] = '\0';

        // Split abilities_cleaned at commas
        char *abilities_tokens[20];
        int num_abilities_tokens = split_at_char(abilities_cleaned, ',', abilities_tokens, 20);

        // Trim abilities tokens
        for (int i = 0; i < num_abilities_tokens; i++) {
            abilities_tokens[i] = trim(abilities_tokens[i]);
        }

        // Remove leading and trailing single quotes from abilities
        for (int i = 0; i < num_abilities_tokens; i++) {
            char *ability = abilities_tokens[i];
            int len = strlen(ability);
            if (len >= 2 && ability[0] == '\'' && ability[len - 1] == '\'') {
                ability[len - 1] = '\0';
                abilities_tokens[i] = ability + 1;
            }
        }

        // Store abilities
        pokemon.abilities = malloc(num_abilities_tokens * sizeof(char *));
        pokemon.num_abilities = num_abilities_tokens;
        for (int i = 0; i < num_abilities_tokens; i++) {
            pokemon.abilities[i] = strdup(abilities_tokens[i]);
        }

        // Free abilities_cleaned
        free(abilities_cleaned);

        // Now process blocos[2], but since it starts with a comma, we need to handle that
        // Remove leading comma from blocos[2]
        char *resto = blocos[2];
        if (resto[0] == ',') {
            resto++;
        }

        // Split resto at commas
        char *atributo3[20];
        int num_atributo3 = split_at_char(resto, ',', atributo3, 20);

        // Trim leading/trailing spaces from atributo3
        for (int i = 0; i < num_atributo3; i++) {
            atributo3[i] = trim(atributo3[i]);
        }

        // Parse weight
        if (num_atributo3 > 0 && strlen(atributo3[0]) > 0) {
            pokemon.weight = atof(atributo3[0]);
        }

        // Parse height
        if (num_atributo3 > 1 && strlen(atributo3[1]) > 0) {
            pokemon.height = atof(atributo3[1]);
        }

        // Parse captureRate
        if (num_atributo3 > 2) {
            char *captureRateStr = atributo3[2];
            pokemon.captureRate = atoi(captureRateStr);
        }

        // Parse isLegendary
        if (num_atributo3 > 3) {
            char *isLegendaryStr = atributo3[3];
            pokemon.isLegendary = atoi(isLegendaryStr);
        }

        // Parse captureDate
        if (num_atributo3 > 4) {
            char *captureDateStr = atributo3[4];
            if (strptime(captureDateStr, "%d/%m/%Y", &pokemon.captureDate) == NULL) {
                printf("Erro ao parsear a data: %s\n", captureDateStr);
            }
        }
        
        // Add the Pokemon to the array
        if (pokedex->numPokemons >= capacity) {
            capacity *= 2;
            pokedex->listaDePokemons = (Pokemon *)realloc(pokedex->listaDePokemons, capacity * sizeof(Pokemon));
        }

        pokedex->listaDePokemons[pokedex->numPokemons++] = pokemon;
    }

    fclose(file);
}

void imprimirPokemon(Pokemon *pokemon) {
    char dateStr[20];
    strftime(dateStr, sizeof(dateStr), "%d/%m/%Y", &pokemon->captureDate);

    printf("[#%s -> %s: %s - [", pokemon->id, pokemon->name, pokemon->description);

    // Print types
    for (int i = 0; i < pokemon->num_types; i++) {
        printf("'%s'", pokemon->types[i]);
        if (i < pokemon->num_types -1) {
            printf(", ");
        }
    }
    printf("] - [");

    // Print abilities
    for (int i = 0; i < pokemon->num_abilities; i++) {
        printf("'%s'", pokemon->abilities[i]);
        if (i < pokemon->num_abilities -1) {
            printf(", ");
        }
    }
    printf("] - ");

    printf("%.1fkg - %.1fm - %d%% - %s - %d gen] - %s\n",
        pokemon->weight, pokemon->height, pokemon->captureRate,
        pokemon->isLegendary ? "true" : "false",
        pokemon->generation, dateStr);
}

// Function to copy a Pokemon (deep copy)
void copyPokemon(Pokemon *dest, Pokemon *src) {
    strcpy(dest->id, src->id);
    dest->generation = src->generation;
    strcpy(dest->name, src->name);
    strcpy(dest->description, src->description);

    dest->num_types = src->num_types;
    dest->types = malloc(dest->num_types * sizeof(char *));
    for (int i = 0; i < dest->num_types; i++) {
        dest->types[i] = strdup(src->types[i]);
    }

    dest->num_abilities = src->num_abilities;
    dest->abilities = malloc(dest->num_abilities * sizeof(char *));
    for (int i = 0; i < dest->num_abilities; i++) {
        dest->abilities[i] = strdup(src->abilities[i]);
    }

    dest->weight = src->weight;
    dest->height = src->height;
    dest->captureRate = src->captureRate;
    dest->isLegendary = src->isLegendary;
    dest->captureDate = src->captureDate;
}

// Recursive Selection Sort function to sort Pokémons by their names
void recursiveSelectionSort(Pokemon arr[], int n, int index) {
    // Base case: If starting index is greater than or equal to n-1
    if (index >= n - 1)
        return;

    // Find the minimum element in the unsorted array
    int min_idx = index;
    for (int i = index + 1; i < n; i++) {
        if (strcmp(arr[i].name, arr[min_idx].name) < 0) {
            min_idx = i;
        }
    }

    // Swap the found minimum element with the current element
    if (min_idx != index) {
        Pokemon temp = arr[min_idx];
        arr[min_idx] = arr[index];
        arr[index] = temp;
    }

    // Recursively call selection sort for the rest of the array
    recursiveSelectionSort(arr, n, index + 1);
}

int main() {
    Pokedex pokedex;
    lerDadosDoArquivo(&pokedex);

    char ids[100][20];
    int numIds = 0;

    // Collect IDs from user input
    char entrada[100];
    while (fgets(entrada, sizeof(entrada), stdin)) {
        // Remove newline character
        entrada[strcspn(entrada, "\r\n")] = 0;

        if (strcmp(entrada, "FIM") == 0) {
            break;
        }

        strcpy(ids[numIds++], entrada);
    }

    Pokemon selectedPokemons[100];
    int numSelectedPokemons = 0;

    // Retrieve Pokémons based on IDs
    for (int i = 0; i < numIds; i++) {
        int pokemonEncontrado = 0;

        for (int j = 0; j < pokedex.numPokemons; j++) {
            if (strcmp(pokedex.listaDePokemons[j].id, ids[i]) == 0) {
                pokemonEncontrado = 1;
                copyPokemon(&selectedPokemons[numSelectedPokemons++], &pokedex.listaDePokemons[j]);
                break;
            }
        }

        if (!pokemonEncontrado) {
            printf("Pokemon não encontrado\n");
        }
    }

    // Sort the selected Pokémons by their names using recursive selection sort
    recursiveSelectionSort(selectedPokemons, numSelectedPokemons, 0);

    // Print the sorted Pokémons
    for (int i = 0; i < numSelectedPokemons; i++) {
        imprimirPokemon(&selectedPokemons[i]);
    }

    // Free allocated memory for selectedPokemons
    for (int i = 0; i < numSelectedPokemons; i++) {
        Pokemon *pokemon = &selectedPokemons[i];
        for (int j = 0; j < pokemon->num_types; j++) {
            free(pokemon->types[j]);
        }
        free(pokemon->types);
        for (int j = 0; j < pokemon->num_abilities; j++) {
            free(pokemon->abilities[j]);
        }
        free(pokemon->abilities);
    }

    // Free allocated memory for pokedex
    for (int i = 0; i < pokedex.numPokemons; i++) {
        Pokemon *pokemon = &pokedex.listaDePokemons[i];
        for (int j = 0; j < pokemon->num_types; j++) {
            free(pokemon->types[j]);
        }
        free(pokemon->types);
        for (int j = 0; j < pokemon->num_abilities; j++) {
            free(pokemon->abilities[j]);
        }
        free(pokemon->abilities);
    }
    free(pokedex.listaDePokemons);

    return 0;
}

