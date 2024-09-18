package estudos_sobre_tads;
import java.util.Random;
import java.util.Stack;

public class StackTest {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        Random random = new Random();

        for(int i = 0; i < 10; i++)
            stack.add(random.nextInt(100));

        for(Integer i : stack)
            System.out.println(i);

        System.out.println();
        
        System.out.println(stack.peek() + " ultimo elemento inserido"); //retorna o valor do primeiro elemento da pilha, sem remove-lo

        System.out.println(stack.pop() + " ultimo elemento foi removido, o seguinte é: " + stack.peek() + "(esse não foi removido)");

        stack.add(3, 57);

        System.out.println(stack.elementAt(3));

        
    }

}
