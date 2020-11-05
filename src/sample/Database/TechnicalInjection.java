package sample.Database;

import java.sql.Date;

public class TechnicalInjection {

    private String id;
    private Date dateTI;
    private String result;
    private String causeOfFailure;

    public TechnicalInjection(String id, Date date, String result, String causeOfFailure) {
        this.id = id;
        this.dateTI = date;
        this.result = result;
        this.causeOfFailure = causeOfFailure;
    }

    public String getId() {
        return id;
    }

    public Date getDateTI() {
        return dateTI;
    }

    public String getResult() {
        return result;
    }

    public String getCauseOfFailure() {
        return causeOfFailure;
    }
}
