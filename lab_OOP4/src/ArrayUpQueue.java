public class ArrayUpQueue<T> implements Queue<T> {
    private final int maxSize = 5;
    private final T[] elements;
    private int front, rear, size;

    public ArrayUpQueue(int i, Class<String> stringClass) {
        elements = (T[]) new Object[maxSize];
        front = 0;
        rear = -1;;
        size = 0;
    }

    @Override
    public void enqueue(T element) {
        if (!isFull()) {
            rear = (rear + 1) % maxSize;
            elements[rear] = element;
            size++;
        } else {
            System.out.println("Queue is full. Cannot enqueue element: " + element);
        }
    }

    @Override
    public T dequeue() {
        if (!isEmpty()) {
            T data = elements[front];
            front = (front + 1) % maxSize;
            size--;
            return data;
        } else {
            System.out.println("Queue is empty. Cannot dequeue element.");
            return null;
        }
    }

    @Override
    public T peek() {
        if (!isEmpty()) {
            return elements[front];
        } else {
            System.out.println("Queue is empty. Cannot peek.");
            return null;
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == maxSize;
    }
}

