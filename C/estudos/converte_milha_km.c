#include <stdio.h>

float milha (float num);
float km (float num);

int main () {
    float num, res;
    int opcao;
    char continuar;


    do {
        printf("digite a distancia que voce quer converter\n");
        scanf("%f", &num);
    
        printf("voce quer:\n1- converter milha para km \n2- converter km para milha\n");
        scanf ("%i", &opcao);

        switch (opcao)
        {
        case 1: res = milha(num);
            break;
        case 2: res = km(num);
            break;
        } 


        printf("%.2f", res);

        printf("\nquer continuar?\n");
        scanf(" %c", &continuar);
    }   while (continuar == 'S' || continuar == 's');
    
    return 0;
}

float milha (float num) {
    return num*1.6;
}

float km (float num) {
    return num / 1.6;
}