#include <stdio.h>

int main() {
    FILE *file;
    int n;

    scanf("%d", &n);

    // Abertura do arquivo
    file = fopen("valores.txt", "wb");

    // Leitura e escrita no arquivo
    for (int i = 0; i < n; i++) {
        double valor;
        scanf("%lf", &valor);
        fwrite(&valor, sizeof(double), 1, file);
    }

    fclose(file);

    // Reabertura do arquivo para leitura reversa
    file = fopen("valores.txt", "rb");

    // Posicionamento no final do arquivo
    fseek(file, 0, SEEK_END);

    // Leitura de trás para frente e exibição dos valores
    for (int i = 0; i < n; i++) {
        fseek(file, -sizeof(double), SEEK_CUR);
        double valor;
        fread(&valor, sizeof(double), 1, file);
        printf("%lf\n", valor);
    }

    // Fechamento do arquivo novamente
    fclose(file);

    return 0;
}
