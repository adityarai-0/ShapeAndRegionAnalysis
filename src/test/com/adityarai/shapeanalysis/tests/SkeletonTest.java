package test.com.adityarai.shapeanalysis.tests;

import com.adityarai.shapeanalysis.analysis.Skeletonizer;
import com.adityarai.shapeanalysis.image.BinaryImage;

public class SkeletonTest {

    public static void main(String[] args) {
        System.out.println("=== Running SkeletonTest ===");

        int[][] data = {
                {0, 1, 1, 0},
                {1, 1, 1, 1},
                {0, 1, 1, 0}
        };

        BinaryImage img = new BinaryImage(data);
        Skeletonizer skeletonizer = new Skeletonizer();

        BinaryImage skeleton = skeletonizer.skeletonize(img);
        System.out.println("Skeletonized Image: ");
        skeleton.print();

        System.out.println("=== SkeletonTest Completed ===");
    }
}
