/*O estacionamento tem apenas um corredor, com largura o suficiente para acomodar um carro, e profundidade suficiente para acomodar K carros, um atrás do outro. Como este estacionamento só tem um portão, só é possível entrar e sair por ele.

Quando o primeiro carro entra no estacionamento, o mesmo ocupa a posição próxima à parede, ao fundo do estacionamento. Todos os próximos carros estacionam logo atrás dele, formando uma fila. Obviamente, não é possível que um carro passe por cima de outro, portanto só é possível que um carro saia do estacionamento se ele for o último da fila.

Dados o horário de chegada e saída prevista de N motoristas, incluindo Rafael, diga se é possível que todos consigam estacionar e remover seus carros no estacionamento citado.

Entrada
Haverá diversos casos de teste. Cada caso de teste inicia com dois inteiros N e K (3 ≤ N ≤ 10⁴, 1 ≤ K ≤ 10³), representando o número de motoristas que farão uso do estacionamento, e o número de carros que o estacionamento consegue comportar, respectivamente.

Em seguida haverá N linhas, cada uma contendo dois inteiros Ci e Si (1 ≤ Ci, Si ≤ 10⁵), representando, respectivamente, o horário de chegada e saída do motorista i (1 ≤ i ≤ N). Os valores de Ci são dados de forma crescente, ou seja, Ci < Ci+1 para todo 1 ≤ i < N.

Não haverá mais de um motorista que chegam ao mesmo tempo, e nem mais de um motorista que saiam ao mesmo tempo. É possível que um motorista consiga estacionar no mesmo momento em que outro motorista deseja sair.

O último caso de teste é indicado quando N = K = 0, o qual não deverá ser processado.

Saída
Para cada caso de teste imprima uma linha, contendo a palavra “Sim”, caso seja possível que todos os N motoristas façam uso do estacionamento, ou “Nao” caso contrário. 

Exemplo de Entrada	
3 2
1 10
2 5
6 9
3 2
1 10
2 5
6 12
0 0

Exemplo de Saída

Sim
Nao
*/


#include <stdio.h>

int main(){

	int motoristas, vagas;
	scanf("%d %d", &motoristas, &vagas);
	while(motoristas != 0 && vagas != 0){
		int chegada[motoristas], saida[motoristas];

		for(int i = 0; i < motoristas; i++){
			scanf("%d %d", &chegada[i], &saida[i]);
		}

		int cont = 0;
		for(int i = 0; i < motoristas; i++){

			for(int j = 0; j < motoristas; j++){
				if(chegada[j] < saida[i]){
					cont++;
				}
			}

			if(cont >= vagas){
				printf("Nao\n");
				break;
			} else if(cont < vagas){
				printf("Sim\n");
				break;
			}
		}
		
		scanf("%d %d", &motoristas, &vagas);
	}


	return 0;
}
