/*created this code for getting some stats using 'perf' on linux with different values*/

#include <stdio.h>

long long int calculaFat(long long int x){
	if(x == 1)
		return x;
	else
		return x * calculaFat(x - 1);
}

int main(){
    long long int x;
    scanf("%lld", &x);
    printf("%lld\n", calculaFat(x));
    return 0;
}

