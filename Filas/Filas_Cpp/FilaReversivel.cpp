#include <iostream>
#include <stdexcept>
#include <string>

class FilaException : public std::runtime_error {
public:
    explicit FilaException(const std::string& message) : std::runtime_error(message) {}
};

template <typename T>
class Fila {
public:
    virtual ~Fila() = default;
    virtual void enqueue(const T& dado) = 0;
    virtual T dequeue() = 0;
    virtual T peek() const = 0;
    virtual bool isEmpty() const = 0;
    virtual int size() const = 0;
    virtual void clear() = 0;
};

template <typename T>
class FilaReversivel : public Fila<T> {
private:
    T* array;           // Array dinâmico para armazenar os elementos  
    int capacity;      // Tamanho total alocado na memória
    int count;         // Quantidade atual de elementos (size)
    int head;          // Índice onde está o primeiro elemento
    int direction;     // 1 para frente (normal), -1 para trás (reversa)

    void resize(int new_capacity) {
        T* new_array = new T[new_capacity];
        
        for (int i = 0; i < count; ++i) {
            int current_idx = (head + i * direction) % capacity;
            if (current_idx < 0) current_idx += capacity; // Corrige módulo negativo
            
            new_array[i] = array[current_idx];
        }
        
        delete[] array;          
        array = new_array;       
        capacity = new_capacity; 
        head = 0;                
        direction = 1;           
    }

public:
    // Construtor
    FilaReversivel(int initial_capacity = 4) 
        : capacity(initial_capacity), count(0), head(0), direction(1) {
        array = new T[capacity];
    }

    // Destrutor
    ~FilaReversivel() override {
        delete[] array;
    }

    void enqueue(const T& dado) override {
        // Se estiver cheio, dobra a capacidade
        if (count == capacity) {
            resize(capacity * 2);
        }
        
        // Calcula o índice do final da fila
        int tail_idx = (head + count * direction) % capacity;
        if (tail_idx < 0) tail_idx += capacity;
        
        array[tail_idx] = dado;
        count++;
    }

    T dequeue() override {
        if (isEmpty()) throw FilaException("Erro: A fila esta vazia!");

        T dado = array[head]; // Salva o dado do início
        
        // Move o início um passo na direção atual
        head = (head + direction) % capacity;
        if (head < 0) head += capacity;
        
        count--;

        // Reduz o array pela metade se atingir 1/3 da capacidade (com limite mínimo)
        if (count > 0 && count <= capacity / 3 && capacity > 4) {
            resize(capacity / 2);
        }

        return dado;
    }

    T peek() const override {
        if (isEmpty()) throw FilaException("Erro: A fila esta vazia!");
        return array[head];
    }

    bool isEmpty() const override {
        return count == 0;
    }

    int size() const override {
        return count;
    }

    void clear() override {
        count = 0;
        head = 0;
        direction = 1;
        if (capacity > 4) resize(4); // Volta ao tamanho original
    }

    // O(1)
    void reverse() {
        if (count <= 1) return; // Fila vazia ou com 1 elemento já está invertida

        // 'head' (início) passa a ser o antigo 'tail' (fim)
        head = (head + (count - 1) * direction) % capacity;
        if (head < 0) head += capacity;

        // Inverte a direção de crescimento/leitura
        direction = -direction;
    }
};