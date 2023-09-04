import java.util.Scanner;

public class MenorElementoArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o tamanho do array: ");
        int n = scanner.nextInt();

        int[] array = new int[n];

        // Lê os elementos do array
        for (int i = 0; i < n; i++) {
            System.out.print("Digite o elemento " + (i + 1) + ": ");
            array[i] = scanner.nextInt();
        }

        int menor = array[0];
        int posicaoMenor = 0;

        // Encontra o menor elemento e sua posição
        for (int i = 1; i < n; i++) {
            if (array[i] < menor) {
                menor = array[i];
                posicaoMenor = i;
            }
        }

        System.out.println("O menor elemento está na posição: " + posicaoMenor);

        scanner.close();
    }
}
