import java.io.*;
import java.util.*;

/*
 ID: cdma.861
 TASK: gift1
 LANG: JAVA
 */
public class gift1 {

    public static class Person {
        private Integer index;
        private String name;
        private int amount;

        public Person(String name, int index) {
            this.name = name;
            this.index = index;
        }

        public void addMoney(int amount) {
            this.amount += amount;
        }

        public int give(int initialAmount, int n) {
            int quantity;
            if (n == 0) {
                quantity = 0;
            } else {
                quantity = initialAmount / n;
            }

            addMoney(-(quantity*n));

            return quantity;
        }

        @Override
        public String toString() {
            return String.format("%s %s", name, amount);
        }
    }

    public static Comparator<Person> comparator = new Comparator<Person>() {
        @Override
        public int compare(Person o1, Person o2) {
            return o1.index.compareTo(o2.index);
        }
    };

    public static class IOHelper {
        private BufferedReader in;
        private PrintWriter out;

        public IOHelper(String name) throws IOException {
            in = new BufferedReader(new FileReader(name + ".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(name + ".out")));
        }

        public BufferedReader in() {
            return in;
        }
        public PrintWriter out() {
            return out;
        }
    }

    public static void main(String... args) throws IOException {
        IOHelper io = new IOHelper("gift1");

        int p = Integer.parseInt(io.in.readLine());
        Map<String, Person> persons = new HashMap<String, Person>();
        for (int i = 0; i < p; i++) {
            Person person = new Person(io.in.readLine(), i);
            persons.put(person.name, person);
        }

        for (int i = 0; i < p; i++) {
            Person giver = persons.get(io.in.readLine());
            StringTokenizer st = new StringTokenizer(io.in.readLine());

            int amount = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            int quantity = giver.give(amount, n);
            for (int k = 0; k < n; k++) {
                Person receiver = persons.get(io.in.readLine());
                receiver.addMoney(quantity);
            }
        }

        List<Person> list = new ArrayList<Person>(persons.values());
        Collections.sort(list, comparator);

        for (Person ps : list) {
            io.out.println(ps);
        }
        io.out.close();
    }
}
