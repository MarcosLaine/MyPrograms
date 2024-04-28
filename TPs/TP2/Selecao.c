#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <ctype.h>

#define FILE_NAME "/tmp/characters.csv"
#define MAX_LINE_LENGTH 1024
#define MAX_FIELD_LENGTH 256
#define MAX_PERSONAGENS 100
#define TOTAL_CAMPOS 18

typedef struct {
    char id[MAX_FIELD_LENGTH];
    char name[MAX_FIELD_LENGTH];
    char alternateNames[MAX_FIELD_LENGTH];
    char house[MAX_FIELD_LENGTH];
    char ancestry[MAX_FIELD_LENGTH];
    char species[MAX_FIELD_LENGTH];
    char patronus[MAX_FIELD_LENGTH];
    bool hogwartsStaff;
    bool hogwartsStudent;
    char actorName[MAX_FIELD_LENGTH];
    bool alive;
    char alternateActors[MAX_FIELD_LENGTH];
    char dateOfBirth[MAX_FIELD_LENGTH];
    int yearOfBirth;
    char eyeColour[MAX_FIELD_LENGTH];
    char gender[MAX_FIELD_LENGTH];
    char hairColour[MAX_FIELD_LENGTH];
    bool wizard;
} Personagem;

bool parseBool(const char *str) {
    return strcasecmp(str, "true") == 0;
}

int parseIntSafe(const char *str) {
    char *endptr;
    long val = strtol(str, &endptr, 10);
    return *str != '\0' && *endptr == '\0' ? (int)val : -1;
}

void printPersonagem(const Personagem *p) {
    printf("[%s ## %s ## {%s} ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %d ## %s ## %s ## %s ## %s]\n",
           p->id, p->name, p->alternateNames, p->house, p->ancestry, p->species, p->patronus,
           p->hogwartsStaff ? "true" : "false", p->hogwartsStudent ? "true" : "false", p->actorName,
           p->alive ? "true" : "false", p->alternateActors, p->dateOfBirth, p->yearOfBirth,
           p->eyeColour, p->gender, p->hairColour, p->wizard ? "true" : "false");
}

int lerDadosDoArquivo(const char *fileName, Personagem personagens[], int maxPersonagens) {
    FILE *file = fopen(fileName, "r");
    if (!file) {
        perror("Erro ao abrir o arquivo");
        return 0;
    }

    char line[MAX_LINE_LENGTH];
    fgets(line, sizeof(line), file); // Pular cabeçalho

    int count = 0;
    while (fgets(line, sizeof(line), file) && count < maxPersonagens) {
        stripNewline(line); // Remove caracteres de nova linha no final

        char *token = strtok(line, ";");
        int field = 0;

        Personagem temp = {0};
        while (token != NULL && field < TOTAL_CAMPOS) {
            switch (field) {
                case 0: strncpy(temp.id, token, MAX_FIELD_LENGTH - 1); break;
                case 1: strncpy(temp.name, token, MAX_FIELD_LENGTH - 1); break;
                case 2: strncpy(temp.alternateNames, token, MAX_FIELD_LENGTH - 1); break;
                case 3: strncpy(temp.house, token, MAX_FIELD_LENGTH - 1); break;
                case 4: strncpy(temp.ancestry, token, MAX_FIELD_LENGTH - 1); break;
                case 5: strncpy(temp.species, token, MAX_FIELD_LENGTH - 1); break;
                case 6: strncpy(temp.patronus, token, MAX_FIELD_LENGTH - 1); break;
                case 7: temp.hogwartsStaff = parseBool(token); break;
                case 8: temp.hogwartsStudent = parseBool(token); break;
                case 9: strncpy(temp.actorName, token, MAX_FIELD_LENGTH - 1); break;
                case 10: temp.alive = parseBool(token); break;
                case 11: strncpy(temp.alternateActors, token, MAX_FIELD_LENGTH - 1); break;
                case 12: strncpy(temp.dateOfBirth, token, MAX_FIELD_LENGTH - 1); break;
                case 13: temp.yearOfBirth = parseIntSafe(token); break;
                case 14: strncpy(temp.eyeColour, token, MAX_FIELD_LENGTH - 1); break;
                case 15: strncpy(temp.gender, token, MAX_FIELD_LENGTH - 1); break;
                case 16: strncpy(temp.hairColour, token, MAX_FIELD_LENGTH - 1); break;
                case 17: temp.wizard = parseBool(token); break;
                default: break;
            }
            token = strtok(NULL, ";");
            field++;
        }

        if (field == TOTAL_CAMPOS) {
            personagens[count++] = temp;
        }
    }

    fclose(file);
    return count;
}


void ordenarPorSelecao(Personagem personagens[], int count) {
    for (int i = 0; i < count - 1; i++) {
        int min_idx = i;
        for (int j = i + 1; j < count; j++) {
            if (strcmp(personagens[j].name, personagens[min_idx].name) < 0) {
                min_idx = j;
            }
        }
        if (min_idx != i) {
            Personagem temp = personagens[i];
            personagens[i] = personagens[min_idx];
            personagens[min_idx] = temp;
        }
    }
}

int compararPorNome(const void *a, const void *b) {
    const Personagem *pa = (const Personagem *)a;
    const Personagem *pb = (const Personagem *)b;
    return strcmp(pa->name, pb->name);
}

void stripNewline(char *str) {
    char *ptr;
    if ((ptr = strchr(str, '\n')) != NULL) {
        *ptr = '\0';
    }
    if ((ptr = strchr(str, '\r')) != NULL) {
        *ptr = '\0';
    }
}

int main() {
    Personagem personagens[MAX_PERSONAGENS];
    char ids[MAX_PERSONAGENS][MAX_FIELD_LENGTH]; // Suponha que não teremos mais IDs do que personagens
    int totalPersonagens = lerDadosDoArquivo(FILE_NAME, personagens, MAX_PERSONAGENS);
    int totalIds = 0;
    
    // Ler os IDs de entrada
    while (scanf("%s", ids[totalIds]) == 1) {
        if (strcmp(ids[totalIds], "FIM") == 0) {
            break;
        }
        totalIds++;
    }
    
    // Filtrar personagens que correspondem aos IDs fornecidos
    Personagem personagensFiltrados[MAX_PERSONAGENS];
    int totalPersonagensFiltrados = 0;
    for (int i = 0; i < totalIds; i++) {
        for (int j = 0; j < totalPersonagens; j++) {
            if (strcmp(ids[i], personagens[j].id) == 0) {
                personagensFiltrados[totalPersonagensFiltrados++] = personagens[j];
                break;
            }
        }
    }
    
    // Ordenar os personagens filtrados por nome
    qsort(personagensFiltrados, totalPersonagensFiltrados, sizeof(Personagem), compararPorNome);

    // Imprimir os personagens filtrados e ordenados
    for (int i = 0; i < totalPersonagensFiltrados; i++) {
        printPersonagem(&personagensFiltrados[i]);
    }

    return 0;
}