/**
 * Created by Yulong on 3/31/2016.
 */
public class StackNode<E> {
    public E data;
    public StackNode next;

    public StackNode() {
        this(null);
    }

    public StackNode(E e) {
        this(e, null);
    }

    public StackNode(E e, StackNode next) {
        this.data = e;
        this.next = next;
    }
}
