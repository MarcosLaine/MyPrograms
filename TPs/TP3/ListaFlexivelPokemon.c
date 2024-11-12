#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <ctype.h>

// Estruturas
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

typedef struct Celula
{
    Pokemon elemento;
    struct Celula *prox;
} Celula;

// Variáveis globais da lista
Celula *primeiro;
Celula *ultimo;

// Funções de lista
Celula *novaCelula(Pokemon elemento)
{
    Celula *nova = (Celula *)malloc(sizeof(Celula));
    nova->elemento = elemento;
    nova->prox = NULL;
    return nova;
}

void start()
{
    primeiro = novaCelula((Pokemon){0}); // Celula cabeça com Pokémon vazio
    ultimo = primeiro;
}

void inserirInicio(Pokemon x)
{
    Celula *tmp = novaCelula(x);
    tmp->prox = primeiro->prox;
    primeiro->prox = tmp;
    if (primeiro == ultimo)
    {
        ultimo = tmp;
    }
}

void inserirFim(Pokemon x)
{
    ultimo->prox = novaCelula(x);
    ultimo = ultimo->prox;
}

void inserirPos(Pokemon x, int pos)
{
    int tam = 0;
    Celula *i;
    for (i = primeiro->prox; i != NULL; i = i->prox)
        tam++;

    if (pos < 0 || pos > tam)
    {
        printf("Erro ao inserir na posição (%d/tamanho = %d) inválida!\n", pos, tam);
        exit(1);
    }
    else if (pos == 0)
    {
        inserirInicio(x);
    }
    else if (pos == tam)
    {
        inserirFim(x);
    }
    else
    {
        Celula *j = primeiro;
        for (int k = 0; k < pos; k++, j = j->prox)
            ;

        Celula *tmp = novaCelula(x);
        tmp->prox = j->prox;
        j->prox = tmp;
    }
}

Pokemon removerInicio()
{
    if (primeiro == ultimo)
    {
        printf("Erro ao remover: lista vazia!\n");
        exit(1);
    }

    Celula *tmp = primeiro->prox;
    Pokemon resp = tmp->elemento;
    primeiro->prox = tmp->prox;
    if (tmp == ultimo)
    {
        ultimo = primeiro;
    }
    free(tmp);
    return resp;
}

Pokemon removerFim()
{
    if (primeiro == ultimo)
    {
        printf("Erro ao remover: lista vazia!\n");
        exit(1);
    }

    Celula *i;
    for (i = primeiro; i->prox != ultimo; i = i->prox)
        ;

    Pokemon resp = ultimo->elemento;
    free(ultimo);
    ultimo = i;
    ultimo->prox = NULL;
    return resp;
}

Pokemon removerPos(int pos)
{
    int tam = 0;
    Celula *i;
    for (i = primeiro->prox; i != NULL; i = i->prox)
        tam++;

    if (pos < 0 || pos >= tam)
    {
        printf("Erro ao remover na posição (%d/tamanho = %d) inválida!\n", pos, tam);
        exit(1);
    }
    else if (pos == 0)
    {
        return removerInicio();
    }
    else if (pos == tam - 1)
    {
        return removerFim();
    }
    else
    {
        Celula *j = primeiro;
        for (int k = 0; k < pos; k++, j = j->prox)
            ;

        Celula *tmp = j->prox;
        Pokemon resp = tmp->elemento;
        j->prox = tmp->prox;
        free(tmp);
        return resp;
    }
}

// Função para imprimir o Pokémon
void imprimirPokemon(Pokemon *pokemon, int index)
{
    char dateStr[20];
    strftime(dateStr, sizeof(dateStr), "%d/%m/%Y", &pokemon->captureDate);

    printf("[%d] [#%s -> %s: %s - [", index, pokemon->id, pokemon->name, pokemon->description);
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
    printf("] - %.1fkg - %.1fm - %d%% - %s - %d gen] - %s\n",
           pokemon->weight, pokemon->height, pokemon->captureRate,
           pokemon->isLegendary ? "true" : "false",
           pokemon->generation, dateStr);
}

// Função para remover espaços em branco das extremidades de uma string
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

// Função para dividir uma string em tokens com base em um delimitador
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

