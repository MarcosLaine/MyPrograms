/*  data: 16/05/2023
    Lista de exercícios, Assunto: Vetores
    Exercício: 2.12 separação em vetores específicos
    Aluno: Marcos Paulo da Silva Laine  RA:1430851
*/
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void preencher_vetor(int vetor[], int tamanho) {
    int i;
    srand(time(NULL));
    for (i = 0; i < tamanho; i++) {
        vetor[i] = rand() % 200 + 1;
    }
}

int eh_primo(int num) {
    int i;
    if (num < 2) {
        return 0;
    }
    for (i = 2; i <= num / 2; i++) {
        if (num % i == 0) {
            return 0;
        }
    }
    return 1;
}

void alimentar_vetores(int vetor[], int tamanho, int vpr[], int vpa[], int vpi[]) {
    int i, j = 0, k = 0, l = 0;
    for (i = 0; i < tamanho; i++) {
        if (vetor[i] % 2 == 0) {
            vpa[j] = vetor[i];
            j++;
        } else {
            vpi[k] = vetor[i];
            k++;
        }
        if (eh_primo(vetor[i])) {
            vpr[l] = vetor[i];
            l++;
        }
    }
}

void imprimir_vetor(int vetor[], int tamanho) {
    int i;
    for (i = 0; i < tamanho; i++) {
        if (vetor[i] != 0) {
            printf("%d ", vetor[i]);
        }
    }
    printf("\n");
}

int main() {
    int v[200];
    int vpr[200] = {0}, vpa[200] = {0}, vpi[200] = {0};
    int i;

    preencher_vetor(v, 200);
    alimentar_vetores(v, 200, vpr, vpa, vpi);

    printf("Vetor v:\n");
    imprimir_vetor(v, 200);

    printf("Vetor VPR (números primos):\n");
    imprimir_vetor(vpr, 200);

    printf("Vetor VPA (números pares):\n");
    imprimir_vetor(vpa, 200);

    printf("Vetor VPI (números ímpares):\n");
    imprimir_vetor(vpi, 200);

    return 0;
}
