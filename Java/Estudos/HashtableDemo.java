import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class HashtableDemo {

    public static void main(String[] args) {
        // Usar long para armazenar milissegundos
        long initialTime = System.currentTimeMillis();
        long finalTime;

        String[] companies = {"Yahoo", "Vodafone", "Samsung"};

        // Ordenação crescente
        System.out.println("Exemplo de Ordenação Crescente em Java");
        System.out.println("Array de String Desordenada: ");
        printNumbers(companies);
        Arrays.sort(companies);
        System.out.println("Array de String Ordenada em Ordem Crescente: ");
        printNumbers(companies);

        // Ordenação decrescente
        System.out.println("Exemplo de Ordenação Decrescente em Java");
        System.out.println("Array de String Desordenada: ");
        printNumbers(companies);
        Arrays.sort(companies, Collections.reverseOrder());
        System.out.println("Array de String Ordenada em Ordem Decrescente: ");
        printNumbers(companies);

        // Ordenação de parte do array
        System.out.println("Classificando parte do Array em Java:");
        Random random = new Random();
        int[] numbers = new int[1000000000];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt();
        }
        Arrays.sort(numbers);
        System.out.println("Sub Array Classificado em Java: ");

        // Calculando o tempo decorrido
        finalTime = System.currentTimeMillis();
        double totalTime = (finalTime - initialTime) / 1000.0; // Divisão por 1000 para converter milissegundos em segundos
        System.out.printf("%.7f seconds%n", totalTime);
    }

    public static void printNumbers(String[] companies) {
        for (String company : companies) {
            System.out.println(company);
        }
    }
}
