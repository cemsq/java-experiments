import java.io.*;

/*
 ID: cdma.861
 TASK: friday
 LANG: JAVA
 */
public class friday {

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

    public static int daysForMonth(int month, int year) {
        switch (month) {
            case 2:
                return isLeapYear(year)? 29 : 28;

            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;

            default: return 30;
        }
    }

    public static boolean isLeapYear(int year) {
        if (year % 100 == 0) {
            return year % 400 == 0;
        }

        return year % 4 == 0;
    }

    public static void main(String... args) throws IOException {
        IOHelper io = new IOHelper("friday");

        int N = Integer.parseInt(io.in.readLine());
        int END_YEAR = 1900 + N;
        int []weekDayCount = new int[7];

        int weekDay = 0;

        for (int year = 1900; year < END_YEAR; year++) {
            for (int month = 1; month <= 12; month++) {
                weekDayCount[weekDay % 7]++;

                weekDay += daysForMonth(month, year);
            }
        }

        for (int i=0; i < 7; i++) {
            if (i > 0) {
                io.out.print(" ");
            }
            io.out.print(weekDayCount[i]);
        }

        io.out.println();

        io.out.close();
    }
}
