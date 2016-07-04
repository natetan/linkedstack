/**
 *  Yulong Tan
 *  3.31.16
 *
 *  Node class for LinkedStack
 */

public class StackNode<E> {
    public E data;         // generic data for storage
    public StackNode next; // reference to the next node

    // Constructs a StackNode with null as its data
    public StackNode() {
        this(null);
    }

    // Constructs a StackNode with the given data
    public StackNode(E e) {
        this(e, null);
    }

    // Constructs a StackNode with the given data and its next node
    public StackNode(E e, StackNode next) {
        this.data = e;
        this.next = next;
    }
}
