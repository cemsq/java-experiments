package org.cem.jkal;

import org.cem.jkal.model.Item;
import org.cem.jkal.model.Plan;
import org.cem.jkal.model.Meal;
import org.testng.annotations.Test;


/**
 *
 */
public class NutritionTest {
    @Test
    public void testFood() {
//        System.out.println(Items.pollo.consume(100));
//        System.out.println(Items.protein.consume(30));
        System.out.println(Items.arroz.consume(70));

//        System.out.println(Items.panDeSandwich.consume(238));
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
                , new Plan("train1")
                        .add(Meals.sandwich("m1", 4, 125))
                        .add(Meals.preWorkout())
                        .add(Meals.postWorkout())
                        .add(Meals.standard("m2", Items.arroz, 100))
                        .add(Meals.ricePudding("m3", 60))
                        .add(Meals.standard("m4", Items.arroz, 100))
                , new Plan("train2")
                        .add(Meals.sandwich("m1", 4, 125))
                        .add(Meals.preWorkout())
//                        .add(Meals.postWorkout())
                        .add(Meals.standard("m2", Items.arroz, 100))
                        .add(Meals.sandwich("m1", 2, 125))
                        .add(Meals.standard("m4", Items.arroz, 100))
                , new Plan("rest1")
                        .add(Meals.avena("m1", 80))
                        .add(Meals.standard("m2", Items.arroz, 100))
                        .add(Meals.ricePudding("m3", 60))
                        .add(Meals.standard("m4", Items.arroz, 100))

                , new Plan("rest2")
                        .add(Meals.sandwich("m1", 4, 125))
                        .add(Meals.standard("m2", Items.arroz, 100))
                        .add(Meals.ricePudding("m3", 60))
                        .add(Meals.standard("m4", Items.arroz, 100))

                , new Plan("x")
                        .add(Meals.avena("m1", 100))
                        .add(Meals.standard("m2", Items.arroz, 100))
                        .add(Meals.sandwich("m3", 4, 250))
                        .add(Meals.standard("m4", Items.arroz, 100))

        );
    }

    @Test
    public void bulk_25() {
        compare(new Plan("cut7 - 06/30 - 67.6 kg")
                        .add(Meals.panConTopfen("m1", 2))
                        .add(Meals.standard("m2", Items.arroz, 60))
                        .add(Meals.panConTopfen("m3", 2))
                        .add(Meals.grasa(30))
                        .add(Meals.standard("m4", Items.arroz, 0)),

                new Plan("init - 09/15 - 64.2 kg")
                        .add(Meals.panConTopfen("m1", 3))
                        .add(Meals.standard("m2", Items.arroz, 60))
                        .add(Meals.grasa(30))
                        .add(Meals.standard("m4", Items.arroz, 60)),

                new Plan("vol1 - 10/13 - 64.2 kg")
//                        .add(Meals.avenaNat("m1", 70, 0))
                        .add(Meals.panConTopfen("m1", 4))
                        .add(Meals.standard("m2", Items.arroz, 70))
                        .add(Meals.grasa(30))
                        .add(Meals.panConTopfen("m3", 2, 0, 100))
                        .add(Meals.standard("m4", Items.arroz, 35)),

        new Plan("vol2 - 11/03 - 64.2 kg")
//                        .add(Meals.avenaNat("m1", 70, 0))
                .add(Meals.panConTopfen("m1", 4))
                .add(Meals.standard("m2", Items.arroz, 60))
                .add(Meals.grasa(30))
                .add(Meals.panConTopfen("m3", 4))
                .add(Meals.standard("m4", Items.arroz, 60)),

                new Plan("vol3 - 11/23 - 64.08 kg")
//                        .add(Meals.avenaNat("m1", 80, 0))
                        .add(Meals.panConTopfen("m1", 5))
                        .add(Meals.standard("m2", Items.arroz, 60))
                        .add(Meals.grasa(30))
                        .add(Meals.panConTopfen("m3", 4))
                        .add(Meals.standard("m4", Items.arroz, 60)),

                new Plan("mini - 02/02 - 62.84 kg")
                        .add(Meals.avenaNat("m1", 50, 0))
//                        .add(Meals.panConTopfen("m1", 5))
                        .add(Meals.standard("m2", Items.arroz, 60))
                        .add(Meals.grasa(30))
                        .add(Meals.panConTopfen("m3", 2))
                        .add(Meals.standard("m4", Items.arroz, 0)),

                new Plan("mini - 03/16 - 63.19 kg")
                        .add(Meals.avenaNat("m1", 60, 0))
//                        .add(Meals.panConTopfen("m1", 5))
                        .add(Meals.standard("m2", Items.arroz, 70))
                        .add(Meals.grasa(30))
                        .add(Meals.panConTopfen("m3", 4))
                        .add(Meals.standard("m4", Items.arroz, 0))
        );
    }

    @Test
    public void cut() {
        compare(new Plan("endVol - 70.3 kg")
                        .add(Meals.avena("m1", 100))
                        .add(Meals.standard("m2", Items.arroz, 110))
                        .add(Meals.avena("m3", 100))
                        .add(Meals.standard("m4", Items.arroz, 110))
                , new Plan("cut1 - marzo 30 - 70.3 kg")
                        .add(Meals.avena("m1", 100))
                        .add(Meals.standard("m2", Items.arroz, 100))
                        .add(Meals.avena("m3", 100))
                        .add(Meals.standard("m4", Items.arroz, 100))
                , new Plan("cut2 - abril 7 - 70.2 kg")
                        .add(Meals.avena("m1", 100))
                        .add(Meals.standard("m2", Items.arroz, 80))
                        .add(Meals.avena("m3", 100))
                        .add(Meals.standard("m4", Items.arroz, 80))
                , new Plan("cut3 - abril 14 - 70 kg")
                        .add(Meals.avena("m1", 80))
                        .add(Meals.standard("m2", Items.arroz, 90))
                        .add(Meals.avena("m3", 80))
                        .add(Meals.standard("m4", Items.arroz, 90))
                , new Plan("cut4 - abril 28 - 70.2 kg")
                        .add(Meals.avenaNat("m1", 50))
//                        .add(Meals.panConTopfen("m1", 3))
                        .add(Meals.standard("m2", Items.arroz, 80))
                        .add(Meals.avenaNat("m3", 50))
//                        .add(Meals.panConTopfen("m3", 3))
                        .add(Meals.standard("m4", Items.arroz, 80))
                , new Plan("cut5 - mayo 14 - 69.3 kg")
//                        .add(Meals.avenaNat("m1", 50))
                        .add(Meals.panConTopfen("m1", 3))
                        .add(Meals.standard("m2", Items.arroz, 80))
//                        .add(Meals.avenaNat("m3", 50))
                        .add(Meals.panConTopfen("m3", 3))
//                        .add(Meals.preWorkout())
//                        .add(Meals.postWorkout())
                        .add(Meals.standard("m4", Items.arroz, 40))

                , new Plan("cut6 - junio 16 - 68.0 kg")
//                        .add(Meals.avenaNat("m1", 50))
                        .add(Meals.panConTopfen("m1", 4))
                        .add(Meals.standard("m2", Items.arroz, 80))
//                        .add(Meals.avenaNat("m3", 50))
                        .add(Meals.panConTopfen("m3", 4))
//                        .add(Meals.preWorkout())
//                        .add(Meals.postWorkout())
                        .add(Meals.grasa(30))
                        .add(Meals.standard("m4", Items.arroz, 0))

                , new Plan("cut7 - junio 30 - 67.6 kg")
//                        .add(Meals.avenaNat("m1", 50))
                        .add(Meals.panConTopfen("m1", 2))
                        .add(Meals.standard("m2", Items.arroz, 70))
//                        .add(Meals.avenaNat("m3", 50))
                        .add(Meals.panConTopfen("m3", 2))
//                        .add(Meals.preWorkout())
//                        .add(Meals.postWorkout())
                        .add(Meals.grasa(30))
                        .add(Meals.standard("m4", Items.arroz, 0))
        );
    }

    @Test
    public void weekly() {
        compare(new Plan("l")
                .add(Meals.sandwich("m1", 4, 125))
//                .add(new Meal("m1").add(Items.protein, 45).add(Items.avena, 70))
                .add(Meals.preWorkout(60))
                .add(Meals.postWorkout())
                .add(new Meal("m2").add(Items.pollo, 150).add(Items.arroz, 80))
                .add(Meals.ricePudding("m3", 30, 30))
                .add(new Meal("m4").add(Items.pollo, 150).add(Items.arroz, 80).add(Items.verduras, 150))
//                .add(new Meal("m4").add(Items.cottage, 125).add(Items.panDeSandwich, 25))
        );
    }

    @Test
    public void actual() {
        compare(new Plan("train")
                        .add(Meals.avena("m1", 100))
                        .add(Meals.preWorkout(30))
                        .add(Meals.postWorkout())
                        .add(Meals.standard("m2", Items.arroz, 100))
                        .add(Meals.avena("m3", 100))
                        .add(Meals.standard("m4", Items.arroz, 70))
        );

    }

    @Test
    public void computeFromOtherItem() {
        double quantity = 50;
//        Item source = Items.arroz;
        Item source = new Item("carbs", 100, 0, 0);
        Item target = Items.panDeSandwich;

        double equivalent = target.computeFromCarbs(source, quantity);

        String msg = String.format("%s g %s = %s g %s", quantity, source.getName(), equivalent, target.getName());
        System.out.println(msg);
    }

    @Test
    public void testMeal() {
//        Meal d1 = Meals.sandwich_2("Desayuno");
//        System.out.println(Meals.avena("m1", 80).compute());
//        System.out.println(Meals.sandwich("m2", 5, 125).compute());

        test(Meals.panConTopfen("pan", 4));
        test(Meals.panConTopfen("pan", 5));
        test(Meals.standard("arroz", Items.arroz, 70));
        test(Meals.avenaNat("avena", 70));
        test(Meals.avenaNat("avena", 80));
        test(Meals.create("arroz").add(Items.arroz, 60));
    }

    private void test(Meal m) {
        System.out.println(m.compute());
    }

    private void compare(Plan...days) {
        for (Plan d : days) {
            if (d.isEnabled()) {
                System.out.println(d.compute());
            }
        }
    }
}

