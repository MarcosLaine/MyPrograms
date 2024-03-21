#include <stdio.h>
#include <stdbool.h>

int verifica_intervalo(float ini, float fim, float valor);

int verifica_intervalo(float ini, float fim, float valor) {
    if (valor <= fim && valor >= ini) {
        return true;
    } else {
        return false;
    }
}

int main () {
    float ini, fim, valor;
    printf(" digite o inicio do intervalo fechado, o fim, e o valor que deseja verificar\n");
    scanf("%f %f %f", &ini, &fim, &valor);
    if ( verifica_intervalo (ini, fim, valor) == true) {
        printf("%f esta no intervalo", valor);
    } else {
        printf("%f nao esta no intervalo", valor);
    }
    return 0;
}