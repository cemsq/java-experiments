import java.io.*;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

/*
 ID: cdma.861
 TASK: milk2
 LANG: JAVA
 */
class Block {
    private int ini;
    private int end;

    public Block(int ini, int end) {
        this.ini = ini;
        this.end = end;
    }

    public Block compact(Block b) {
        Block left = ini <= b.ini? this : b;
        Block right = ini > b.ini? this : b;

        int minL = left.ini;
        int maxL = (right.ini <= left.end) ? Math.max(right.end, left.end) : -1;

        return maxL != -1? new Block(minL, maxL) : null;
    }

    public int getIni() {
        return ini;
    }

    public int getEnd() {
        return end;
    }

    public int size() {
        return end - ini;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Block)) {
            return false;
        }
        Block block = (Block) o;

        return ini == block.ini && end == block.end;
    }
}

class BlockSet {
    private SortedSet<Block> list = new TreeSet<Block>(comparator);
    private final static Comparator<Block> comparator = new Comparator<Block>() {
        @Override
        public int compare(Block o1, Block  o2) {
            if (o1.equals(o2)) {
                return 0;
            }

            if (o1.getIni() < o2.getIni()) {
                return -1;
            }
            if (o1.getIni() == o2.getIni()) {
                if (o1.getEnd() < o2.getEnd()) {
                    return -1;
                } else return 1;
            }

            return 1;
        }
    };

    public boolean add(Block block) {
        Block compacted = null;
        Block toRemove = null;

        for (Block that : list) {
            compacted = that.compact(block);
            if (compacted != null) {
                toRemove = that;
                break;
            }
        }

        if (compacted != null) {
            list.remove(toRemove);
            return this.add(compacted);
        } else {
            return list.add(block);
        }
    }

    public SortedSet<Block> list() {
        return list;
    }
}

public class milk2 {

    public static void main(String ...args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("milk2.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));

        BlockSet set = new BlockSet();
        int N = Integer.parseInt(in.readLine());
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int ini = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            set.add(new Block(ini, end));
        }


        int mjt = 0;
        int mft = 0;
        Block last = null;
        for (Block that : set.list()) {
            mjt = Math.max(mjt, that.size());

            if (last != null) {
                mft = Math.max(mft, that.getIni() - last.getEnd());
            }

            last = that;
        }

        out.println(mjt + " " + mft);
        out.close();
    }
}
