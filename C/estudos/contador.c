/*  data: 16/05/2023
    Lista de exercícios, Assunto: Vetores
    Exercício: 2.5 contador maior e menor
    Aluno: Marcos Paulo da Silva Laine  RA:1430851
*/
#include <stdio.h>

void leVetor(int vetor[], int tamanho);
void imprimeVetor(int vetor[], int tamanho);
int contarImpares(int vetor[], int tamanho);
int contarPares(int vetor[], int tamanho);

int main() {
    int tamanho, i;
    printf("Informe o tamanho do vetor (deve ser par): ");
    scanf("%d", &tamanho);

    // Verificar se o tamanho é par
    if (tamanho % 2 != 0) {
        printf("O tamanho do vetor deve ser par.\n");
        return 0;
    }

    int vetor[tamanho];
    leVetor(vetor, tamanho);

    int count_impares = contarImpares(vetor, tamanho/2);
    int count_pares = contarPares(vetor + tamanho/2, tamanho/2);

    printf("Quantidade de elementos ímpares na primeira metade: %d\n", count_impares);
    printf("Quantidade de elementos pares na segunda metade: %d\n", count_pares);

    return 0;
}

void leVetor(int vetor[], int tamanho) {
    printf("Informe os elementos do vetor:\n");
    for (int i = 0; i < tamanho; i++) {
        printf("Elemento %d: ", i+1);
        scanf("%d", &vetor[i]);
    }
}

void imprimeVetor(int vetor[], int tamanho) {
    printf("Vetor: ");
    for (int i = 0; i < tamanho; i++) {
        printf("%d ", vetor[i]);
    }
    printf("\n");
}

int contarImpares(int vetor[], int tamanho) {
    int count_impares = 0;
    for (int i = 0; i < tamanho; i++) {
        if (vetor[i] % 2 != 0) {
            count_impares++;
        }
    }
    return count_impares;
}

int contarPares(int vetor[], int tamanho) {
    int count_pares = 0;
    for (int i = 0; i < tamanho; i++) {
        if (vetor[i] % 2 == 0) {
            count_pares++;
        }
    }
    return count_pares;
}
