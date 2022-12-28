package pl.domanski.carRent.customer.rent.model;

public enum SortingType {
    ASC("ascending"),
    DESC("descending");

    private final String name;

    SortingType(String name) {
        this.name = name;
    }

    public SortingType getByName(String name) {
        return SortingType.valueOf(name);
    }
}
