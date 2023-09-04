//package Lista_nivelamento_AEDSII.00g.Inteiro N;

import java.util.Scanner;

public class InteiroNImpares {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int numero, contador = 0;

        System.out.println("digite o numero: ");
        numero=scanner.nextInt();

        while( contador < numero ){
            if (contador %2==1)
                System.out.println(contador);
            contador++;
        }
        scanner.close();
    }
}
