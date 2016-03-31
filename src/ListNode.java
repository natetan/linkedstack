/**
 * Created by Yulong on 3/31/2016.
 */
public class ListNode<E> {
    private E data;
    private ListNode next;

    public ListNode() {
        this(null);
    }

    public ListNode(E e) {
        this(e, null);
    }

    public ListNode(E e, ListNode next) {
        this.data = e;
        this.next = next;
    }
}
