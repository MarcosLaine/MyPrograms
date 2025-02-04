#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <ctype.h>
#include <time.h>

#define TAM_TAB 21
#define FILE_NAME "/tmp/pokemon.csv"  // A macro FILE_NAME já está definida

// Estrutura do Pokémon
typedef struct Pokemon {
    char id[10];
    int generation;
    char name[50];
    char description[200];
    double weight;
    double height;
    int captureRate;
    bool isLegendary;
    struct tm captureDate;  // Usando struct tm para armazenar a data
    char **types;
    int num_types;
    char **abilities;
    int num_abilities;
    struct Pokemon* next;  // Ponteiro para a próxima lista
} Pokemon;

// Estrutura da Tabela Hash
typedef struct {
    Pokemon* tabela[TAM_TAB];
} Hash;

// Função para criar um novo Pokémon
Pokemon* criarPokemon(char* id, int generation, char* name, char* description, double weight, double height, int captureRate, bool isLegendary, struct tm captureDate) {
    Pokemon* novo = (Pokemon*)malloc(sizeof(Pokemon));
    strcpy(novo->id, id);
    novo->generation = generation;
    strcpy(novo->name, name);
    strcpy(novo->description, description);
    novo->weight = weight;
    novo->height = height;
    novo->captureRate = captureRate;
    novo->isLegendary = isLegendary;
    novo->captureDate = captureDate;
    novo->next = NULL;
    novo->types = NULL;
    novo->num_types = 0;
    novo->abilities = NULL;
    novo->num_abilities = 0;
    return novo;
}

// Função de hash baseada na soma dos valores ASCII do nome
int hashFunction(char* nome) {
    int asciiSum = 0;
    for (int i = 0; nome[i] != '\0'; i++) {
        asciiSum += nome[i];
    }
    return asciiSum % TAM_TAB;
}

// Inicializa a Tabela Hash
void inicializarHash(Hash* hash) {
    for (int i = 0; i < TAM_TAB; i++) {
        hash->tabela[i] = NULL;
    }
}

// Função para inserir um Pokémon na tabela hash
bool inserir(Hash* hash, Pokemon* p) {
    int pos = hashFunction(p->name);
    Pokemon* novo = criarPokemon(p->id, p->generation, p->name, p->description, p->weight, p->height, p->captureRate, p->isLegendary, p->captureDate);

    if (hash->tabela[pos] == NULL) {
        hash->tabela[pos] = novo;
    } else {
        Pokemon* temp = hash->tabela[pos];
        while (temp->next != NULL) {
            temp = temp->next;
        }
        temp->next = novo;
    }

    return true;
}

// Função para buscar um Pokémon na tabela hash
Pokemon* search(Hash* hash, char* nome) {
    int pos = hashFunction(nome);
    Pokemon* temp = hash->tabela[pos];

    while (temp != NULL) {
        if (strcmp(temp->name, nome) == 0) {
            return temp;
        }
        temp = temp->next;
    }
    
    return NULL;
}

// Função para imprimir informações de um Pokémon
void imprimirPokemon(Pokemon* p) {
    if (p != NULL) {
        printf("ID: %s\n", p->id);
        printf("Name: %s\n", p->name);
        printf("Description: %s\n", p->description);
        printf("Weight: %.1fkg\n", p->weight);
        printf("Height: %.1fm\n", p->height);
        printf("Capture Rate: %d%%\n", p->captureRate);
        printf("Legendary: %s\n", p->isLegendary ? "Yes" : "No");

        char dateBuffer[20];
        strftime(dateBuffer, sizeof(dateBuffer), "%d/%m/%Y", &p->captureDate);
        printf("Capture Date: %s\n", dateBuffer);

        printf("Generation: %d\n", p->generation);
    } else {
        printf("Pokémon não encontrado.\n");
    }
}

// Função auxiliar para dividir a string
int split_at_char(char *str, char delimiter, char **tokens, int max_tokens) {
    int i = 0;
    char *token = strtok(str, &delimiter);
    while (token != NULL && i < max_tokens) {
        tokens[i++] = token;
        token = strtok(NULL, &delimiter);
    }
    return i;
}

// Função para remover espaços em branco antes e depois de uma string
char* trim(char *str) {
    while (isspace((unsigned char)*str)) str++;
    if (*str == 0) return str;
    char *end = str + strlen(str) - 1;
    while (end > str && isspace((unsigned char)*end)) end--;
    end[1] = '\0';
    return str;
}

// Função para ler Pokémons do arquivo CSV
void lerDadosDoArquivo(Hash* hash) {
    FILE *file = fopen(FILE_NAME, "r");  // Usando a macro FILE_NAME
    if (!file) {
        perror("Erro ao abrir o arquivo");
        exit(1);
    }

    char line[2048];
    char *result;

    // Skip header line
    result = fgets(line, sizeof(line), file);

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

        // Parse captureDate using sscanf instead of strptime
        if (num_atributo3 > 4) {
            char *captureDateStr = atributo3[4];
            sscanf(captureDateStr, "%d/%d/%d", &pokemon.captureDate.tm_mday, &pokemon.captureDate.tm_mon, &pokemon.captureDate.tm_year);
            pokemon.captureDate.tm_mon--;  // Mês começa de 0 em struct tm
            pokemon.captureDate.tm_year -= 1900;  // Ano desde 1900
        }

        // Add the Pokemon to the hash table
        inserir(hash, &pokemon);
    }

    fclose(file);
}

// Função principal
int main() {
    Hash hash;
    inicializarHash(&hash);

    // Lê os Pokémons do arquivo CSV e insere na tabela hash
    lerDadosDoArquivo(&hash);

    // Busca um Pokémon pelo nome
    char nomeBusca[50];
    scanf("%s", nomeBusca);

    Pokemon* encontrado = search(&hash, nomeBusca);
    imprimirPokemon(encontrado);

    return 0;
}
