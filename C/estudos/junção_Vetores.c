/*  data: 16/05/2023
    Lista de exercícios, Assunto: Vetores
    Exercício: 2.7 junção de vetores
    Aluno: Marcos Paulo da Silva Laine  RA:1430851
*/
#include <stdio.h>
#include <stdlib.h>

void lerVetor(float vetor[], int tamanho);
void imprimirVetor(const float vetor[], int tamanho);
void juntarVetores(const float vetorA[], int tamanhoA, const float vetorB[], int tamanhoB, const float vetorC[], int tamanhoC, float vetorD[], int tamanhoD);

int main() {
    int n1, n2, n3;
    printf("Digite o tamanho do vetor A: ");
    scanf("%d", &n1);

    printf("Digite o tamanho do vetor B: ");
    scanf("%d", &n2);

    printf("Digite o tamanho do vetor C: ");
    scanf("%d", &n3);

    float *vetorA = (float *)malloc(n1 * sizeof(float));
    float *vetorB = (float *)malloc(n2 * sizeof(float));
    float *vetorC = (float *)malloc(n3 * sizeof(float));
    float *vetorD = (float *)malloc((n1 + n2 + n3) * sizeof(float));

    printf("\nDigite os elementos do vetor A:\n");
    lerVetor(vetorA, n1);

    printf("\nDigite os elementos do vetor B:\n");
    lerVetor(vetorB, n2);

    printf("\nDigite os elementos do vetor C:\n");
    lerVetor(vetorC, n3);

    juntarVetores(vetorA, n1, vetorB, n2, vetorC, n3, vetorD, n1 + n2 + n3);

    printf("\nVetor A:\n");
    imprimirVetor(vetorA, n1);

    printf("\nVetor B:\n");
    imprimirVetor(vetorB, n2);

    printf("\nVetor C:\n");
    imprimirVetor(vetorC, n3);

    printf("\nVetor D (juncao de A, B e C):\n");
    imprimirVetor(vetorD, n1 + n2 + n3);

    free(vetorA);
    free(vetorB);
    free(vetorC);
    free(vetorD);

    return 0;
}

void lerVetor(float vetor[], int tamanho) {
    for (int i = 0; i < tamanho; i++) {
        printf("Elemento %d: ", i + 1);
        scanf("%f", &vetor[i]);
    }
}

void imprimirVetor(const float vetor[], int tamanho) {
    for (int i = 0; i < tamanho; i++) {
        printf("%.2f\t", vetor[i]);
    }
    printf("\n");
}

void juntarVetores(const float vetorA[], int tamanhoA, const float vetorB[], int tamanhoB, const float vetorC[], int tamanhoC, float vetorD[], int tamanhoD) {
    int indexD = 0;

    for (int i = 0; i < tamanhoA; i++) {
        vetorD[indexD++] = vetorA[i];
    }

    for (int i = 0; i < tamanhoB; i++) {
        vetorD[indexD++] = vetorB[i];
    }

    for (int i = 0; i < tamanhoC; i++) {
        vetorD[indexD++] = vetorC[i];
    }
}
