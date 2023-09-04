package exercício1;
import java.util.*;

class Somae2Numeros {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num1, num2, soma;
		
		System.out.println("digite um numero");
		num1=sc.nextInt();
		System.out.println("digite um numero");
		num2=sc.nextInt();
		
		soma=num1 + num2;
		
		System.out.println("SOma: " + soma);
		sc.close();
	}
}
