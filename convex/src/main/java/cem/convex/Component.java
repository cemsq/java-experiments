package cem.convex;

/**
 *
 */
public enum Component {
    X {
        @Override
        public int getDistance(Point a, Point b) {
            return Math.abs(a.getX() - b.getX());
        }

        @Override
        public Point getSmaller(Point a, Point b) {
            return a.getX() < b.getX()? a: b;
        }
    },
    Y {
        @Override
        public int getDistance(Point a, Point b) {
            return Math.abs(a.getY() - b.getY());
        }

        @Override
        public Point getSmaller(Point a, Point b) {
            return a.getY() < b.getY()? a: b;
        }
    };

    public abstract int getDistance(Point a, Point b);

    public abstract Point getSmaller(Point a, Point b);
}
