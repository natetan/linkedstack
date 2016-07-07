import java.util.List;

/**
 * Created by Yulong on 3/31/2016.
 */
public class Test {

    public static void main(String[] params) {
        LinkedStack<Integer> stack = new LinkedStack<>();
        stack.push(5);
        stack.push(3);
        stack.push(2);
        System.out.println("First iteration: " + stack.toString()); // 2 3 5
        System.out.println("Size: " + stack.size());
        int x = stack.pop();
        System.out.println("Removed top: " + stack.toString()); // 3 5
        System.out.println("Removed: " + x);
        int y = stack.remove(stack.size() - 1); // 5
        System.out.println("Removed last element: " + stack.toString()); // 3
        System.out.println("Removed: " + y);
        System.out.println("Size: " + stack.size());

        // Sort

        stack.push(5);
        stack.push(1);
        stack.push(8);
        stack.push(6);
        System.out.println("Stack: " + stack.toString());
        System.out.println("Sorted: " + stack.isSorted()); // false
        System.out.println("Size: " + stack.size());
        stack.sort();
        System.out.println("Sorted stack: " + stack.toString());
        System.out.println("Sorted: " + stack.isSorted()); // true
        System.out.println("Size: " + stack.size());

        // Reverse
        stack.reverse();
        System.out.println("Reversed stack: " + stack.toString());

        // Unique
        stack.push(5);
        System.out.println("Stack: " + stack.toString());
        System.out.println("Unique: " + stack.isUnique()); // false
        stack.pop();
        System.out.println("Stack: " + stack.toString());
        System.out.println("Unique: " + stack.isUnique()); // true

        System.out.println("Top: " + stack.getTop());       // 8
        System.out.println("Bottom: " + stack.getBottom()); // 1

        // Equals
        LinkedStack<Integer> s2 = new LinkedStack<>();
        int position = 0;
        for (int i : stack) {
            s2.push((Integer)stack.nodeAt(position).data);
            position++;
        }
        System.out.println("Stack: " + stack.toString());
        System.out.println("Stack2: " + s2.toString());
        System.out.println("Equals: " + stack.equals(s2)); // false
        s2.reverse();
        System.out.println("Stack: " + stack.toString());
        System.out.println("Stack2: " + s2.toString());
        System.out.println("Equals: " + stack.equals(s2)); // true

        // toArray
        System.out.println("Stack: " + stack.toString());
        List<Integer> list = stack.toArray();
        System.out.println("List: t" + list.toString() + "b");
        System.out.println("Stack after: " + stack.toString());

    }
}
