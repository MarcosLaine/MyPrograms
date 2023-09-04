/*  data: 16/05/2023
    Lista de exercícios, Assunto: Vetores
    Exercício: 2.10 busca em vetor aleatório
    Aluno: Marcos Paulo da Silva Laine  RA:1430851
*/
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

// Função para gerar um número aleatório de 1 a limit
int get_random_num(int limit) {
    return (rand() % limit) + 1;
}

// Função para preencher o vetor com números aleatórios
void preencherVetor(int vetor[], int tamanho) {
    int i;
    for (i = 0; i < tamanho; i++) {
        vetor[i] = get_random_num(100); // Gera números aleatórios de 1 a 100
    }
}

// Função para buscar um valor no vetor
int buscarValor(int vetor[], int tamanho, int valor) {
    int i, ultimoIndice = -1;
    for (i = 0; i < tamanho; i++) {
        if (vetor[i] == valor) {
            ultimoIndice = i;
        }
    }
    return ultimoIndice;
}

int main() {
    int n, i;
    printf("Digite o tamanho do vetor: ");
    scanf("%d", &n);

    int vetor[n];

    srand(time(NULL)); // Inicializa o gerador de números aleatórios com uma nova semente

    preencherVetor(vetor, n);

    // Imprime o vetor
    printf("Vetor: ");
    for (i = 0; i < n; i++) {
        printf("%d ", vetor[i]);
    }
    printf("\n");

    // Busca de valores
    int valor;
    char continuar;
    do {
        printf("Digite um valor para buscar no vetor: ");
        scanf("%d", &valor);

        int indice = buscarValor(vetor, n, valor);
        if (indice != -1) {
            printf("Valor %d encontrado no índice %d\n", valor, indice);
        } else {
            printf("Valor %d não encontrado no vetor\n", valor);
        }

        printf("Deseja buscar outro valor? (S/N): ");
        scanf(" %c", &continuar);
    } while (continuar == 'S' || continuar == 's');

    return 0;
}
