import java.util.*;

public class UniaoIntersecaoArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o tamanho do primeiro array: ");
        int tamanhoArray1 = scanner.nextInt();

        int[] array1 = new int[tamanhoArray1];
        for (int i = 0; i < tamanhoArray1; i++) {
            System.out.print("Digite o elemento " + (i + 1) + " do primeiro array: ");
            array1[i] = scanner.nextInt();
        }

        System.out.print("Digite o tamanho do segundo array: ");
        int tamanhoArray2 = scanner.nextInt();

        int[] array2 = new int[tamanhoArray2];
        for (int i = 0; i < tamanhoArray2; i++) {
            System.out.print("Digite o elemento " + (i + 1) + " do segundo array: ");
            array2[i] = scanner.nextInt();
        }

        int[] uniao = new int[tamanhoArray1 + tamanhoArray2];
        int[] intersecao = new int[Math.min(tamanhoArray1, tamanhoArray2)];

        int contadorUniao = 0;
        int contadorIntersecao = 0;

        for (int num : array1) {
            uniao[contadorUniao] = num;
            contadorUniao++;
        }

        for (int num : array2) {
            if (contains(uniao, num)) {
                intersecao[contadorIntersecao] = num;
                contadorIntersecao++;
            }
            uniao[contadorUniao] = num;
            contadorUniao++;
        }

        uniao = Arrays.copyOf(uniao, contadorUniao);
        intersecao = Arrays.copyOf(intersecao, contadorIntersecao);

        System.out.println("União dos arrays: " + Arrays.toString(uniao));
        System.out.println("Interseção dos arrays: " + Arrays.toString(intersecao));

        scanner.close();
    }

    public static boolean contains(int[] array, int value) {
        for (int num : array) {
            if (num == value) {
                return true;
            }
        }
        return false;
    }
}