// Função para ler os dados do arquivo
void lerDadosDoArquivo()
{
    const char *FILE_NAME = "/tmp/pokemon.csv";
    // const char *FILE_NAME = "/home/marcoslaine/Área de trabalho/Programacao/MyPrograms/TPs/TP3/tmp/pokemon.csv"; //my directory in linux
    // contr char *FILE_NAME = "C:\Users\kino1\Desktop\Programacao\MyPrograms\TPs\TP3\tmp\pokemon.csv"; //my directory in windows
    FILE *file = fopen(FILE_NAME, "r");
    if (!file)
    {
        perror("Erro ao abrir o arquivo");
        exit(1);
    }

    char line[2048];
    char *result;

    // Ignorar a linha de cabeçalho
    result = fgets(line, sizeof(line), file);

    while (fgets(line, sizeof(line), file))
    {
        line[strcspn(line, "\r\n")] = 0; // Remove o caractere de nova linha

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
        int num_atributo = split_at_char(blocos[0], ',', atributo, 20);
        for (int i = 0; i < num_atributo; i++)
        {
            atributo[i] = trim(atributo[i]);
        }

        if (num_atributo < 6)
        {
            printf("Linha mal formatada ou incompleta: %s\n", line);
            continue;
        }

        strcpy(pokemon.id, atributo[0]);
        pokemon.generation = atoi(atributo[1]);
        strcpy(pokemon.name, atributo[2]);
        strcpy(pokemon.description, atributo[3]);

        // Parse dos tipos
        pokemon.types = malloc(2 * sizeof(char *));
        pokemon.num_types = 0;
        if (strlen(trim(atributo[4])) > 0)
        {
            pokemon.types[pokemon.num_types++] = strdup(trim(atributo[4]));
        }
        if (num_atributo > 5 && strlen(trim(atributo[5])) > 0)
        {
            pokemon.types[pokemon.num_types++] = strdup(trim(atributo[5]));
        }

        // Parse das habilidades
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

        // Parse dos atributos restantes
        char *resto = blocos[2];
        if (resto[0] == ',')
            resto++;

        char *atributo3[20];
        int num_atributo3 = split_at_char(resto, ',', atributo3, 20);
        for (int i = 0; i < num_atributo3; i++)
        {
            atributo3[i] = trim(atributo3[i]);
        }

        if (num_atributo3 > 0)
            pokemon.weight = atof(atributo3[0]);
        if (num_atributo3 > 1)
            pokemon.height = atof(atributo3[1]);
        if (num_atributo3 > 2)
            pokemon.captureRate = atoi(atributo3[2]);
        if (num_atributo3 > 3)
            pokemon.isLegendary = atoi(atributo3[3]);
        if (num_atributo3 > 4)
        {
            char *captureDateStr = atributo3[4];
            if (strptime(captureDateStr, "%d/%m/%Y", &pokemon.captureDate) == NULL)
            {
                printf("Erro ao parsear a data: %s\n", captureDateStr);
            }
        }

        inserirFim(pokemon);
    }

    fclose(file);
}

int encontrarPokemonPorId(Celula *inicio, char *id, Pokemon *resultado) {
    Celula *atual = inicio->prox; // Começa após a célula cabeça
    while (atual != NULL) {
        if (strcmp(atual->elemento.id, id) == 0) {
            *resultado = atual->elemento; // Copia o Pokémon encontrado para resultado
            return 1; // Retorna 1 indicando que o Pokémon foi encontrado
        }
        atual = atual->prox;
    }
    return 0; // Retorna 0 se o Pokémon não foi encontrado
}


int main()
{
    start();

    // Inicializa a Pokédex completa e lê os dados do arquivo
    lerDadosDoArquivo();

    char entrada[100];

    // Leitura de IDs de Pokémon do usuário
    while (fgets(entrada, sizeof(entrada), stdin))
    {
        entrada[strcspn(entrada, "\r\n")] = 0;
        if (strcmp(entrada, "FIM") == 0)
        {
            break;
        }

        Pokemon pokemonEncontrado;
        if (encontrarPokemonPorId(primeiro, entrada, &pokemonEncontrado))
        {
            inserirFim(pokemonEncontrado);
        }
        else
        {
            printf("Pokemon com ID %s não encontrado\n", entrada);
        }
    }

    int numComandos;
    scanf("%d", &numComandos);
    getchar();

    // Processar comandos de manipulação
    while (numComandos > 0)
    {
        fgets(entrada, sizeof(entrada), stdin);
        entrada[strcspn(entrada, "\r\n")] = 0;

        char *partes[3];
        int num_partes = split_at_char(entrada, ' ', partes, 3);
        char *comando = partes[0];

        if (strcmp(comando, "II") == 0)
        { // Inserir no início
            Pokemon pokemon;
            if (encontrarPokemonPorId(primeiro, partes[1], &pokemon))
            {
                inserirInicio(pokemon);
            }
        }
        else if (strcmp(comando, "IF") == 0)
        { // Inserir no fim
            Pokemon pokemon;
            if (encontrarPokemonPorId(primeiro, partes[1], &pokemon))
            {
                inserirFim(pokemon);
            }
        }
        else if (strcmp(comando, "I*") == 0)
        { // Inserir em posição específica
            int posicao = atoi(partes[1]);
            Pokemon pokemon;
            if (encontrarPokemonPorId(primeiro, partes[2], &pokemon))
            {
                inserirPos(pokemon, posicao);
            }
        }
        else if (strcmp(comando, "RI") == 0)
        { // Remover do início
            Pokemon removido = removerInicio();
            printf("(R) %s\n", removido.name);
        }
        else if (strcmp(comando, "RF") == 0)
        { // Remover do fim
            Pokemon removido = removerFim();
            printf("(R) %s\n", removido.name);
        }
        else if (strcmp(comando, "R*") == 0)
        { // Remover de posição específica
            int posicao = atoi(partes[1]);
            Pokemon removido = removerPos(posicao);
            printf("(R) %s\n", removido.name);
        }
        else
        {
            printf("Comando inválido: %s\n", comando);
        }
        numComandos--;
    }

    // Mostrar a Pokédex do usuário com índices
    int index = 0;
    Celula *i;
    for (i = primeiro->prox; i != NULL; i = i->prox)
    {
        imprimirPokemon(&i->elemento, index++);
    }

    // Limpeza de memória
    while (primeiro != ultimo)
    {
        removerInicio();
    }
    free(primeiro);

    return 0;
}
