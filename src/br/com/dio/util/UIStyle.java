package br.com.dio.util;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Classe utilitária para centralizar a estilização da interface (UI).
 * Utiliza Java 2D (Graphics2D) para criar efeitos de gradiente e bordas arredondadas.
 */
public class UIStyle {

    // --- PALETA DE CORES (Baseadas na imagem enviada) ---
    // Cor de fundo profundo da janela principal
    public static final Color BACKGROUND = new Color(20, 24, 35);

    // Cor de fundo dos setores 3x3 (um azul levemente mais claro que o fundo)
    public static final Color CELL_BG = new Color(30, 36, 48);

    // Cor das bordas das células individuais
    public static final Color BORDER = new Color(45, 52, 65);

    // Cor das bordas que separam os blocos 3x3 (mais destacadas)
    public static final Color SECTOR_BORDER = new Color(70, 80, 100);

    // Cor para números pré-definidos (fixos) do jogo
    public static final Color FIXED_TEXT = new Color(150, 180, 210);

    // Cor para os números que o jogador digita
    public static final Color USER_TEXT = Color.WHITE;

    // Cor base para os botões (Azul Gamer)
    public static final Color BUTTON_BG = new Color(60, 110, 170);
    public static final Color BUTTON_TEXT = Color.WHITE;

    // --- FONTES ---
    public static final Font CELL_FONT = new Font("Segoe UI", Font.BOLD, 22);
    public static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 14);

    /**
     * Estiliza as células onde os números são digitados.
     * O segredo aqui é o setOpaque(false) para criar o efeito de transparência.
     */
    public static void estilizarNumeroTexto(JTextField campo) {
        campo.setOpaque(false); // Permite ver o fundo do painel (efeito vidro)
        campo.setBackground(new Color(0, 0, 0, 0)); // Fundo totalmente transparente
        campo.setForeground(USER_TEXT); // Cor do texto
        campo.setFont(CELL_FONT);
        campo.setHorizontalAlignment(JTextField.CENTER); // Centraliza o número

        // Borda interna muito fina para dar profundidade à célula
        campo.setBorder(BorderFactory.createLineBorder(new Color(15, 15, 20), 1));
    }

    /**
     * Estiliza o painel que agrupa o setor 3x3 do Sudoku.
     */
    public static void estilizarSudokuSector(JPanel panel) {
        panel.setBackground(CELL_BG);
        // Aplica a borda mais grossa que separa os blocos
        panel.setBorder(BorderFactory.createLineBorder(SECTOR_BORDER, 2));
    }

    /**
     * Estiliza o fundo de painéis genéricos.
     */
    public static void estilizarFundo(JPanel panel) {
        panel.setBackground(BACKGROUND);
    }

    /**
     * Estiliza o fundo da janela principal (JFrame).
     */
    public static void estilizarFundo(JFrame frame) {
        frame.getContentPane().setBackground(BACKGROUND);
    }

    /**
     * O método mais complexo: redesenha o botão do zero para criar gradientes,
     * bordas iluminadas e cantos arredondados (Efeito Gamer).
     */
    public static void estilizarBotaoGamer(JButton button) {
        // Remove as decorações padrão do Windows/Swing
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);

        button.setForeground(BUTTON_TEXT);
        button.setFont(BUTTON_FONT);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cursor de "mãozinha"

        // Sobrescreve a UI padrão do botão para desenhar o gráfico customizado
        button.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                // Graphics2D permite desenhos mais avançados (suavização e gradientes)
                Graphics2D g2 = (Graphics2D) g.create();

                // Ativa o Antialiasing (suaviza as bordas arredondadas para não ficarem "serrilhadas")
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int w = c.getWidth();
                int h = c.getHeight();

                // 1. DESENHA O "GLOW" (BRILHO EXTERNO)
                // Criamos uma borda azul clara semi-transparente
                g2.setPaint(new Color(100, 180, 255, 120));
                g2.drawRoundRect(0, 0, w - 1, h - 1, 15, 15);

                // 2. DESENHA O GRADIENTE DE FUNDO
                // Começa com BUTTON_BG no topo e termina em um azul bem escuro na base
                GradientPaint gp = new GradientPaint(
                        0, 0, BUTTON_BG,
                        0, h, new Color(25, 45, 75)
                );

                g2.setPaint(gp);
                // Preenche o retângulo com cantos arredondados (raio de 15px)
                g2.fillRoundRect(1, 1, w - 2, h - 2, 15, 15);

                // 3. EFEITO DE REFLEXO (VIDRO) NA PARTE SUPERIOR
                // Desenha uma forma branca bem transparente na metade de cima do botão
                g2.setPaint(new Color(255, 255, 255, 40));
                g2.fill(new RoundRectangle2D.Double(2, 2, w - 4, h / 2.2, 15, 15));

                g2.dispose(); // Libera a memória do objeto gráfico
                super.paint(g, c); // Desenha o texto do botão por cima de tudo
            }
        });

        // Define um espaçamento interno para o botão não ficar "esmagado"
        button.setBorder(new EmptyBorder(10, 25, 10, 25));
    }
}