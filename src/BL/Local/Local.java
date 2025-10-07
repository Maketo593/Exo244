package BL.Local;

public class Local {

    private final int id;
    private String number;
    private String location;
    private String type;

    public Local(int id, String number, String location, String type) {
        this.id = id;
        this.number = number;
        this.location = location;
        this.type = type;
    }
    public int getId() {
        return this.id;
    }
    public String getNumber() {
        return this.number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getLocation() {
        return this.location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
