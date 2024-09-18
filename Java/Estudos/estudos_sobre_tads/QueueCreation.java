package estudos_sobre_tads;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class QueueCreation {
    public static void main(String args[]) {

        Random random = new Random();
        
        // Cria uma fila de inteiros usando LinkedList
        Queue<Integer> queue = new LinkedList<>();
        
        // Usando for para adicionar 10 números aleatórios
        for (int i = 0; i < 10; i++) {
            queue.add(random.nextInt(100));
        }

        // Exibindo os elementos da fila após a adição
        System.out.println("Elementos da fila: " + queue);
        
        // Usando foreach para iterar sobre os elementos da fila
        for (Integer i : queue) {
            System.out.println("Elemento: " + i);
        }

        queue.remove();
        System.out.println("Elementos da fila após a remoção: " + queue);

        queue.add(4);

        queue.iterator();
        System.out.println("Elementos da fila após a adição: " + queue);

        Integer[] queueArray = queue.toArray(Integer[]::new);

        Arrays.sort(queueArray);

        System.out.println("Elementos da fila ordenados: " + Arrays.toString(queueArray));
    }
}
