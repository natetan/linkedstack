import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Yulong Tan
 * 3.31.15
 *
 * LinkedList implementation of a Stack. The front is the 'top'.
 */

public class LinkedStack<E> implements Iterable<E> {
    private StackNode front;
    private int size;

    public LinkedStack() {
        this.front = null;
        this.size = 0;
    }

    private class LinkedStackIterator implements Iterator<E> {
        private LinkedStack stack;
        private int position;
        private boolean isRemovable;

        public LinkedStackIterator(LinkedStack stack) {
            this.stack = stack;
            this.position = 0;
            this.isRemovable = false;
        }

        public E next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            E data = (E) LinkedStack.this.nodeAt(this.position).data;
            this.position++;
            this.isRemovable = true;
            return data;
        }

        public boolean hasNext() {
            return this.position < this.stack.size();
        }

        public void remove() {
            if (!this.isRemovable) {
                throw new IllegalStateException();
            }
            stack.remove(this.position - 1);
            this.position--;
            this.isRemovable = false;
        }
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public Iterator iterator() {
        return new LinkedStackIterator(this);
    }

    public StackNode nodeAt(int index) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        if (index == 0) {
            return this.front;
        } else {
            StackNode current = this.front;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current;
        }
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

    public E remove() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.remove(0);
    }

    public E remove(int index) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException("Index: "+ index);
        }
        E data;
        this.size--;
        if (index == 0) {
            data = (E) this.front.data;
            this.front = this.front.next;
            return data;
        } else {
            StackNode current = this.front;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            data = (E) current.next.data;
            current.next = current.next.next;
            return data;
        }
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
