#include <stdio.h>

int main (){
    int num;
    scanf("%d", &num);
    (num % 2 == 0) ? printf("par") : printf("impar");
    return 0;
}