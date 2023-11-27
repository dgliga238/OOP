public class ArrayUpStack<T> implements Stack<T> {
    private final int maxSize;
    private final Class<T> dataType;
    private Object[] data;
    private int size;

    public ArrayUpStack(int maxSize, Class<T> dataType) {
        this.maxSize = maxSize;
        this.dataType = dataType;
        this.data = new Object[maxSize];
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
        data[size++] = element;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return dataType.cast(data[--size]);
    }

    @Override
    public T element() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return dataType.cast(data[size - 1]);
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