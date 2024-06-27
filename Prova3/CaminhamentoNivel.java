import java.util.Queue;
import java.util.Scanner;

class Main {
    static class Node {
        int data;
        Node left, right;

        Node(int item) {
            data = item;
            left = right = null;
        }
    }

    static class BinaryTree {
        Node root;

        void insert(int data) {
            root = insertRec(root, data);
        }

        Node insertRec(Node root, int data) {
            if (root == null) {
                root = new Node(data);
                return root;
            }

            if (data < root.data)
                root.left = insertRec(root.left, data);
            else if (data > root.data)
                root.right = insertRec(root.right, data);

            return root;
        }

        void printLevelOrder() {
            if (root == null)
                return;

            Queue<Node> queue = new LinkedList<>();
            queue.add(root);

            while (!queue.isEmpty()) {
                Node tempNode = queue.poll();
                System.out.print(tempNode.data + " ");

                if (tempNode.left != null)
                    queue.add(tempNode.left);

                if (tempNode.right != null)
                    queue.add(tempNode.right);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int C = sc.nextInt();
        for (int i = 1; i <= C; i++) {
            int N = sc.nextInt();
            BinaryTree tree = new BinaryTree();
            for (int j = 0; j < N; j++) {
                int data = sc.nextInt();
                tree.insert(data);
            }
            System.out.print("Case " + i + ": ");
            tree.printLevelOrder();
            System.out.println();
        }
        sc.close();
    }
}
