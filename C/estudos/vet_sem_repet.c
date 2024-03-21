/*  data: 16/05/2023
    Lista de exercícios, Assunto: Vetores
    Exercício: 2.8 vetor sem repetição
    Aluno: Marcos Paulo da Silva Laine  RA:1430851
*/
#include <stdio.h>

void lerVetor(int vetor[], int tamanho);
void imprimirVetor(const int vetor[], int tamanho);
int buscarElemento(const int vetor[], int tamanho, int elemento);

int main() {
    int n;

    printf("Digite o tamanho do vetor: ");
    scanf("%d", &n);

    int vetor[n];

    printf("Digite os elementos do vetor:\n");
    lerVetor(vetor, n);

    printf("\nVetor digitado:\n");
    imprimirVetor(vetor, n);

    int elemento;
    printf("\nDigite um elemento para buscar no vetor: ");
    scanf("%d", &elemento);

    int indice = buscarElemento(vetor, n, elemento);
    if (indice != -1) {
        printf("\nO elemento %d foi encontrado no índice %d do vetor.\n", elemento, indice);
    } else {
        printf("\nO elemento %d nao foi encontrado no vetor.\n", elemento);
    }

    return 0;
}

void lerVetor(int vetor[], int tamanho) {
    for (int i = 0; i < tamanho; i++) {
        printf("Elemento %d: ", i + 1);
        scanf("%d", &vetor[i]);

        // Verifica se o elemento já existe no vetor
        for (int j = 0; j < i; j++) {
            if (vetor[j] == vetor[i]) {
                printf("Valor repetido! Digite novamente.\n");
                i--;
                break;
            }
        }
    }
}

void imprimirVetor(const int vetor[], int tamanho) {
    for (int i = 0; i < tamanho; i++) {
        printf("%d ", vetor[i]);
    }
    printf("\n");
}

int buscarElemento(const int vetor[], int tamanho, int elemento) {
    for (int i = 0; i < tamanho; i++) {
        if (vetor[i] == elemento) {
            return i;
        }
    }
    return -1;
}
