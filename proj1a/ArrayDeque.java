public class ArrayDeque<T> {

    public T[] array;
    public int size;
    public int front;
    public int rear;
    public int length;

    public ArrayDeque() {
        array = (T[]) new Object[8];
        size = 0;
        length = 8;
        front = 0;
        rear = 0;
    }

    public void change(int length) {
        T[] newArray = (T[]) new Object[length];
        int ptr = front;
        int newPtr = front;
        while (ptr != rear) {
            newArray[newPtr] = array[ptr];
            ptr = plusOne(ptr, this.length);
            newPtr = plusOne(newPtr, length);
        } this.array = newArray;
        this.rear = newPtr;
    }

    private int plusOne(int index, int length) {
        if (index == length - 1) {
            return 0;
        } else {
            return index + 1;
        }
    }

    private int minusOne(int index) {
        if (index == 0) {
            return length - 1;
        } else {
            return index - 1;
        }
    }

    private void enlarge() {
        change(length * 2);
    }

    private void shrink() {
        change(length / 2);
    }

    public void addFirst(T item) {
        if (size == length) {
            enlarge();
        } front = minusOne(front);
        array[front] = item;
        size++;
    }

    public void addLast(T item) {
        if (size == length) {
            enlarge();
        } array[rear] = item;
        rear = plusOne(rear, length);
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int ptr = front;
        while (ptr != rear) {
            System.out.print(array[ptr] + " ");
            ptr = plusOne(ptr, length);
        }
    }

    public T removeFirst() {
        if (size >= 16 && length / size >= 4) {
            shrink();
        } if (size == 0) {
            return null;
        } T ret = array[front];
        front = plusOne(front, length);
        size--;
        return ret;
    }

    public T removeLast() {
        if (size >= 16 && length / size >= 4) {
            shrink();
        } if (size == 0) {
            return null;
        } rear = minusOne(rear);
        T ret = array[rear];
        size--;
        return ret;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        } int ptr = front;
        for (int i = 0; i < index; i++) {
            ptr = plusOne(ptr, length);
        } return array[ptr];
    }
}
