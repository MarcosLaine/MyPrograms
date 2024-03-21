/*  data: 16/05/2023
    Lista de exercícios, Assunto: Vetores
    Exercício: 2.14 conjuntos
    Aluno: Marcos Paulo da Silva Laine  RA:1430851
*/
#include <stdio.h>

void leVetor(int vetor[], int tamanho) {
    int i;
    for (i = 0; i < tamanho; i++) {
        printf("Insira o elemento %d: ", i + 1);
        scanf("%d", &vetor[i]);
    }
}

void imprimeVetor(int vetor[], int tamanho) {
    int i;
    for (i = 0; i < tamanho; i++) {
        printf("%d ", vetor[i]);
    }
    printf("\n");
}

void intersecao(int v1[], int v2[], int vi[], int n, int *qtde) {
    int i, j;
    *qtde = 0;

    for (i = 0; i < n; i++) {
        for (j = 0; j < n; j++) {
            if (v1[i] == v2[j]) {
                vi[*qtde] = v1[i];
                (*qtde)++;
                break;
            }
        }
    }
}

void uniao(int v1[], int v2[], int vu[], int n, int *qtde) {
    int i, j;
    *qtde = 0;

    for (i = 0; i < n; i++) {
        vu[*qtde] = v1[i];
        (*qtde)++;
    }

    for (i = 0; i < n; i++) {
        int elementoRepetido = 0;
        for (j = 0; j < *qtde; j++) {
            if (v2[i] == vu[j]) {
                elementoRepetido = 1;
                break;
            }
        }
        if (!elementoRepetido) {
            vu[*qtde] = v2[i];
            (*qtde)++;
        }
    }
}

void diferenca(int v1[], int v2[], int vd[], int n, int *qtde) {
    int i, j;
    *qtde = 0;

    for (i = 0; i < n; i++) {
        int elementoRepetido = 0;
        for (j = 0; j < n; j++) {
            if (v1[i] == v2[j]) {
                elementoRepetido = 1;
                break;
            }
        }
        if (!elementoRepetido) {
            vd[*qtde] = v1[i];
            (*qtde)++;
        }
    }
}

int main() {
    int n;
    printf("Insira o tamanho dos vetores: ");
    scanf("%d", &n);

    int v1[n], v2[n];
    printf("Insira os elementos do primeiro vetor:\n");
    leVetor(v1, n);

    printf("Insira os elementos do segundo vetor:\n");
    leVetor(v2, n);

    int vi[n], vu[2 * n], vd[n];
    int qtdeIntersecao, qtdeUniao, qtdeDiferenca;

    intersecao(v1, v2, vi, n, &qtdeIntersecao);
    uniao(v1, v2, vu, n, &qtdeUniao);
    diferenca(v1, v2, vd, n, &qtdeDiferenca);

    printf("Vetor Interseção:\n");
    imprimeVetor(vi, qtdeIntersecao);

    printf("Vetor União:\n");
    imprimeVetor(vu, qtdeUniao);

    printf("Vetor Diferença (v1 - v2):\n");
    imprimeVetor(vd, qtdeDiferenca);

    return 0;
}
