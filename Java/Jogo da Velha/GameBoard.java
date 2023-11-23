import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameBoard extends JPanel {
    private String currentPlayer;
    private String[] board;
    private boolean gameEnded;

    public GameBoard() {
        this.currentPlayer = "X";
        this.board = new String[9];
        this.gameEnded = false;
        setLayout(new GridLayout(3, 3));
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 9; i++) {
            int index = i;  // Corrigido: Captura o índice para uso no lambda
            JButton button = new JButton();
            button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 80));
            button.setFocusPainted(false);
            button.addActionListener(e -> handleButtonClick(index, button));
            this.add(button);
        }
    }

    private void handleButtonClick(int index, JButton button) {
        if (gameEnded || !button.getText().equals("")) {
            return;
        }

        board[index] = currentPlayer;  // Atualiza o tabuleiro
        button.setText(currentPlayer);
        checkGameStatus();
        currentPlayer = currentPlayer.equals("X") ? "O" : "X";
    }

    private void checkGameStatus() {
        for (int i = 0; i < 8; i++) {
            String line = switch (i) {
                case 0 -> board[0] + board[1] + board[2];
                case 1 -> board[3] + board[4] + board[5];
                case 2 -> board[6] + board[7] + board[8];
                case 3 -> board[0] + board[3] + board[6];
                case 4 -> board[1] + board[4] + board[7];
                case 5 -> board[2] + board[5] + board[8];
                case 6 -> board[0] + board[4] + board[8];
                case 7 -> board[2] + board[4] + board[6];
                default -> null;
            };

            if (line.equals("XXX") || line.equals("OOO")) {
                gameEnded = true;
                JOptionPane.showMessageDialog(this, currentPlayer + " wins!");
                return;
            }
        }

        if (isBoardFull()) {
            gameEnded = true;
            JOptionPane.showMessageDialog(this, "It's a draw!");
        }
    }

    private boolean isBoardFull() {
        for (String s : board) {
            if (s == null || s.isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
