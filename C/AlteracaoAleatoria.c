#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>

char substituir(char frase[], char letra1, char letra2) {
    int len = strlen(frase);
    char fraseTrocada[len + 1];

    for (int i = 0; i < len; i++) {
        if (frase[i] == letra1) {
            fraseTrocada[i] = letra2;
        } else {
            fraseTrocada[i] = frase[i];
        }
    }
    fraseTrocada[len] = '\0';

    return strdup(fraseTrocada);
}

int main() {
    srand(4);
    char letra1 = 'a' + (rand() % 26);
    char letra2 = 'a' + (rand() % 26);

    while (1) {
        char entrada[100];
        scanf("%s", entrada);

        if (strcmp(entrada, "FIM") == 0) {
            break;
        }

        char *trocada = substituir(entrada, letra1, letra2);

        printf("%s\n", trocada);

        free(trocada);
    }

    return 0;
}
