🧩 Sudoku — Aplicação de Jogo em Java 🎮

Um jogo de Sudoku desenvolvido em Java com interface gráfica usando Swing, organizado de forma modular e com arquitetura limpa.
Permite iniciar um tabuleiro, preencher números, verificar inconsistências, reiniciar e concluir o jogo.


---

📌 Sobre

Este projeto representa uma implementação completa do clássico jogo de lógica Sudoku, com:

✔ Interface gráfica personalizada
✔ Validação de entradas (somente números de 1 a 9)
✔ Verificação do estado do jogo (incompleto, completo e com ou sem erros)
✔ Controle de reinício e conclusão
✔ Estrutura orientada a objetos

O código está organizado em pacotes que separam lógica do modelo, interface gráfica e serviços.


---

📂 Estrutura do Projeto

br.com.dio
│
├── model                # Modelos de dados (Espaco, Quadro, GameStatus)
├── service              # Lógica de jogo e eventos
│   ├── EventoEnum
│   ├── EventoOuvinte
│   ├── NotificadorServico
│   └── QuadroServico
│
├── ui
│   ├── custom
│   │   ├── button       # Botões da interface
│   │   ├── frame        # Janela principal
│   │   ├── input        # Campos de texto (células do Sudoku)
│   │   ├── panel        # Painéis gráficos
│   │   └── screen       # Tela completa do jogo
│   │
│   └── util             # Estilos e utilitários
│
├── util
│   └── ModeloDeQuadro   # (se houver utilidades extras)
│
├── UIMain.java          # Ponto de entrada do programa
└── Main.java            # Opcional (dependendo da sua estrutura)


---

🧠 Como Funciona

🚀 Inicialização

O programa começa pela classe UIMain, que lê os argumentos de configuração do tabuleiro e envia para a tela principal (MainScreen).

📊 Interface

O tabuleiro é formado por células customizadas (NumeroTexto) organizadas em setores de 3×3 (SudokuSector), exibidos em um painel principal (MainPanel) dentro de uma janela (MainFrame).

🔁 Funcionalidades

Ação	Descrição

Reiniciar Jogo	Limpa o tabuleiro e retorna à configuração inicial
Verificar Jogo	Informa se o jogo está completo/incompleto e se contém erros
Concluir	Verifica se o jogo foi finalizado corretamente



---

📦 Dependências

Este projeto usa apenas bibliotecas internas do Java (Swing) — sem dependências externas.

Requisitos:

✔ Java 17+
✔ IDE (IntelliJ, Eclipse, VSCode etc.)


---

🚀 Como Executar

1. Clone o repositório:



git clone https://github.com/CarlosssEduardo/Sudoku.git

2. Abra no IntelliJ ou outra IDE Java.


3. Compile o projeto.


4. Execute a classe:



UIMain

ou configure sua Run Configuration apontando para br.com.dio.UIMain.


---

🖼️ Visual do Jogo

O jogo possui uma interface interativa com:

✔ Setores bem destacados
✔ Números fixos em uma cor
✔ Números do usuário em outra cor
✔ Botões na parte inferior 
