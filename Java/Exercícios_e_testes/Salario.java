import java.util.Scanner;

public class Salario{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        int num = sc.nextInt(), hora = sc.nextInt();
        float salario = sc.nextFloat(), total;
        
        total = (float) (hora * salario);
        
        System.out.println("NUMBER = " + num);
        System.out.println("SALARY = " + total);
        sc.close();
    }
}