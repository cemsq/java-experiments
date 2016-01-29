package convex;

import cem.convex.Component;
import cem.convex.Point;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class PointTest {

    @Test
    public void test() {
        Point p1 = new Point(10, 0);

        List<Point> list = new ArrayList<>();
        list.add(p1);
        list.add(new Point(0, 0));
        list.add(new Point(3, 3));
        list.add(new Point(5, 5));
        list.add(new Point(7, 7));
        list.add(new Point(10, 10));
        list.add(new Point(15, 5));

        for (Point p : list) {
            System.out.println(p +" : "+ p1.angle(p));
        }
    }

    @Test
    public void testCrossProduct() {
        Point p1 = new Point(10, 0);
        Point p2 = new Point(5, 0);
        Point p3 = new Point(0, 0);

        Assert.assertEquals(Point.crossProduct(p1, p2, p3), 0);

        // good
        p1 = new Point(10, 0);
        p2 = new Point(1, 1);
        p3 = new Point(0, 4);
        Assert.assertTrue(Point.crossProduct(p1, p2, p3) < 0);

        // bad
        p1 = new Point(0, 4);
        p2 = new Point(3, 3);
        p3 = new Point(4, 5);
        Assert.assertTrue(Point.crossProduct(p1, p2, p3) > 0);
    }

    @Test(dataProvider = "distanceProvider")
    public void shouldGetCorrectDistance(Component comp, Point a, Point b, int expectedX) {
        Assert.assertEquals(comp.getDistance(a, b), expectedX);
    }

    @DataProvider
    public Object[][] distanceProvider() {
        return new Object[][] {
                {Component.X, new Point(5, 5), new Point(5, 5), 0},
                {Component.X, new Point(0, 5), new Point(5, 5), 5},
                {Component.X, new Point(0, 5), new Point(-5, 5), 5},
                {Component.X, new Point(-5, 5), new Point(5, 5), 10},

                {Component.Y, new Point(5, 5), new Point(5, 5), 0},
                {Component.Y, new Point(5, 0), new Point(3, 5), 5},
                {Component.Y, new Point(3, 0), new Point(3, -5), 5},
                {Component.Y, new Point(6, -5), new Point(7, 5), 10}
        };
    }

    @Test(dataProvider = "angleProvider")
    public void shouldGetAngle(Point a, Point b, double minorThan) {
        double angle = a.angle(b);
        Assert.assertTrue( angle <= minorThan, "angle = " + angle + ". minor than expected = " +minorThan);
    }

    @DataProvider
    public Object[][] angleProvider() {
        return new Object[][] {
                {new Point(0, 0), new Point(5,5), 135},
                {new Point(0, 0), new Point(0,5), 90}
        };
    }
}


