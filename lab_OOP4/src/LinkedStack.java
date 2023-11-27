public class LinkedStack<T> implements Stack<T> {
    private final int maxSize;
    private final Class<T> dataType;
    private Node<T> top;
    private int size;

    public LinkedStack(int maxSize, Class<T> dataType) {
        this.maxSize = maxSize;
        this.dataType = dataType;
        this.top = null;
        this.size = 0;
    }

    @Override
    public void push(T element) {
        if (!dataType.isInstance(element)) {
            throw new IllegalArgumentException("Element type is not the same as the specified data type");
        }
        if (size == maxSize) {
            throw new IllegalStateException("Stack is full");
        }
        Node<T> node = new Node<>(element);
        node.next = top;
        top = node;
        size++;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        T popped = top.data;
        top = top.next;
        size--;
        return popped;
    }

    @Override
    public T element() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return top.data;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == maxSize;
    }

    private static class Node<T> {
        private final T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }
}
