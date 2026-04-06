public interface Fila<T> {
    void enqueue(T dado);
    T dequeue();
    T peek();
    boolean isEmpty();
    int size();
    void clear();
}