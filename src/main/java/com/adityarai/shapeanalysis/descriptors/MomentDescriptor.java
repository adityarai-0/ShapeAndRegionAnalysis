package com.adityarai.shapeanalysis.descriptors;

import com.adityarai.shapeanalysis.image.BinaryImage;

public class MomentDescriptor {
    private BinaryImage img;

    public MomentDescriptor(BinaryImage img) {
        this.img = img;
    }

    public double[] computeHuMoments() {
        int[][] data = img.getData();
        double[] hu = new double[7];

        // Dummy Hu moments computation for illustration
        for (int i = 0; i < 7; i++) {
            hu[i] = Math.random();
        }
        return hu;
    }
}
