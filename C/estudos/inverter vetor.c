/*  data: 16/05/2023
    Lista de exercícios, Assunto: Vetores
    Exercício: 2.9 vetor aleatório
    Aluno: Marcos Paulo da Silva Laine  RA:1430851
*/
#include <stdio.h>

void lerVetor(int vetor[], int tamanho) {
    int i;
    printf("Digite os elementos do vetor:\n");
    for (i = 0; i < tamanho; i++) {
        printf("Elemento %d: ", i+1);
        scanf("%d", &vetor[i]);
    }
}

void inverterVetor(int vetor[], int vetorInvertido[], int tamanho) {
    int i, j = 0;
    for (i = tamanho - 1; i >= 0; i--) {
        vetorInvertido[j] = vetor[i];
        j++;
    }
}

void imprimirVetor(int vetor[], int tamanho, const char* d) {
    int i;
    printf("%s", d);
    for (i = 0; i < tamanho; i++) {
        printf("%d", vetor[i]);
        if (i < tamanho - 1) {
            printf(",");
        }
    }
    printf("\n");
}

int main() {
    int n;
    printf("Digite o tamanho do vetor: ");
    scanf("%d", &n);

    int vetor[n];
    int vetorInvertido[n];

    lerVetor(vetor, n);
    inverterVetor(vetor, vetorInvertido, n);

    imprimirVetor(vetor, n, "Vetor original (v): ");
    imprimirVetor(vetorInvertido, n, "Vetor invertido (vi), obtido a partir do vetor original: ");

    return 0;
}
