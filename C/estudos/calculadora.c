#include <stdio.h>
#include <math.h>
#include <locale.h>

//implementação
int soma(int n1, int n2){
  return n1 + n2;
} 
int sub(int n1, int n2){
  return n1 - n2;
}
int div(int n1, int n2){
  return n1 / n2;
} 
int mult(int n1, int n2){
  return n1 * n2;
}
int pot(int n1, int n2){
  return pow(n1, n2);
}
int radn1(int n1){
  return sqrt(n1);
}
int radn2(int n2){
  return sqrt(n2);
}

//chamada
int main(void) {
  setlocale (LC_ALL, "Portuguese");
  int n1, n2, res = 0, operacao;
  printf("digite o primeiro numero\n");
  scanf("%i",&n1);
  printf("digite o segundo numero\n");
  scanf("%i",&n2);
  
  do {
    printf("qual operacao voce quer?\n 1-soma\n 2-subtracao\n 3-divisao\n 4-multiplicacao\n 5-exponenciacao\n 6-raiz do primeiro numero\n 7-raiz do segundo numero\n");
    scanf("%i",&operacao);
    switch (operacao) {
      case 1: res = soma(n1, n2); break;
      case 2: res = sub(n1, n2); break;
      case 3: res = div(n1, n2); break;
      case 4: res = mult(n1, n2); break;
      case 5: res = pot(n1, n2); break;
      case 6: res = radn1(n1); break;
      case 7: res = radn2(n2); break;
    } 
  }  while (operacao < 1 || operacao > 7);
  
  printf("o resultado e: %i\n", res);
  system("Pause");
  return 0;
}