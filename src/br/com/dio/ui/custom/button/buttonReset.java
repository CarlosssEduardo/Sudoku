// Define o pacote onde essa classe está localizada dentro do projeto
package br.com.dio.ui.custom.button;

import br.com.dio.util.UIStyle;

// Importa a classe JButton, que é o botão padrão da biblioteca gráfica Swing
import javax.swing.JButton;

// Importa a interface ActionListener, responsável por "escutar" eventos de clique
import java.awt.event.ActionListener;

/*
 * Classe buttonReset
 * Representa um botão personalizado da ‘Interface’ gráfica.
 * a sua função principal é criar um botão já configurado para reiniciar o jogo.
 * extends: Você está dizendo: "o meu buttonReset É um botão."
 */
public class buttonReset extends JButton {

    /*
     * Construtor da classe
     * Este metodo é executado no momento em que o botão é criado.
     * Ele recebe como parâmetro um ActionListener, que contém o código
     * que será executado quando o botão for clicado.
     */
    public buttonReset(final ActionListener acaoOuvinte) {

        // Define o texto que aparecerá escrito no botão
        // setText: "Define o texto que aparece escrito no botão."
        this.setText("REINICIAR O JOGO");

        // Associa o evento de clique do botão ao código recebido no parâmetro
        // Sempre que o usuario clicar no botão, esse ActionListener será executado
        // addActionListener: "Ei botão, quando alguém clicar em você, execute este código."
        this.addActionListener(acaoOuvinte);

        UIStyle.estilizarBotaoGamer(this);



    }
}
