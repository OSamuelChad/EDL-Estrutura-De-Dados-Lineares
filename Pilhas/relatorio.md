# Relatório de Atividade: Pilha Rubro-Negra
 
**Disciplina:** Estrutura de Dados  
**Atividade:** Implementação de Pilha Dupla com Alocação Dinâmica  
**Estrutura:** Pilha Rubro-Negra (duas pilhas em um único array)
 
---

## 1. Estrutura do Projeto
 
```
EDL-ESTRUTURA-DE-DADOS/
└── Pilhas/
    ├── Main.java             # Interface de usuário via menu
    ├── Pilha.java            # Interface genérica de Pilha
    └── PilhaRubroNegra.java  # Implementação da Pilha Rubro-Negra
```
 
---

## Estratégia de Crescimento do Array

```
Índice:  [ 0 ][ 1 ][ 2 ][ _ ][ _ ][ 5 ][ 6 ][ 7 ]
          ←── Vermelha ──→         ←── Preta ──→
          tVermelho = 2            tPreto = 5
```
 


## 3. Operações 

 
| Método              | Descrição                                                  |
|---------------------|------------------------------------------------------------|
| `pushVermelho(o)`   | Insere elemento no topo da Pilha Vermelha                  |
| `pushPreto(o)`      | Insere elemento no topo da Pilha Preta                     |
| `popVermelho()`     | Remove e retorna o topo da Pilha Vermelha                  |
| `popPreto()`        | Remove e retorna o topo da Pilha Preta                     |
| `topVermelho()`     | Consulta o topo da Pilha Vermelha sem remover              |
| `topPreto()`        | Consulta o topo da Pilha Preta sem remover                 |
| `isEmptyVermelho()` | Verifica se a Pilha Vermelha está vazia                    |
| `isEmptyPreto()`    | Verifica se a Pilha Preta está vazia                       |
| `getCapacidade()`   | Retorna a capacidade atual do array interno                |
| `imprimirArrayInterno()` | Exibe o array com cores ANSI (vermelho/preto)        |
 
## 4. Complexidade das Operações

| Operação       | Caso Médio | Caso Pior (com realocação) |
|----------------|------------|----------------------------|
| `push`         | O(1)       | O(n)                       |
| `pop`          | O(1)       | O(n)                       |
| `top`          | O(1)       | O(1)                       |
| `isEmpty`      | O(1)       | O(1)                       |
| Realocação     | —          | O(n)                       |
 
A realocação ocorre amortizadamente, de modo que o custo médio de `push` e `pop` ao longo de `n` operações é **O(1) amortizado**.

