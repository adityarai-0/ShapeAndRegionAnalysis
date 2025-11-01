package com.adityarai.shapeanalysis.tests;

import com.adityarai.shapeanalysis.image.BinaryImage;
import com.adityarai.shapeanalysis.descriptors.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class DescriptorTest {
    public static void main(String[] args) {
        int[][] data = {
                {0, 1, 1, 0},
                {1, 1, 1, 1},
                {0, 1, 1, 0}
        };

        BinaryImage img = new BinaryImage(data);

        ChainCode cc = new ChainCode(img);
        cc.extract();

        List<Point> boundary = cc.getBoundaryPoints();
        FourierDescriptor fd = new FourierDescriptor(boundary);
        fd.computeNormalizedDescriptor(10);

        MomentDescriptor md = new MomentDescriptor(img);
        md.computeHuMoments();

        System.out.println("DescriptorTest completed successfully.");
    }
}
