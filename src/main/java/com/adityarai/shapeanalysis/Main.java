package com.adityarai.shapeanalysis;

import com.adityarai.shapeanalysis.analysis.*;
import com.adityarai.shapeanalysis.descriptors.*;
import com.adityarai.shapeanalysis.image.*;
import java.awt.Point;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[][] data = {
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {0, 0, 1, 1},
                {0, 0, 1, 1}
        };

        BinaryImage binary = new BinaryImage(data);

        // Connected Components
        ConnectedComponents cc = new ConnectedComponents(binary);
        cc.labelObjects();
        System.out.println("Objects Detected: " + cc.getObjectCount());

        // Extract first object
        BinaryImage object = cc.extractAsBinaryImage(1);
        System.out.println("Extracted Object:");
        object.print();

        // Distance Transform
        DistanceTransform dt = new DistanceTransform(binary);
        BinaryImage distMap = dt.cityBlockDistance();
        System.out.println("Distance Transform:");
        distMap.print();

        // Chain code and descriptors
        ChainCode chain = new ChainCode(object);
        chain.extract();
        List<Point> points = chain.getBoundaryPoints();

        FourierDescriptor fd = new FourierDescriptor(points);
        fd.computeNormalizedDescriptor(10);

        MomentDescriptor md = new MomentDescriptor(object);
        md.computeHuMoments();

        System.out.println("All processing complete ✅");
    }
}
