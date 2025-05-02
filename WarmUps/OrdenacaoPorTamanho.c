/*Crie um programa para ordenar um conjunto de strings pelo seu tamanho. Seu programa deve receber um conjunto de strings e retornar este mesmo conjunto ordenado pelo tamanho das palavras, se o tamanho das strings for igual, deve-se manter a ordem original do conjunto.

Entrada
A primeira linha da entrada possui um único inteiro N, que indica o número de casos de teste. Cada caso de teste poderá conter de 1 a 50 strings inclusive, e cada uma das strings poderá conter entre 1 e 50 caracteres inclusive. Os caracteres poderão ser espaços, letras, ou números.

Saída
A saída deve conter o conjunto de strings da entrada ordenado pelo tamanho das strings. Um espaço em branco deve ser impresso entre duas palavras.*/

#include <stdio.h>
#include <string.h>

int main()
{
    int n;
    scanf("%d", &n);
    while(n > 0){
        char string[51];
        scanf(" %[^\n]", string);
        char meiaPalavra[50][50];
        char* palavra;
        int i =0;
        
        
        palavra = strtok(string, " ");
        while(palavra!= NULL){
            strcpy(meiaPalavra[i],palavra);
            i++;
            palavra = strtok(NULL, " ");
        }

        bubbleSort(meiaPalavra, i);

        for(int t = 0; t<i;t++){
            printf("%s ",meiaPalavra[t]);
        }   
        printf(" \n"); 


        n--;


    }
    return 0;
}
void bubbleSort(char meiaPalavra[][50], int n) {
    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < n - i - 1; j++) {
            if (strlen(meiaPalavra[j]) < strlen(meiaPalavra[j + 1])) {
                // Swap strings
                char temp[50];
                strcpy(temp, meiaPalavra[j]);
                strcpy(meiaPalavra[j], meiaPalavra[j + 1]);
                strcpy(meiaPalavra[j + 1], temp);
            }
        }
    }
}