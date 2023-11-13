# Projeto Fundo de Investimentos

Projeto realizado na disciplina de Lógica 2, no IFSP, para o aprendizado de estruturas de dados, gravação em arquivos de texto e algoritmos de ordenação e de busca em java.

## Integrantes

- Mateus Cruzatto Ramos
- Tharik Mohamad Mourad
- Tiago dos Santos Souza

## Descrição do projeto

O código Java fornecido representa um sistema de simulação de negociação na Bolsa de Valores (B3).

### Estrutura do Projeto:

O projeto está dividido em vários pacotes, incluindo algoritmosdeordenacao, dao, entities, estruturasdedados, e main.

- Entidades:

As principais entidades do sistema estão no pacote entities, incluindo Bolsa, Empresa, Investidor, InvestidorFisico, Corretora, Ativo, Ordinaria, Preferencial, Ordem, e Registro.
Essas entidades representam as bolsas, empresas listadas, investidores, tipos de ativos, ordens de compra/venda e registros de transações.

- DAO (Data Access Object):

O pacote dao contém classes para interagir com o armazenamento persistente, simulado aqui por arquivos de texto. Cada entidade tem uma classe DAO associada (por exemplo, BolsaDAO, EmpresaDAO, etc.).
Essas classes fornecem métodos para salvar, recuperar e manipular os dados das entidades no sistema.

- Algoritmos de Ordenação:

O pacote algoritmosdeordenacao contém implementações de algoritmos de ordenação, como o algoritmo ShellSort.

- Estruturas de Dados:

O pacote estruturasdedados contém implementações de estruturas de dados, como LinkedList, Pilha, Fila e Arvore Binária.

- Main:

A classe Main contém o método principal (main) que inicia a execução do programa.
Inicializa uma instância da Bolsa de Valores (B3) e algumas entidades, como empresas, investidores e ativos, para simular o ambiente da bolsa.
Oferece uma interface de linha de comando para os usuários interagirem com o sistema, realizando ações como cadastrar-se, entrar, listar empresas, listar ações, comprar/vender ações, entre outras.

- Reseta Arquivos:

A função resetarArquivos é usada para limpar o conteúdo dos arquivos de dados simulados, reiniciando o estado do sistema.

- Fluxo de Execução:

O programa utiliza um loop do-while para apresentar um menu de opções ao usuário, permitindo que eles interajam com o sistema até optarem por encerrar.

- Observações:

Há uma mistura de manipulação de entrada do usuário usando Scanner e leitura/gravação de arquivos simulados para persistência de dados.
O código também usa assertivas (assert) em alguns lugares para verificar condições que o programador considera como verdadeiras. É importante observar que o uso de assertivas pode ser desativado em tempo de execução.