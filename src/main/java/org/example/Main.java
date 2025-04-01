package org.example;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            // Carregar a imagem
            BufferedImage imageBFS = ImageProcessor.loadImage("src/main/resources/CapturaDeTela.png");
            BufferedImage imageDFS = ImageProcessor.loadImage("src/main/resources/CapturaDeTela.png");

            int startX = 50, startY = 50;
            Color colorBFS = Color.RED;
            Color colorDFS = Color.BLUE;

            // Aplicar preenchimento de cor
            FloodFill.floodFillBFS(imageBFS, startX, startY, colorBFS);
            FloodFill.floodFillDFS(imageDFS, startX, startY, colorDFS);

            // Salvar imagens finais
            ImageProcessor.saveImage(imageBFS, "saida_final_BFS.png");
            ImageProcessor.saveImage(imageDFS, "saida_final_DFS.png");

        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
