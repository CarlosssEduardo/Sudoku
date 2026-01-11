package br.com.dio.service;

import br.com.dio.model.Quadro;
import br.com.dio.model.GameStatus;
import br.com.dio.model.Espaco;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QuadroServico {

    // Constante que define o tamanho padrão do tabuleiro (9x9) — típico de Sudoku
    private final static int MODELO_QUADRO = 9;

    // O objeto Quadro é o coração do jogo, guarda o estado do tabuleiro
    private final Quadro quadro;

    // Construtor: recebe a configuração inicial do jogo e monta o tabuleiro
    public QuadroServico(final Map<String, String> gameConfig) {
        this.quadro = new Quadro(initBoard(gameConfig)); // inicializa o quadro com base no mapa de configuração
    }

    // Retorna a matriz de espaços (cada célula do tabuleiro)
    public List<List<Espaco>> getEspaco(){
        return quadro.getEspaco();
    }

    // Reseta o tabuleiro para o estado inicial
    public void reset() {
        quadro.reset();
    }

    // Verifica se existem erros no tabuleiro (ex.: números repetidos ou inválidos)
    public boolean VerificarErros(){
        return quadro.VerificarErros();
    }

    // Retorna o status atual do jogo (em andamento, finalizado, etc.)
    public GameStatus getStatus(){
        return quadro.getStatus();
    }

    // Diz se o jogo já foi concluído
    public boolean gameFinalizado(){
        return quadro.gameFinalizado();
    }

    // Metodo privado que monta o tabuleiro inicial a partir da configuração recebida
    private List<List<Espaco>> initBoard(final Map<String, String> gameConfig) {
        List<List<Espaco>> space = new ArrayList<>(); // cria a matriz de espaços
        for (int i = 0; i < MODELO_QUADRO; i++) { // percorre as linhas
            space.add(new ArrayList<>());
            for (int j = 0; j < MODELO_QUADRO; j++) { // percorre as colunas
                // Busca a configuração da posição atual no mapa (ex.: "0,0" -> "5,true")
                var positionConfig = gameConfig.get("%s,%s".formatted(i, j));

                // Extrai o número esperado (valor da célula)
                var esperado = Integer.parseInt(positionConfig.split(",")[0]);

                // Extrai se o valor é definitivo (pré-definido pelo jogo) ou pode ser alterado
                var definitivo = Boolean.parseBoolean(positionConfig.split(",")[1]);

                // Cria o objeto Espaco com essas informações
                var EspacoAtual = new Espaco(esperado, definitivo);

                // Adiciona o espaço na matriz
                space.get(i).add(EspacoAtual);
            }
        }

        // Retorna a matriz completa, pronta para ser usada pelo Quadro
        return space;
    }
}