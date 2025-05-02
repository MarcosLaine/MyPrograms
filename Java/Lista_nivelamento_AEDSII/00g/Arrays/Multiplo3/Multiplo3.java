//package Lista_nivelamento_AEDSII.00g.ArraysEMatrizes.Multiplo3;
import java.util.Scanner;

public class Multiplo3 {
    public static void main (String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("digite o tamanho do vetor");
        int tam = scanner.nextInt();
        int aux=0;
        int[] array = new int[tam];
        for(int i=0;i<tam;i++){
            System.out.println("digite o numero da posição " + (i+1) + " ");
            array[i] = scanner.nextInt();
            if((array[i]%3)==0)
                aux += array[i];
        }
        System.out.printf("%d",aux );

        scanner.close();
    }
}
