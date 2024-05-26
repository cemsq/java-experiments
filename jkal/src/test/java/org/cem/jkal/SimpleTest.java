package org.cem.jkal;

import org.cem.jkal.model.Plan;
import org.cem.jkal.model.Meal;
import org.testng.annotations.Test;


/**
 *
 */
public class SimpleTest {

    @Test
    public void testFood() {

        System.out.println(Foods.pollo.consume(100));
        System.out.println(Foods.protein.consume(30));
    }

    @Test
    public void testMeal() {
        Meal d1 = Meals.sandwich_2("Desayuno");
        System.out.println(d1.compute());
    }

    @Test
    public void testDay() {
        Meal d1 = Meals.sandwich_2("FullDay")

                .add(Foods.arroz, 100)
                .add(Foods.pollo, 150)
                .add(Foods.verduras, 100)


                .add(Foods.arroz, 70)
                .add(Foods.verduras, 70)
                .add(Foods.pollo, 150);

        System.out.println(d1.compute());
    }

    @Test
    public void compareRestDays() {
        compare(new Plan("base")
                        .add(new Meal("m1").add(Foods.avena, 100).add(Foods.protein, 30))
                        .add(new Meal("m2").add(Foods.arroz, 100).add(Foods.pollo, 150).add(Foods.verduras, 150))
                        .add(new Meal("m3").add(Foods.avena, 70).add(Foods.protein, 30))
                        .add(new Meal("m2").add(Foods.arroz, 0).add(Foods.pollo, 150).add(Foods.verduras, 150))
                , new Plan("r1")
                        .add(Meals.sandwich_2("m1"))
                        .add(new Meal("m2").add(Foods.arroz, 100).add(Foods.pollo, 150).add(Foods.verduras, 150))
                        .add(Meals.sandwich_1("m3"))
                        .add(new Meal("m4").add(Foods.arroz, 100).add(Foods.pollo, 150).add(Foods.verduras, 150))
                , new Plan("r2")
                        .add(new Meal("m1").add(Foods.avena, 100).add(Foods.protein, 30))
                        .add(new Meal("m2").add(Foods.arroz, 100).add(Foods.pollo, 150).add(Foods.verduras, 150))
                        .add(new Meal("m3").add(Foods.avena, 70).add(Foods.protein, 30))
                        .add(new Meal("m2").add(Foods.arroz, 50).add(Foods.pollo, 150).add(Foods.verduras, 150))
                , new Plan("r3")
                        .add(new Meal("m1").add(Foods.avena, 100).add(Foods.protein, 30))
                        .add(new Meal("m2").add(Foods.arroz, 80).add(Foods.pollo, 150).add(Foods.verduras, 150))
                        .add(new Meal("m3").add(Foods.avena, 70).add(Foods.protein, 30))
                        .add(new Meal("m4").add(Foods.arroz, 80).add(Foods.pollo, 150).add(Foods.verduras, 150))
                , new Plan("r4")
                        .add(new Meal("m1").add(Foods.ricePudding, 50).add(Foods.protein, 30))
                        .add(new Meal("m2").add(Foods.arroz, 80).add(Foods.pollo, 150).add(Foods.verduras, 150))
                        .add(new Meal("m3").add(Foods.ricePudding, 50).add(Foods.protein, 30))
                        .add(new Meal("m4").add(Foods.arroz, 80).add(Foods.pollo, 150).add(Foods.verduras, 150))
                , new Plan("r5")
                        .add(new Meal("m1").add(Foods.avena, 70).add(Foods.protein, 30))
                        .add(new Meal("m2").add(Foods.arroz, 80).add(Foods.pollo, 150).add(Foods.verduras, 150))
                        .add(new Meal("m3").add(Foods.avena, 70).add(Foods.protein, 30))
                        .add(new Meal("m4").add(Foods.arroz, 80).add(Foods.pollo, 150).add(Foods.verduras, 150))
        );



    }

    @Test
    public void compareTrainingDays() {
        compare(new Plan("base")
                        .add(Meals.avena("m1", 100))
                        .add(Meals.preWorkout())
                        .add(Meals.postWorkout())
                        .add(Meals.standard("m2", Foods.arroz, 100))
                        .add(Meals.avena("m3", 70))
                        .add(Meals.standard("m4", Foods.arroz, 100))
                , new Plan("p1")
                        .add(Meals.ricePudding("m1", 60))
                        .add(Meals.preWorkout())
                        .add(Meals.postWorkout())
                        .add(Meals.standard("m2", Foods.arroz, 100))
                        .add(Meals.ricePudding("m3", 60))
                        .add(Meals.standard("m4", Foods.arroz, 100))
        );
    }

    @Test
    public void actual() {
        compare(new Plan("train")
                        .add(Meals.avena("m1", 100))
                        .add(Meals.preWorkout())
                        .add(Meals.postWorkout())
                        .add(Meals.standard("m2", Foods.arroz, 100))
                        .add(Meals.avena("m3", 70))
                        .add(Meals.standard("m4", Foods.arroz, 100))
                , new Plan("rest")
                        .add(Meals.avena("m1", 100))
                        .add(Meals.standard("m2", Foods.arroz, 100))
                        .add(Meals.avena("m3", 70))
                        .add(Meals.standard("m4", Foods.arroz, 0))
        );

    }

    private void compare(Plan...days) {
        for (Plan d : days) {
            System.out.println(d.compute());
        }
    }
}

