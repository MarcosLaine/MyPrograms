/*  data: 16/05/2023
    Lista de exercícios, Assunto: Vetores
    Exercício: 2.3 Média
    Aluno: Marcos Paulo da Silva Laine  RA:1430851
*/
#include <stdio.h>
#include <math.h>

void le_vetor (float v[], int n);
void imprime_vetor( float v[], int n);
float calcula_media(float v[], int n, float *soma);
void imprime_vetor_invertido(float v[], int n);

int main(){
    int n;
    float media,soma;
    printf("digite o tamanho do vetor\n");
    scanf("%d", &n);
    float v[n];
    le_vetor(v,n);
    printf("\n\nelementos do vetor original:\n");
    imprime_vetor (v,n);
    media = calcula_media(v,n,&soma);
    printf("\n\nmedia\n");
    printf("   media: %.2f\n\n", media);
    printf("\n\nimpressao do vetor invertido\n");
    imprime_vetor_invertido(v,n);
    return 0;
    system ("pause");
}

void le_vetor (float v[], int n){
    for(int i = 0;i<n;i++){
        printf("digite o vetor na posicao %i/%i\n", i+1, n);
        scanf("%f",&v[i]);
    }
}

void imprime_vetor( float v[], int n){
    for(int i = 0;i<n;i++){
    printf(" %.2f  ",v[i]);
    }
}


float calcula_media(float v[], int n, float *soma){
    *soma=0;
    for(int i=0; i<n; i++){
        *soma+=v[i];
    }
    return *soma/n;
}

void imprime_vetor_invertido(float v[], int n){
  int aux[n];
    for(int i = 0;i<n;i++){
      aux[i]=v[n-1-i];
    }
    for(int i = 0;i<n;i++){
      v[i] = aux [i];
        printf(" %.2f", v[i]);
    }
}
