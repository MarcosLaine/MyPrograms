#include <stdio.h>

#define TOTAL_NOTAS 7

int notas[TOTAL_NOTAS] = {100, 50, 20, 10, 5, 2, 1};
int estoque[TOTAL_NOTAS] = {0};

void calcularNotas(int valor, int quantidade[]) {
    int valor_restante = valor;

    for (int i = 0; i < TOTAL_NOTAS; i++) {
        quantidade[i] = valor_restante / notas[i];
        valor_restante %= notas[i];
    }
}

int main() {
    int valor;

    while (scanf("%d", &valor) != EOF) {
        printf("%d\n", valor);
        int quantidade[TOTAL_NOTAS] = {0};
        calcularNotas(valor, quantidade);

        for (int i = 0; i < TOTAL_NOTAS; i++) {
            printf("%d nota(s) de R$ %d,00\n", quantidade[i], notas[i]);
        }
    }

    return 0;
}
