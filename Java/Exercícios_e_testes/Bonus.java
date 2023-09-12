import java.util.Scanner;

public class Bonus{
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        
        String nome = sc.nextLine();
        double salario = sc.nextDouble();
        double vendido = sc.nextDouble();
        double total =(float) (salario + (vendido*0.15));
        
        System.out.printf("TOTAL = R$ %.2f%n",total);
        sc.close();
    }
}