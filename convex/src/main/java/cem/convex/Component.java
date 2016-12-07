package cem.convex;

import java.util.function.Function;

/**
 *
 */
public enum Component {
    X(Point::getX),
    Y(Point::getY);

    private Function<Point, Integer> component;

    Component(Function<Point, Integer> component) {
        this.component = component;
    }

    public int getDistance(Point a, Point b) {
        return Math.abs(component.apply(a) - component.apply(b));
    }

    public Point getSmaller(Point a, Point b) {
        return component.apply(a) <= component.apply(b)? a : b;
    }

    public Point getBigger(Point a, Point b) {
        return component.apply(a) >= component.apply(b)? a : b;
    }
}
