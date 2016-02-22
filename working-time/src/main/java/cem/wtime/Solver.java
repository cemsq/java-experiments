package cem.wtime;

import org.joda.time.LocalTime;
import org.mockito.cglib.core.Local;

import javax.swing.*;

public class Solver {

    private JTextField from;
    private JTextField to;
    private JTextField pause;
    private JTextField worked;

    public Solver(JTextField from, JTextField to, JTextField pause, JTextField worked) {
        this.from = from;
        this.to = to;
        this.pause = pause;
        this.worked = worked;
    }

    public String solve() {
        WorkingTime from = createWorkingTime(this.from);
        WorkingTime to = createWorkingTime(this.to);
        WorkingTime pause = createWorkingTime(this.pause);

        return to.minus(from).minus(pause).toString();
    }

    private WorkingTime createWorkingTime(JTextField textField) {
        try {
            filter(textField);
            return new WorkingTime(LocalTime.parse(textField.getText()));

        } catch (IllegalArgumentException  e) {
            textField.setText("00:00");
            return new WorkingTime(LocalTime.fromMillisOfDay(0));
        }
    }

    public void filter(JTextField text) {
        String str = text.getText();

        int pos = str.indexOf(":");
        if (pos < 0) {
            switch (str.length()) {
                case 1: str = String.format("00:0%s", str);
                    break;

                case 2: str = String.format("00:%s", str);
                    break;

                case 3: str = String.format("0%s:%s", str.substring(0,1), str.substring(1));
                    break;

                case 4: str = String.format("%s:%s", str.substring(0,2), str.substring(2));
                    break;

                default: str = "00:00";
            }
        }

        text.setText(str);
    }
}

