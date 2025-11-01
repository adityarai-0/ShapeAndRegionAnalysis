package com.adityarai.shapeanalysis.descriptors;

import com.adityarai.shapeanalysis.image.BinaryImage;

/**
 * Computes a simple centroidal distance profile.
 */
public class CentroidalProfile {

    public CentroidalProfile() {}

    public double compute(BinaryImage img) {
        int[][] data = img.getData();
        int width = img.getWidth();
        int height = img.getHeight();

        int count = 0;
        double sumX = 0, sumY = 0;
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                if (data[y][x] == 1) { sumX += x; sumY += y; count++; }

        if (count == 0) return 0;
        double cx = sumX / count;
        double cy = sumY / count;

        double sumDist = 0;
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                if (data[y][x] == 1)
                    sumDist += Math.hypot(x - cx, y - cy);

        return sumDist / count;
    }
}
