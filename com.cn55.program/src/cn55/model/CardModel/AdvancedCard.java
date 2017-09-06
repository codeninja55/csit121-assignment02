package cn55.model.CardModel;

@SuppressWarnings("unused")
abstract class AdvancedCard extends Card {
    String name;
    String email;
    double balance;

    AdvancedCard (String name, String email) {
        super ();
        this.name = name;
        this.email = email;
        this.balance = 0;
    }

    public boolean equals (AdvancedCard other) { return this.name.equals(other.name); }
}
