package com.adityarai.shapeanalysis.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Loading and threshold utilities
 */
public class ImageLoader {
    public static BufferedImage loadGray(String path) {
        try {
            BufferedImage img = ImageIO.read(new File(path));
            BufferedImage gray = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
            gray.getGraphics().drawImage(img, 0, 0, null);
            return gray;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load image: " + path, e);
        }
    }

    public static BinaryImage threshold(BufferedImage gray, int thresh) {
        int w = gray.getWidth(), h = gray.getHeight();
        BinaryImage b = new BinaryImage(w, h);
        for (int y = 0; y < h; y++) for (int x = 0; x < w; x++) {
            int val = gray.getRGB(x, y) & 0xFF;
            b.setPixel(x, y, val < thresh ? 1 : 0); // foreground=1 for dark pixels
        }
        return b;
    }
}
