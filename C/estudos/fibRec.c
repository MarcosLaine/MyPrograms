/*created this code for getting some stats using 'perf' on linux with different values*/

#include <stdio.h>

int fibRec(int n){
    if(n < 2){
        return n;
    }else{
        return fibRec(n-1) + fibRec(n-2);
    }
}

int main(){
    int n;
    scanf("%d", &n);
    printf("%d\n", fibRec(n));
    return 0;
}
