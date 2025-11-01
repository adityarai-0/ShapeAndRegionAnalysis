package com.adityarai.shapeanalysis.analysis;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Very simple active contour: moves a closed polygon towards edge (mock demo).
 * This is illustrative, not a production snake solver.
 */
public class ActiveContour {
    private List<Point> contour;

    public ActiveContour(List<Point> initial) {
        this.contour = new ArrayList<>(initial);
    }

    /**
     * Runs a few iterations of a simple energy-based relaxation:
     * - internal: smoothness (average of neighbors)
     * - external: mock (pulls points slightly inward if outside, outward if inside)
     * For a real implementation you'd compute image gradient to attract contour to edges.
     */
    public List<Point> iterate(int iterations, double alpha) {
        int n = contour.size();
        for (int it = 0; it < iterations; it++) {
            List<Point> next = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                Point prev = contour.get((i - 1 + n) % n);
                Point curr = contour.get(i);
                Point nextp = contour.get((i + 1) % n);
                // smooth term: average neighbors
                double sx = (prev.x + nextp.x) / 2.0;
                double sy = (prev.y + nextp.y) / 2.0;
                double nx = curr.x + alpha * (sx - curr.x);
                double ny = curr.y + alpha * (sy - curr.y);
                next.add(new Point((int)Math.round(nx), (int)Math.round(ny)));
            }
            contour = next;
        }
        return contour;
    }
}
