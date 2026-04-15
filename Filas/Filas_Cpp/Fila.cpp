template <typename T>
class Fila {
    public:
        //Destructor
        virtual ~Fila() = default;

        //Métodos puro virtual
        virtual void enqueue(const T& dado) = 0;
        virtual T dequeue() = 0;

        virtual T peek() const = 0;
        virtual bool isEmpty() const = 0;
        virtual int size() const = 0;

        virtual void clear() = 0;
}