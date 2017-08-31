package cn55.model.CardModel;

public abstract class AdvancedCard extends Card {
    protected String name;
    protected String email;
    protected double balance;

    AdvancedCard (String name, String email) {
        super ();
        this.name = name;
        this.email = email;
        this.balance = 0;
    }

    public boolean equals (AdvancedCard other) { return this.name.equals(other.name); }
}
