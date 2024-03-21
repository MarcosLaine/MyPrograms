/*  data: 16/05/2023
    Lista de exercícios, Assunto: Vetores
    Exercício: 2.11 vetor fat
    Aluno: Marcos Paulo da Silva Laine  RA:1430851
*/
#include <stdio.h>

// Função para calcular o fatorial de um número
int fat(int x) {
    int resultado = 1;
    int i;
    for (i = 1; i <= x; i++) {
        resultado *= i;
    }
    return resultado;
}

// Função para ler um vetor do usuário
void lerVetor(int vetor[], int tamanho) {
    int i;
    for (i = 0; i < tamanho; i++) {
        printf("Digite o elemento %d: ", i + 1);
        scanf("%d", &vetor[i]);
    }
}

// Função para imprimir um vetor
void imprimirVetor(int vetor[], int tamanho) {
    int i;
    for (i = 0; i < tamanho; i++) {
        printf("%d ", vetor[i]);
    }
    printf("\n");
}

int main() {
    int n, i;
    printf("Digite o tamanho do vetor: ");
    scanf("%d", &n);

    int vetorA[n];
    int vetorB[n];

    lerVetor(vetorA, n);

    for (i = 0; i < n; i++) {
        vetorB[i] = fat(vetorA[i]);
    }

    printf("Vetor A: ");
    imprimirVetor(vetorA, n);

    printf("Vetor B: ");
    imprimirVetor(vetorB, n);

    return 0;
}
