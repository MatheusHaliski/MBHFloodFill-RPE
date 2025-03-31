package org.example;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageColorFill {
    public static void main(String[] args) throws IOException {
        File inputFile12 = new File("src/main/resources/CapturaDeTela.png");

        if (!inputFile12.exists()) {
            System.out.println("Erro: Arquivo de entrada not found!");
            return;
        }

        BufferedImage image21 = ImageIO.read(inputFile12);
        BufferedImage image31 = ImageIO.read(inputFile12);
        if (image21 == null) {
            System.out.println("Erro: Falha ao carregar img!");
            return;
        }

        int startX = 50, startY = 50;
        Color newColor = Color.RED;
        Color newColor1 = Color.BLUE;
        // Escolha o método de preenchimento: BFS (fila) ou DFS (pilha)
        floodFillBFS1(image21, startX, startY, newColor);
        floodFillDFS1(image31, startX, startY, newColor1);
        File outputFile11 = new File("saida_final30.png");
        File outputFile13 = new File("saida_final32.png");
        boolean success = ImageIO.write(image21, "png", outputFile11);
        boolean success2 = ImageIO.write(image31, "png", outputFile13);
        if (!success) {
            System.out.println("Erro: Falha ao salvar a imagem!");
        } else {
            System.out.println("Imagem processada salva como saida_final.png");
        }
    }

    /**
     * Implementação de Flood Fill usando BFS (Fila)
     */
    public static void floodFillBFS1(BufferedImage image, int x, int y, Color newColor) throws IOException {
        int targetColor = image.getRGB(x, y);
        if ((targetColor & 0xFFFFFF) == (newColor.getRGB() & 0xFFFFFF)) {
            System.out.println("A cor inicial já é a mesma da nova cor. Nada será alterado.");
            return;
        }

        MinhaFila1<int[]> queue23 = new MinhaFila1<>();
        queue23.enqueue(new int[]{x, y});
        image.setRGB(x, y, newColor.getRGB());

        int width = image.getWidth();
        int height = image.getHeight();
        int[][] directions11 = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int pixelsProcessed = 0;
        int frameCount = 1;

        while (!queue23.isEmpty()) {
            int[] pixel = queue23.dequeue();
            int px = pixel[0], py = pixel[1];

            for (int[] dir : directions11) {
                int nx = px + dir[0], ny = py + dir[1];

                if (nx >= 0 && nx < width && ny >= 0 && ny < height &&
                        (image.getRGB(nx, ny) & 0xFFFFFF) == (targetColor & 0xFFFFFF)) {

                    queue23.enqueue(new int[]{nx, ny});
                    image.setRGB(nx, ny, newColor.getRGB());
                    pixelsProcessed++;

                    // Salva frames a cada 500 pixels modificados
                    if (pixelsProcessed % 500 == 0) {
                        saveImage(image, "frameB_" + frameCount++ + ".png");
                    }
                }
            }
        }
    }


    /**
     * Implementação de Flood Fill usando DFS (Pilha)
     */
    public static void floodFillDFS1(BufferedImage image, int x, int y, Color newColor) throws IOException {
        int targetColor = image.getRGB(x, y);
        if ((targetColor & 0xFFFFFF) == (newColor.getRGB() & 0xFFFFFF)) {
            System.out.println("A cor inicial já é a mesma da nova cor. Nada será alterado.");
            return;
        }

        MinhaPilha1<int[]> stack22 = new MinhaPilha1<>();
        stack22.push(new int[]{x, y});  // Usando "push()" para adicionar elementos na pilha
        image.setRGB(x, y, newColor.getRGB());
        int width = image.getWidth();
        int height = image.getHeight();
        int[][] directions11 = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int pixelsProcessed = 0;
        int frameCount = 1;

        while (!stack22.isEmpty()) {
            int[] pixel = stack22.pop();  // Usando "pop()" para remover do topo da pilha
            int px = pixel[0], py = pixel[1];

            for (int[] dir : directions11) {
                int nx = px + dir[0], ny = py + dir[1];

                if (nx >= 0 && nx < width && ny >= 0 && ny < height &&
                        (image.getRGB(nx, ny) & 0xFFFFFF) == (targetColor & 0xFFFFFF)) {

                    stack22.push(new int[]{nx, ny});
                    image.setRGB(nx, ny, newColor.getRGB());
                    pixelsProcessed++;

                    // Salva frames a cada 500 pixels modificados
                    if (pixelsProcessed % 500 == 0) {
                        saveImage(image, "frame_" + frameCount++ + ".png");
                    }
                }
            }
        }
    }


    /**
     * Método para salvar imagens intermediárias durante a animação
     */
    private static void saveImage(BufferedImage image, String filename) throws IOException {
        File outputFile = new File(filename);
        ImageIO.write(image, "png", outputFile);
        System.out.println("Frame salvo: " + filename);
    }
}
class MinhaFila1<T> {
    private MinhaArrayList<T> lista = new MinhaArrayList<>();

    public void enqueue(T elemento) { // Adiciona no final (enqueue)
        lista.add(elemento);
    }

    public T dequeue() { // Remove do início (dequeue)
        if (isEmpty()) {
            throw new IllegalStateException("A fila está vazia!");
        }
        return lista.remove(0);
    }

    public boolean isEmpty() {
        return lista.size() == 0;
    }

    public int size() {
        return lista.size();
    }
}

class MinhaPilha1<T> {
    private MinhaArrayList<T> lista = new MinhaArrayList<>();

    public void push(T elemento) { // Adiciona no topo da pilha
        lista.add(elemento);
    }

    public T pop() { // Remove do topo da pilha
        if (isEmpty()) {
            throw new IllegalStateException("A pilha está vazia!");
        }
        return lista.remove(lista.size() - 1);
    }

    public boolean isEmpty() {
        return lista.size() == 0;
    }

    public int size() {
        return lista.size();
    }
}


