package com.adityarai.shapeanalysis.analysis;

import com.adityarai.shapeanalysis.image.BinaryImage;

public class DistanceTransform {
    private BinaryImage image;

    public DistanceTransform(BinaryImage img) {
        this.image = img;
    }

    public BinaryImage cityBlockDistance() {
        int[][] src = image.getData();
        int rows = src.length;
        int cols = src[0].length;
        int[][] dist = new int[rows][cols];

        // Initialize distance map
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                dist[i][j] = src[i][j] == 0 ? 9999 : 0;
            }
        }

        // Forward pass
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (dist[i][j] != 0) {
                    dist[i][j] = Math.min(dist[i][j], Math.min(dist[i - 1][j], dist[i][j - 1]) + 1);
                }
            }
        }

        // Backward pass
        for (int i = rows - 2; i >= 0; i--) {
            for (int j = cols - 2; j >= 0; j--) {
                if (dist[i][j] != 0) {
                    dist[i][j] = Math.min(dist[i][j], Math.min(dist[i + 1][j], dist[i][j + 1]) + 1);
                }
            }
        }

        return new BinaryImage(dist);
    }
}
