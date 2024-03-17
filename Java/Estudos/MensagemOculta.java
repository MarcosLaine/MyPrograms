import java.util.Scanner;

public class MensagemOculta {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int teste = sc.nextInt();
        while(teste > 0){
            String mensagem = sc.nextLine();
            char letra = mensagem.charAt(0);
        } 
        sc.close();
    }
}
