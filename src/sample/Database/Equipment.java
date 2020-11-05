package sample.Database;

public class Equipment {

    private String id;
    private String title;

    public Equipment(String id, String title) {
        this.id = id;
        this.title = title;
    }


    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }
}
