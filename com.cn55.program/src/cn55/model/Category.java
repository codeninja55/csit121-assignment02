package cn55.model;

import java.util.Comparator;

public class Category implements Comparable<Category> {

    private int id;
    private String name;
    private String description;
    private double amount;
    private static int idCounter = 100;

    /*============================== CONSTRUCTORS  ==============================*/
    public Category(String name) {
        this.id = Database.generateCategoryID();
        this.name = name;
        this.description = "";
        this.amount = 0;
    }

    public Category(String name, String description) {
        this.id = Database.generateCategoryID();
        this.name = name;
        this.description = description;
        this.amount = 0D;
    }

    private Category(int id, String name, String description, double amount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.amount = amount;
    }

    /*============================== COPY CONSTRUCTOR ==============================*/
    public Category(Category other) {
        this(other.getId(), other.getName(), other.getDescription(), other.getAmount());
    }

    /*============================== MUTATORS  ==============================*/
    void setId(int id) { this.id = id; }

    void setName(String name) { this.name = name; }

    void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(double amount) { this.amount = amount; }

    /*============================== ACCESSORS  ==============================*/
    public int getId() { return id; }

    public String getName() { return name; }

    public String getDescription() {
        return description;
    }

    public double getAmount() { return amount; }

    public String toString() {
        return String.format("%n%s : $%.2f", name, amount);
    }

    @Override
    public int compareTo(Category o) {
        /* ASCENDING ORDER */
        return this.getName().compareTo(o.getName());
        /* DESCENDING ORDER */
        //return o.getName().compareTo(this.getName());
    }
}
