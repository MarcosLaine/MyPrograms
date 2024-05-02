#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

// Definindo a estrutura do Personagem
typedef struct {
    char id[100];
    char name[100];
    char alternateNames[500]; // Pode ser uma string com os nomes alternativos separados por vírgula
    char house[100];
    char ancestry[100];
    char species[100];
    char patronus[100];
    bool hogwartsStaff;
    bool hogwartsStudent;
    char actor[100];
    bool alive;
    char dateOfBirth[100];
    char yearOfBirth[100];
    char eyeColour[100];
    char gender[100];
    char hairColour[100];
    bool wizard;
} Character;

// Função para ler os dados de um personagem a partir de uma string
void readCharacterData(char* str, Character* character) {
    char* token = strsep(&str, ";");
    strcpy(character->id, token);

    token = strsep(&str, ";");
    strcpy(character->name, token);

    token = strsep(&str, ";");
    strcpy(character->alternateNames, token);

    token = strsep(&str, ";");
    strcpy(character->house, token);

    token = strsep(&str, ";");
    strcpy(character->ancestry, token);

    token = strsep(&str, ";");
    strcpy(character->species, token);

    token = strsep(&str, ";");
    strcpy(character->patronus, token);

    token = strsep(&str, ";");
    character->hogwartsStaff = (strcmp(token, "1") == 0);

    token = strsep(&str, ";");
    character->hogwartsStudent = (strcmp(token, "1") == 0);

    token = strsep(&str, ";");
    strcpy(character->actor, token);

    token = strsep(&str, ";");
    character->alive = (strcmp(token, "1") == 0);

    token = strsep(&str, ";");
    strcpy(character->dateOfBirth, token);

    token = strsep(&str, ";");
    strcpy(character->yearOfBirth, token);

    token = strsep(&str, ";");
    strcpy(character->eyeColour, token);

    token = strsep(&str, ";");
    strcpy(character->gender, token);

    token = strsep(&str, "\n");
    strcpy(character->hairColour, token);

    // Supondo que se a espécie for "human", então é um bruxo
    character->wizard = (strcmp(character->species, "human") == 0);
}

// Função para imprimir os dados de um personagem
void printCharacter(Character* character) {
    printf("[%s ## %s ## %s ## %s ## %s ## %s ## %s ## %d ## %d ## %s ## %d ## %s ## %s ## %s ## %s ## %s ## %d]\n",
           character->id, character->name, character->alternateNames, character->house, character->ancestry, 
           character->species, character->patronus, character->hogwartsStaff, character->hogwartsStudent,
           character->actor, character->alive, character->dateOfBirth, character->yearOfBirth, character->eyeColour,
           character->gender, character->hairColour, character->wizard);
}

// Adaptação da função readPlayersFromFile para ler os personagens
void readCharactersFromFile(Character* characters[], const char* filename) {
    FILE *file = fopen(filename, "r");
    if (file == NULL) {
        perror("Error opening file");
        return;
    }

    char line[1024];
    int i = 0;
    while (fgets(line, 1024, file) && i < 3922) { // 3922 é apenas um exemplo, ajuste conforme necessário
        characters[i] = (Character*) malloc(sizeof(Character));
        if (characters[i] == NULL) {
            perror("Error allocating memory");
            return;
        }
        readCharacterData(line, characters[i]);
        i++;
    }
    fclose(file);
}

// Função para verificar se a entrada é "FIM" para encerrar o programa
bool isEnd(const char *str) {
    return (strcmp(str, "FIM") == 0);
}

int main() {
    const int num_characters = 3922; // ajuste conforme o número real de personagens
    Character* characters[num_characters];

    readCharactersFromFile(characters, "/tmp/characters.csv");

    char input[100];
    scanf("%99s", input);
    while (!isEnd(input)) {
        int position = atoi(input);
        if (position >= 0 && position < num_characters) {
            printCharacter(characters[position]);
        }
        scanf("%99s", input);
    }

    // Libera a memória alocada
    for (int i = 0; i < num_characters; i++) {
        free(characters[i]);
    }

    return 0;
}
