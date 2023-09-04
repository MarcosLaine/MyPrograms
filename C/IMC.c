#include <stdio.h>
#include <math.h>
#include <stdbool.h>

//Protótipo
void entrada(float *peso, float *altura);
bool validaEntrada(float peso, float altura);
float calculaIMC(float peso, float altura);
void exibeClassificacaoIMC (float imc);
void exibeTabelaIMC();

//chamada
 int main (){
   float p, a, imc;
   bool valida;
   entrada (&p, &a);
   valida = validaEntrada(p, a);
   if (valida == false){
        printf("entrada invalida");
        return 1;
   }
   calculaIMC (p, a);
   imc = calculaIMC(p,a);
    printf("Seu imc e: %.2f\n", imc);
   
   exibeClassificacaoIMC(imc);
   exibeTabelaIMC();
   return 0;
 }

//implementação
void entrada(float *peso, float *altura){
  printf("digite o peso: ");
  scanf("%f", peso);
  printf("digite a altura: ");
  scanf("%f", altura);
}

bool validaEntrada(float peso, float altura){
  if (peso>0 && altura > 0){
    return true;
  } else {
    return false;
  }
}

float calculaIMC(float peso, float altura){
 return peso/pow(altura/100,2);
}

void exibeClassificacaoIMC (float imc){
  if (imc < 18.5) {
    printf("Abaixo do peso");
  } else if (imc >= 18.5 && imc < 25.0) {
    printf("peso normal");
  } else if (imc >= 25.0 && imc < 30.0) {
    printf("Acima do peso");
  } else if (imc >= 30.0 && imc < 35.0) {
    printf("Obesidade grau I");
  } else if (imc >= 35 && imc < 40) {
    printf("Obesidade grau II");
  } else if (imc >= 40){
    printf("Obesidade grau III");
  }
}

void exibeTabelaIMC(){
  printf("\n\nIMC:  | Classificacao:");
  printf("\n--------------------------");
  printf("\n<18   | abaixo do peso");
  printf("\n18-25 | peso normal");
  printf("\n25-30 | acima do peso");
  printf("\n30-35 | obesidade grau I");
  printf("\n35-40 | obesidade grau II");
  printf("\n>40   | obesidade grau III");
}