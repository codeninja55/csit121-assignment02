package cn55.controller;

import cn55.view.MainFrame;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import cn55.model.*;

/*
 * @author Dinh Che
 * Student Number: 5721970
 * Email: dbac496@uowmail.edu.au
 */

/* CONTROLLER CLASS */
/* GUI view package will only interact
 * with data through this controller class
 * and MainFrame controller class */

public class Program {

    private static ArrayList<Card> cards;

    public static void main(String[] args) {

        /*try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/

        Shop shop = new Shop();

        cards = shop.getCards();

        createTestCode(shop);
        //shop.showCards();

        new MainFrame(shop);

    } // end of main method

    private static void createTestCode(Shop shop) {
        /*########## TESTING CODE ##########*/

        // Cash purchase test
        Map<String, Double> cat1 = new HashMap<>();
        cat1.put("Toys", 0D);
        cat1.put("Sporting Goods", 800D);
        cat1.put("Electronics", 0D);
        cat1.put("Motors", 100D);
        cat1.put("Fashion", 0D);
        cat1.put("Deals", 500D);

        shop.makePurchase("cash", cat1);

        // AnonCard Test

        cards.add(new AnonCard("111"));

        Map<String, Double> cat2 = new HashMap<>();
        cat2.put("Deals", 0D);
        cat2.put("Electronics", 200D);
        cat2.put("Fashion", 80D);
        cat2.put("Sporting Goods", 0D);
        cat2.put("Toys", 100D);
        cat2.put("Motors", 0D);

        shop.makePurchase("111", cat2);

        // BasicCard Test
        cards.add(new BasicCard("69", "Natasha Romanov",
                "blackwidow@avengers.team", 0));

        Map<String, Double> cat3 = new HashMap<>();
        cat3.put("Electronics", 3000D);
        cat3.put("Fashion", 5000D);
        cat3.put("Sporting Goods", 500D);
        cat3.put("Motors", 0D);
        cat3.put("Toys", 1000D);
        cat3.put("Deals", 2000D);

        shop.makePurchase("69", cat3);

        // BasicCard Test 2
        cards.add(new BasicCard("001", "Steve Rogers",
                        "captain_a@avengers.team",0D));

        Map<String, Double> cat4 = new HashMap<>();
        cat4.put("Electronics", 100D);
        cat4.put("Fashion", 0D);
        cat4.put("Sporting Goods", 500D);
        cat4.put("Motors", 0D);
        cat4.put("Toys", 100D);
        cat4.put("Deals", 2000D);

        shop.makePurchase("001", cat4);

        // PremiumCard Test
        cards.add(new PremiumCard("75", "Tony Stark",
                "ironman@avengers.team",0));

        Map<String, Double> cat5 = new HashMap<>();
        cat5.put("Electronics", 1000000D);
        cat5.put("Deals", 500000D);
        cat5.put("Toys", 300D);
        cat5.put("Motors", 10000D);
        cat5.put("Sporting Goods", 500D);
        cat5.put("Fashion", 2000D);

        shop.makePurchase("75", cat5);

        // PremiumCard Test 2
        cards.add(new PremiumCard("666", "Nick Fury",
                "nick@shield.com",0));

        Map<String, Double> cat6 = new HashMap<>();
        cat6.put("Electronics", 10000D);
        cat6.put("Deals", 0D);
        cat6.put("Toys", 300D);
        cat6.put("Motors", 1000000D);
        cat6.put("Sporting Goods", 500D);
        cat6.put("Fashion", 2000D);

        shop.makePurchase("666", cat6);

        cards.add(new BasicCard("444","Hank Pym","ants@avengers.team",0));
        cards.add(new BasicCard("88","Peter Parker", "spidey@avengers.team",0));

    }

} // end of Assignment1 class
