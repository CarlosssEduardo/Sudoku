package br.com.dio.ui.custom.button;

import br.com.dio.util.UIStyle;

import javax.swing.JButton;
import java.awt.event.ActionListener;

public class buttonVerificarStatusdoJoigo extends JButton{

    public buttonVerificarStatusdoJoigo(final ActionListener acaoOuvinte) {
        this.setText("VERIFICAR JOGO");
        this.addActionListener(acaoOuvinte);
        UIStyle.estilizarBotaoGamer(this);

    }
}
