#include <stdio.h>

// Função que verifica se um elemento está presente no array
int estaContido(int array[], int tamanho, int x) {
    for (int i = 0; i < tamanho; i++) {
        if (array[i] == x) {
            return 1; // Retorna 1 se o elemento estiver presente
        }
    }
    return 0; // Retorna 0 se o elemento não estiver presente
}

int main() {
    int tamanho;
    
    printf("Digite o tamanho do array: ");
    scanf("%d", &tamanho);

    int array[tamanho];

    // Preenchendo o array com os valores fornecidos pelo usuário
    for (int i = 0; i < tamanho; i++) {
        printf("Digite o valor para a posição %d: ", i);
        scanf("%d", &array[i]);
    }

    int x;
    printf("Digite o valor de x: ");
    scanf("%d", &x);

    // Verificando se x está contido no array
    if (estaContido(array, tamanho, x)) {
        printf("%d está contido no array.\n", x);
    } else {
        printf("%d não está contido no array.\n", x);
    }

    return 0;
}
