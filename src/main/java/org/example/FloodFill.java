package org.example;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class FloodFill {

    public static void floodFillBFS(BufferedImage image, int x, int y, Color newColor) throws IOException {
        int targetColor = image.getRGB(x, y);
        if ((targetColor & 0xFFFFFF) == (newColor.getRGB() & 0xFFFFFF)) {
            System.out.println("A cor inicial já é a mesma da nova cor. Nada será alterado.");
            return;
        }

        MinhaFila<int[]> queue = new MinhaFila<>();
        queue.enqueue(new int[]{x, y});
        image.setRGB(x, y, newColor.getRGB());

        int width = image.getWidth();
        int height = image.getHeight();
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int pixelsProcessed = 0;
        int frameCount = 1;

        while (!queue.isEmpty()) {
            int[] pixel = queue.dequeue();
            int px = pixel[0], py = pixel[1];

            for (int[] dir : directions) {
                int nx = px + dir[0], ny = py + dir[1];

                if (nx >= 0 && nx < width && ny >= 0 && ny < height &&
                        (image.getRGB(nx, ny) & 0xFFFFFF) == (targetColor & 0xFFFFFF)) {

                    queue.enqueue(new int[]{nx, ny});
                    image.setRGB(nx, ny, newColor.getRGB());
                    pixelsProcessed++;

                    if (pixelsProcessed % 500 == 0) {
                        ImageProcessor.saveImage(image, "frameB_" + frameCount++ + ".png");
                    }
                }
            }
        }
    }

    public static void floodFillDFS(BufferedImage image, int x, int y, Color newColor) throws IOException {
        int targetColor = image.getRGB(x, y);
        if ((targetColor & 0xFFFFFF) == (newColor.getRGB() & 0xFFFFFF)) {
            System.out.println("A cor inicial já é a mesma da nova cor. Nada será alterado.");
            return;
        }

        MinhaPilha<int[]> stack = new MinhaPilha<>();
        stack.push(new int[]{x, y});
        image.setRGB(x, y, newColor.getRGB());

        int width = image.getWidth();
        int height = image.getHeight();
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int pixelsProcessed = 0;
        int frameCount = 1;

        while (!stack.isEmpty()) {
            int[] pixel = stack.pop();
            int px = pixel[0], py = pixel[1];

            for (int[] dir : directions) {
                int nx = px + dir[0], ny = py + dir[1];

                if (nx >= 0 && nx < width && ny >= 0 && ny < height &&
                        (image.getRGB(nx, ny) & 0xFFFFFF) == (targetColor & 0xFFFFFF)) {

                    stack.push(new int[]{nx, ny});
                    image.setRGB(nx, ny, newColor.getRGB());
                    pixelsProcessed++;

                    if (pixelsProcessed % 500 == 0) {
                        ImageProcessor.saveImage(image, "frameD_" + frameCount++ + ".png");
                    }
                }
            }
        }
    }
}
