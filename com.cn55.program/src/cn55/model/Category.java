package cn55.model;

public class Category {

    private int id;
    private String name;
    private double amount;

    public Category(String name) {
        this.id = generateCatID();
        this.name = name;
        this.amount = 0;
    }

    public Category(String name, double amount) {
        this.id = generateCatID();
        this.name = name;
        this.amount = amount;
    }

    private int generateCatID() {
        int idCounter = 10000;
        idCounter++;
        return idCounter;
    }

    public void setId(int id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setAmount(double amount) { this.amount = amount; }

    public int getId() { return id; }

    public String getName() { return name; }

    public double getAmount() { return amount; }
}
