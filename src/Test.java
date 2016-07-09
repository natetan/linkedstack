import java.util.List;

/**
 * Created by Yulong on 3/31/2016.
 */
public class Test {

    public static void main(String[] params) {
        System.out.println("Generic first test");
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
        System.out.println();

        // Sort
        System.out.println("Testing sort");
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
        System.out.println();

        // Reverse
        System.out.println("Testing reverse");
        stack.reverse();
        System.out.println("Reversed stack: " + stack.toString());
        System.out.println();

        // Unique
        System.out.println("Testing isUnique");
        stack.push(5);
        System.out.println("Stack: " + stack.toString());
        System.out.println("Unique: " + stack.isUnique()); // false
        stack.pop();
        System.out.println("Stack: " + stack.toString());
        System.out.println("Unique: " + stack.isUnique()); // true
        System.out.println();

        // Top and Bottom
        System.out.println("Testing top and bottom");
        System.out.println("Top: " + stack.getTop());       // 8
        System.out.println("Bottom: " + stack.getBottom()); // 1
        System.out.println();

        // Equals
        System.out.println("Testing equals");
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
        System.out.println();

        // toArray
        System.out.println("Testing toArray");
        System.out.println("Stack: " + stack.toString());
        List<Integer> list = stack.toArray();
        System.out.println("List: t" + list.toString() + "b");
        System.out.println("Stack after: " + stack.toString());
        System.out.println();

        // contains and indexOf
        System.out.println("Testing contains and indexOf");
        System.out.println("Stack: " + stack.toString());
        System.out.println("Contains 3: " + stack.contains(3)); // true
        System.out.println("Contains 1: " + stack.contains(3)); // true
        System.out.println("Contains 2: " + stack.contains(2)); // false
        System.out.println("Index of 2: " + stack.indexOf(2));  // -1
        System.out.println("Index of 1: " + stack.indexOf(1));  // 4

        // Shuffle test
        System.out.println("Testing shuffle");
        System.out.println("Stack: " + stack.toString());
        System.out.println("Size: " + stack.size());
        stack.shuffle();
        System.out.println("Shuffled stack: " + stack.toString());
        System.out.println("Size: " + stack.size());

    }
}
