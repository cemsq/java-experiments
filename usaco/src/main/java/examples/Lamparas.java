package examples;

import java.util.*;

/**
 *
 */
interface Switchable {
    public void interact();
}

class Lampara implements Switchable {

    private boolean on;

    public Lampara(char on) {
        this.on = on == '1';
    }

    @Override
    public void interact() {
        on = !on;
    }

    @Override
    public String toString() {
        return on? "1" : "0";
    }

}
class Switch implements Switchable {
    private List<Lampara> list = new ArrayList<>();
    private boolean change;

    public Switch(int count) {
        this.change = (count % 2) != 0;
    }

    public void add(Lampara lampara) {
        list.add(lampara);
    }

    @Override
    public void interact() {
        if (change) {
            for (Lampara lampara : list) {
                lampara.interact();
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        for (Lampara l : list) {
            sb.append(l.toString());
        }

        return sb.toString();
    }
}

enum SwitchType {
    ALL(0),
    PAR(1),
    IMPAR(2),
    MULT3(3);

    private int index;

    SwitchType(int index) {
        this.index = index;
    }

    public int index() {
        return index;
    }
}

public class Lamparas {

    public static void main(String ...args) {
        String input1 = "2 3 1 4";
        String input2 = "0110";

        List<Switch> controls = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(input1);
        while (st.hasMoreTokens()) {
            Switch s = new Switch(Integer.parseInt(st.nextToken()));
            controls.add(s);
        }

        for (int i = 0; i < input2.length(); i++) {
            char c = input2.charAt(i);
            Lampara l = new Lampara(c);

            controls.get(SwitchType.ALL.index()).add(l);
            if (i % 2 == 0) {
                controls.get(SwitchType.PAR.index()).add(l);
            } else {
                controls.get(SwitchType.IMPAR.index()).add(l);
            }
            if ((i - 1) % 3 == 0) {
                controls.get(SwitchType.MULT3.index()).add(l);
            }
        }

        Switch all = controls.get(SwitchType.ALL.index());
        Map<String, Object> possible = new HashMap<>();
        for (Switch s : controls) {
            s.interact();
            possible.put(all.toString(), null);
        }

        for (String value : possible.keySet()) {
            System.out.println(value);
        }
    }
}
