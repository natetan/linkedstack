import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Yulong Tan
 * 3.31.15
 * <p>
 * LinkedList implementation of a Stack. Last in, first out structure (LIFO)
 */

public class LinkedStack<E> implements Iterable<E> {
    private StackNode top;    // reference to the top
    private StackNode bottom; // reference to the bottom
    private int size;         // size of stack

    // Constructs an empty LinkedStack
    public LinkedStack() {
        this.top = null;
        this.bottom = this.top;
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

    // Adds given element to the bottom of the stack. If stack is empty, it creates
    // a node at the top. Size is increased
    private void append(E e) {
        if (this.isEmpty()) {
            this.makeFirstNode(e);
        } else {
            this.bottom.next = new StackNode(e);
            this.bottom = this.bottom.next;
        }
        this.size++;
    }

    // Returns true if the stack is empty and false otherwise
    public boolean isEmpty() {
        return this.size() == 0;
    }

    public boolean isUnique() {
        
    }

    // Returns an iterator over the stack
    public Iterator iterator() {
        return new LinkedStackIterator(this);
    }

    // Creates the first node if stack is empty
    private void makeFirstNode(E e) {
        this.top = new StackNode(e);
        this.bottom = this.top;
    }

    // Returns the node at the given index. Throws
    // an IndexOutOfBoundsException if the index >= the size
    public StackNode nodeAt(int index) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        if (index == 0) {
            return this.top;
        } else {
            StackNode current = this.top;
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
        if (this.top == null) {
            throw new NoSuchElementException();
        }
        return this.peek(0);
    }

    public E peek(int index) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        return (E) this.nodeAt(index).data;
    }

    // Returns and removes the element at the top of the stack.
    // Throws a NoSuchElementException if stack is empty.
    // Size is decreased.
    public E pop() {
        if (this.top == null) {
            throw new NoSuchElementException();
        }
        E data = (E) this.top.data;
        this.top = this.top.next;
        this.size--;
        return data;
    }

    // Puts the given element to the top of the stack.
    // Size is increased
    public void push(E e) {
        if (this.isEmpty()) {
            this.makeFirstNode(e);
        } else {
            this.top = new StackNode(e, this.top);
        }
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
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        E data;
        this.size--;
        if (index == 0) {
            data = (E) this.top.data;
            this.top = this.top.next;
            return data;
        } else if (index == this.size - 1) {
            StackNode current = this.top;
            for (int i = 0; i < this.size - 2; i++) {
                current = current.next;
            }
            data = (E) current.next.data;
            current.next = null;
            this.bottom = current;
            return data;
        } else {
            StackNode current = this.top;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            data = (E) current.next.data;
            current.next = current.next.next;
            return data;
        }
    }

    // Reverses the stack
    public void reverse() {
        if (this.top != null && this.top.next != null) {
            StackNode current = this.top;
            StackNode prev = null;
            StackNode next = null;
            while (current != null) {
                next = current.next;
                current.next = prev;
                prev = current;
                current = next;
            }
            this.top = prev;
        }
    }

    // Returns the size of the stack
    public int size() {
        return this.size;
    }

    // Sorts the stack from the top to the bottom
    public void sort() {
        if (this.size > 1) {
            int size1 = this.size / 2;
            int size2 = this.size - size1;
            LinkedStack half1 = new LinkedStack();
            LinkedStack half2 = new LinkedStack();
            for (int i = 0; i < size1; i++) {
                half1.append(this.pop());
            }
            for (int i = 0; i < size2; i++) {
                half2.append(this.pop());
            }
            half1.sort();
            half2.sort();
            this.mergeSort(this, half1, half2);
        }
    }

    // Performs the merge sort algorithm
    private void mergeSort(LinkedStack result, LinkedStack half1, LinkedStack half2) {
        while (!half1.isEmpty() && !half2.isEmpty()) {
            if (((Comparable) half1.peek()).compareTo(half2.peek()) <= 0) {
                result.append(half1.pop());
            } else {
                result.append(half2.pop());
            }
        }
        while (!half1.isEmpty()) {
            result.append(half1.pop());
        }
        while (!half2.isEmpty()) {
            result.append(half2.pop());
        }
    }

    // Returns a string representation of the stack.
    // Elements are inside square brackets, separated by commas.
    // 'Top' and 'bottom' of the stack are marked
    public String toString() {
        if (this.top == null) {
            return "[]";
        } else {
            String result = "t[" + this.top.data;
            StackNode current = this.top.next;
            while (current != null) {
                result += ", " + current.data;
                current = current.next;
            }
            return result + "]b";
        }
    }
}
