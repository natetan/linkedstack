import java.util.NoSuchElementException;

/**
 * Yulong Tan
 * 3.31.15
 *
 * LinkedList implementation of a Stack. The front is the 'top'.
 */

public class LinkedStack<E> {
    private StackNode front;
    private int size;

    public LinkedStack() {
        this.front = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public E peek() {
        if (this.front == null) {
            throw new NoSuchElementException();
        }
        return (E) this.front.data;
    }

    public E pop() {
        if (this.front == null) {
            throw new NoSuchElementException();
        }
        E data = (E) this.front.data;
        this.front = this.front.next;
        this.size--;
        return data;
    }

    public void push(E e) {
        this.front = new StackNode(e, this.front);
        this.size++;
    }

    public int size() {
        return this.size;
    }


    public String toString() {
        if (this.front == null) {
            return "[]";
        } else {
            String result = "[" + this.front.data;
            StackNode current = this.front.next;
            while (current != null) {
                result += ", " + current.data;
                current = current.next;
            }
            return result + "]";
        }
    }
}
