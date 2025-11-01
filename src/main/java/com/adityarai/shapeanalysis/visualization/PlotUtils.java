package com.adityarai.shapeanalysis.visualization;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Very small utilities to plot 1D arrays in a Swing panel
 */
public class PlotUtils {
    public static void showDoubleArray(double[] arr, String title) {
        JFrame f = new JFrame(title);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setSize(600, 300);
        f.add(new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (arr == null || arr.length == 0) return;
                int w = getWidth(), h = getHeight();
                double max = 0;
                for (double v : arr) max = Math.max(max, Math.abs(v));
                int n = arr.length;
                int prevX = 0, prevY = h/2;
                for (int i = 0; i < n; i++) {
                    int x = (int)(i * (double)w / n);
                    int y = (int)(h/2 - (arr[i] / (max == 0 ? 1 : max)) * (h/2 - 10));
                    g.drawLine(prevX, prevY, x, y);
                    prevX = x; prevY = y;
                }
            }
        });
        f.setVisible(true);
    }
}
