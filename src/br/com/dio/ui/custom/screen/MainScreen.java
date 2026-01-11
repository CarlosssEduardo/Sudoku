package br.com.dio.ui.custom.screen;
// ↑ Pacote onde esta classe está localizada

import br.com.dio.model.Espaco;
// ↑ Representa cada célula lógica do tabuleiro

import br.com.dio.service.QuadroServico;
// ↑ Serviço responsável pela lógica do tabuleiro (regras do jogo)

import br.com.dio.service.NotificadorServico;
// ↑ Serviço responsável por notificar eventos entre os componentes

import br.com.dio.ui.custom.button.buttonVerificarStatusdoJoigo;
// ↑ Botão personalizado para verificar o status do jogo

import br.com.dio.ui.custom.button.buttonTerminarJogo;
// ↑ Botão personalizado para finalizar o jogo

import br.com.dio.ui.custom.button.buttonReset;
// ↑ Botão personalizado para reiniciar o jogo

import br.com.dio.ui.custom.frame.MainFrame;
// ↑ Janela principal da aplicação

import br.com.dio.ui.custom.input.NumeroTexto;
// ↑ Campo de texto que representa cada célula do Sudoku

import br.com.dio.ui.custom.panel.MainPanel;
// ↑ Painel principal que contém todos os componentes

import br.com.dio.ui.custom.panel.SudokuSector;
// ↑ Painel que representa cada setor 3x3 do Sudoku

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
// ↑ Componentes gráficos principais

import java.awt.Dimension;
// ↑ Classe para definir largura e altura da tela

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
// ↑ Estruturas de dados

import static br.com.dio.service.EventoEnum.LIMPAR_ESPACO;
// ↑ Importa o evento LIMPAR_ESPACO diretamente

import static javax.swing.JOptionPane.*;
// ↑ Importa métodos de diálogo: showMessageDialog, showConfirmDialog, etc.

public class MainScreen {

    private final static Dimension dimension = new Dimension(650, 650);
    // ↑ Tamanho fixo da janela principal (600x600 pixels)

    private final QuadroServico boardService;
    // ↑ Controla as regras do jogo e o estado do tabuleiro

    private final NotificadorServico notifierService;
    // ↑ Controla a comunicação entre componentes via eventos

    private JButton checkGameStatusButton;
    private JButton finishGameButton;
    private JButton resetButton;
    // ↑ Referências para os três botões da ‘interface’

    public MainScreen(final Map<String, String> gameConfig) {
        // ↑ Construtor recebe a configuração inicial do jogo

        this.boardService = new QuadroServico(gameConfig);
        // ↑ Cria o serviço do tabuleiro a partir da configuração

        this.notifierService = new NotificadorServico();
        // ↑ Cria o serviço de notificação de eventos
    }

    public void buildMainScreen(){
        // ↑ Monta toda a ‘interface’ do jogo

        JPanel mainPanel = new MainPanel(dimension);
        // ↑ Cria o painel principal com o tamanho da tela

        JFrame mainFrame = new MainFrame(dimension, mainPanel);
        // ↑ Cria a janela principal e adiciona o painel



        // Tradução final em português simples: O código está a caminhar pelos 9 blocos grandes do ‘Sudoku’, pegando os 9 quadradinhos de cada bloco e desenhando eles na tela.
        for (int r = 0; r < 9; r+=3) {
            // ↑ Percorre as linhas do tabuleiro a cada 3

            var endRow = r + 2;

            for (int c = 0; c < 9; c+=3) {
                // ↑ Percorre as colunas a cada 3

                var endCol = c + 2;

                var spaces = getSpacesFromSector(
                        boardService.getEspaco(),
                        c, endCol,
                        r, endRow
                );
                // ↑ Obtém os 9 espaços que formam um setor 3x3

                JPanel sector = generateSection(spaces);
                // ↑ Cria visualmente o setor do ‘Sudoku’

                mainPanel.add(sector);
                // ↑ Adiciona o setor ao painel principal
            }
        }

        addResetButton(mainPanel);
        addCheckGameStatusButton(mainPanel);
        addFinishGameButton(mainPanel);
        // ↑ Cria e adiciona os botões na ‘interface’

        mainFrame.revalidate();
        mainFrame.repaint();
        // ↑ Atualiza a tela
    }

    private List<Espaco> getSpacesFromSector(
            final List<List<Espaco>> spaces,
            final int initCol, final int endCol,
            final int initRow, final int endRow){

        List<Espaco> spaceSector = new ArrayList<>();
        // ↑ Lista que armazenará as 9 células do setor

        for (int r = initRow; r <= endRow; r++) {
            for (int c = initCol; c <= endCol; c++) {
                spaceSector.add(spaces.get(c).get(r));
                // ↑ Adiciona cada célula pertencente ao setor
            }
        }
        return spaceSector;
    }

    private JPanel generateSection(final List<Espaco> spaces){
        // ↑ Converte as células lógicas em campos visuais

        List<NumeroTexto> fields =
                new ArrayList<>(spaces.stream().map(NumeroTexto::new).toList());
        // ↑ Cria um campo de texto para cada espaço

        fields.forEach(t -> notifierService.inscrever_se(LIMPAR_ESPACO, t));
        // ↑ Registry cada campo para ouvir eventos de limpar espaço

        return new SudokuSector(fields);
        // ↑ Cria o painel do setor 3x3
    }

    private void addFinishGameButton(final JPanel mainPanel) {
        finishGameButton = new buttonTerminarJogo(e -> {
            // ↑ Botão que verifica se o jogo foi concluído corretamente

            if (boardService.gameFinalizado()){
                showMessageDialog(null, "Parabéns você concluiu o jogo");

                resetButton.setEnabled(false);
                checkGameStatusButton.setEnabled(false);
                finishGameButton.setEnabled(false);
                // ↑ Bloqueia todos os botões
            } else {
                showMessageDialog(null,
                        "Seu jogo tem alguma inconsistência, ajuste e tente novamente");
            }
        });

        mainPanel.add(finishGameButton);
    }

    private void addCheckGameStatusButton(final JPanel mainPanel) {
        checkGameStatusButton = new buttonVerificarStatusdoJoigo(e -> {

            var hasErrors = boardService.VerificarErros();
            var gameStatus = boardService.getStatus();

            var message = switch (gameStatus){
                case NON_STARTED -> "O jogo não foi iniciado";
                case INCOMPLETE -> "O jogo está incompleto";
                case COMPLETE -> "O jogo está completo";
            };

            message += hasErrors ? " e contém erros" : " e não contém erros";

            showMessageDialog(null, message);
        });

        mainPanel.add(checkGameStatusButton);
    }

    private void addResetButton(final JPanel mainPanel) {
        resetButton = new buttonReset(e ->{

            var dialogResult = showConfirmDialog(
                    null,
                    "Deseja realmente reiniciar o jogo?",
                    "Limpar o jogo",
                    YES_NO_OPTION,
                    QUESTION_MESSAGE
            );
            // ↑ Exibe uma caixa de confirmação

            if (dialogResult == 0){
                boardService.reset();
                notifierService.notificacao(LIMPAR_ESPACO);
                // ↑ Limpa o tabuleiro e notifica os campos
            }
        });

        mainPanel.add(resetButton);
    }

}
