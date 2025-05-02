import java.util.Scanner;

public class palindromo{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String palavra;
        int tamanho,i,j;
        boolean palindromo;
        while(true){
            //System.out.print("Digite uma palavra ou FIM para encerrar: ");
            palavra = scanner.nextLine().toLowerCase();

            if(palavra.equals("fim")){
                //System.out.println("encerrando");
                break;
            }
        tamanho = palavra.length();
        palindromo = true;

        for (i=0, j= tamanho - 1; i<j; i++, j--){
            if(palavra.charAt(i)!= palavra.charAt(j)){
                palindromo = false;
                break;
            }
        }
        if(palindromo){
            System.out.println("SIM");
        }
        else{
            System.out.println("NAO");
        }    
        
        }
        scanner.close();
    }
}