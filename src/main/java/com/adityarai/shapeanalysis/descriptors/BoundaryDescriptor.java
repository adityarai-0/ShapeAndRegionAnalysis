package com.adityarai.shapeanalysis.descriptors;

import com.adityarai.shapeanalysis.image.BinaryImage;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Computes simple boundary features like boundary length from a binary image.
 */
public class BoundaryDescriptor {

    private List<Point> boundaryPoints;

    // ✅ No-argument constructor (for test compatibility)
    public BoundaryDescriptor() {
        this.boundaryPoints = new ArrayList<>();
    }

    // Optional constructor if user wants to pass boundary points manually
    public BoundaryDescriptor(List<Point> boundaryPoints) {
        this.boundaryPoints = boundaryPoints;
    }

    public double computeBoundaryLength(BinaryImage img) {
        int[][] data = img.getData();
        int width = img.getWidth();
        int height = img.getHeight();

        double length = 0;

        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                if (data[y][x] == 1) {
                    // If any of the 4-neighbors is 0, this pixel is on boundary
                    if (data[y - 1][x] == 0 || data[y + 1][x] == 0 ||
                            data[y][x - 1] == 0 || data[y][x + 1] == 0) {
                        boundaryPoints.add(new Point(x, y));
                        length++;
                    }
                }
            }
        }
        return length;
    }

    public List<Point> getBoundaryPoints() {
        return boundaryPoints;
    }
}
