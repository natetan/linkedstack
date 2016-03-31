import java.util.NoSuchElementException;

/**
 * Yulong Tan
 * 3.31.15
 * <p>
 * LinkedList implementation of a Stack. The front is the 'top'.
 */

public class LinkedStack<E> {
    private ListNode front;
    private int count;

    public LinkedStack() {
        this.front = null;
        this.count = 0;
    }

    public void push(E e) {
        this.front = new ListNode(e, this.front);
        this.count++;
    }

    public E pop() {
        if (this.front == null) {
            throw new NoSuchElementException();
        }
        E data = (E) this.front.data;
        this.front = this.front.next;
        this.count--;
        return data;
    }

    public E peek() {
        if (this.front == null) {
            throw new NoSuchElementException();
        }
        return (E) this.front.data;
    }

    public int size() {
        return this.count;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public String toString() {
        if (this.front == null) {
            return "[]";
        } else {
            String result = "[" + this.front.data;
            ListNode current = this.front.next;
            while (current != null) {
                result += ", " + current.data;
                current = current.next;
            }
            return result + "]";
        }
    }
}
