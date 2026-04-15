public class FilaReversivel<T> implements Fila<T> {
    private T[] array;          
    private int capacity;       
    private int count;          
    private int head;          
    private int direction;      

    // Construtor
    @SuppressWarnings("unchecked")
    public FilaReversivel() {
        this.capacity = 4; 
        this.count = 0;
        this.head = 0;
        this.direction = 1;
        this.array = (T[]) new Object[capacity];
    }

    // Método privado para dobrar ou reduzir o array
    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        T[] newArray = (T[]) new Object[newCapacity];
        
        for (int i = 0; i < count; i++) {
            int currentIdx = (head + i * direction) % capacity;
            if (currentIdx < 0) currentIdx += capacity;
            
            newArray[i] = array[currentIdx];
        }
        
        array = newArray;
        capacity = newCapacity;
        head = 0;
        direction = 1; 
    }

    @Override
    public void enqueue(T dado) {
        // Regra de duplicação
        if (count == capacity) {
            resize(capacity * 2);
        }
        
        int tailIdx = (head + count * direction) % capacity;
        if (tailIdx < 0) tailIdx += capacity;
        
        array[tailIdx] = dado;
        count++;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new FilaException("Erro: A fila está vazia!");
        }

        T dado = array[head];
        array[head] = null; 
        
        head = (head + direction) % capacity;
        if (head < 0) head += capacity;
        
        count--;

        // Regra de redução (1/3 da capacidade)
        if (count > 0 && count <= capacity / 3 && capacity > 4) {
            resize(capacity / 2);
        }

        return dado;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new FilaException("Erro: A fila está vazia!");
        }
        return array[head];
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public void clear() {
        for (int i = 0; i < count; i++) {
            int currentIdx = (head + i * direction) % capacity;
            if (currentIdx < 0) currentIdx += capacity;
            array[currentIdx] = null;
        }
        count = 0;
        head = 0;
        direction = 1;
        if (capacity > 4) {
            resize(4);
        }
    }

    public void reverse() {
        if (count <= 1) return;

        // Inicio == Fim
        head = (head + (count - 1) * direction) % capacity;
        if (head < 0) head += capacity;

        // Inverte a direção da fila
        direction = -direction;
    }
}