#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <ctype.h>
#include <stdbool.h>

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

    int capacity = 1000; // capacidade inicial
    pokedex->listaDePokemons = (Pokemon *)malloc(capacity * sizeof(Pokemon));
    pokedex->numPokemons = 0;

    while (fgets(line, sizeof(line), file)) {
        
        line[strcspn(line, "\r\n")] = 0;

        // Passar para struct
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

        // Adicionar Poke no  array
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

    // Print tipos
    for (int i = 0; i < pokemon->num_types; i++) {
        printf("'%s'", pokemon->types[i]);
        if (i < pokemon->num_types -1) {
            printf(", ");
        }
    }
    printf("] - [");

    // Print abilidades
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


// lista flexível
typedef struct Lista {
    char *valor;
    struct Lista *proximo;
} Lista;

// cabeça da lista
typedef struct {
    Lista *inicio;
} Cabeca;

// hash
typedef struct {
    Cabeca **tabela;
    int tamanho;
} Hash;

// Construtor
Lista* criarLista(char *valor) {
    Lista *novaLista = (Lista *)malloc(sizeof(Lista));
    novaLista->valor = strdup(valor); // Copia a string para evitar problemas de memória
    novaLista->proximo = NULL;
    return novaLista;
}

// Construtor
Cabeca* criarCabeca() {
    Cabeca *novaCabeca = (Cabeca *)malloc(sizeof(Cabeca));
    novaCabeca->inicio = NULL;
    return novaCabeca;
}

// ja sabe ne
Hash* criarHash(int tamanho) {
    Hash *novaHash = (Hash *)malloc(sizeof(Hash));
    novaHash->tamanho = tamanho;
    novaHash->tabela = (Cabeca **)malloc(tamanho * sizeof(Cabeca *));
    for (int i = 0; i < tamanho; i++) {
        novaHash->tabela[i] = criarCabeca();
    }
    return novaHash;
}

//duvido acertar
int mod21(const char *str) {
    int sum = 0;
    for (int i = 0; str[i] != '\0'; i++) {
        sum += (int)str[i];
    }
    return sum % 21;
}



void inserirFila(Lista **lista, char *valor) {
    Lista *novo = criarLista(valor);
    if (*lista == NULL) {
     
        *lista = novo;
    } else {
        Lista *atual = *lista;
        while (atual->proximo != NULL) {
            
            atual = atual->proximo;
        }
        atual->proximo = novo;
    }
}

void inserirNaHash(Hash *hash, const char *valor) {
    int posicao = mod21(valor);
    Cabeca *cabeca = hash->tabela[posicao];
    inserirFila(&(cabeca->inicio), strdup(valor));
}

void imprimirHash(Hash *hash) {

    printf("Valores armazenados na Hash:\n");
    for (int i = 0; i < hash->tamanho; i++) {
        Cabeca *cabeca = hash->tabela[i];
        Lista *atual = cabeca->inicio;
        printf("Posicao %d: ", i);
        while (atual != NULL) {
            printf("%s -> ", atual->valor);
            atual = atual->proximo;
        }
        printf("NULL\n");
    }
}

bool buscarNaHash(Hash *hash, char *valor) {
    for (int i = 0; i < hash->tamanho; i++) {
        Cabeca *cabeca = hash->tabela[i];
        Lista *atual = cabeca->inicio;
        while (atual != NULL) {
            if (strcmp(atual->valor, valor) == 0) {
                return true;
            }
            atual = atual->proximo;
        }
    }
    return false;
}


void Busca(char *valor, Hash *hash) {
    printf("%s", valor);
    if (buscarNaHash(hash, valor)) {
        printf(" SIM\n");
    } else {
        printf(" NAO\n");
    }
}

const char *obterIdPorNome(Pokedex *pokedex, const char *nome) {
    for (int i = 0; i < pokedex->numPokemons; i++) {
        if (strcmp(pokedex->listaDePokemons[i].name, nome) == 0) {
            return pokedex->listaDePokemons[i].id;
        }
    }
    return NULL; // Retorna NULL se o nome não for encontrado
}



int main() {
    Pokedex pokedex;
    lerDadosDoArquivo(&pokedex); // Carrega os dados do arquivo na pokedex

    Hash *rachi = criarHash(21); // Cria uma hash table com tamanho 21

      char entrada[100];
    // Leitura dos IDs
    while (fgets(entrada, sizeof(entrada), stdin)) {
        entrada[strcspn(entrada, "\r\n")] = 0; // Remove o caractere de nova linha
        if (strcmp(entrada, "FIM") == 0) break;

        inserirNaHash(rachi, entrada); // Insere o ID na hash
    }


   // Leitura dos nomes
    while (fgets(entrada, sizeof(entrada), stdin)) {
        entrada[strcspn(entrada, "\r\n")] = 0; // Remove o caractere de nova linha
        if (strcmp(entrada, "FIM") == 0) break;

        const char *id = obterIdPorNome(&pokedex, entrada);

        if (id == NULL) {
            printf("=> %s: NAO\n", entrada);
        } else if (buscarNaHash(rachi, id)) {
            printf("=> %s: (Posicao: %d) SIM \n", entrada, mod21(id));
        } else {
            printf("=> %s: NAO\n", entrada);
        }
    }

    return 0;
}
