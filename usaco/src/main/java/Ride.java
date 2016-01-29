import java.io.*;
import java.util.StringTokenizer;

/*
 ID: cdma.861
 TASK: ride
 LANG: JAVA
 */
public class ride {

    public static int compute(String name) {
        int A = 'A' - 1;

        int x = 1;
        for (char c : name.toCharArray()) {
            x *= c - A;
        }

        return x % 47;
    }

    public static void main(String... args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("ride.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());

        // Get line, break into tokens
        String group = st.nextToken();

        st = new StringTokenizer(f.readLine());
        String name = st.nextToken();

        out.println(compute(group) == compute(name)? "GO" : "STAY");

        out.close();

        System.exit(0);
    }
}
