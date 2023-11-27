public class LinkedQueue<T> implements Queue<T> {
    private static class Node<T> {
        T data;
        Node<T> next;;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> front, rear;
    private int size;
    private final int maxSize = 5;

    public LinkedQueue(int i, Class<Integer> integerClass) {
        front = null;
        rear = null;
        size = 0;
    }

    @Override
    public void enqueue(T element) {
        if (!isFull()) {
            Node<T> newNode = new Node<>(element);
            if (isEmpty()) {
                front = newNode;
            } else {
                rear.next = newNode;
            }
            rear = newNode;
            size++;
        } else {
            System.out.println("Queue is full. Cannot enqueue element: " + element);
        }
    }

    @Override
    public T dequeue() {
        if (!isEmpty()) {
            T data = front.data;
            front = front.next;
            if (front == null) {
                rear = null;
            }
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
            return front.data;
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

