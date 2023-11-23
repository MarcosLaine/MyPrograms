import javax.swing.*;
import java.awt.*;

public class Matriz extends JPanel {

    class Node {
        protected int value;
        protected Node top;
        protected Node bottom;
        protected Node left;
        protected Node right;

        Node() {

        }

        Node(int value) {
            this.value = value;
        }

        // getters
        public Node getTop() {
            return top;
        }

        public Node getBottom() {
            return bottom;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public int getValue() {
            return value;
        }

        // setters
        public void setTop(Node top) {
            this.top = top;
        }

        public void setBottom(Node bottom) {
            this.bottom = bottom;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    int rows;
    int columns;
    Node start; // posição 0x0 na matriz

    Matriz() {

    }

    public void generateMatrix(int rows, int columns) {
        populate();
    }

    Matriz(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        populate();
    }

    Matriz(int rows, int columns, int value) {
        this.rows = rows;
        this.columns = columns;
        this.start = new Node(value);
        populate();
    }

    protected void createRows() {
        Node temp = start;
        for (int i = 1; i < rows; i++) {
            Node newNode = new Node(1);
            temp.setBottom(newNode);
            newNode.setTop(temp);
            temp = newNode;
        }
    }

    public void populate() {
        if (start == null) {
            start = new Node(3);
        }
        createRows();
        Node C1 = start;
        Node C2 = start.getBottom();
        for (int i = 1; i < rows; i++) {
            C1.setBottom(C2);
            C2.setTop(C1);
            createColumns(C1, C2);
            C1 = C2;
            C2 = C2.getBottom();
        }
    }

    protected void createColumns(Node C1, Node C2) { // C1 está acima de C2
        for (int i = 1; i < columns; i++) {
            Node nextC1;
            Node nextC2 = new Node(1);
            if (C1.getRight() != null) { // já fez direcionamento nessa linha
                nextC1 = C1.getRight();
            } else { // linha precisa de direcionamento
                nextC1 = new Node(1);
                C1.setRight(nextC1);
                nextC1.setLeft(C1);
            }
            nextC1.setBottom(nextC2);
            C2.setRight(nextC2);
            nextC2.setLeft(C2);
            nextC2.setTop(nextC1);
            C1 = nextC1;
            C2 = nextC2;
        }
    }

    public void print() {
        Node rowTemp = start;
        Node columnTemp;
        for (; rowTemp != null; rowTemp = rowTemp.getBottom()) {
            columnTemp = rowTemp;
            for (; columnTemp != null; columnTemp = columnTemp.getRight()) {
                System.out.print(columnTemp.getValue() + " ");
            }
            System.out.println();
        }
    }

    public void printDiagonal() {
        Node temp = start;
        while (true) {
            System.out.print(temp.value + " ");
            temp = temp.bottom;
            if (temp == null) {
                break;
            }
            temp = temp.right;
        }
        System.out.println();
    }

    public void printReverseDiagonal() {
        Node temp = start;
        while (temp.getRight() != null) {
            temp = temp.getRight();
        }
        while (true) {
            System.out.print(temp.getValue() + " ");
            if (temp.getBottom() == null) {
                break;
            }
            temp = temp.getBottom().getLeft();
        }
        System.out.println();
    }

    public void setElement(int rowIndex, int columnIndex, int value) { // vetor index
        Node element = start;
        for (int i = 0; i < columnIndex; i++) {
            element = element.getRight();
        }
        for (int i = 0; i < rowIndex; i++) {
            element = element.getBottom();
        }
        element.setValue(value);
    }

    public int getElement(int rowIndex, int columnIndex) { // vetor index
        Node element = start;
        for (int i = 0; i < columnIndex; i++) {
            element = element.getRight();
        }
        for (int i = 0; i < rowIndex; i++) {
            element = element.getBottom();
        }
        return element.getValue();
    }

    public static Matriz readMatrix(Matriz matrix) {
        int rows1, columns1;
        rows1 = MyIO.readInt();
        columns1 = MyIO.readInt();
        matrix = new Matriz(rows1, columns1);
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < columns1; j++) {
                matrix.setElement(i, j, MyIO.readInt());
            }
        }
        return matrix;
    }

    public static void printSum(Matriz matrix1, Matriz matrix2) {
        Matriz matrix3 = new Matriz(matrix1.rows, matrix1.columns);
        for (int i = 0; i < matrix1.rows; i++) {
            for (int j = 0; j < matrix1.columns; j++) {
                matrix3.setElement(i, j, matrix1.getElement(i, j) + matrix2.getElement(i, j));
            }
        }
        matrix3.print();
    }

    public static void printMultiplication(Matriz matrix1, Matriz matrix2) {
        Matriz matrix3 = new Matriz(matrix1.rows, matrix2.columns);
        for (int i = 0; i < matrix1.rows; i++) {
            for (int j = 0; j < matrix2.columns; j++) {
                int sum = 0;
                for (int k = 0; k < matrix1.columns; k++) {
                    sum += matrix1.getElement(i, k) * matrix2.getElement(k, j);
                }
                matrix3.setElement(i, j, sum);
            }
        }
        matrix3.print();
    }

    public static void main(String args[]) {
        int cases = MyIO.readInt();
        for (; cases > 0; cases--) {
            Matriz matrices[] = new Matriz[2];
            for (int i = 0; i < 2; i++) {
                matrices[i] = readMatrix(matrices[i]);
            }
            matrices[0].printDiagonal();
            matrices[0].printReverseDiagonal();
            printSum(matrices[0], matrices[1]);
            printMultiplication(matrices[0], matrices[1]);
        }
    }
}
