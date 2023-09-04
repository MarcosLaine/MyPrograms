#include <stdio.h>

//protótipos
void entrada(float *n1, float *n2, float *n3);
int validacao(float n1, float n2, float n3);
float ret_maior(float n1, float n2, float n3);
float ret_menor(float n1, float n2, float n3);

//chamada
int main (){
    float n1, n2, n3;
    entrada(&n1, &n2, &n3);
        printf("Maior Numero: %.2f\n", ret_maior(n1, n2, n3));
        printf("Menor Numero: %.2f\n", ret_menor(n1, n2, n3));
        return 1;
}

//implementação
void entrada(float *n1, float *n2, float *n3) {
    printf("Digite os tres Numeros:\n");
    scanf("%f %f %f", n1, n2, n3);
}

int validacao(float n1, float n2, float n3){
    if (n1 <= 0 || n1 > 100 || n2 <= 0 || n2 > 100 || n3 <= 0 || n3 > 100 ) {
    printf("Entrada invalida. Digite Numeros entre 0 e 100.\n");
    return 1;
    } else if (n1 == n2 || n2 == n3 || n1 == n3){
        printf("Numeros iguais. Digite Numeros diferentes.\n");
        return 0;
    } else {
        return 0;
    }
}

float ret_maior(float n1, float n2, float n3){
    float maior = n1;
    if (n2 > maior) {
        maior = n2;
    }
    if (n3 > maior) {
        maior = n3;
    }
    return maior;
}

float ret_menor(float n1, float n2, float n3){
    float menor = n1;
    if (n2 < menor) {
        menor = n2;
    }
    if (n3 < menor) {
        menor = n3;
    }
    return menor;
}
