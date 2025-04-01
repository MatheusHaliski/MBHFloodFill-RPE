package org.example;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BorderAnimationGif {

    public static void main(String[] args) {
        int width = 200, height = 200;
        int borderThickness = 10;
        int framesCount = 50; // Número de frames no GIF

        try {
            // Gerar frames da animação
            List<BufferedImage> frames = generateBorderAnimation(width, height, borderThickness, framesCount);

            // Salvar GIF animado
            saveGif(frames, "border_animation.gif", 100);
            System.out.println("GIF gerado com sucesso: border_animation.gif");

        } catch (IOException e) {
            System.out.println("Erro ao criar GIF: " + e.getMessage());
        }
    }

    private static List<BufferedImage> generateBorderAnimation(int width, int height, int thickness, int framesCount) {
        List<BufferedImage> frames = new ArrayList<>();

        for (int i = 0; i < framesCount; i++) {
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();

            g.setColor(Color.WHITE);
            g.fillRect(0, 0, width, height);

            float ratio = (float) i / framesCount;
            Color borderColor = new Color(Color.HSBtoRGB(ratio, 1.0f, 1.0f));

            g.setColor(borderColor);
            g.fillRect(0, 0, width, thickness);
            g.fillRect(0, height - thickness, width, thickness);
            g.fillRect(0, 0, thickness, height);
            g.fillRect(width - thickness, 0, thickness, height);

            g.dispose();
            frames.add(image);
        }

        return frames;
    }

    private static void saveGif(List<BufferedImage> frames, String outputPath, int delay) throws IOException {
        if (frames.isEmpty()) {
            throw new IllegalArgumentException("A lista de frames está vazia!");
        }

        ImageOutputStream output = new FileImageOutputStream(new File(outputPath));
        GifSequenceWriter writer = new GifSequenceWriter(output, frames.get(0).getType(), delay, true);

        for (BufferedImage frame : frames) {
            writer.writeToSequence(frame);
        }

        writer.close();
    }
}
