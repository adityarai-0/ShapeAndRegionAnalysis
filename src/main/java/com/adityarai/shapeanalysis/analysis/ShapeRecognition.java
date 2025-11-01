package com.adityarai.shapeanalysis.analysis;

import java.util.Arrays;

/**
 * Simple shape recognition helpers (distance metrics)
 */
public class ShapeRecognition {

    /**
     * Euclidean distance between two Fourier descriptor vectors.
     */
    public double compareFourier(double[] a, double[] b) {
        if (a == null || b == null) return Double.MAX_VALUE;
        int n = Math.min(a.length, b.length);
        double sum = 0;
        for (int i = 0; i < n; i++) {
            double d = a[i] - b[i];
            sum += d * d;
        }
        return Math.sqrt(sum);
    }

    /**
     * Compare Hu moments (simple L1 distance)
     */
    public double compareHu(double[] a, double[] b) {
        if (a == null || b == null) return Double.MAX_VALUE;
        int n = Math.min(a.length, b.length);
        double s = 0;
        for (int i = 0; i < n; i++) s += Math.abs(a[i] - b[i]);
        return s;
    }
}
