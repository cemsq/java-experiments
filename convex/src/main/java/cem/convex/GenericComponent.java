package cem.convex;

import java.util.function.Function;

/**
 *
 */
public class GenericComponent<SOURCE> {

    public static final GenericComponent<Polygon> X_POLYGON = new GenericComponent<>(Polygon::getLeft);
    public static final GenericComponent<Polygon> Y_POLYGON = new GenericComponent<>(Polygon::getTop);

    public static final GenericComponent<Point> X_POINT = new GenericComponent<>(Point::getX);
    public static final GenericComponent<Point> Y_POINT = new GenericComponent<>(Point::getY);

    private Function<SOURCE, Integer> f;
    private GenericComponent(Function<SOURCE, Integer> f) {
        this.f = f;
    }

    public Integer getDistance(SOURCE a, SOURCE b) {
        return f.apply(a) - f.apply(b);
    }


    static class Polygon {
        private int top;
        private int left;

        public int getTop() {
            return top;
        }

        public int getLeft() {
            return left;
        }
    }
}
