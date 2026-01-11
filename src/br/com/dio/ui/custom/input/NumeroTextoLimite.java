package br.com.dio.ui.custom.input;
// ↑ Define o pacote (local do arquivo dentro do projeto)

import javax.swing.text.AttributeSet;
// ↑ Representa os atributos do texto (estilo, formatação, etc.)

import javax.swing.text.BadLocationException;
// ↑ Exceção lançada quando se tenta mexer numa posição inválida do texto

import javax.swing.text.PlainDocument;
// ↑ Classe base que controla como o texto de um JTextField funciona

import java.util.List;
// ↑ Estrutura de lista usada para armazenar os números permitidos

import static java.util.Objects.isNull;
// ↑ Importa o metodo isNull() diretamente, permitindo usar sem escrever Objects.isNull()
// isNull: "é nulo?" "não existe nada".

public class NumeroTextoLimite extends PlainDocument {
    // ↑ Esta classe HERDA o comportamento de PlainDocument
    //   e permite modificar como o utilizador pode digitar no campo

    private final List<String> NUMERO = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9");
    // ↑ Lista dos valores PERMITIDOS no campo do ‘SUDOKU’. Para o jogador digitar,
    //   Somente números de 1 a 9 (regra do ‘Sudoku’)


    // Construtor.
    public void inseriString(final int offs, final String str, final AttributeSet a)
            throws BadLocationException {
        // ↑ Metodo chamado automaticamente quando o utilizador tenta digitar algo no campo
        //   offs → posição do cursor
        //   str → texto digitado
        //   a → atributos do texto

        if (isNull(str) || (!NUMERO.contains(str))) return;
        // ↑ Se o texto for nulo OU não estiver entre "1" e "9"
        //   → cancela a digitação e sai do metodo

        if (getLength() + str.length() <= 1) {
            // ↑ Verifica se o campo terá no máximo 1 caractere
            //   getLength() → tamanho atual do texto
            //   str.length() → tamanho do que o usuário quer digitar
            //   Isso garante que só exista UM número na célula

            super.insertString(offs, str, a);
            // ↑ Insere o texto no campo (comportamento normal do JTextField)
        }
    }
}
