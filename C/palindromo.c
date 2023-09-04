#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <ctype.h>

int main() {
    char linha[100];
    int tamanho, i, j;
    bool palindromo;

    while (true) {
        fgets(linha, sizeof(linha), stdin);
        linha[strcspn(linha, "\n")] = '\0'; // Remove a quebra de linha

        // Verifica se a linha é "FIM" para sair do programa
        if (strcmp(linha, "fim") == 0 || strcmp(linha, "FIM") == 0) {
            break;
        }

        // Remove espaços em branco da linha
        int len = strlen(linha);
        char temp[len];
        int index = 0;
        for (int k = 0; k < len; k++) {
            if (!isspace(linha[k])) {
                temp[index] = linha[k];
                index++;
            }
        }
        temp[index] = '\0';
        strcpy(linha, temp);

        tamanho = strlen(linha);
        palindromo = true;

        // Verifica se a palavra é um palíndromo por iteração
        for (i = 0, j = tamanho - 1; i < j; i++, j--) {
            if (linha[i] != linha[j]) {
                palindromo = false;
                break;
            }
        }

        if (palindromo) {
            printf("SIM\n");
        } else {
            printf("NAO\n");
        }
    }

    return 0;
}
