import java.util.Scanner;

public class MatrizTransp {
    public static void main(String[] args){
        Scanner sc = new Scanner (System.in);

        System.out.println("digite o numero de linhas: ");
        int L = sc.nextInt();
        System.out.println("digite o numero de colunas: ");
        int C = sc.nextInt();

        int[][] matriz = new int [L][C];
        System.out.println("Digites os elementos da matriz: ");

        for (int i = 0;i<L;i++){
            for(int j=0;j<C;j++){
                System.out.printf("elemento [%d][%d]", i+1, j+1);
                matriz[i][j] =sc.nextInt();
            }
        }

        //transposta:
        int[][] matt = new int[C][L];

        System.out.println("matriz original: ");
        imprimirMatriz(matriz, L, C);

        for (int i = 0;i<L;i++){
            for(int j=0;j<C;j++){
                matt[j][i] = matriz[i][j];
            }
        }

        System.out.println("matriz Transposta: ");
        imprimirMatriz(matt, C, L);
        sc.close();
    }
    public static void imprimirMatriz(int[][] matriz, int L, int C){
        for (int i = 0;i<L;i++){
            for(int j=0;j<C;j++){
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
}