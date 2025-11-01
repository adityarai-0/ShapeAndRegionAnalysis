package com.adityarai.shapeanalysis.analysis;

import com.adityarai.shapeanalysis.image.BinaryImage;

import java.util.ArrayList;
import java.util.List;

/**
 * Zhang-Suen thinning algorithm implementation (returns new BinaryImage)
 */
public class Thinning {
    private final BinaryImage src;

    public Thinning(BinaryImage src) {
        this.src = src;
    }

    public BinaryImage zhangSuenThinning() {
        int w = src.getWidth(), h = src.getHeight();
        int[][] img = new int[h][w];
        for (int y = 0; y < h; y++) for (int x = 0; x < w; x++) img[y][x] = src.getPixel(x, y);

        boolean changed;
        do {
            changed = false;
            List<int[]> remove = new ArrayList<>();
            // step 1
            for (int y = 1; y < h-1; y++) for (int x = 1; x < w-1; x++) {
                if (img[y][x] == 1) {
                    int[] n = neighbors(img, x, y);
                    int bp = sum(n);
                    int ap = transitions(n);
                    if (bp >= 2 && bp <= 6 && ap == 1 && (n[0]*n[2]*n[4] == 0) && (n[2]*n[4]*n[6] == 0)) {
                        remove.add(new int[]{x,y});
                    }
                }
            }
            if (!remove.isEmpty()) changed = true;
            for (int[] p : remove) img[p[1]][p[0]] = 0;
            remove.clear();
            // step 2
            for (int y = 1; y < h-1; y++) for (int x = 1; x < w-1; x++) {
                if (img[y][x] == 1) {
                    int[] n = neighbors(img, x, y);
                    int bp = sum(n);
                    int ap = transitions(n);
                    if (bp >= 2 && bp <= 6 && ap == 1 && (n[0]*n[2]*n[6] == 0) && (n[0]*n[4]*n[6] == 0)) {
                        remove.add(new int[]{x,y});
                    }
                }
            }
            if (!remove.isEmpty()) changed = true;
            for (int[] p : remove) img[p[1]][p[0]] = 0;
        } while (changed);

        return new BinaryImage(img);
    }

    private int[] neighbors(int[][] img, int x, int y) {
        return new int[]{
                img[y-1][x],     // p2
                img[y-1][x+1],   // p3
                img[y][x+1],     // p4
                img[y+1][x+1],   // p5
                img[y+1][x],     // p6
                img[y+1][x-1],   // p7
                img[y][x-1],     // p8
                img[y-1][x-1]    // p9
        };
    }

    private int sum(int[] p) { int s = 0; for (int v : p) s += v; return s; }

    private int transitions(int[] p) {
        int c = 0;
        for (int i = 0; i < p.length; i++) {
            int cur = p[i];
            int next = p[(i+1) % p.length];
            if (cur == 0 && next == 1) c++;
        }
        return c;
    }
}
