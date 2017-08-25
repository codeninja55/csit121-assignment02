package cn55.model;

/* THIS CLASS IS A TEMP DATABASE */

/*
*
* Things to move here:
* - Cards ArrayList
* - Purchases ArrayList
* - Categories ArrayList
* - ReceiptID Set
* - CardID Set
*
* */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

class Database {

    private ArrayList<Card> cards;
    private ArrayList<Purchase> purchases;
    private ArrayList<String> categories;
    private Set<Integer> receiptSet;
    private Set<String> cardIDSet;

    public Database() {
        cards = new ArrayList<>();
        purchases = new ArrayList<>();
        categories = new ArrayList<>();
        receiptSet = new HashSet<>();
        cardIDSet = new HashSet<>();

    }
}
