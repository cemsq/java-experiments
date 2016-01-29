package convex;

import cem.convex.ConvexPolygon;
import cem.convex.Point;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class ConvexPolygonTest {

    ConvexPolygon polygon = new ConvexPolygon();

    List<Point> points = Arrays.asList(
            new Point(5, 6),
            new Point(5, 15),
            new Point(4, 5),
            new Point(1, 3),
            new Point(3, 3),
            new Point(0, 4)
    );

    @Test
    public void shouldGetPointOnTheBottomSide() {
        Point point = polygon.getInitialPoint(points);

        Assert.assertEquals(point.getX(), 3);
        Assert.assertEquals(point.getY(), 3);
    }

    @Test
    public void shouldSortByAngle() {

        Point initial = polygon.getInitialPoint(points);

        ConvexPolygon.comparator.setInitial(initial);
        points.sort(ConvexPolygon.comparator);

        for (Point p : points) {
            System.out.println(p + ": " + initial.angle(p));
        }
    }

    @Test
    public void shouldCreatePolygon1() {
        List<Point> points = new ArrayList<>();
        Point p1 = create(points, 0, 0);
        Point p2 = create(points, 5, 0);
        Point p3 = create(points, 0, 5);
        Point p4 = create(points, -5, 0);
        Point p5 = create(points, -7, -7);
        Point p6 = create(points, 0, -4);

        List<Point> list = polygon.create(points);
        Assert.assertEquals(list.size(), 5);

        Assert.assertTrue(list.contains(p2));
        Assert.assertTrue(list.contains(p3));
        Assert.assertTrue(list.contains(p4));
        Assert.assertTrue(list.contains(p5));
        Assert.assertTrue(list.contains(p6));
    }

    @Test
    public void shouldCreatePolygon2() {
        List<Point> points = new ArrayList<>();
        Point p1 = create(points, 4, 5);
        Point p2 = create(points, 3, 3);
        Point p3 = create(points, 5, 3);
        Point p4 = create(points, 5, 7);
        Point p5 = create(points, 0, 4);
        Point p6 = create(points, 5, 6);
        Point p7 = create(points, 4, 0);

        List<Point> list = polygon.create(points);
        Assert.assertEquals(list.size(), 5);

        Assert.assertEquals(list.get(0), p7);
        Assert.assertEquals(list.get(1), p5);
        Assert.assertEquals(list.get(2), p4);
        Assert.assertEquals(list.get(3), p6);
        Assert.assertEquals(list.get(4), p3);
    }

    public Point create(List<Point> list, int x, int y) {
        Point p = new Point(x, y);
        list.add(p);

        return p;
    }
}

