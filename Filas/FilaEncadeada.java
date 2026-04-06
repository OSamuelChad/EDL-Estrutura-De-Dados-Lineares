public class FilaEncadeada<T> implements Fila<T> {

    private No<T> frente;
    private No<T> fim;
    private int tamanho;

    public FilaEncadeada() {
        this.frente = null;
        this.fim = null;
        this.tamanho = 0;
    }

    @Override
    public void enqueue(T dado) {
        No<T> novo = new No<>(dado);

        if (isEmpty()) {
            frente = novo;
        } else {
            fim.proximo = novo;
        }

        fim = novo;
        tamanho++;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new FilaException("Fila vazia: não é possível remover.");
        }

        T dadoRemovido = frente.dado;
        frente = frente.proximo;

        if (frente == null) {
            fim = null;
        }

        tamanho--;
        return dadoRemovido;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new FilaException("Fila vazia: não há elemento na frente.");
        }
        return frente.dado;
    }

    @Override
    public boolean isEmpty() {
        return frente == null;
    }

    @Override
    public int size() {
        return tamanho;
    }

    @Override
    public void clear() {
        frente   = null;
        fim      = null;
        tamanho  = 0;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "Fila: []";

        StringBuilder sb = new StringBuilder("Fila (frente → fim): [");
        No<T> atual = frente;

        while (atual != null) {
            sb.append(atual.dado);
            if (atual.proximo != null) sb.append(", ");
            atual = atual.proximo;
        }

        return sb.append("]").toString();
    }
}