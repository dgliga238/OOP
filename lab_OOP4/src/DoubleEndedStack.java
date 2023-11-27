import java.lang.reflect.Array;

public class DoubleEndedStack<T> implements Stack<T> {
    private int maxSize;
    private T[] stack;
    private int top = -1;

    @SuppressWarnings("unchecked")
    public DoubleEndedStack(int maxSize, Class<T> clazz) {
        this.maxSize = maxSize;
        this.stack = (T[]) Array.newInstance(clazz, maxSize);
    }

    @Override
    public void push(T value) {
        if (isFull()) {
            throw new RuntimeException("Stack is full");
        }
        stack[++top] = value;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return stack[top--];
    }

    @Override
    public T element() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return stack[top];
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public boolean isFull() {
        return top == maxSize - 1;
    }
}
