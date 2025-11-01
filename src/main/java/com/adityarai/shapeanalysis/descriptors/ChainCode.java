package com.adityarai.shapeanalysis.descriptors;

import com.adityarai.shapeanalysis.image.BinaryImage;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class ChainCode {
    private List<Point> boundaryPoints;
    private List<Integer> chainCode;

    public ChainCode(BinaryImage img) {
        this.boundaryPoints = extractBoundary(img);
        this.chainCode = new ArrayList<>();
    }

    private List<Point> extractBoundary(BinaryImage img) {
        int[][] data = img.getData();
        List<Point> points = new ArrayList<>();

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                if (data[i][j] == 1 && hasBackgroundNeighbor(data, i, j))
                    points.add(new Point(i, j));
            }
        }
        return points;
    }

    private boolean hasBackgroundNeighbor(int[][] data, int x, int y) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if (nx >= 0 && ny >= 0 && nx < data.length && ny < data[0].length && data[nx][ny] == 0)
                return true;
        }
        return false;
    }

    public void extract() {
        // Simple dummy chain code example
        for (int i = 0; i < boundaryPoints.size(); i++) {
            chainCode.add(i % 8);
        }
    }

    public List<Point> getBoundaryPoints() {
        return boundaryPoints;
    }

    public List<Integer> getChainCode() {
        return chainCode;
    }
}
