#include <stdio.h>

void encontrarMaiorEMenorOtimizado(int array[], int tamanho) {
    if (tamanho <= 0) {
        printf("O array está vazio.\n");
        return;
    }

    int maior, menor;
    
    // Se o tamanho do array for ímpar, inicializamos ambos com o primeiro elemento
    // Se o tamanho for par, inicializamos comparando os dois primeiros elementos
    if (tamanho % 2 == 0) {
        if (array[0] > array[1]) {
            maior = array[0];
            menor = array[1];
        } else {
            maior = array[1];
            menor = array[0];
        }
        // Começamos a partir do terceiro elemento
        for (int i = 2; i < tamanho; i += 2) {
            if (array[i] > array[i + 1]) {
                if (array[i] > maior) {
                    maior = array[i];
                }
                if (array[i + 1] < menor) {
                    menor = array[i + 1];
                }
            } else {
                if (array[i + 1] > maior) {
                    maior = array[i + 1];
                }
                if (array[i] < menor) {
                    menor = array[i];
                }
            }
        }
    } else {
        maior = array[0];
        menor = array[0];
        // Começamos a partir do segundo elemento
        for (int i = 1; i < tamanho; i += 2) {
            if (array[i] > array[i + 1]) {
                if (array[i] > maior) {
                    maior = array[i];
                }
                if (array[i + 1] < menor) {
                    menor = array[i + 1];
                }
            } else {
                if (array[i + 1] > maior) {
                    maior = array[i + 1];
                }
                if (array[i] < menor) {
                    menor = array[i];
                }
            }
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

    // Chamando a função otimizada para encontrar e exibir o maior e o menor elemento
    encontrarMaiorEMenorOtimizado(array, tamanho);

    return 0;
}
