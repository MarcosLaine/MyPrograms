#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

char substituir(char letra1, char letra2, char *frase, int index) {
    if (frase[index] == '\0') {
        return '\0'; // Retorna o caractere nulo para indicar o fim da string
    }

    if (frase[index] == letra1) {
        frase[index] = letra2; // Substitui a letra1 pela letra2 na posição atual
    }

    return substituir(letra1, letra2, frase, index + 1); // Chama a função recursiva para o próximo caractere
}

void subtituir(char letra1, char letra2, char *frase) {
    substituir(letra1, letra2, frase, 0); // Inicia a função recursiva a partir do índice 0
}

int main() {
    srand(4); // Defina a semente uma vez no início do programa
    char entrada[100]; // Tamanho máximo da entrada
    char letra1, letra2;

    while (1) {
        fgets(entrada, sizeof(entrada), stdin);

        if (strcmp(entrada, "FIM\n") == 0) {
            break;
        }

        letra1 = 'a' + (rand() % 26); // Gera letra1 aleatoriamente
        letra2 = 'a' + (rand() % 26); // Gera letra2 aleatoriamente

        subtituir(letra1, letra2, entrada);

        printf("%s", entrada);
    }

    return 0;
}
