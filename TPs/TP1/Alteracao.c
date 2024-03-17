#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

char substituir(char letra1, char letra2, char *frase, int index) {
    if (frase[index] == '\0') {
        return '\0'; 
    }

    if (frase[index] == letra1) {
        frase[index] = letra2;
    }

    return substituir(letra1, letra2, frase, index + 1); 
}

void subtituir(char letra1, char letra2, char *frase) {
    substituir(letra1, letra2, frase, 0); 
}

int main() {
    srand(4); 
    char entrada[100]; 
    char letra1, letra2;

    while (1) {
        fgets(entrada, sizeof(entrada), stdin);

        if (strcmp(entrada, "FIM\n") == 0) {
            break;
        }

        letra1 = 'a' + (rand() % 26);
        letra2 = 'a' + (rand() % 26);

        subtituir(letra1, letra2, entrada);

        printf("%s", entrada);
    }

    return 0;
}
