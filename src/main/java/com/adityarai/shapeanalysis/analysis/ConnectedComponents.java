package com.adityarai.shapeanalysis.analysis;

import com.adityarai.shapeanalysis.image.BinaryImage;
import java.util.LinkedList;
import java.util.Queue;

public class ConnectedComponents {
    private BinaryImage image;
    private int[][] labels;
    private int objectCount = 0;

    public ConnectedComponents(BinaryImage img) {
        this.image = img;
    }

    /** Labels connected components and counts them */
    public void labelObjects() {
        int[][] data = image.getData();
        int rows = data.length;
        int cols = data[0].length;
        labels = new int[rows][cols];
        boolean[][] visited = new boolean[rows][cols];
        objectCount = 0;

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (data[i][j] == 1 && !visited[i][j]) {
                    objectCount++;
                    bfsLabel(data, visited, labels, i, j, dx, dy, objectCount);
                }
            }
        }
    }

    private void bfsLabel(int[][] data, boolean[][] visited, int[][] labels,
                          int i, int j, int[] dx, int[] dy, int label) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});
        visited[i][j] = true;
        labels[i][j] = label;

        while (!q.isEmpty()) {
            int[] p = q.poll();
            for (int k = 0; k < 4; k++) {
                int nx = p[0] + dx[k];
                int ny = p[1] + dy[k];
                if (nx >= 0 && ny >= 0 && nx < data.length && ny < data[0].length) {
                    if (data[nx][ny] == 1 && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        labels[nx][ny] = label;
                        q.add(new int[]{nx, ny});
                    }
                }
            }
        }
    }

    /** Returns the number of detected objects */
    public int getObjectCount() {
        return objectCount;
    }

    /** Extracts a binary image corresponding to one labeled object */
    public BinaryImage extractAsBinaryImage(int label) {
        if (labels == null) return null;

        int rows = labels.length;
        int cols = labels[0].length;
        int[][] extracted = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                extracted[i][j] = (labels[i][j] == label) ? 1 : 0;
            }
        }

        return new BinaryImage(extracted);
    }

    /** Optional: get full label map */
    public int[][] getLabelMap() {
        return labels;
    }

    /** Simple component counter without labeling (used by tests) */
    public int countComponents(BinaryImage img) {
        labelObjects();
        return objectCount;
    }
}
