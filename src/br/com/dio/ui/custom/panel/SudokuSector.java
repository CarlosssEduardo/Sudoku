package br.com.dio.ui.custom.panel;
// ↑ Pacote onde esta classe está localizada

import br.com.dio.ui.custom.input.NumeroTexto;
// ↑ Importa o componente de cada célula do Sudoku

import javax.swing.*;
// ↑ Classe base para criação de painéis na ‘interface’ gráfica

import br.com.dio.util.UIStyle;
import javax.swing.BorderFactory;

import javax.swing.border.LineBorder;
// ↑ Classe usada para desenhar bordas no painel

import java.awt.Dimension;
// ↑ Classe usada para definir largura e altura dos componentes

import java.util.List;
// ↑ Estrutura de dados para armazenar várias células do ‘Sudoku’

import static java.awt.Color.black;
// ↑ Importa a cor preta diretamente (evita escrever Color.black)

public class SudokuSector extends JPanel {
    // ↑ Cria um painel que representa UM bloco 3x3 do Sudoku

    public SudokuSector(final List<NumeroTexto> textFields) {
        // ↑ Construtor recebe a lista de células (campos de texto) do setor

        var dimension = new Dimension(180, 180);
        // ↑ Cria um objeto que define largura e altura do setor (170x170 pixels)

        this.setSize(dimension);
        // ↑ Define o tamanho atual do painel usando a dimensão criada

        this.setPreferredSize(dimension);
        // ↑ Define o tamanho preferido do painel para o layout da tela

        this.setBorder(new LineBorder(black, 2, true));
        // ↑ Cria uma borda preta em volta do setor
        //   black → cor da borda
        //   2 → espessura da borda (2 píxeis)
        //   true → bordas arredondadas

        this.setVisible(true);
        // ↑ Torna o painel visível na tela

        // Aplica o estilo visual definido na classe UIStyle
        setBackground(UIStyle.BACKGROUND);
        setBorder(BorderFactory.createLineBorder(UIStyle.SECTOR_BORDER, 3, true));


        textFields.forEach(this::add);
        // ↑ Para cada NumeroTexto da lista:
        //   adiciona ele dentro deste painel (setor do ‘Sudoku’)
    }

}
