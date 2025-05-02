#include <stdio.h>

int calcularIdade(int dia, int mes, int ano) {
    int idade = 2019 - ano;
    if (mes == 12 && dia == 31) {
        printf("Você terá %d anos hoje!\n", idade);
    } else {
        if (mes > 12 || (mes == 12 && dia > 31)) {
            idade--;
        }
        printf("Sua idade no dia 31/12/2019 era %d anos.\n", idade);
    }
    return idade;
}

int main() {
    int dia, mes, ano;
    printf("Digite sua data de nascimento (dia, mês, ano): ");
    scanf("%d %d %d", &dia, &mes, &ano);
    calcularIdade(dia, mes, ano);
    return 0;
}
