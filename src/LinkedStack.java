import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Yulong Tan
 * 3.31.15
 *
 * LinkedList implementation of a Stack. The front is the 'top'.
 */

public class LinkedStack<E> implements Iterable<E> {
    private StackNode front; // reference to the front
    private int size;        // size of stack

    // Constructs an empty LinkedStack
    public LinkedStack() {
        this.front = null;
        this.size = 0;
    }

    // The LinkedStackIterator class allows for the iteration over
    // a LinkedStack and removal of its elements
    private class LinkedStackIterator implements Iterator<E> {
        private LinkedStack stack;   // Stack to iterate over
        private int position;        // current position within the stack
        private boolean isRemovable; // whether or not it's okay to remove

        // Constructs an iterator over the given stack
        public LinkedStackIterator(LinkedStack stack) {
            this.stack = stack;
            this.position = 0;
            this.isRemovable = false;
        }

        // Returns the next element in the iteration. Throws a NoSuchElementException
        // when there is nothing next.
        public E next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            E data = (E) LinkedStack.this.nodeAt(this.position).data;
            this.position++;
            this.isRemovable = true;
            return data;
        }

        // Returns true if there is a next element and false otherwise
        public boolean hasNext() {
            return this.position < this.stack.size();
        }

        // Removes the last element returned by the iterator.
        // Throws an IllegalStateException if next() has not been called
        public void remove() {
            if (!this.isRemovable) {
                throw new IllegalStateException();
            }
            stack.remove(this.position - 1);
            this.position--;
            this.isRemovable = false;
        }
    }

    // Returns true if the stack is empty and false otherwise
    public boolean isEmpty() {
        return this.size() == 0;
    }

    // Returns an iterator over the stack
    public Iterator iterator() {
        return new LinkedStackIterator(this);
    }

    // Returns the node at the given index. Throws
    // an IndexOutOfBoundsException if the index >= the size
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

    // Returns the element at the top of the stack.
    // Throws a NoSuchElementException if stack is empty
    // Size is decreased.
    public E peek() {
        if (this.front == null) {
            throw new NoSuchElementException();
        }
        return (E) this.front.data;
    }

    // Returns and removes the element at the top of the stack.
    // Throws a NoSuchElementException if stack is empty.
    // Size is decreased.
    public E pop() {
        if (this.front == null) {
            throw new NoSuchElementException();
        }
        E data = (E) this.front.data;
        this.front = this.front.next;
        this.size--;
        return data;
    }

    // Puts the given element to the top of the stack.
    // Size is increased
    public void push(E e) {
        this.front = new StackNode(e, this.front);
        this.size++;
    }

    // Returns and removes the element at the first index. Throws
    // a NoSuchElementException if the stack is empty.
    // Size is decreased.
    public E remove() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.remove(0);
    }

    // Returns and removes the element at the given index. Throws
    // a NoSuchElementException if the stack is empty.
    // Size is decreased.
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

    // Returns the size of the stack
    public int size() {
        return this.size;
    }

    // Returns a string representation of the stack.
    // Elements are inside square brackets, separated by commas.
    // 'Top' and 'bottom' of the stack are marked
    public String toString() {
        if (this.front == null) {
            return "[]";
        } else {
            String result = "t[" + this.front.data;
            StackNode current = this.front.next;
            while (current != null) {
                result += ", " + current.data;
                current = current.next;
            }
            return result + "]b";
        }
    }
}
