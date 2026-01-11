package br.com.dio.ui.custom.input;
// package - define a pasta (organização) da classe no projeto

import br.com.dio.util.UIStyle;
import javax.swing.BorderFactory;
import java.awt.Color;

import br.com.dio.model.Espaco;
// import - traz a classe Espaco para poder ser usada aqui.

import br.com.dio.service.EventoEnum;
// EventoEnum - define tipos de eventos do sistema

import br.com.dio.service.EventoOuvinte;
// EventoOuvinte - ‘Interface’ para receber notificações de eventos

import javax.swing.*;
// JTextField - campo de texto da interface gráfica

import javax.swing.event.DocumentEvent;
// DocumentEvent - evento que ocorre quando o texto muda

import javax.swing.event.DocumentListener;
// DocumentListener - "escuta" alterações no texto

import java.awt.Dimension;
// Dimension - define largura e altura do componente

import java.awt.Font;
// Font - define fonte, tamanho e estilo do texto

import static br.com.dio.service.EventoEnum.LIMPAR_ESPACO;
// static import - permite usar LIMPAR_ESPACO direto, sem escrever EventoEnum.LIMPAR_ESPACO

import static java.awt.Font.BOLD;
import static java.awt.Font.ITALIC;
import static java.awt.Font.PLAIN;
// Static import - permite usar PLAIN direto, sem escrever Font.PLAIN
// Primeiro: o que é static import? Import static significa: “Traga um valor fixo (constante) de outra classe para eu usar direto, sem escrever o nome da classe.”
// Sem isso, você teria que escrever: Font.PLAIN.
// Com isso, você pode escrever só: PLAIN.
/*
O que é Font.PLAIN? Dentro da classe Font existem constantes que definem o estilo da fonte:
Constante	Significado
Font.PLAIN ------ Texto normal (sem negrito, sem itálico)
Font.‘BOLD’	------ Texto em negrito
Font.ITALIC	------- Texto em itálico
Font.‘BOLD’ + Font.ITALIC --------- Negrito + itálico
*/



/*
 * Classe NumeroTexto
 * Representa um campo de entrada de número no ‘SUDOKU’.
 */
public class NumeroTexto extends JTextField implements EventoOuvinte {
    // extends JTextField - NumeroTexto É UM campo de texto
    // implements EventoOuvinte - NumeroTexto sabe reagir a eventos do sistema

    private final Espaco space;
    // space - representa o espaço (célula) do tabuleiro

    // CONSTRUTOR.
    public NumeroTexto(final Espaco space) {

        // this.space = space - guarda o espaço recebido no objeto
        this.space = space;

        // new Dimension(50, 50) - largura 50px, altura 50px
        var dimension = new Dimension(50, 50);

        // setSize(dimension) - define o tamanho REAL do campo de texto
        setSize(dimension);

        // setPreferredSize(dimension) - define o tamanho PREFERIDO do campo
        setPreferredSize(dimension);

        // setVisible(true) - torna o campo visível na tela
        setVisible(true);

        // setFont(new Font("Arial", PLAIN, 20))
        // "Arial" - tipo da fonte
        // PLAIN - estilo normal (sem negrito)
        // 20 - tamanho do número que fica dentro do quadrado.
        setFont(new Font("Courier New", BOLD, 20));

        // setHorizontalAlignment(CENTER)
        // CENTER - centraliza o texto dentro do campo
        setHorizontalAlignment(CENTER);

        // setDocument(new NumeroTextoLimite())
        // define um filtro que limita o que pode ser digitado
        setDocument(new NumeroTextoLimite());

        // setEnabled(!space.isDefinitivo())
        // O símbolo ! é o operador de negação lógica: Lê-se: "NÃO". Ele inverte um valor booleano.
        // isDefinitivo() - se for verdadeiro, o campo fica bloqueado
        setEnabled(!space.isDefinitivo());

        // Se o espaço for definitivo, mostra o número inicial do jogo
        if (space.isDefinitivo()) {
            setText(space.getReal().toString());
        }
        // Aplica o estilo visual definido na classe UIStyle
        setBackground(UIStyle.CELL_BG);
        setForeground(space.isDefinitivo() ? UIStyle.FIXED_TEXT : UIStyle.USER_TEXT);
        setCaretColor(Color.WHITE);
        setFont(UIStyle.CELL_FONT);
        setBorder(BorderFactory.createLineBorder(UIStyle.BORDER, 1));


        // getDocument().addDocumentListener(...)
        // adiciona um "ouvinte" que reage quando o texto muda
        getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                // insertUpdate - chamado quando o usuário digita algo
                changeSpace();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                // removeUpdate - chamado quando o usuário apaga algo
                changeSpace();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // changedUpdate - chamado quando o texto é alterado de outra forma
                changeSpace();
            }

            // changeSpace() - atualiza o valor do espaço do Sudoku
            private void changeSpace() {

                // getText() - pega o texto digitado
                // trim() - remove espaços antes e depois
                String text = getText().trim();

                // text.isEmpty() - verifica se o campo está vazio
                if (text.isEmpty()) {
                    // Limpa o valor do espaço
                    space.LimparEspaco();
                    return;
                }

                // text.matches("[1-9]")
                // verifica se é um número de 1 a 9 (regra do Sudoku)
                if (!text.matches("[1-9]")) {
                    setText("");
                    return;
                }

                // Integer.parseInt(text) - converte texto para número
                int value = Integer.parseInt(text);

                // Atualiza o valor real do espaço
                space.setReal(value);
            }
        });
    }

    // Metodo da interface EventoOuvinte
    @Override
    public void atualizar(final EventoEnum eventoTipo) {

        // Se o evento for LIMPAR_ESPACO e o campo estiver ativo
        if (eventoTipo.equals(LIMPAR_ESPACO) && isEnabled()) {

            // Limpa o texto do campo
            setText("");
        }
    }
}
