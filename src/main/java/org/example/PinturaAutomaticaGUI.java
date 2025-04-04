import javax.swing.*;
import java.awt.*;

public class PinturaAutomaticaGUI extends JFrame {
    private static final int LINHAS = 10;
    private static final int COLUNAS = 15;
    private static final int TAMANHO_PIXEL = 40;
    private Color[][] matriz = new Color[LINHAS][COLUNAS];

    private int linhaAtual = 0;
    private int colunaAtual = 0;

    // 👇 Altere aqui para "quadrado", "circulo" ou "triangulo"
    private String forma = "quadrado";

    public PinturaAutomaticaGUI() {
        setTitle("Pintura Automática com Formas");
        setSize(COLUNAS * TAMANHO_PIXEL + 20, LINHAS * TAMANHO_PIXEL + 40);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        inicializarMatriz();

        JPanel painel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                desenharMatriz(g);
            }
        };

        add(painel);
        setVisible(true);

        Timer timer = new Timer(100, e -> {
            if (linhaAtual < LINHAS) {
                matriz[linhaAtual][colunaAtual] = Color.RED;
                colunaAtual++;
                if (colunaAtual >= COLUNAS) {
                    colunaAtual = 0;
                    linhaAtual++;
                }
                painel.repaint();
            } else {
                ((Timer) e.getSource()).stop();
            }
        });

        timer.start();
    }

    private void inicializarMatriz() {
        for (int i = 0; i < LINHAS; i++) {
            for (int j = 0; j < COLUNAS; j++) {
                matriz[i][j] = Color.WHITE;
            }
        }
    }

    private void desenharMatriz(Graphics g) {
        for (int i = 0; i < LINHAS; i++) {
            for (int j = 0; j < COLUNAS; j++) {
                int x = j * TAMANHO_PIXEL;
                int y = i * TAMANHO_PIXEL;

                g.setColor(matriz[i][j]);

                switch (forma.toLowerCase()) {
                    case "circulo":
                        g.fillOval(x, y, TAMANHO_PIXEL, TAMANHO_PIXEL);
                        break;
                    case "triangulo":
                        Polygon triangulo = new Polygon();
                        triangulo.addPoint(x + TAMANHO_PIXEL / 2, y); // topo
                        triangulo.addPoint(x, y + TAMANHO_PIXEL); // canto esquerdo
                        triangulo.addPoint(x + TAMANHO_PIXEL, y + TAMANHO_PIXEL); // canto direito
                        g.fillPolygon(triangulo);
                        break;
                    default: // quadrado
                        g.fillRect(x, y, TAMANHO_PIXEL, TAMANHO_PIXEL);
                        break;
                }

                g.setColor(Color.BLACK);
                g.drawRect(x, y, TAMANHO_PIXEL, TAMANHO_PIXEL);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PinturaAutomaticaGUI::new);
    }
}
