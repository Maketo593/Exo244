package BL.Local;
import BL.Seance.Seance;
import java.util.ArrayList;

public class Local {

    private final int id;
    private String number;
    private String location;
    private String type;
    private ArrayList<Seance> sessionsList;

    public Local(int id, String number, String location, String type) {
        this.id = id;
        this.number = number;
        this.location = location;
        this.type = type;
        this.sessionsList = new ArrayList<>();
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
    public ArrayList<Seance> getSessionsList() {
        return this.sessionsList;
    }
    public void setSessionsList(ArrayList<Seance> sessionsList) {
        this.sessionsList = sessionsList;
    }
    
    public void addSeance(Seance seance) {
        if (!this.sessionsList.equals(null) && !this.sessionsList.contains(seance)) {
            seance.setLocal(this);
            this.sessionsList.add(seance);
        }
    }

    public void removeSeance(Seance seance) {
        if (!this.sessionsList.equals(null) && this.sessionsList.contains(seance)) {
            seance.setLocal(null);
            this.sessionsList.remove(seance);
        }
    }
}
