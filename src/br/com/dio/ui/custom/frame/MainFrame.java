package br.com.dio.ui.custom.frame;

// Import: significa: “Eu quero usar uma classe que está em outro lugar do Java.”
// javax é o pacote principal de bibliotecas gráficas do Java. Antigamente tudo era java, depois a parte gráfica ficou em javax.
/* swing: swing é o conjunto de ferramentas gráficas do Java.
Ou seja:
javax.swing → biblioteca gráfica do Java
Ela tem: botões, janelas, painéis, caixas de texto, menus */

import javax.swing.JFrame; // JFrame - representa uma janela
import javax.swing.JPanel; // JPanel - painel que fica dentro da janela
import java.awt.Dimension; // Dimension -> guarda largura e altura
import br.com.dio.util.UIStyle;

/*
 * Janela principal do jogo
 */
public class MainFrame extends JFrame {

    public MainFrame(final Dimension dimension, final JPanel mainPanel) {

        // super("Sudoku") -> chama o construtor da classe JFrame
        // "Sudoku" - é o texto que aparece na barra superior da janela
        super("SUDOKU");

        // setSize(dimension) -> define o tamanho REAL da janela
        // dimension - objeto que contém largura e altura (ex: 600x600)
        this.setSize(dimension);

        // setPreferredSize(dimension) -> informa ao sistema o tamanho IDEAL da janela
        // dimension -> largura e altura preferidas
        this.setPreferredSize(dimension);

        // setDefaultCloseOperation(EXIT_ON_CLOSE) -> define o que acontece ao fechar a janela
        // EXIT_ON_CLOSE -> encerra o programa inteiro
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // setVisible(true) -> controla se a janela aparece ou não
        // true -> a janela fica visível
        // false -> a janela fica invisível
        this.setVisible(true);

        // setLocationRelativeTo(null) -> define a posição da janela na tela
        // null -> significa "em relação à tela inteira"
        // ou seja -> centraliza a janela no meio do monitor
        this.setLocationRelativeTo(null);

        // setResizable(false) -> define se o jogador pode redimensionar a janela
        // false - NÃO pode mudar o tamanho
        // true - pode mudar o tamanho
        this.setResizable(false);

        // add(mainPanel) -> adiciona o painel principal dentro da janela
        // mainPanel -> e o conteúdo visual do jogo.
        this.add(mainPanel);

        // Aplica o estilo visual definido na classe UIStyle
        UIStyle.estilizarFundo(this);

    }
}
