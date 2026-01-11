/*
    Pacote principal da aplicação.
    Organiza o projeto e evita conflito de nomes de classes.
*/
package br.com.dio;

/*
    Importa a classe Espaco.
    Cada objeto Espaco representa uma célula do Sudoku.
*/
import br.com.dio.model.Espaco;

/*
    Importa a classe Quadro.
    O Quadro representa o tabuleiro completo do jogo.
*/
import br.com.dio.model.Quadro;

/*
    Importações de estruturas e ferramentas do Java.
*/
import java.util.ArrayList;    // Implementação de List
import java.util.List;         // Estrutura de lista
import java.util.Map;          // Estrutura chave → valor
import java.util.Scanner;      // Leitura de dados digitados
import java.util.stream.Stream;// Operações em fluxo de dados

/*
    Import estático do modelo visual do tabuleiro.
*/
import static br.com.dio.util.ModeloDeQuadro.MODELO_QUADRO;

/*
    Métodos utilitários para verificação de nulo.
*/
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/*
    Permite usar toMap sem escrever Collectors.toMap.
*/
import static java.util.stream.Collectors.toMap;

/*
    Classe principal do programa.
    Controla menu, entradas do utilizador e fluxo do jogo.
*/
public class Main {

    // Objeto responsável por ler o que o utilizador digita
    private final static Scanner scanner = new Scanner(System.in);

    // Representa o tabuleiro atual do jogo
    private static Quadro quadro;

    // Tamanho padrão do ‘Sudoku’: 9x9
    private final static int MODELO_LIMITE = 9;

    /*
        Ponto inicial da aplicação.
    */
    public static void main(String[] args) {

        /*
            Converte os argumentos de inicialização em um Map.
            Cada posição do tabuleiro recebe:
            chave → "linha, coluna"
            valor → "valorEsperado, ehDefinitivo"
        */
        final var posicao = Stream.of(args)
                .collect(toMap(
                        k -> k.split(";")[0],
                        v -> v.split(";")[1]
                ));

        var opcao = -1;

        // ‘Loop’ principal do menu do jogo
        while (true){
            System.out.println("=======SUDOKU=======");
            System.out.println("Selecione uma das opções abaixo");
            System.out.println("1 - Iniciar Um Novo Jogo");
            System.out.println("2 - Colocar Um Novo Número");
            System.out.println("3 - Remover Um Número");
            System.out.println("4 - Visualizar Jogo Atual");
            System.out.println("5 - Verificar Status do Jogo");
            System.out.println("6 - Limpar Jogo");
            System.out.println("7 - Finalizar Jogo");
            System.out.println("8 - Sair");

            opcao = scanner.nextInt();

            // Direciona a opção escolhida
            switch (opcao){
                case 1 -> startGame(posicao);
                case 2 -> colocarnumero();
                case 3 -> removenumero();
                case 4 -> mostrarjogoatual();
                case 5 -> mostrarstatusjogo();
                case 6 -> limparjogo();
                case 7 -> finalizarjogo();
                case 8 -> System.exit(0);
                default -> System.out.println("Opção inválida");
            }
        }
    }

    /*
        Cria o tabuleiro e inicia um novo jogo.
    */
    private static void startGame(final Map<String, String> posicao){

        if (nonNull(quadro)){
            System.out.println("O jogo já foi iniciado");
            return;
        }

        List<List<Espaco>> spaces = new ArrayList<>();

        for (int i = 0; i < MODELO_LIMITE; i++) {
            spaces.add(new ArrayList<>());

            for (int j = 0; j < MODELO_LIMITE; j++) {

                var posicaoConfig = posicao.get("%s,%s".formatted(i, j));
                var esperado = Integer.parseInt(posicaoConfig.split(",")[0]);
                var definitivo = Boolean.parseBoolean(posicaoConfig.split(",")[1]);

                var espacoAtual = new Espaco(esperado, definitivo);
                spaces.get(i).add(espacoAtual);
            }
        }

        quadro = new Quadro(spaces);
        System.out.println("O jogo está pronto para começar");
    }

    /*
        Permite inserir um número no tabuleiro.
    */
    private static void colocarnumero() {
        if (isNull(quadro)){
            System.out.println("O jogo ainda não foi iniciado");
            return;
        }

        System.out.println("Informe a coluna:");
        var coluna = runUntilGetValidNumber(0, 8);

        System.out.println("Informe a linha:");
        var linha = runUntilGetValidNumber(0, 8);

        System.out.println("Informe o número:");
        var valor = runUntilGetValidNumber(1, 9);

        if (!quadro.AlterarValor(coluna, linha, valor)){
            System.out.println("Essa posição é fixa");
        }
    }

    /*
        Remove um número do tabuleiro.
    */
    private static void removenumero(){
        if (isNull(quadro)) return;

        System.out.println("Informe a coluna:");
        var coluna = runUntilGetValidNumber(0, 8);

        System.out.println("Informe a linha:");
        var linha = runUntilGetValidNumber(0, 8);

        quadro.LimparValor(coluna, linha);
    }

    /*
        Exibe o tabuleiro atual.
    */
    private static void mostrarjogoatual() {
        if (isNull(quadro)) return;

        var args = new Object[81];
        var pos = 0;

        for (int i = 0; i < MODELO_LIMITE; i++) {
            for (var coluna: quadro.getEspaco()){
                args[pos++] = " " + (isNull(coluna.get(i).getReal()) ? " " : coluna.get(i).getReal());
            }
        }

        System.out.printf(MODELO_QUADRO + "\n", args);
    }

    /*
        Mostra o status do jogo.
    */
    private static void mostrarstatusjogo() {
        if (isNull(quadro)) return;

        System.out.println("Status: " + quadro.getStatus().getRotulo());
    }

    /*
        Limpa o tabuleiro.
    */
    private static void limparjogo() {
        if (isNull(quadro)) return;
        quadro.reset();
    }

    /*
        Finaliza o jogo.
    */
    private static void finalizarjogo() {
        if (isNull(quadro)) return;

        if (quadro.gameFinalizado()){
            System.out.println("Parabéns! Jogo concluído.");
            mostrarjogoatual();
            quadro = null;
        }
    }

    /*
        Garante que o número digitado está dentro do intervalo permitido.
    */
    private static int runUntilGetValidNumber(final int minimo, final int maximo) {
        var atual = scanner.nextInt();

        while (atual < minimo || atual > maximo) {
            System.out.printf("Digite um número entre %d e %d\n", minimo, maximo);
            atual = scanner.nextInt();
        }

        return atual;
    }
}
