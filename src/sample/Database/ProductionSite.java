package sample.Database;

public class ProductionSite {
    private String id;
    private String name;

    public ProductionSite(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
