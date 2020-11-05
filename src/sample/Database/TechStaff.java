package sample.Database;

public class TechStaff {

    String id;
    String snp;
    String position;

    public TechStaff(String id, String snp, String position) {
        this.id = id;
        this.snp = snp;
        this.position = position;
    }
    public String getId() {
        return id;
    }

    public String getSnp() {
        return snp;
    }

    public String getPosition() {
        return position;
    }

}

