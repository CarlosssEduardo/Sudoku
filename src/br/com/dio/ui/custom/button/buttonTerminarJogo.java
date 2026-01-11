package br.com.dio.ui.custom.button;

import br.com.dio.util.UIStyle;

import java.awt.event.ActionListener;
import javax.swing.JButton;

public class buttonTerminarJogo extends JButton{

    public buttonTerminarJogo(final ActionListener acaoOuvinte) {
        this.setText("CONCLUIR");
        this.addActionListener(acaoOuvinte);
        UIStyle.estilizarBotaoGamer(this);

    }
}
