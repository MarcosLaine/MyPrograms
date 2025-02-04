#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <time.h>
#include <stdlib.h>

typedef struct {
    char id[20], name[100], description[1000];
    int generation, captureRate, isLegendary, num_types, num_abilities;   
    char **types;  
    char **abilities;
    double height, weight; 
    struct tm captureDate;
} Pok;

typedef struct {
    Pok *escolhidos;
    int totPok;
} BancoPok;

char *pv(char *str) {
    while (isspace((unsigned char)*str)) {  
        str++;
    }
    if (*str == 0) {
        return str;
    }     
    char *fim = str + strlen(str) - 1;
    while (fim > str && isspace((unsigned char)*fim)) {
        fim--;
    }
    fim[1] = '\0';
    return str;
}

int splt(char *str, char lim, char **indice, int max_indice) {
    int cont = 0;
    char *comeco = str;
    while (*str) {
        if (*str == lim) {
            *str = '\0';
            indice[cont++] = comeco;
            comeco = str + 1;
            if (cont >= max_indice) {
                return cont;
            }            
        }
        str++;
    }
    indice[cont++] = comeco;
    return cont;
}

void readFilePok(BancoPok *bancoPok, char **chamados, int numchamados) {
    const char *ARQ = "/tmp/pokemon.csv";
    FILE *file = fopen(ARQ, "r");
    if (!file) { 
        perror("Erro ao abrir o arquivo");
        exit(1);
    }
    char line[801];
    fgets(line, sizeof(line), file); // Pular cabeçalho

    int capacity = 801;
    bancoPok->escolhidos = (Pok *)malloc(capacity * sizeof(Pok));
    bancoPok->totPok = 0;

    while (fgets(line, sizeof(line), file)) {
        line[strcspn(line, "\r\n")] = 0;
        Pok atual;
        memset(&atual, 0, sizeof(Pok));

        char *blocos[10];
        int num_blocos = splt(line, '"', blocos, 10);

        if (num_blocos < 3) {
            printf("Linha mal formatada ou incompleta: %s\n", line);
            continue;
        }

        char *atributo[20];
        int quant_atributo = splt(blocos[0], ',', atributo, 20);
        for (int i = 0; i < quant_atributo; i++) {
            atributo[i] = pv(atributo[i]);
        }

        if (quant_atributo < 6) {
            printf("Linha mal formatada ou incompleta: %s\n", line);
            continue;
        }

        char *id = atributo[0];
        strcpy(atual.id, id);

        int found = 0;
        for (int i = 0; i < numchamados; i++) {
            if (strcmp(id, chamados[i]) == 0) {
                found = 1;
                break;
            }
        }
        if (!found) continue;

        atual.generation = atoi(atributo[1]);
        strcpy(atual.name, atributo[2]);
        strcpy(atual.description, atributo[3]);

        atual.types = malloc(2 * sizeof(char *));
        atual.num_types = 0;
        if (strlen(pv(atributo[4])) > 0) {
            atual.types[atual.num_types++] = strdup(pv(atributo[4]));
        }
        if (quant_atributo > 5 && strlen(pv(atributo[5])) > 0) {
            atual.types[atual.num_types++] = strdup(pv(atributo[5]));
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

        char *abilities_indice[20];
        int num_abilities_indice = splt(abilities_cleaned, ',', abilities_indice, 20);
        for (int i = 0; i < num_abilities_indice; i++) {
            abilities_indice[i] = pv(abilities_indice[i]);
        }

        atual.abilities = malloc(num_abilities_indice * sizeof(char *));
        atual.num_abilities = num_abilities_indice;
        for (int i = 0; i < num_abilities_indice; i++) {
            atual.abilities[i] = strdup(abilities_indice[i]);
        }

        free(abilities_cleaned);

        char *resto = blocos[2];
        if (resto[0] == ',') resto++;
        char *atributo3[20];
        int quant_atributo3 = splt(resto, ',', atributo3, 20);
        for (int i = 0; i < quant_atributo3; i++) {
            atributo3[i] = pv(atributo3[i]);
        }

        if (quant_atributo3 > 0) {
            atual.weight = atof(atributo3[0]);
        }
        if (quant_atributo3 > 1) {
            atual.height = atof(atributo3[1]);
        }
        if (quant_atributo3 > 2) {
            atual.captureRate = atoi(atributo3[2]);
        }
        if (quant_atributo3 > 3) {
            atual.isLegendary = atoi(atributo3[3]);
        }
        if (quant_atributo3 > 4) {
            char *captureDateStr = atributo3[4];
            if (strptime(captureDateStr, "%d/%m/%Y", &atual.captureDate) == NULL) {
                printf("Erro ao parsear a data: %s\n", captureDateStr);
            }
        }

        if (bancoPok->totPok >= capacity) {
            capacity *= 2;
            bancoPok->escolhidos = (Pok *)realloc(bancoPok->escolhidos, capacity * sizeof(Pok));
        }
        bancoPok->escolhidos[bancoPok->totPok++] = atual;
    }

    fclose(file);
}

void exibirLista(Pok *atual) {
    char dateStr[20];
    strftime(dateStr, sizeof(dateStr), "%d/%m/%Y", &atual->captureDate);
    printf("[#%s -> %s: %s - [", atual->id, atual->name, atual->description);

    for (int i = 0; i < atual->num_types; i++) {
        printf("'%s'", atual->types[i]);
        if (i < atual->num_types - 1) {
            printf(", ");
        }
    }
    printf("] - [");
    for (int i = 0; i < atual->num_abilities; i++) {
        printf("%s", atual->abilities[i]);
        if (i < atual->num_abilities - 1) {
            printf(", ");
        }
    }
    printf("] - ");
    printf("%.1fkg - %.1fm - %d%% - %s - %d gen] - %s\n",
           atual->weight, atual->height, atual->captureRate,
           atual->isLegendary ? "true" : "false",
           atual->generation, dateStr);
}

// Função para obter o número máximo de caracteres em abilities
int getMaxLengthAbility(Pok *pokemons, int n) {
    int maxLen = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < pokemons[i].num_abilities; j++) {
            int len = strlen(pokemons[i].abilities[j]);
            if (len > maxLen) {
                maxLen = len;
            }
        }
    }
    return maxLen;
}

