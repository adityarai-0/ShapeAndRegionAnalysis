/* FILE: src/main/java/com/adityarai/shapeanalysis/Main.java */
package com.adityarai.shapeanalysis;

import com.adityarai.shapeanalysis.image.BinaryImage;
import com.adityarai.shapeanalysis.image.ImageLoader;
import com.adityarai.shapeanalysis.analysis.ConnectedComponents;
import com.adityarai.shapeanalysis.analysis.Thinning;
import com.adityarai.shapeanalysis.analysis.DistanceTransform;
import com.adityarai.shapeanalysis.descriptors.ChainCode;
import com.adityarai.shapeanalysis.descriptors.FourierDescriptor;
import com.adityarai.shapeanalysis.descriptors.MomentDescriptor;
import com.adityarai.shapeanalysis.visualization.DisplayWindow;

import java.awt.image.BufferedImage;
import java.nio.file.Paths;

/**
 * Entry point — runs a pipeline on a sample image.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        String defaultPath = "src/main/resources/sample_images/shapes1.png";
        String imagePath = args.length > 0 ? args[0] : defaultPath;

        BufferedImage bi = ImageLoader.loadGray(imagePath);
        BinaryImage bin = ImageLoader.threshold(bi, 128); // binary (foreground = 1)

        System.out.println("Image loaded: " + Paths.get(imagePath).getFileName());
        System.out.println("Dimensions: " + bin.getWidth() + "x" + bin.getHeight());

        ConnectedComponents cc = new ConnectedComponents(bin);
        cc.labelObjects();
        System.out.println("Connected components found: " + cc.getObjectCount());

        // Choose first component for further analysis
        BinaryImage firstObject = cc.extractAsBinaryImage(1);
        if (firstObject == null) {
            System.out.println("No first object to analyze.");
            return;
        }

        // Distance transform (city-block)
        DistanceTransform dt = new DistanceTransform(firstObject);
        int[][] dist = dt.cityBlockDistance();

        // Thinning (Zhang-Suen)
        Thinning thinning = new Thinning(firstObject);
        BinaryImage skeleton = thinning.zhangSuenThinning();

        // Boundary chain code
        ChainCode chain = new ChainCode(firstObject);
        chain.extract();
        System.out.println("Boundary points: " + chain.getBoundaryPoints().size());

        // Fourier descriptor
        FourierDescriptor fd = new FourierDescriptor(chain.getBoundaryPoints());
        double[] desc = fd.computeNormalizedDescriptor(20); // first 20 coefficients
        System.out.println("Fourier descriptor length: " + desc.length);

        // Moments
        MomentDescriptor md = new MomentDescriptor(firstObject);
        double[] hu = md.computeHuMoments();
        System.out.println("Hu moments (log abs): ");
        for (double v : hu) System.out.printf("%.5e ", v);
        System.out.println();

        // Show display window
        DisplayWindow.show(bin, firstObject, skeleton, dist);

        System.out.println("Analysis complete.");
    }
}
