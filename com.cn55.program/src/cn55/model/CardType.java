package cn55.model;

public enum CardType {

    // Declare constants
    Cash("Cash", 0),
    Card("Card", 1),
    AnonCard("AnonCard", 2),
    BasicCard("BasicCard", 3),
    PremiumCard("PremiumCard", 4)
    ;

    // Instance Variables
    private final int id;
    private final String name;

    CardType(String name,int id) {
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id; }

    public String getName() {
        return name;
    }
}
