//package Lista_nivelamento_AEDSII.00g.ArraysEMatrizes.ParesEDivisiveis;
import java.util.Scanner;

public class ParesEDivisiveis {
    public static void main (String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("digite o numero de elementos: ");
        int n = scanner.nextInt(); //atribui o valor(tamanho do array) para N
        int[] array = new int[n];

        for(int i = 0; i<n; i++){
            System.out.println("digite o elemento" + (i+1) + ": ");
            array[i] = scanner.nextInt();
        }

        System.out.println("elementos Pares:");
        for(int i=0; i<n; i++){
            if(array[i]%2==0)
                System.out.println(" " + array[i]);
        }
        System.out.println("elementos divisiveis por 3:");
        for(int i=0; i<n; i++){
            if(array[i]%3==0)
                System.out.println(" " + array[i]);
        }
        scanner.close();
    } 
}
