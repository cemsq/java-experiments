package uva;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Score {

    public static void main(String ...args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int count = 0;
        int total = 0;
        int N = Integer.parseInt(in.readLine().trim());
        while (N > 0) {
            char c = (char)in.read();
            if (c == '\n') {
                System.out.println(total);
                count = 0;
                total = 0;
                N--;
            } else {
                c = Character.toLowerCase(c);
                if (c == 'o') {
                    count++;
                    total += count;
                } else {
                    count = 0;
                }
            }
        }
    }
}
