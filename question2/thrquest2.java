import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Stand {
    String owner;
    public Stand(String owner) { this.owner = owner; }
    public boolean check(String name) { return false; }
}

class Jotaro extends Stand {
    public Jotaro() { super("Jotaro"); }
    @Override public boolean check(String name) { return name.length() <= 3; } // [cite: 71]
}

class Okuyasu extends Stand {
    public Okuyasu() { super("Okuyasu"); }
    @Override public boolean check(String name) {
        for (int i = 0; i < name.length() - 1; i++) {
            if (name.charAt(i) == name.charAt(i+1)) return true; // [cite: 72]
        }
        return false;
    }
}

class Koichi extends Stand {
    public Koichi() { super("Koichi"); }
    @Override public boolean check(String name) {
        int count = 0;
        for (char c : name.toLowerCase().toCharArray()) {
            if ("aeiou".indexOf(c) != -1) count++;
        }
        return count >= 3; // [cite: 73]
    }
}

class Rohan extends Stand {
    public Rohan() { super("Rohan"); }
    @Override public boolean check(String name) {
        return name.equals(new StringBuilder(name).reverse().toString()); // [cite: 74]
    }
}

public class thrquest2 {
    public static void main(String[] args) {
        // Contoh Test Case Koichi
        solve("Koichi", "john paul mary ian leo samuel lan roy aaron");
    }

    public static void solve(String standOwner, String namesInput) {
        Stand stand;
        if (standOwner.equals("Jotaro")) stand = new Jotaro();
        else if (standOwner.equals("Okuyasu")) stand = new Okuyasu();
        else if (standOwner.equals("Koichi")) stand = new Koichi();
        else if (standOwner.equals("Rohan")) stand = new Rohan();
        else return;

        Queue<String> queue = new LinkedList<>(Arrays.asList(namesInput.split(" "))); // [cite: 68]
        Stack<String> arrested = new Stack<>();

        while (!queue.isEmpty()) {
            String name = queue.poll();
            if (stand.check(name)) arrested.push(name);
        }

        System.out.println(standOwner + (arrested.isEmpty() ? " exposed no one." : " exposed someone!"));
        System.out.println("Arrested: " + arrested);
    }
}