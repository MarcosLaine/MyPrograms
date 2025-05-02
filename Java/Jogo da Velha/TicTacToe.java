import javax.swing.*;

public class TicTacToe {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Tic Tac Toe");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new GameBoard());
            frame.setSize(300, 300);
            frame.setVisible(true);
        });
    }
}
