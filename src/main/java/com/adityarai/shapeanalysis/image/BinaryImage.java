package com.adityarai.shapeanalysis.image;

import java.awt.image.BufferedImage;
import java.awt.Color;

public class BinaryImage {
    private int[][] data;

    public BinaryImage(int[][] data) {
        this.data = data;
    }

    public BinaryImage(int width, int height) {
        this.data = new int[height][width];
    }

    public int[][] getData() {
        return data;
    }

    public int getWidth() {
        return data[0].length;
    }

    public int getHeight() {
        return data.length;
    }

    public void setPixel(int x, int y, int value) {
        data[y][x] = value;
    }

    public int getPixel(int x, int y) {
        return data[y][x];
    }

    public void print() {
        for (int[] row : data) {
            for (int pixel : row) {
                System.out.print(pixel + " ");
            }
            System.out.println();
        }
    }

    public BufferedImage toBufferedImage() {
        int width = getWidth();
        int height = getHeight();
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int color = (data[y][x] == 1) ? Color.WHITE.getRGB() : Color.BLACK.getRGB();
                img.setRGB(x, y, color);
            }
        }
        return img;
    }
}
