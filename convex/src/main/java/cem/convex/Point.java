package cem.convex;

/**
 *
 */
public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Double angle(Point b) {
        double x = Component.X.getDistance(this, b);
        double y = Component.Y.getDistance(this, b);
        double h = Math.hypot(x, y);

        if (h == 0) {
            return 0D;
        }

        double a = Math.toDegrees(Math.asin(y / h));

        return this.getX() < b.getX()? 180 - a : a;
    }

    public static int crossProduct(Point p1, Point p2, Point p3) {
        int a = (p2.getX() - p1.getX()) * (p3.getY() - p1.getY());
        int b = (p2.getY() - p1.getY()) * (p3.getX() - p1.getX());

        return a - b;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", x, y);
    }
}
