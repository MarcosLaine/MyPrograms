import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class MergeSortPresentation extends JFrame {
    private static final int RECT_WIDTH = 30;
    private static final int RECT_HEIGHT = 30;
    private static final int ARRAY_SIZE = 10;
    private JPanel panel;
    private List<int[]> steps;

    public MergeSortPresentation() {
        setTitle("Merge Sort Visualization");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int y = 20;
                for (int[] array : steps) {
                    drawArray(g, array, y);
                    y += 40; // Move down for the next step
                }
            }
        };

        panel.setPreferredSize(new Dimension(800, 1250)); // Set preferred size to accommodate multiple steps

        JScrollPane scrollPane = new JScrollPane(panel);

        add(scrollPane, BorderLayout.CENTER);

        int[] array = new Random().ints(ARRAY_SIZE, 1, 100).toArray();
        steps = new ArrayList<>();
        visualizeMergeSort(array);

        setVisible(true);
    }

    private void drawArray(Graphics g, int[] array, int y) {
        for (int i = 0; i < array.length; i++) {
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(i * (RECT_WIDTH + 5), y, RECT_WIDTH, RECT_HEIGHT);
            g.setColor(Color.BLACK);
            g.drawRect(i * (RECT_WIDTH + 5), y, RECT_WIDTH, RECT_HEIGHT);
            g.drawString(String.valueOf(array[i]), i * (RECT_WIDTH + 5) + 10, y + 20);
        }
    }

    private void visualizeMergeSort(int[] array) {
        steps.add(array.clone());
        if (array.length < 2) {
            return;
        }

        int mid = array.length / 2;
        int[] left = new int[mid];
        int[] right = new int[array.length - mid];

        System.arraycopy(array, 0, left, 0, mid);
        System.arraycopy(array, mid, right, 0, array.length - mid);

        visualizeMergeSort(left);
        visualizeMergeSort(right);

        merge(left, right, array);
        steps.add(array.clone());
    }

    private void merge(int[] left, int[] right, int[] result) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }
        while (i < left.length) {
            result[k++] = left[i++];
        }
        while (j < right.length) {
            result[k++] = right[j++];
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MergeSortPresentation::new);
    }
}
