#include <stdio.h>
int main(){
//declaração de variáveis
int multiplo; 
//início do bloco de comandos
do {
 printf("Digite um multiplo de tres no intervalo entre 3 e 99, desconsiderando os mesmos: ");
 scanf("%d",&multiplo);
 
  if (multiplo < 3 || multiplo > 99)
  {
    //Número fora do intervalo
    printf("nao esta no intervalo de 3 a 99!\n");
    
  } else if (multiplo % 3 != 0){
    //Número não é múltiplo
    printf("Nao e multiplo de 3!\n");
  } else {
    //Múltiplo de 3
    printf("Entrada Validada com sucesso");
    break;
  }
}
while (1);
return 0;
    }
