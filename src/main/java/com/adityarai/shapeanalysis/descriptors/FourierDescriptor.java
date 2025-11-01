package com.adityarai.shapeanalysis.descriptors;

import java.awt.Point;
import java.util.List;

public class FourierDescriptor {
    private List<Point> boundary;

    public FourierDescriptor(List<Point> boundary) {
        this.boundary = boundary;
    }

    public double[] computeNormalizedDescriptor(int n) {
        double[] desc = new double[n];
        for (int i = 0; i < n; i++) {
            desc[i] = Math.sin(i) * boundary.size();
        }
        return desc;
    }
}
