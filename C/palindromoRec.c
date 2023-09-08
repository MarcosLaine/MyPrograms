#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <ctype.h>

bool isPalindromo(char *linha, int inicio, int fim) {
    if (inicio >= fim) {
        return true;
    }
    
    if (linha[inicio] != linha[fim]) {//verifica se a letra na posição inicio é diferente da letra na posição fim
        return false;
    }

    return isPalindromo(linha, inicio + 1, fim - 1);// faz a chamada recursiva
}

int main() {
    char linha[100];
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
            if (!isspace(linha[k])) {//verifica espaços em branco
                temp[index] = linha[k];
                index++;
            }
        }
        temp[index] = '\0';

        int tamanho = strlen(temp);
        palindromo = isPalindromo(temp, 0, tamanho - 1);//chamada da função que verifica palindromo

        if (palindromo) {
            printf("SIM\n");
        } else {
            printf("NAO\n");
        }
    }

    return 0;
}
