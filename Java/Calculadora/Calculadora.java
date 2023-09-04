import java.util.Scanner;
import java.lang.Math;

public class Calculadora {
      static double taxa;
      static double tempo;
      static double ValorInicial;
  
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("digite o valor inicial: ");
        ValorInicial = scanner.nextDouble();
        System.out.println("digite o tempo: ");
        tempo = scanner.nextDouble();
        System.out.println("digite a taxa: ");
        taxa = scanner.nextDouble();
        System.out.println("Valor Final: " + calcular());
        scanner.close();
    }
    

    public static double calcular() {
        double valorFinal = ValorInicial * Math.pow(1 + (taxa/100), tempo);
        return valorFinal;
    }
    
}