// Função de counting sort para Radix Sort
void countingSort(Pok *array, int size, int exp, int maxAbilityLen) {
    Pok *output = (Pok *)malloc(size * sizeof(Pok));
    int count[256] = {0};

    for (int i = 0; i < size; i++) {
        char *ability = array[i].abilities[0]; // Usar a primeira habilidade como chave
        int index = (i < size && ability) ? ability[exp] : 0;
        count[index]++;
    }

    for (int i = 1; i < 256; i++) {
        count[i] += count[i - 1];
    }

    for (int i = size - 1; i >= 0; i--) {
        char *ability = array[i].abilities[0];
        int index = (i < size && ability) ? ability[exp] : 0;
        output[count[index] - 1] = array[i];
        count[index]--;
    }

    for (int i = 0; i < size; i++) {
        array[i] = output[i];
    }

    free(output);
}

// Função de Radix Sort
void radixSort(Pok *array, int size) {
    int maxAbilityLen = getMaxLengthAbility(array, size);

    for (int exp = maxAbilityLen - 1; exp >= 0; exp--) {
        countingSort(array, size, exp, maxAbilityLen);
    }

    // Ordenar por nome em caso de empate
    for (int i = 0; i < size - 1; i++) {
        for (int j = i + 1; j < size; j++) {
            if (strcmp(array[i].abilities[0], array[j].abilities[0]) == 0 &&
                strcmp(array[i].name, array[j].name) > 0) {
                Pok temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
    }
}

void memoryFree(BancoPok *bancoPok) {
    for (int i = 0; i < bancoPok->totPok; i++) {
        free(bancoPok->escolhidos[i].types);
        for (int j = 0; j < bancoPok->escolhidos[i].num_abilities; j++) {
            free(bancoPok->escolhidos[i].abilities[j]);
        }
        free(bancoPok->escolhidos[i].abilities);
    }
    free(bancoPok->escolhidos);
}

int main() {
    long numMovimentacoes = 0; 
    long numComparacoes = 0;  
    BancoPok bancoPok;
    char *chamados[100];
    int numchamados = 0;

    char entrada[20];
    while (fgets(entrada, sizeof(entrada), stdin) != NULL) {
        entrada[strcspn(entrada, "\r\n")] = 0;
        if (strlen(entrada) == 0) {
            break; 
        } 
        chamados[numchamados++] = strdup(entrada);
    }

    readFilePok(&bancoPok, chamados, numchamados); 

    clock_t start = clock(); // Início do tempo
    radixSort(bancoPok.escolhidos, bancoPok.totPok);
    clock_t fim = clock(); // Fim do tempo

    double tempoExecucao = ((double)(fim - start)) / CLOCKS_PER_SEC * 1000; 

    for (int i = 0; i < bancoPok.totPok; i++) {
        exibirLista(&bancoPok.escolhidos[i]);
    }
    memoryFree(&bancoPok);
    for (int i = 0; i < numchamados; i++) {
        free(chamados[i]);
    }

    // arquivo de log
    FILE *logFile = fopen("813225_radixsort.txt", "w");
    if (logFile) {
        fprintf(logFile, "813225\t%.2lfms\t%ld\t%ld\n", tempoExecucao, numComparacoes, numMovimentacoes);
        fclose(logFile);
    } else {
        printf("Erro ao escrever no log.\n");
    }
    return 0;
}
