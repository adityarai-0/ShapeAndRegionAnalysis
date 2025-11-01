package com.adityarai.shapeanalysis.utils;

import com.adityarai.shapeanalysis.image.BinaryImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageUtils {

    public static void saveAsImage(BinaryImage bin, String fileName) {
        BufferedImage img = bin.toBufferedImage();
        try {
            ImageIO.write(img, "png", new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
