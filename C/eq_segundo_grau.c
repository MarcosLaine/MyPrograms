#include <stdio.h>
#include <math.h>

//protótipos
float calcDelta (float a, float b, float c);
float calcX1 (float a, float b, float c);
float calcX2 (float a, float b, float c);

//chamadas
int main () {
    float a, b, c, x1, x2;
    if (calcDelta < 0) {
        printf("delta Negativo");
        return 1;
    } else {
    printf("Entre com a, b, c; na formula ax^2 + bx + c: \n");
    scanf("%f %f %f", &a, &b, &c);
    x1 = calcX1(a, b, c);
    x2 = calcX2(a, b, c);
    printf("x1 = %.2f\n", x1);
    printf("x2 = %.2f\n", x2);
    return 0;}
}

//implementação
float calcDelta(float a, float b, float c){
    return (pow(b,2) - 4 * a * c);
}

float calcX1(float a, float b, float c){
    return (-b + sqrt(calcDelta(a, b, c)))/(2 * a);
}

float calcX2(float a, float b, float c){
    return (-b - sqrt(calcDelta(a, b, c)))/(2 * a);
}
