# Relatório de Implementação: Fila com Reversão em $O(1)$

**Aluno(a):** [Samuel Chad Dantas da Silva]
**Disciplina:** Estrutura de Dados / Algoritmos 
**Data:** [14/04/2026]

---

## 1. Objetivo
O presente relatório documenta a implementação de uma Fila Reversível em Java. O principal desafio da atividade consistia em adicionar uma operação `reverse()` capaz de inverter a fila em tempo constante $O(1)$, além de gerenciar a memória dinamicamente através de um array circular (dobrando a capacidade ao encher e reduzindo à metade ao atingir 1/3 de ocupação).

## 2. Decisões Arquiteturais

### 2.1. Abandono da Abordagem com Nós (`No<T>`)
Inicialmente, considerei reutilizar uma estrutura de Lista Encadeada Simples utilizando uma classe `No<T>`. Contudo, ao analisar os requisitos da atividade, concluí que essa abordagem inviabilizaria o projeto por dois motivos:
1. **Requisito de Reversão:** Em uma lista encadeada simples, inverter a lógica de remoção (fazer o `dequeue` remover do final) exigiria percorrer a lista até o penúltimo elemento, resultando em uma complexidade de tempo $O(N)$, o que fere a exigência do $O(1)$.
2. **Requisito de Array Circular:** O enunciado exigia explicitamente o controle de "capacidade", "duplicação" e "redução" (1/3). Listas encadeadas crescem dinamicamente nó a nó e não possuem o conceito de capacidade total predefinida.

Portanto, optei pela implementação estrita utilizando um **Array Dinâmico Circular** (`T[] array`), garantindo controle absoluto sobre os índices e a alocação de memória.

### 2.2. Tipagem Genérica em Java (Type Erasure)
Devido ao *Type Erasure* da linguagem Java, não é possível instanciar arrays genéricos diretamente (ex: `new T[capacity]`). Para contornar essa limitação técnica, o array foi inicializado como um array de `Object` e sofreu um *cast* seguro para `T[]` (`(T[]) new Object[capacity]`), encapsulando os avisos do compilador com `@SuppressWarnings("unchecked")`.

## 3. A Lógica da Reversão em $O(1)$

Para alcançar a complexidade de tempo $O(1)$ no método `reverse()`, utilizei uma abordagem baseada em ponteiros virtuais e direção vetorial, evitando a movimentação física dos dados na memória.

Foram criadas três variáveis de controle fundamentais:
* `head`: Aponta para o índice do primeiro elemento válido da fila.
* `count`: Armazena a quantidade atual de elementos.
* `direction`: Um multiplicador vetorial (`1` para crescimento normal, `-1` para crescimento reverso).

**Como funciona o `reverse()`?**
Quando o método é chamado, apenas duas operações matemáticas ocorrem:
1. O índice `head` é recalculado para apontar para onde ficava o final da fila: 
   `head = (head + (count - 1) * direction) % capacity`
2. O sentido de leitura é invertido: 
   `direction = -direction`

Como não há nenhum laço de repetição (`for` ou `while`) movimentando os dados no array, a operação executa um número fixo e constante de instruções, caracterizando matematicamente o tempo **$O(1)$**.

## 4. Gerenciamento do Array Circular

O cálculo de qualquer índice (seja para inserir ou remover) foi generalizado pela fórmula matemática do resto da divisão (`%`):
`indice_alvo = (head + passos * direction) % capacity`

Como no Java o operador `%` pode retornar valores negativos, adicionei uma correção de módulo (`if (indice_alvo < 0) indice_alvo += capacity;`) para garantir que o array se comporte de forma perfeitamente circular, dando "a volta" para os últimos índices quando necessário.

### 4.1. Redimensionamento e Garbage Collector
* **Expansão:** Ao tentar inserir um elemento e constatar que `count == capacity`, o método `resize()` é acionado para dobrar o tamanho do array (`capacity * 2`), copiando os elementos de forma linear e normalizando a direção.
* **Redução:** No método `dequeue()`, se a quantidade de elementos cair para 1/3 da capacidade total (respeitando um limite mínimo de 4 espaços), o array é reduzido pela metade (`capacity / 2`).
* **Memory Loitering:** Uma boa prática implementada no `dequeue()` foi atribuir `null` à posição do array da qual o elemento acabou de sair (`array[head] = null`). Isso garante que o *Garbage Collector* do Java possa liberar o objeto da memória, evitando vazamentos.

## 5. Análise de Complexidade Final

| Operação | Complexidade de Tempo | Justificativa |
| :--- | :--- | :--- |
| `enqueue(T)` | $O(1)$ amortizado | Acesso direto via índice. Torna-se $O(N)$ apenas nos raros momentos de redimensionamento (amortizado). |
| `dequeue()` | $O(1)$ amortizado | Acesso direto e avanço do ponteiro `head`. Torna-se $O(N)$ apenas ao reduzir o array (amortizado). |
| `peek()` | $O(1)$ | Retorno direto da posição `array[head]`. |
| `reverse()` | **$O(1)$** | Apenas cálculos matemáticos simples de atribuição e mudança de sinal, sem laços de repetição. |

## 6. Conclusão
A implementação cumpriu todos os requisitos propostos. A classe `FilaReversivel<T>` demonstrou como variáveis de controle de estado (como a mudança de direção de leitura) podem substituir a necessidade de processamento massivo de dados em memória, resultando em algoritmos altamente otimizados e elegantes.