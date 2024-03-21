/*  data: 16/05/2023
    Lista de exercícios, Assunto: Vetores
    Exercício: 2.6 soma de 3 vetores
    Aluno: Marcos Paulo da Silva Laine  RA:1430851
*/
#include <stdio.h>

#define TAMANHO 10

void lerVetor(float vetor[], int tamanho);
void somarVetores(const float vetorA[], const float vetorB[], float vetorC[], int tamanho);
void imprimirVetores(const float vetorA[], const float vetorB[], const float vetorC[], int tamanho);

int main() {
    float vetorA[TAMANHO];
    float vetorB[TAMANHO];
    float vetorC[TAMANHO];

    printf("Digite os elementos do vetor A:\n");
    lerVetor(vetorA, TAMANHO);

    printf("\nDigite os elementos do vetor B:\n");
    lerVetor(vetorB, TAMANHO);

    somarVetores(vetorA, vetorB, vetorC, TAMANHO);

    printf("\nVetor A:\n");
    imprimirVetores(vetorA, NULL, NULL, TAMANHO);

    printf("\nVetor B:\n");
    imprimirVetores(vetorB, NULL, NULL, TAMANHO);

    printf("\nVetor C (soma de A + B):\n");
    imprimirVetores(vetorC, NULL, NULL, TAMANHO);

    return 0;
}

void lerVetor(float vetor[], int tamanho) {
    for (int i = 0; i < tamanho; i++) {
        printf("Elemento %d: ", i + 1);
        scanf("%f", &vetor[i]);
    }
}

void somarVetores(const float vetorA[], const float vetorB[], float vetorC[], int tamanho) {
    for (int i = 0; i < tamanho; i++) {
        vetorC[i] = vetorA[i] + vetorB[i];
    }
}

void imprimirVetores(const float vetorA[], const float vetorB[], const float vetorC[], int tamanho) {
    for (int i = 0; i < tamanho; i++) {
        printf("Elemento %d: ", i + 1);

        if (vetorA != NULL) {
            printf("%.2f\t", vetorA[i]);
        }
        if (vetorB != NULL) {
            printf("%.2f\t", vetorB[i]);
        }
        if (vetorC != NULL) {
            printf("%.2f", vetorC[i]);
        }

        printf("\n");
    }
}
