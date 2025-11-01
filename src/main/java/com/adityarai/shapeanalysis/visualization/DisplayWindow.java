package com.adityarai.shapeanalysis.visualization;

import com.adityarai.shapeanalysis.image.BinaryImage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Simple Swing viewer: shows original, first object, skeleton, and distance map.
 */
public class DisplayWindow {
    public static void show(BinaryImage original, BinaryImage object, BinaryImage skeleton, int[][] dist) {
        JFrame frame = new JFrame("ShapeAndRegionAnalysis Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Math.max(900, original.getWidth() * 3 + 60), Math.max(600, original.getHeight() + 200));
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int gap = 10;
                int x = gap, y = gap;
                BufferedImage b1 = original.toBufferedImage();
                BufferedImage b2 = object == null ? new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB) : object.toBufferedImage();
                BufferedImage b3 = skeleton == null ? new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB) : skeleton.toBufferedImage();

                g.drawImage(b1, x, y, null);
                g.drawString("Original (binary)", x, y + b1.getHeight() + 12);
                x += b1.getWidth() + gap;

                g.drawImage(b2, x, y, null);
                g.drawString("First object", x, y + b2.getHeight() + 12);
                x += b2.getWidth() + gap;

                g.drawImage(b3, x, y, null);
                g.drawString("Skeleton", x, y + b3.getHeight() + 12);

                if (dist != null) {
                    int w = dist[0].length, h = dist.length;
                    BufferedImage dm = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                    int max = 1;
                    for (int yy=0; yy<h; yy++) for (int xx=0; xx<w; xx++) max = Math.max(max, dist[yy][xx]);
                    for (int yy=0; yy<h; yy++) for (int xx=0; xx<w; xx++) {
                        int v = (int)(255.0 * dist[yy][xx] / max);
                        int rgb = new Color(v, 0, 255-v).getRGB();
                        dm.setRGB(xx, yy, rgb);
                    }
                    x = gap; y += b1.getHeight() + 30;
                    g.drawImage(dm, x, y, null);
                    g.drawString("Distance transform (city-block)", x, y + dm.getHeight() + 12);
                }
            }
        };

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
