/*  data: 16/05/2023
    Lista de exercícios, Assunto: Vetores
    Exercício: 2.2 Notas semestrais - Implementação
    Aluno: Marcos Paulo da Silva Laine  RA:1430851
*/
#include <stdio.h>

//carrega os dados do vetor
void load_list(float arr[], int size){
    int i;
    for(i = 0; i < size; i++){
        printf("informe a nota (0 a 20): ");
        scanf("%f", &arr[i]);
        }
}

//soma os elementos do vetor
float sum_list(float arr[], int size){
    int i;
    float soma = 0;
    for(i = 0; i < size; i++){ 
        soma += arr[i]; 
    }
    return soma;
}

//funcao principal
int main(){ 
    int const size=5, min_aprov=70; 
    float notas[size], soma=0;

    //varregar vetor de notas
    load_list(notas, size);

    //efetuar a soma dos dados
    soma = sum_list(notas, size); 

    //verifica status aprovacao
    printf("sua nota foi %f no semestre e voce foi: ", soma);
    if (soma >= min_aprov) 
        printf("APROVADO!");
    else
        printf("reprovado.");
    return 0;
}