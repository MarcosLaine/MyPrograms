#include <stdio.h>

float fahCel (float temp);
float celFah ( float temp);

int main () {
    float temp, res;
    int  opcao;
    printf("digite a temperatura que voce que converter\n");
    scanf("%f", &temp);
    printf("voce quer converter: \n1- Celcius para Fahrenheit \n2- Fahrenheit para Celcius\n");
    scanf("%i", &opcao);
    if (opcao == 1) {
        float fah = celFah(temp);
        printf ("%.2fC euqivalem a %.2fF\n", temp, fah);
    } else if (opcao == 2) {
        float cel = fahCel (temp);
        printf(" %.2fF equivalem a %.2fC\n", temp, cel);
    }

    return 0;
}

float fahCel(float temp){
   float res = (temp - 32)*5 / 9;
    return res;
}

float celFah( float temp){
    float res = (temp * 9 / 5) + 32;
    return res;
}