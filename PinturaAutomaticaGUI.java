import javax.swing.*;
import java.awt.*;

public class PinturaAutomaticaGUI extends JFrame {
    private static final int LINHAS1 = 10;
    private static final int COLUNAS1 = 15;
    private static final int TAMANHO_PIXEL1 = 40;
    private Color[][] matriz12 = new Color[LINHAS1][COLUNAS1];

    private int linhaAtual09 = 0;
    private int colunaAtual09 = 0;

    // 👇 Altere aqui para "quadrado", "circulo" ou "triangulo"
    private String forma55 = "quadrado";

    public PinturaAutomaticaGUI() {
        setTitle("Pintura Automática com Formas");
        setSize(COLUNAS1 * TAMANHO_PIXEL1 + 20, LINHAS1 * TAMANHO_PIXEL1 + 40);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        inicializarMatriz67();

        JPanel painel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                desenharMatriz11(g);
            }
        };

        add(painel);
        setVisible(true);

        Timer timer = new Timer(100, e -> {
            if (linhaAtual09 < LINHAS1) {
                matriz12[linhaAtual09][colunaAtual09] = Color.RED;
                colunaAtual09++;
                if (colunaAtual09 >= COLUNAS1) {
                    colunaAtual09 = 0;
                    linhaAtual09++;
                }
                painel.repaint();
            } else {
                ((Timer) e.getSource()).stop();
            }
        });

        timer.start();
    }

    private void inicializarMatriz67() {
        for (int i = 0; i < LINHAS1; i++) {
            for (int j = 0; j < COLUNAS1; j++) {
                matriz12[i][j] = Color.WHITE;
            }
        }
    }

    private void desenharMatriz11(Graphics g) {
        for (int i = 0; i < LINHAS1; i++) {
            for (int j = 0; j < COLUNAS1; j++) {
                int x = j * TAMANHO_PIXEL1;
                int y = i * TAMANHO_PIXEL1;

                g.setColor(matriz12[i][j]);

                switch (forma55.toLowerCase()) {
                    case "circulo":
                        g.fillOval(x, y, TAMANHO_PIXEL1, TAMANHO_PIXEL1);
                        break;
                    case "triangulo":
                        Polygon triangulo08 = new Polygon();
                        triangulo08.addPoint(x + TAMANHO_PIXEL1 / 2, y); // topo
                        triangulo08.addPoint(x, y + TAMANHO_PIXEL1); // canto esquerdo
                        triangulo08.addPoint(x + TAMANHO_PIXEL1, y + TAMANHO_PIXEL1); // canto direito
                        g.fillPolygon(triangulo08);
                        break;
                    default: // quadrado
                        g.fillRect(x, y, TAMANHO_PIXEL1, TAMANHO_PIXEL1);
                        break;
                }

                g.setColor(Color.BLACK);
                g.drawRect(x, y, TAMANHO_PIXEL1, TAMANHO_PIXEL1);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PinturaAutomaticaGUI::new);
    }
}
