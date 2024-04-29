
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

#define FILE_NAME "/tmp/characters.csv"
#define MAX_LINE_LENGTH 1024

typedef struct {
    char id[20];
    char name[100];
    char alternateNames[200];
    char house[50];
    char ancestry[50];
    char species[50];
    char patronus[50];
    bool hogwartsStaff;
    bool hogwartsStudent;
    char actorName[100];
    bool alive;
    char alternateActors[200];
    char dateOfBirth[20];
    int yearOfBirth;
    char eyeColour[20];
    char gender[20];
    char hairColour[20];
    bool wizard;
} Personagem;

Personagem* lerDadosDoArquivo(int* numPersonagens) {
    FILE* file = fopen(FILE_NAME, "r");
    if (file == NULL) {
        printf("Erro ao abrir o arquivo %s\n", FILE_NAME);
        return NULL;
    }

    char linha[MAX_LINE_LENGTH];
    Personagem* personagens = NULL;
    int capacidade = 0;
    *numPersonagens = 0;

    // Pular a primeira linha (cabeçalho)
    fgets(linha, MAX_LINE_LENGTH, file);

    while (fgets(linha, MAX_LINE_LENGTH, file) != NULL) {
        if (*numPersonagens >= capacidade) {
            capacidade += 10;
            personagens = realloc(personagens, capacidade * sizeof(Personagem));
        }

        char* token = strtok(linha, ";");
        int i = 0;
        Personagem personagem;

        while (token != NULL) {
            switch (i) {
                case 0:
                    strcpy(personagem.id, token);
                    break;
                case 1:
                    strcpy(personagem.name, token);
                    break;
                case 2:
                    strcpy(personagem.alternateNames, token);
                    break;
                case 3:
                    strcpy(personagem.house, token);
                    break;
                case 4:
                    strcpy(personagem.ancestry, token);
                    break;
                case 5:
                    strcpy(personagem.species, token);
                    break;
                case 6:
                    strcpy(personagem.patronus, token);
                    break;
                case 7:
                    personagem.hogwartsStaff = (strcmp(token, "true") == 0);
                    break;
                case 8:
                    personagem.hogwartsStudent = (strcmp(token, "true") == 0);
                    break;
                case 9:
                    strcpy(personagem.actorName, token);
                    break;
                case 10:
                    personagem.alive = (strcmp(token, "true") == 0);
                    break;
                case 11:
                    strcpy(personagem.alternateActors, token);
                    break;
                case 12:
                    strcpy(personagem.dateOfBirth, token);
                    break;
                case 13:
                    personagem.yearOfBirth = atoi(token);
                    break;
                case 14:
                    strcpy(personagem.eyeColour, token);
                    break;
                case 15:
                    strcpy(personagem.gender, token);
                    break;
                case 16:
                    strcpy(personagem.hairColour, token);
                    break;
                case 17:
                    personagem.wizard = (strcmp(token, "true") == 0);
                    break;
            }

            token = strtok(NULL, ";");
            i++;
        }

        personagens[*numPersonagens] = personagem;
        (*numPersonagens)++;
    }

    fclose(file);
    return personagens;
}

int main() {
    int numPersonagens;
    Personagem* personagens = lerDadosDoArquivo(&numPersonagens);

    if (personagens == NULL) {
        return 1;
    }
 
    char nomeBusca[MAX_NAME_LENGTH];

    while (fgets(nomeBusca, MAX_NAME_LENGTH, stdin)) {
        nomeBusca[strcspn(nomeBusca, "\n")] = 0; // Remove new line character
        if (strcmp(nomeBusca, "FIM") == 0) {
            break;
        }

        bool encontrado = false;
        for (int i = 0; i < numPersonagens; i++) {
            if (strcasecmp(personagens[i].name, nomeBusca) == 0) {
                encontrado = true;
                break;
            }
        }

        printf("%s\n", encontrado ? "SIM" : "NAO");
    }

    free(personagens);
    return 0;
}