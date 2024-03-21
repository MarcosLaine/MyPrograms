#include <stdio.h>
#include <stdlib.h>

// Função para formatar um número com até 3 casas decimais
void formatarNumero(double valor, char *formatado) {
    if (valor == (long long)valor) {
        snprintf(formatado, 20, "%.0lf", valor); // Mostrar números inteiros sem decimais
    } else if (valor * 10 == (long long)(valor * 10)) {
        snprintf(formatado, 20, "%.1lf", valor); // Mostrar números com uma casa decimal
    } else if (valor * 100 == (long long)(valor * 100)) {
        snprintf(formatado, 20, "%.2lf", valor); // Mostrar números com duas casas decimais
    } else {
        snprintf(formatado, 20, "%.3lf", valor); // Mostrar números com até 3 casas decimais
    }
}

int main() {
    FILE *file;
    int n;

    // Leitura do número inteiro n
    scanf("%d", &n);

    // Abertura do arquivo para escrita
    file = fopen("valores.txt", "wb");

    // Leitura dos valores e escrita no arquivo
    double valor;
    for (int i = 0; i < n; i++) {
        scanf("%lf", &valor);
        fwrite(&valor, sizeof(double), 1, file);
    }

    // Fechar o arquivo
    fclose(file);

    // Reabrir o arquivo para leitura reversa
    file = fopen("valores.txt", "rb");

    // Posicionar o cursor no final do arquivo
    fseek(file, 0, SEEK_END);
    long long tamanho = ftell(file);

    // Ler os valores em ordem reversa e mostrar na tela
    for (long long i = tamanho - sizeof(double); i >= 0; i -= sizeof(double)) {
        fseek(file, i, SEEK_SET);
        fread(&valor, sizeof(double), 1, file);
        
        char formatado[20];
        formatarNumero(valor, formatado);
        printf("%s\n", formatado);
    }

    // Fechar o arquivo novamente
    fclose(file);

    return 0;
}
