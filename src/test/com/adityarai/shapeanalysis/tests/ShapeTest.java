package com.adityarai.shapeanalysis.tests;

import com.adityarai.shapeanalysis.analysis.ConnectedComponents;
import com.adityarai.shapeanalysis.image.BinaryImage;

public class ShapeTest {
    public static void main(String[] args) {
        int[][] data = {
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {0, 0, 1, 1},
                {0, 0, 1, 1}
        };

        BinaryImage img = new BinaryImage(data);
        ConnectedComponents cc = new ConnectedComponents(img);
        int count = cc.countComponents(img);

        System.out.println("Connected Components: " + count);
    }
}
