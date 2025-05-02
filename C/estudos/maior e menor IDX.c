/*  data: 16/05/2023
    Lista de exercícios, Assunto: Vetores
    Exercício: 2.4 maior e menor indices
    Aluno: Marcos Paulo da Silva Laine  RA:1430851
*/
#include <stdio.h>
#include <math.h>

void leVetor(float v[], int n);
float calcMedia(float v[], int n);
float retMaior(float v[], int n, int *indice);
float retMenor(float v[], int n, int *indice);
void imprimeVetor(float v[], int n);
void imprimeResultados(float media, float maior, int idxmaior, float menor, int idxmenor);

int main() {
    int n;
    float media, maior, menor;
    int idxmaior, idxmenor;
    printf("Digite o tamanho do vetor: ");
    scanf("%d", &n);
    float v[n];
    leVetor(v, n);
    printf("\nElementos do vetor original:\n");
    imprimeVetor(v, n);
    maior = retMaior(v, n, &idxmaior);
    menor = retMenor(v, n, &idxmenor);
    media = calcMedia(v, n);
    imprimeResultados(media, maior, idxmaior, menor, idxmenor);
    return 0;
}

void leVetor(float v[], int n) {
    for (int i = 0; i < n; i++) {
        printf("Digite o valor na posicao %d/%d: ", i + 1, n);
        scanf("%f", &v[i]);
    }
}

float calcMedia(float v[], int n) {
    float soma = 0;
    for (int i = 0; i < n; i++) {
        soma += v[i];
    }
    return soma / n;
}

float retMaior(float v[], int n, int *indice) {
    float maior = v[0];
    *indice = 0;
    for (int i = 1; i < n; i++) {
        if (v[i] > maior) {
            maior = v[i];
            *indice = i;
        }
    }
    return maior;
}

float retMenor(float v[], int n, int *indice) {
    float menor = v[0];
    *indice = 0;
    for (int i = 1; i < n; i++) {
        if (v[i] < menor) {
            menor = v[i];
            *indice = i;
        }
    }
    return menor;
}

void imprimeVetor(float v[], int n) {
    for (int i = 0; i < n; i++) {
        printf("%.2f ", v[i]);
    }
}

void imprimeResultados(float media, float maior, int idxmaior, float menor, int idxmenor) {
    printf("\nMaior elemento: %.2f (indice %d)\n", maior, idxmaior);
    printf("Menor elemento: %.2f (indice %d)\n", menor, idxmenor);
    printf("Media: %.2f\n", media);
}
