package org.example;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageProcessor {

    public static BufferedImage loadImage(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            throw new IOException("Erro: Arquivo de entrada não encontrado!");
        }
        return ImageIO.read(file);
    }

    public static void saveImage(BufferedImage image, String filename) throws IOException {
        File outputFile = new File(filename);
        boolean success = ImageIO.write(image, "png", outputFile);
        if (!success) {
            throw new IOException("Erro: Falha ao salvar a imagem!");
        }
        System.out.println("Imagem salva como: " + filename);
    }
}
