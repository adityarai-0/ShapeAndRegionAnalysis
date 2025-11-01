package com.adityarai.shapeanalysis.utils;

/**
 * Small math helpers
 */
public class MathUtils {
    public static double[] normalize(double[] v) {
        double max = 0;
        for (double d : v) max = Math.max(max, Math.abs(d));
        if (max == 0) return v.clone();
        double[] out = new double[v.length];
        for (int i=0;i<v.length;i++) out[i] = v[i] / max;
        return out;
    }
}
