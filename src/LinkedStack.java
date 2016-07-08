import java.util.*;

/**
 * Yulong Tan
 * 3.31.15
 *
 *
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

    // Adds all the contents from the other LinkedStack to this one. Appends to the bottom
    public void addAll(LinkedStack other) {
        this.addAll(other, false);
    }

    // Adds the contents from the other LinkedStack to this one,
    // and sorts it if the boolean passed in is true
    public void addAll(LinkedStack other, boolean sorted) {
        int size = other.size();
        for (int i = 0; i < size; i++) {
            E next = (E) other.pop();
            this.append(next);
            other.append(next);
        }
        if (sorted) {
            this.sort();
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

    

    // Returns true if the stack contains the given element and false otherwise
    public boolean contains(E e) {
        if (this.isEmpty()) {
            return false;
        } else {
            StackNode current = this.top;
            while (current != null) {
                if (current.data.equals(e)) {
                    return true;
                }
                current = current.next;
            }
            return false;
        }
    }

    // Returns true if this LinkedStack is equal to the given LinkedStack
    // and false otherwise.
    public boolean equals(LinkedStack other) {
        if (this.size != other.size) {
            return false;
        } else {
            StackNode current1 = this.top;
            StackNode current2 = other.top;
            while (current1 != null) {
                if (!current1.data.equals(current2.data)) {
                    return false;
                }
                current1 = current1.next;
                current2 = current2.next;
            }
            return true;
        }
    }

    // Returns the element at the bottom of the stack
    // Throws a NoSuchElementException if the stack is empty
    public E getBottom() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        return (E) this.bottom.data;
    }

    // Returns the element at the top of the stack
    // Throws a NoSuchElementException if the stack is empty
    public E getTop() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        return (E) this.top.data;
    }

    // Returns the index of the first occurrence of the given data
    // Returns -1 if given data is not in the stack
    public int indexOf(E e) {
        if (!this.contains(e)) {
            return -1;
        } else {
            int index = 0;
            if (this.top.data.equals(e)) {
                return 0;
            } else {
                StackNode current = this.top.next;
                while (current != null) {
                    index++;
                    if (current.data.equals(e)) {
                        current = null; // stops early
                    } else {
                        current = current.next;
                    }
                }
                return index;
            }
        }
    }

    // Returns true if the stack is empty and false otherwise
    public boolean isEmpty() {
        return this.size() == 0;
    }

    // Returns true if the stack is sorted (from top to bottom)
    public boolean isSorted() {
        if (this.isEmpty() || this.size == 1) {
            return true;
        } else {
            StackNode current = this.top;
            while (current.next != null) {
                if (((Comparable) current.data).compareTo(current.next.data) > 0) {
                    return false;
                }
                current = current.next;
            }
            return true;
        }
    }

    // Returns true if the stack contains no duplicates and false otherwise
    public boolean isUnique() {
        if (this.isEmpty() && this.size == 1) {
            return true;
        } else {
            Set<E> uniques = new HashSet<E>();
            for (int i = 0; i < this.size; i++) {
                E data = (E) this.nodeAt(i).data;
                if (uniques.contains(data)) {
                    return false;
                } else {
                    uniques.add(data);
                }
            }
            return true;
        }
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

    // Removes all duplicates from stack and returns the duplicates as a set.
    // Returns an empty set if there are no duplicates
    public Set<E> removeDuplicates() {
        Set<E> dupes = new TreeSet<>();
        if (this.size > 1) {
            for (int i = 0; i < this.size; i++) {
                E data = (E) this.nodeAt(i).data;
                if (dupes.contains(data)) {
                    this.remove(i);
                    i--;
                } else {
                    dupes.add(data);
                }
            }
        }
        return dupes;
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

            // Set the bottom after reversal
            StackNode position = this.top;
            for (int i = 0; i < this.size - 1; i++) {
                position = position.next;
            }
            this.bottom = position;
        }
    }

    public void shuffle() {
        if (this.size > 1) {
            Random r = new Random();
            LinkedStack<E> storage = new LinkedStack<>();
            storage.addAll(this);

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

    // Returns an ArrayList with the stack's elements in order of top to bottom
    public List<E> toArray() {
        List<E> list = new ArrayList<>();
        for (int i = 0; i < this.size; i++) {
            list.add((E) this.nodeAt(i).data);
        }
        return list;
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
