package org.cem.jkal;

import org.cem.jkal.model.Item;
import org.cem.jkal.model.Plan;
import org.cem.jkal.model.Meal;
import org.testng.annotations.Test;


/**
 *
 */
public class SimpleTest {
    @Test
    public void testFood() {
        System.out.println(Items.pollo.consume(100));
        System.out.println(Items.protein.consume(30));
        System.out.println(Items.arroz.consume(125));
        System.out.println(Items.panDeSandwich.consume(238));
    }

    @Test
    public void testMeal() {
        Meal d1 = Meals.sandwich_2("Desayuno");
        System.out.println(d1.compute());
    }

    @Test
    public void testDay() {
        Meal d1 = Meals.sandwich_2("FullDay")

                .add(Items.arroz, 100)
                .add(Items.pollo, 150)
                .add(Items.verduras, 100)


                .add(Items.arroz, 70)
                .add(Items.verduras, 70)
                .add(Items.pollo, 150);

        System.out.println(d1.compute());
    }

    @Test
    public void compareRestDays() {
        compare(new Plan("base")
                        .add(new Meal("m1").add(Items.avena, 100).add(Items.protein, 30))
                        .add(new Meal("m2").add(Items.arroz, 100).add(Items.pollo, 150).add(Items.verduras, 150))
                        .add(new Meal("m3").add(Items.avena, 70).add(Items.protein, 30))
                        .add(new Meal("m2").add(Items.arroz, 0).add(Items.pollo, 150).add(Items.verduras, 150))
                , new Plan("r1")
                        .add(Meals.sandwich_2("m1"))
                        .add(new Meal("m2").add(Items.arroz, 100).add(Items.pollo, 150).add(Items.verduras, 150))
                        .add(Meals.sandwich_1("m3"))
                        .add(new Meal("m4").add(Items.arroz, 100).add(Items.pollo, 150).add(Items.verduras, 150))
                , new Plan("r2")
                        .add(new Meal("m1").add(Items.avena, 100).add(Items.protein, 30))
                        .add(new Meal("m2").add(Items.arroz, 100).add(Items.pollo, 150).add(Items.verduras, 150))
                        .add(new Meal("m3").add(Items.avena, 70).add(Items.protein, 30))
                        .add(new Meal("m2").add(Items.arroz, 50).add(Items.pollo, 150).add(Items.verduras, 150))
                , new Plan("r3")
                        .add(new Meal("m1").add(Items.avena, 100).add(Items.protein, 30))
                        .add(new Meal("m2").add(Items.arroz, 80).add(Items.pollo, 150).add(Items.verduras, 150))
                        .add(new Meal("m3").add(Items.avena, 70).add(Items.protein, 30))
                        .add(new Meal("m4").add(Items.arroz, 80).add(Items.pollo, 150).add(Items.verduras, 150))
                , new Plan("r4")
                        .add(new Meal("m1").add(Items.ricePudding, 50).add(Items.protein, 30))
                        .add(new Meal("m2").add(Items.arroz, 80).add(Items.pollo, 150).add(Items.verduras, 150))
                        .add(new Meal("m3").add(Items.ricePudding, 50).add(Items.protein, 30))
                        .add(new Meal("m4").add(Items.arroz, 80).add(Items.pollo, 150).add(Items.verduras, 150))
                , new Plan("r5")
                        .add(new Meal("m1").add(Items.avena, 70).add(Items.protein, 30))
                        .add(new Meal("m2").add(Items.arroz, 80).add(Items.pollo, 150).add(Items.verduras, 150))
                        .add(new Meal("m3").add(Items.avena, 70).add(Items.protein, 30))
                        .add(new Meal("m4").add(Items.arroz, 80).add(Items.pollo, 150).add(Items.verduras, 150))
        );



    }

    @Test
    public void compareTrainingDays() {
        compare(new Plan("base")
                        .add(Meals.avena("m1", 100))
                        .add(Meals.preWorkout())
                        .add(Meals.postWorkout())
                        .add(Meals.standard("m2", Items.arroz, 100))
                        .add(Meals.avena("m3", 70))
                        .add(Meals.standard("m4", Items.arroz, 100))
                , new Plan("p1")
                        .add(Meals.ricePudding("m1", 60))
                        .add(Meals.preWorkout())
                        .add(Meals.postWorkout())
                        .add(Meals.standard("m2", Items.arroz, 100))
                        .add(Meals.ricePudding("m3", 60))
                        .add(Meals.standard("m4", Items.arroz, 100))
        );
    }

    @Test
    public void actual() {
        compare(new Plan("train")
                        .add(Meals.avena("m1", 100))
                        .add(Meals.preWorkout())
                        .add(Meals.postWorkout())
                        .add(Meals.standard("m2", Items.arroz, 100))
                        .add(Meals.avena("m3", 70))
                        .add(Meals.standard("m4", Items.arroz, 100))
                , new Plan("train2")
                        .add(Meals.sandwich_1("m1"))
                        .add(Meals.preWorkout())
                        .add(Meals.postWorkout())
                        .add(Meals.standard("m2", Items.arroz, 100))
                        .add(Meals.sandwich_1("m3"))
                        .add(Meals.standard("m4", Items.arroz, 100))
                , new Plan("rest")
                        .add(Meals.avena("m1", 100))
                        .add(Meals.standard("m2", Items.arroz, 100))
                        .add(Meals.avena("m3", 70))
                        .add(Meals.standard("m4", Items.arroz, 50))
        );

    }

    @Test
    public void computeFromOtherItem() {
        Item source = Items.arroz;
        Item target = Items.panDeSandwich;
        double quantity = 125;
        double equivalent = target.computeFromCarbs(source, quantity);

        String msg = String.format("%s g %s = %s g %s", quantity, source.getName(), equivalent, target.getName());
        System.out.println(msg);
    }

    private void compare(Plan...days) {
        for (Plan d : days) {
            System.out.println(d.compute());
        }
    }
}

