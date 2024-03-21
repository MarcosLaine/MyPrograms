/* Dada uma pilha de n cartas enumeradas de 1 até n com a carta 1 no topo e a carta n na base.  A seguinte operação é ralizada enquanto tiver 2 ou mais cartas na pilha.

Jogue fora a carta do topo e mova a próxima carta (a que ficou no topo) para a base da pilha.

Sua tarefa é encontrar a sequência de cartas descartadas e a última carta remanescente.

Cada linha de entrada (com exceção da última) contém um número n ≤ 50. A última linha contém 0 e não deve ser processada. Cada número de entrada produz duas linhas de saída. A primeira linha apresenta a sequência de cartas descartadas e a segunda linha apresenta a carta remanescente.

Entrada
A entrada consiste em um número indeterminado de linhas contendo cada uma um valor de 1 até 50. A última linha contém o valor 0.

Saída
Para cada caso de teste, imprima duas linhas. A primeira linha apresenta a sequência de cartas descartadas, cada uma delas separadas por uma vírgula e um espaço. A segunda linha apresenta o número da carta que restou. Nenhuma linha tem espaços extras no início ou no final. Veja exemplo para conferir o formato esperado. */
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Cartas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            int n = sc.nextInt();
            if (n == 0) break;

            Queue<Integer> queue = new LinkedList<>();
            for (int i = 1; i <= n; i++) {
                queue.add(i);
            }

            StringBuilder descartadas = new StringBuilder();
            while (queue.size() > 1) {
                descartadas.append(queue.remove()).append(", ");
                queue.add(queue.remove());
            }

            System.out.println("Discarded cards: " + (descartadas.length() > 0 ? descartadas.substring(0, descartadas.length() - 2) : ""));
            System.out.println("Remaining card: " + queue.peek());
        }

        sc.close();
    }
}
