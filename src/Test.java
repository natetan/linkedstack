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
        System.out.println("Size: " + stack.size());

        // Sort

        stack.push(5);
        stack.push(1);
        stack.push(8);
        stack.push(6);
        System.out.println("Stack: " + stack.toString());
        stack.sort();
        System.out.println("Sorted stack: " + stack.toString());
    }
}
