package br.com.dio.model;

// Enum que representa os possíveis estados do jogo
// Enum vem de enumeration = lista fechada de opções.
// Quando você cria um enum, você diz ao Java:
// “Essa variável só pode assumir UM desses valores. Nenhum outro.”
public enum GameStatus {

    // Cada constante do enum é um objeto do tipo GameStatus
    // e recebe um rótulo para exibição
    NON_STARTED("Não Iniciado"),
    INCOMPLETE("Incompleto"),
    COMPLETE("Completo");

    // Texto descritivo do status
    private String rotulo;

    /*
     Construtor do enum.
     Ele é chamado automaticamente para cada constante acima.
     Exemplo:
     NON_STARTED → rotulo = "Não Iniciado"
    */
    GameStatus(final String rotulo){
        this.rotulo = rotulo;
    }

    // Permite acessar o rótulo do status
    public String getRotulo() {
        return rotulo;
    }
}
