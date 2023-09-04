#include <stdio.h>

void encontrarMaiorEMenor(int array[], int tamanho) {
    if (tamanho <= 0) {
        printf("O array está vazio.\n");
        return;
    }

    int maior = array[0];
    int menor = array[0];

    for (int i = 1; i < tamanho; i++) {
        if (array[i] > maior) {
            maior = array[i];
        }
        if (array[i] < menor) {
            menor = array[i];
        }
    }

    printf("O maior elemento do array é: %d\n", maior);
    printf("O menor elemento do array é: %d\n", menor);
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

    // Chamando a função para encontrar e exibir o maior e o menor elemento
    encontrarMaiorEMenor(array, tamanho);

    return 0;
}
