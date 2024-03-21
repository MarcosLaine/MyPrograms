#include <stdio.h>
#include <math.h>

//protótipos
void calcDelta(float a, float b, float c, float *delta);
void calcRaizes(float a, float b, float c, float *x1, float *x2);

//chamadas
int main () {
    float a, b, c, x1, x2, delta;
    printf("Entre com a, b, c; na formula ax^2 + bx + c: \n");
    scanf("%f %f %f", &a, &b, &c);
    calcRaizes(a, b, c, &x1, &x2);
    printf("x1 = %.2f\n", x1); 
    printf("x2 = %.2f\n", x2);
    return 0;
}

//implementação
void calcDelta(float a, float b, float c, float *delta){
    *delta = pow(b,2) - 4 * a * c;
}

void calcRaizes(float a, float b, float c, float *x1, float *x2){
    float delta;
    calcDelta(a, b, c, &delta);
    if (delta < 0) {
        printf("delta Negativo\n");
        return 1;
    }
    *x1 = (-b + sqrt(delta)) / (2 * a);
    *x2 = (-b - sqrt(delta)) / (2 * a);
}
