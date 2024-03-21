#include <stdio.h>

int main() {
    //declaração de variáveis
    int num, i, primo;
    char continuar;

     
    do /*início do comando DO*/ {
        // Lê o número do usuário
        printf("Digite um numero inteiro positivo maior que 1: ");
        scanf("%d", &num);

        // Verifica se o número é negativo, 1 ou 0
        if (num <= 1) {
            printf("nao e primo\n");
        }

        // Verifica se o número é primo
        primo = 0; /* primo será 0 se o número for primo, ou 1 se não for*/
        for (i = 2; i <= num / 2; i++) {
            if (num % i == 0) {
                primo = 1;
                break; /*Interrompe o loop*/
            }
        }

        // Imprime o resultado
        if (primo == 0) {
            printf("%d e um numero primo.\n", num);
        } else {
            printf("%d nao e um numero primo.\n", num);
        }

        // Pergunta se o usuário deseja continuar
        printf("Deseja fazer outra operacao? (S/N) ");
        scanf(" %c", &continuar);
    } while (continuar == 'S' || continuar == 's');

    return 0;
}
