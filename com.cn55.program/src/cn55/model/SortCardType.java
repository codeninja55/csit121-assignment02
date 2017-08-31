package cn55.model;

public enum SortCardType {
    // Constants
    CreatedOrder(1, "Sort by Created Order"),
    Name(2, "Sort by Name"),
    Points(3, "Sort by Points")
    ;

    // Instance Variables
    private final int id;
    private final String name;

    SortCardType(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
