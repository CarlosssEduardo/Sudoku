package br.com.dio.ui.custom.panel;
// ↑ Define o pacote onde esta classe está localizada no projeto

import javax.swing.JPanel;
// ↑ Classe base que representa um painel na interface gráfica

import br.com.dio.util.UIStyle;

import java.awt.Dimension;
// ↑ Classe usada para definir largura e altura (tamanho) de componentes

public class MainPanel extends JPanel {
    // ↑ Cria uma classe chamada MainPanel
    //   que HERDA todas as funcionalidades de um JPanel

    public MainPanel(final Dimension dimension) {
        // ↑ Construtor da classe MainPanel
        //   Ele recebe um objeto Dimension com largura e altura desejadas

        this.setSize(dimension);
        // ↑ Define o tamanho atual do painel
        //   dimension → contém width (largura) e height (altura)

        this.setPreferredSize(dimension);
        // ↑ Define o tamanho preferido do painel
        //   Isso ajuda os gerenciadores de layout a organizarem a tela corretamente

        // Aplica o estilo visual definido na classe UIStyle
        setBackground(UIStyle.BACKGROUND);


    }

}
