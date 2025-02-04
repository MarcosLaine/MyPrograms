#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <ctype.h>

typedef struct
{
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

typedef struct
{
    Pokemon *listaDePokemons;
    int numPokemons;
} Pokedex;

char *trim(char *str)
{
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

int split_at_char(char *str, char delimiter, char **tokens, int max_tokens)
{
    int count = 0;
    char *start = str;
    while (*str)
    {
        if (*str == delimiter)
        {
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

void lerDadosDoArquivo(Pokedex *pokedex, char **idsSolicitados, int numIdsSolicitados)
{
    const char *FILE_NAME = "/tmp/pokemon.csv";
    FILE *file = fopen(FILE_NAME, "r");
    if (!file)
    {
        perror("Erro ao abrir o arquivo");
        exit(1);
    }

    char line[2048];
    fgets(line, sizeof(line), file);

    int capacity = 1000;
    pokedex->listaDePokemons = (Pokemon *)malloc(capacity * sizeof(Pokemon));
    pokedex->numPokemons = 0;

    while (fgets(line, sizeof(line), file))
    {
        line[strcspn(line, "\r\n")] = 0;

        Pokemon pokemon;
        memset(&pokemon, 0, sizeof(Pokemon));

        char *blocos[10];
        int num_blocos = split_at_char(line, '"', blocos, 10);

        if (num_blocos < 3)
        {
            printf("Linha mal formatada ou incompleta: %s\n", line);
            continue;
        }

        char *atributo[20];
        int quant_atributo = split_at_char(blocos[0], ',', atributo, 20);
        for (int i = 0; i < quant_atributo; i++)
        {
            atributo[i] = trim(atributo[i]);
        }

        if (quant_atributo < 6)
        {
            printf("Linha mal formatada ou incompleta: %s\n", line);
            continue;
        }

        char *id = atributo[0];
        strcpy(pokemon.id, id);

        int found = 0;
        for (int i = 0; i < numIdsSolicitados; i++)
        {
            if (strcmp(id, idsSolicitados[i]) == 0)
            {
                found = 1;
                break;
            }
        }
        if (!found)
            continue;

        pokemon.generation = atoi(atributo[1]);
        strcpy(pokemon.name, atributo[2]);
        strcpy(pokemon.description, atributo[3]);

        pokemon.types = malloc(2 * sizeof(char *));
        pokemon.num_types = 0;
        if (strlen(trim(atributo[4])) > 0)
        {
            pokemon.types[pokemon.num_types++] = strdup(trim(atributo[4]));
        }
        if (quant_atributo > 5 && strlen(trim(atributo[5])) > 0)
        {
            pokemon.types[pokemon.num_types++] = strdup(trim(atributo[5]));
        }

        char *abilities_str = blocos[1];
        char *abilities_cleaned = (char *)malloc(strlen(abilities_str) + 1);
        int idx = 0;
        for (int i = 0; abilities_str[i] != '\0'; i++)
        {
            if (abilities_str[i] != '[' && abilities_str[i] != ']')
            {
                abilities_cleaned[idx++] = abilities_str[i];
            }
        }
        abilities_cleaned[idx] = '\0';

        char *abilities_tokens[20];
        int num_abilities_tokens = split_at_char(abilities_cleaned, ',', abilities_tokens, 20);
        for (int i = 0; i < num_abilities_tokens; i++)
        {
            abilities_tokens[i] = trim(abilities_tokens[i]);
        }

        pokemon.abilities = malloc(num_abilities_tokens * sizeof(char *));
        pokemon.num_abilities = num_abilities_tokens;
        for (int i = 0; i < num_abilities_tokens; i++)
        {
            pokemon.abilities[i] = strdup(abilities_tokens[i]);
        }

        free(abilities_cleaned);

        char *resto = blocos[2];
        if (resto[0] == ',')
            resto++;
        char *atributo3[20];
        int quant_atributo3 = split_at_char(resto, ',', atributo3, 20);
        for (int i = 0; i < quant_atributo3; i++)
        {
            atributo3[i] = trim(atributo3[i]);
        }

        if (quant_atributo3 > 0)
        {
            pokemon.weight = atof(atributo3[0]);
        }
        if (quant_atributo3 > 1)
        {
            pokemon.height = atof(atributo3[1]);
        }
        if (quant_atributo3 > 2)
        {
            pokemon.captureRate = atoi(atributo3[2]);
        }
        if (quant_atributo3 > 3)
        {
            pokemon.isLegendary = atoi(atributo3[3]);
        }
        if (quant_atributo3 > 4)
        {
            char *captureDateStr = atributo3[4];
            if (strptime(captureDateStr, "%d/%m/%Y", &pokemon.captureDate) == NULL)
            {
                printf("Erro ao parsear a data: %s\n", captureDateStr);
            }
        }

        if (pokedex->numPokemons >= capacity)
        {
            capacity *= 2;
            Pokemon *temp = realloc(pokedex->listaDePokemons, capacity * sizeof(Pokemon));
            if (!temp)
            {
                perror("Erro ao realocar memória para listaDePokemons");
                exit(1);
            }
            pokedex->listaDePokemons = temp;
        }
        pokedex->listaDePokemons[pokedex->numPokemons++] = pokemon;
    }

    fclose(file);
}

void imprimirPokemon(Pokemon *pokemon)
{
    char dateStr[20];
    strftime(dateStr, sizeof(dateStr), "%d/%m/%Y", &pokemon->captureDate);
    printf("[#%s -> %s: %s - [", pokemon->id, pokemon->name, pokemon->description);

    for (int i = 0; i < pokemon->num_types; i++)
    {
        printf("'%s'", pokemon->types[i]);
        if (i < pokemon->num_types - 1)
        {
            printf(", ");
        }
    }
    printf("] - [");
    for (int i = 0; i < pokemon->num_abilities; i++)
    {
        printf("%s", pokemon->abilities[i]);
        if (i < pokemon->num_abilities - 1)
        {
            printf(", ");
        }
    }
    printf("] - ");
    printf("%.1fkg - %.1fm - %d%% - %s - %d gen] - %s\n",
           pokemon->weight, pokemon->height, pokemon->captureRate,
           pokemon->isLegendary ? "true" : "false",
           pokemon->generation, dateStr);
}

int getMaxLength(Pokedex *pokedex)
{
    int maxLength = 0;
    for (int i = 0; i < pokedex->numPokemons; i++)
    {
        for (int j = 0; j < pokedex->listaDePokemons[i].num_abilities; j++)
        {
            int length = strlen(pokedex->listaDePokemons[i].abilities[j]);
            if (length > maxLength)
                maxLength = length;
        }
    }
    return maxLength;
}

void countingSortByAbilities(Pokedex *pokedex, int exp)
{
    int *output = malloc(pokedex->numPokemons * sizeof(int));
    if (!output)
    {
        perror("Erro ao alocar memória para output");
        exit(1);
    }
    int count[256] = {0};

    for (int i = 0; i < pokedex->numPokemons; i++)
    {
        char *ability = pokedex->listaDePokemons[i].abilities[0];
        int idx = (exp < strlen(ability)) ? ability[exp] : 0;
        count[idx]++;
    }

    for (int i = 1; i < 256; i++)
    {
        count[i] += count[i - 1];
    }

    for (int i = pokedex->numPokemons - 1; i >= 0; i--)
    {
        char *ability = pokedex->listaDePokemons[i].abilities[0];
        int idx = (exp < strlen(ability)) ? ability[exp] : 0;
        output[count[idx] - 1] = i;
        count[idx]--;
    }

    Pokemon *temp = (Pokemon *)malloc(pokedex->numPokemons * sizeof(Pokemon));
    for (int i = 0; i < pokedex->numPokemons; i++)
    {
        temp[i] = pokedex->listaDePokemons[output[i]];
    }
    free(pokedex->listaDePokemons);
    free(output);
    pokedex->listaDePokemons = temp;
}

void radixSort(Pokedex *pokedex)
{
    int maxLength = getMaxLength(pokedex);

    for (int exp = 0; exp < maxLength; exp++)
    {
        countingSortByAbilities(pokedex, exp);
    }
}

void salvarLog(Pokedex *pokedex, int numComparacoes)
{
    const char *LOG_FILE_NAME = "803627.txt";
    FILE *logFile = fopen(LOG_FILE_NAME, "w");
    if (!logFile)
    {
        perror("Erro ao abrir o arquivo de log");
        exit(1);
    }

    time_t t;
    time(&t);
    fprintf(logFile, "Matrícula: 803627\n");
    fprintf(logFile, "Data e Hora: %s", ctime(&t));
    fprintf(logFile, "Número de comparações: %d\n", numComparacoes);

    fclose(logFile);
}

void ordenarPokemons(Pokedex *pokedex)
{
    radixSort(pokedex);
}

int main(int argc, char *argv[])
{
    if (argc < 2)
    {
        printf("Uso: %s <ids de pokemons separados por espaço>\n", argv[0]);
        return 1;
    }

    int numIdsSolicitados = argc - 1;
    char **idsSolicitados = malloc(numIdsSolicitados * sizeof(char *));
    for (int i = 0; i < numIdsSolicitados; i++)
    {
        idsSolicitados[i] = argv[i + 1];
    }

    Pokedex pokedex;
    lerDadosDoArquivo(&pokedex, idsSolicitados, numIdsSolicitados);

    radixSort(&pokedex);
    for (int i = 0; i < pokedex.numPokemons; i++)
    {
        imprimirPokemon(&pokedex.listaDePokemons[i]);
    }

    int numComparacoes = 0;
    salvarLog(&pokedex, numComparacoes);

    for (int i = 0; i < pokedex.numPokemons; i++)
    {
        for (int j = 0; j < pokedex.listaDePokemons[i].num_types; j++)
        {
            free(pokedex.listaDePokemons[i].types[j]);
        }
        free(pokedex.listaDePokemons[i].types);

        for (int j = 0; j < pokedex.listaDePokemons[i].num_abilities; j++)
        {
            free(pokedex.listaDePokemons[i].abilities[j]);
        }
        free(pokedex.listaDePokemons[i].abilities);
    }
    free(pokedex.listaDePokemons);
    free(idsSolicitados);

    return 0;
}