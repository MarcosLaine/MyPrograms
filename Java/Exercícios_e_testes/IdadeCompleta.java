import java.util.Scanner;

public class IdadeCompleta{
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        
        int input = sc.nextInt(), dia=0, mes=0, ano=0, resto = 0;
        
        if(input >= 365){
            ano = input / 365;
            resto = input %365;
            mes = resto / 30;
            dia = resto % 30;
        
        }
        if(input < 365){
            mes = input / 30;
            dia = input % 30;
        } 
        System.out.println(ano + " ano(s)");
        System.out.println(mes + " mes(es)");
        System.out.println(dia + " dia(s)");
        
        sc.close();
    }
}