// incluir bibliotecas
#include <stdio.h>
#include <math.h>

// prototipagem de funções
float conta(float montante, float tempo, float capital, float taxa, float aportes);
void entrada(float *montante, float *tempo , float *capital, float *taxa, float *aportes);

//implementação da função main
int main (){
    
    char repetir;
    float montante, tempo, capital, taxa, aportes;
    do {
        entrada(&montante, &tempo, &capital, &taxa, &aportes);//chamada da função entrada
        montante = conta(montante, tempo, capital, taxa, aportes);// chamada da função conta
        printf("O valor do montante e: %.3f\n", montante);//printa o valor do montante
        printf("quer repetir a operacao? (S/N)\n");
        scanf(" %c", &repetir);
    } while (repetir == 's' || repetir == 'S');//pergunta se o usuário quer repetir a operação
    return 0;
    system ("pause");//irrelevante aqui no replit
}

//implementação da função entrada(entrar com todos os valores e passar por referência)
void entrada(float *montante, float *tempo , float *capital, float *taxa, float *aportes){
    char resTempo;
    char resAporte;
    printf("Informe o capital \n");//capital inicial
    scanf("%f", &(*capital));
    printf("Informe a taxa anual de juros pretendida\n");//taxa de juros
    scanf("%f", &(*taxa));
    printf("voce quer fazer o calculo em anos ou meses? M para meses ou A para anos\n");// pergunta de que modo quer fazer o cálculo
    scanf(" %c", &resTempo);
    do {
        if (resTempo == 'M' || resTempo == 'm'){
            printf("Digite o tempo em meses\n");
            scanf("%f", &(*tempo));
            *tempo = *tempo/12;//transforma meses em anos para facilitar o calculo
        } else if (resTempo == 'A' || resTempo == 'a'){
            printf("Digite o tempo em anos\n");
            scanf("%f", &(*tempo));
            *tempo = *tempo;
        }
        if (resTempo != 'A' && resTempo != 'a' && resTempo != 'M' && resTempo != 'm') {
            printf("formato invalido. Informe M ou A\n");
            scanf(" %c", &resTempo);
        }
    } while (resTempo != 'A' && resTempo != 'a' && resTempo != 'M' && resTempo != 'm');// obriga o usuário a fazer a escolha de meses ou anos
    printf("voce quer fazer aportes mensais? (S/N)\n");
    scanf(" %c", &resAporte);
    if (resAporte == 's' || resAporte == 'S') {//pergunta se o usuário quer fazer aportes
        printf("digite o valor do aporte mensal\n");
        scanf("%f", &(*aportes));//pede o valor do aporte
    
    }
    
}

//Implementação da função conta
float conta(float montante, float tempo, float capital, float taxa, float aportes){
    taxa = taxa / 100;//transforma a taxa em decimal para faciltar o cálculo
    for (int i = 1; i <= tempo * 12; i++)//faz a conta de mes em mes para calcular os juros com os aportes
      {
        montante = capital * pow(1 + taxa, tempo) + aportes;//conta dos juros com aportes
    }
    return montante;
    //M = P * (1 + i)^n + A * [(1 + i)^n - 1] / i
    //[(1+ taxa) elevado a: prazo desejado/prazo que tenho – 1] x 100.
}