package cem.convex;

import cem.convex.Component;
import cem.convex.Point;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 */
public class ConvexPolygon {

    public List<Point> create(List<Point> initialPoints) {
        if (initialPoints.size() < 3) {
            return initialPoints;
        }

        List<Point> polygon = new ArrayList<>();

        Point first = getInitialPoint(initialPoints);
        comparator.setInitial(first);
        initialPoints.sort(comparator);

        polygon.add(first);
        Point second = initialPoints.get(1);

        for (Point third : initialPoints.subList(2, initialPoints.size())) {
            int product = Point.crossProduct(first, second, third);

            if (product <= 0) {
                polygon.add(second);
                first = second;
            }
            second = third;
        }
        polygon.add(second);

        return polygon;
    }

    public Point getInitialPoint(List<Point> points) {
        Point p = points.get(0);

        for (Point point : points.subList(0, points.size())) {
            p = Component.Y.getSmaller(p, point);
        }

        return p;
    }
    public static PointComparator comparator = new PointComparator();
    public static class PointComparator implements Comparator<Point> {
        private Point initial;

        public void setInitial(Point initial) {
            this.initial = initial;
        }

        @Override
        public int compare(Point o1, Point o2) {
            if (initial == o1) {
                return -1;
            }
            if (initial == o2) {
                return 1;
            }

            Double a1 = initial.angle(o1);
            Double a2 = initial.angle(o2);

            return a1.compareTo(a2);
        }
    }

}
