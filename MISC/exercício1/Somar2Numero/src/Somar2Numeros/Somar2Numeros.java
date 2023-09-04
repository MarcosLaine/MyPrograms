package Somar2Numeros;
import java.util.*;

class Somae2Numeros {
	public static Scanner sc = new Scanner(System.in);
	public static void main (String[] args) {
		int num1, num2, soma;
		
		System.out.println("digite um numero");
		num1=sc.nextInt();
		System.out.println("digite um numero");
		num2=sc.nextInt();
		
		soma=num1 + num2;
		
		System.out.println("SOma: " + soma);

	}
}
