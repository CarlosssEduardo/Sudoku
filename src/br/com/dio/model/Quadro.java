package br.com.dio.model;

import java.util.Collection;
import java.util.List;

import static br.com.dio.model.GameStatus.NON_STARTED;
import static br.com.dio.model.GameStatus.INCOMPLETE;
import static br.com.dio.model.GameStatus.COMPLETE;
import static java.util.Objects.nonNull;
import static java.util.Objects.isNull;

/**
 * Classe responsável por representar o tabuleiro (quadro) do jogo Sudoku
 * e controlar toda a lógica de funcionamento do jogo.
 */
public class Quadro {

    // Matriz do tabuleiro: lista de colunas, cada coluna contém uma lista de espaços
    /* ESTRUTURA DE DADOS - LIST
    List<Espaco>
    → Lista simples (1D)
    Representa uma única linha de elementos.
    Exemplo: [E, E, E, E]

    List<List<Espaco>>
    → Estrutura 2D (tabela / matriz)
    Representa linhas e colunas, como um tabuleiro de Sudoku.
    Exemplo:
    [E, E, E],
    [E, E, E],
    [E, E, E]

   List<List<List<Espaco>>>
   → Estrutura 3D (cubo)
   Representa camadas de tabelas.
   Exemplo:
  [
  [
    [E, E],
    [E, E]
  ],
  [
    [E, E],
    [E, E]
  ]
  ]

  Regra principal: Quantidade de List = quantidade de dimensões da estrutura. */
    private final List<List<Espaco>> espaco;

    /**
     * Construtor do Quadro.
     * Recebe a matriz do jogo já montada.
     */
    public Quadro(final List<List<Espaco>> espaco) {
        this.espaco = espaco;
    }

    /**
     * Retorna o tabuleiro atual do jogo.
     */
    public List<List<Espaco>> getEspaco() {
        return espaco;
    }

    /**
     * Verifica o estado atual do jogo.
     *
     * NON_STARTED  → Nenhum número foi preenchido pelo jogador.
     * INCOMPLETE   → O jogo já começou, mas ainda existem espaços vazios.
     * COMPLETE     → Todos os espaços estão preenchidos.
     */
    public GameStatus getStatus() {

        // Verifica se nenhum espaço editável foi preenchido ainda
        if (espaco.stream()
                .flatMap(Collection::stream)
                .noneMatch(s -> !s.isDefinitivo() && nonNull(s.getReal()))) {
            return NON_STARTED;
        }

        // Se existir algum espaço vazio, o jogo está incompleto
        return espaco.stream()
                .flatMap(Collection::stream)
                .anyMatch(s -> isNull(s.getReal()))
                ? INCOMPLETE
                : COMPLETE;
    }

    /**
     * Verifica se o jogo contém erros.
     * Um erro acontece quando o valor preenchido é diferente do valor esperado.
     */
    public boolean VerificarErros() {

        // Se o jogo nem começou, não existem erros
        if (getStatus() == NON_STARTED) {
            return false;
        }

        // Procura qualquer espaço que tenha valor preenchido diferente do esperado
        return espaco.stream()
                .flatMap(Collection::stream)
                .anyMatch(s -> nonNull(s.getReal()) && !s.getReal().equals(s.getEsperado()));
    }

    /**
     * Tenta alterar o valor de uma posição do tabuleiro.
     * Retorna false se o espaço for definitivo (não pode ser alterado).
     */
    public boolean AlterarValor(final int coluna, final int linha, final int valor) {
        var space = espaco.get(coluna).get(linha);

        // Espaços definitivos não podem ser modificados
        if (space.isDefinitivo()) {
            return false;
        }

        // Atualiza o valor do espaço
        space.setReal(valor);
        return true;
    }

    /**
     * Remove o valor de uma posição do tabuleiro.
     * Retorna false se o espaço for definitivo.
     */
    public boolean LimparValor(final int coluna, final int linha) {
        var space = espaco.get(coluna).get(linha);

        // Espaços definitivos não podem ser apagados
        if (space.isDefinitivo()) {
            return false;
        }

        // Limpa o valor do espaço
        space.LimparEspaco();
        return true;
    }

    /**
     * Limpa todos os valores que o jogador preencheu no tabuleiro.
     */
    public void reset() {
        espaco.forEach(coluna ->
                coluna.forEach(Espaco::LimparEspaco)
        );
    }

    /**
     * Verifica se o jogo foi finalizado com sucesso.
     * Para isso:
     * - Não pode haver erros
     * - O status precisa ser COMPLETE
     */
    public boolean gameFinalizado() {
        return !VerificarErros() && getStatus().equals(COMPLETE);
    }
}
