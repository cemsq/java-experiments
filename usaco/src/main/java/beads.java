import java.io.*;

/*
 ID: cdma.861
 TASK: beads
 LANG: JAVA
 */
public class beads {

    public static int count(String letters) {
        int color = 0;
        int count = 0;
        for (int pos = 0; pos < letters.length(); pos++) {
            if (color == 0) {
                color = letters.charAt(pos);
                color = (color == 'w')? 0: color;
            }

            char actual = letters.charAt(pos);
            if (color == actual || actual == 'w') {
                count++;
            } else {
                break;
            }
        }

        return count;
    }

    public static String reverse(String str) {
        char c[] = new char[str.length()];

        for (int i = 0; i<str.length(); i++) {
            c[i] = str.charAt(str.length() - 1 - i);
        }

        return String.valueOf(c);
    }

    public static void main(String... args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("beads.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("beads.out")));

        int max = 0;
        int total = Integer.parseInt(in.readLine());
        String letters = in.readLine();

        for (int point = 0; point < total; point++) {
            String str = letters.substring(point) + letters.substring(0, point);

            int count = count(str) + count(reverse(str));

            max = Math.max(max, count);
        }

        max = Math.min(max, total);

        out.println(max);
        out.close();
    }
}
