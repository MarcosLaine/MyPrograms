/*  data: 16/05/2023
    Lista de exercícios, Assunto: Vetores
    Exercício: 2.1 Análise Programa Notas Semestrais
    Aluno: Marcos Paulo da Silva Laine  RA:1430851
*/
#include <iostream>
using namespace std;

//carrega os dados do vetor
void load_list(float arr[], int size){
    int i;
    for(i = 0; i < size; i++){
        cout << "Informe a nota " << << "(0 a 20): ";//aqui há um erro de sintaxe ------ Correção: out << "Informe a nota (0 a 20): ";
        cin >> arr[i];
        }
}

//soma os elementos do vetor
float sum_list(float arr[], int size){
    int i;
    float soma = 0;
    for(i = 0; i < size){ //aqui há um erro de sintaxe ------ Correção: for (int i = 0 i < size, i++){}
        soma += arr; ////aqui há um erro de sintaxe ------ Correção: soma += arr[i]
    }
    return soma;
}

//funcao principal
main(){     //aqui há um erro de sintaxe ------ Correção: int main(){}
    int const size=5, min_aprov=70; ////aqui há um erro de logica ------ Correção: inst const size = 5, min_aprov=70;
    float notas[size], soma=0;

    //varregar vetor de notas
    load_list(notas, size);

    //efetuar a soma dos dados
    soma = sum_list(notas, ); ////aqui há um erro de sintaxe ------ Correção: soma = sum_list(notas, size);

    //verifica status aprovacao
    cout << "sua nota foi " << soma << " no semestre e voce foi ";
    if (soma = min_aprov) //aqui há um erro de lógica ------ Corrreção: if (soma >=min_aprov)
        cout << "APROVADO!";
    else
        cout << "reprovado.";
    //aqui há um erro de lógica, por a função precisa de um retorno ------ Correção: return 0;
}