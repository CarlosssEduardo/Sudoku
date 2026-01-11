/*
 Package = “pasta lógica” do projeto.
 Organiza o código e evita conflito de nomes.
 Esse arquivo pertence ao pacote br.com.dio.model
*/
package br.com.dio.model;

// Classe chamada Espaço: é o molde para criar objetos do tipo Espaço.
public class Espaco {

    // =======================
    // Atributos (Estado do Objeto)
    // =======================
    // private: só a própria classe pode acessar.
    // final: depois que definir no construtor, nunca mais pode mudar.
    // Em Java, Integer é uma classe que representa um número inteiro.
    private Integer real;               // Valor que o jogador colocou no espaço.
    private final int esperado;         // Número correto da solução.
    private final boolean definitivo;   // Indica se a célula é fixa ou editável.

    /*
     Quando você escreve:
     Espaço e = new Espaço(5, true);

     Você basicamente gritou:
     "JAVA, constrói um Espaço para mim!"

     Java responde:
     "Beleza, vou executar o construtor abaixo."
    */
    public Espaco(final int esperado, final boolean definitivo) {

        // O objeto nasce sabendo qual é o valor esperado
        // e se ele é definitivo.
        this.esperado = esperado;
        this.definitivo = definitivo;

        // Se esse espaço for definitivo,
        // então o valor real já nasce correto.
        if (definitivo) {
            real = esperado;
        }
    }

    // =======================
    // Getter e Setter
    // =======================

    // Serve para ler o valor de real.
    public Integer getReal() {
        return real;
    }

    /*
     Set  → alterar
     Real → nome do atributo

     Ou seja:
     Metodo responsável por alterar o valor real.

     Integer → tipo do dado: valor que será colocado no espaço.
     real    → nome do parâmetro: variável temporária que recebe o valor passado.
     final   → dentro deste metodo, essa variável não pode ser alterada.
    */
    public void setReal(final Integer real) {

        // Se esse espaço for definitivo, pare aqui e saia do metodo.
        if (definitivo) return;

        this.real = real;
    }

    /*
     Este metodo não retorna nada.
     Ele apenas executa uma ação.

     null → nenhum valor. Vazio.
    */
    public void LimparEspaco() {
        setReal(null);
    }

    // Apenas permite consultar o valor esperado.
    public int getEsperado() {

        /*
         return significa:
         "Aqui está o valor que você pediu."

         O Metodo devolve o conteúdo da variável esperado.
        */
        return esperado;
    }

    /*
     Em Java, para boolean, usamos "is" em vez de "get".

     Tradução humana:
     "Esse espaço é definitivo?"

     return definitivo:
     "Pegue o valor do atributo definitivo e devolva para quem chamou."
    */
    public boolean isDefinitivo() {
        return definitivo;
    }
}
