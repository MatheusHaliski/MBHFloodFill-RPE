package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class FloodFillViewer extends JPanel {
    private ArrayList<BufferedImage> frames;
    private int frameIndex = 0;

    public FloodFillViewer(ArrayList<BufferedImage> frames) {
        this.frames = frames;
        Timer timer = new Timer(50, e -> {
            if (frameIndex < frames.size() - 1) {
                frameIndex++;
                repaint();
            } else {
                ((Timer) e.getSource()).stop();  // Para a animação quando acabar
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (frames != null && frameIndex < frames.size()) {
            g.drawImage(frames.get(frameIndex), 0, 0, null);
        }
    }

    public static void showAnimation(ArrayList<BufferedImage> frames) {
        JFrame frame = new JFrame("Flood Fill Animation");
        FloodFillViewer panel = new FloodFillViewer(frames);
        frame.add(panel);
        frame.setSize(frames.get(0).getWidth(), frames.get(0).getHeight());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

