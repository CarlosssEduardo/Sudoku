package br.com.dio;
// ↑ Define o pacote onde essa classe está localizada dentro do projeto.
//   Serve para organizar o código e evitar conflito de nomes.

import br.com.dio.ui.custom.screen.MainScreen;
// ↑ Importa a classe MainScreen, que é a tela principal do jogo ‘Sudoku’.

import java.util.stream.Stream;
// ↑ Importa Stream, que permite processar listas de dados de forma moderna e organizada.

import static java.util.stream.Collectors.toMap;
// ↑ Importa o metodo toMap para podermos usá-lo direto, sem escrever Collectors.toMap toda a hora.

public class UIMain {
    // ↑ Classe principal do programa. É daqui que o Java começa a execução.

    public static void main(String[] args) {
        // ↑ Metodo principal.
        //   Tudo que o programa faz começa aqui.
        //   Args recebe todos os dados enviados ao programa quando ele é iniciado.

        final var gameConfig = Stream.of(args)
                // ↑ Transforma o array de argumentos (args) em um Stream para poder processar melhor

                .collect(toMap(
                        k -> k.split(";")[0],
                        // ↑ k é cada String de args
                        //   split(";") separa a String pelo character ';'
                        //   [0] pega a parte da posição, exemplo: "0,0"

                        v -> v.split(";")[1]
                        // ↑ v é a mesma String
                        //   [1] pega a parte com o valor e se é fixo, exemplo: "4,false"
                ));
        // ↑ No final disso, o gameConfig vira um Map:
        //   "0,0" - "4,false"
        //   "1,0" -> "7,false"
        //   ...
        //   Ou seja: posição da célula → conteúdo da célula

        var mainsScreen = new MainScreen(gameConfig);
        // ↑ Cria a tela principal do jogo, passando toda a configuração do tabuleiro

        mainsScreen.buildMainScreen();
        // ↑ Monta a interface gráfica e exibe o Sudoku na tela
    }
}
