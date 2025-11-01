package com.adityarai.shapeanalysis.analysis;

import com.adityarai.shapeanalysis.image.BinaryImage;

/**
 * Basic skeletonization (thinning) for binary images.
 * Simplified iterative approach.
 */
public class Skeletonizer {

    public BinaryImage skeletonize(BinaryImage input) {
        int height = input.getHeight();
        int width = input.getWidth();
        int[][] data = input.getData();

        int[][] result = new int[height][width];

        // Very basic "thin" approach: keep center pixels, remove border 1-pixel thick layer
        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                int pixel = data[y][x];
                if (pixel == 1) {
                    // Check neighbors - if all around are 1s, remove edge pixels
                    int sum = 0;
                    for (int j = -1; j <= 1; j++) {
                        for (int i = -1; i <= 1; i++) {
                            if (data[y + j][x + i] == 1)
                                sum++;
                        }
                    }
                    // Keep if it has a core-like structure
                    result[y][x] = (sum > 4) ? 1 : 0;
                }
            }
        }
        return new BinaryImage(result);
    }
}
