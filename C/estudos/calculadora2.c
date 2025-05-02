#include <stdio.h>
#include <math.h>

float conta(float montante, float tempo, float capital, float taxa, float aportes);
void entrada(float *montante, float *tempo , float *capital, float *taxa, float *aportes);

int main (){
    char repetir;
    float montante, tempo, capital, taxa, aportes;
    do {
        entrada(&montante, &tempo, &capital, &taxa, &aportes);
        montante = conta(montante, tempo, capital, taxa, aportes);
        printf("O valor do montante e: %.3f\n", montante);
        printf("quer repetir a operacao? (S/N)\n");
        scanf(" %c", &repetir);
    } while (repetir == 's' || repetir == 'S');
    return 0;
}

void entrada(float *montante, float *tempo , float *capital, float *taxa, float *aportes){
    char resTempo;
    char resAporte;
    printf("Informe o capital \n");
    scanf("%f", &(*capital));
    printf("Informe a taxa anual de juros pretendida\n");
    scanf("%f", &(*taxa));
    printf("voce quer fazer o calculo em anos ou meses? M para meses ou A para anos\n");
    scanf(" %c", &resTempo);
    do {
        if (resTempo == 'M' || resTempo == 'm'){
            printf("Digite o tempo em meses\n");
            scanf("%f", &(*tempo));
            *tempo = *tempo/12;
        } else if (resTempo == 'A' || resTempo == 'a'){
            printf("Digite o tempo em anos\n");
            scanf("%f", &(*tempo));
            *tempo = *tempo;
        }
        if (resTempo != 'A' && resTempo != 'a' && resTempo != 'M' && resTempo != 'm') {
            printf("formato invalido. Informe M ou A\n");
            scanf(" %c", &resTempo);
        }
    } while (resTempo != 'A' && resTempo != 'a' && resTempo != 'M' && resTempo != 'm');
    printf("voce quer fazer aportes mensais? (S/N)\n");
    scanf(" %c", &resAporte);
    if (resAporte == 's' || resAporte == 'S') {
        printf("digite o valor do aporte mensal\n");
        scanf("%f", &(*aportes));
    
    }
    
}

float conta(float montante, float tempo, float capital, float taxa, float aportes){
    taxa = taxa / 100;
    for (int i = 1; i <= tempo * 12; i++) {
        montante = capital * pow(1 + taxa, tempo) + aportes * (pow((1+taxa),tempo)-1)/taxa;
    }
    return montante;
    //M = P * (1 + i)^n + A * [(1 + i)^n - 1] / i
    //[(1+ taxa) elevado a: prazo desejado/prazo que tenho – 1] x 100.
}