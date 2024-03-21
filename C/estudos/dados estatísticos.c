/*  data: 16/05/2023
    Lista de exercícios, Assunto: Vetores
    Exercício: 2.13 dados estatísticos
    Aluno: Marcos Paulo da Silva Laine  RA:1430851
*/
#include <stdio.h>
#include <math.h>

void ler_vetor(float vetor[], int tamanho) {
    int i;
    for (i = 0; i < tamanho; i++) {
        printf("Insira a temperatura do dia %d: ", i + 1);
        scanf("%f", &vetor[i]);
    }
}

void imprimir_vetor(float vetor[], int tamanho) {
    int i;
    for (i = 0; i < tamanho; i++) {
        printf("%.2f ", vetor[i]);
    }
    printf("\n");
}

float calcular_media(float vetor[], int tamanho) {
    float soma = 0;
    int i;
    for (i = 0; i < tamanho; i++) {
        soma += vetor[i];
    }
    return soma / tamanho;
}

float calcular_desvio_padrao(float vetor[], int tamanho, float media) {
    float soma = 0;
    int i;
    for (i = 0; i < tamanho; i++) {
        soma += pow(vetor[i] - media, 2);
    }
    return sqrt(soma / tamanho);
}

int main() {
    int n;
    printf("Insira a quantidade de dias: ");
    scanf("%d", &n);

    float temperatura[n];
    ler_vetor(temperatura, n);

    float media = calcular_media(temperatura, n);
    float desvio_padrao = calcular_desvio_padrao(temperatura, n, media);

    printf("Temperaturas inseridas:\n");
    imprimir_vetor(temperatura, n);

    printf("Temperatura média: %.2f\n", media);
    printf("Desvio padrão da média: %.2f\n", desvio_padrao);

    return 0;
}
