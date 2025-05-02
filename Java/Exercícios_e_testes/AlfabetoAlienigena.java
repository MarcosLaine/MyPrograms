/*Faça um programa que diz se tal texto é alienígena ou não.
A humanidade encontrou um jeito de decifrar uma cifra alienígena e ela funciona assim:

Os aliens pegam N caracteres, envolvendo A à Z, 1 à 9, '!', ';', '#', '@'. E utilizam os caracteres escolhidos para formar uma palavra.

Você irá precisar de uma variável N para dizer quantos caracteres serão usados, uma variável K para dizer o tamanho que será o texto, então se o texto for escrito por Aliens, retornar 'SIM', se não, retornar 'NAO'

ENTRADA:

3 4
@$L
L@$@

Saída

SIM*/

public class AlfabetoAlienigena {
    public static void main(String[] args) {

        int n, k;

        n = MyIO.readInt();
        k = MyIO.readInt();
        
        String letras = MyIO.readLine();
        
        String frase = MyIO.readLine();

        boolean contemTodas = false;


        for(int i = 0; i < k; i++){
            if(letras.contains(String.valueOf(frase.charAt(i))))
                contemTodas = true;
        }

        if (contemTodas) {
            System.out.println("SIM");
        } else {
            System.out.println("NAO");
        }

    }
}
