package BL.Cours;
import BL.Seance.Seance;
import BL.Section.Section;
import java.util.ArrayList;
public class Cours {
    private final int id;
    private String name;
    private Section section;
    private ArrayList<Seance> sessionsList;

    public Cours(int id, String name, Section section) {
        this.id = id;
        this.name = name;
        this.section = section;
        this.sessionsList = new ArrayList<>();
    }

    public int getId() {
        return this.id;
    }
    public String getname() {
        return this.name;
    }
    public void setname(String name) {
        this.name = name;
    }

    public Section getSection() {
        return this.section;
    }

    public void setSection(Section section) {
        if(!this.section.equals(null)) {
            this.section.removeCours(this);
        }
        this.section = section;
    }
    public ArrayList<Seance> getSessionsList() {
        return this.sessionsList;
    }
    public void setSessionsList(ArrayList<Seance> sessionsList) {
        this.sessionsList = sessionsList;
    }
    public void addSeance(Seance seance) {
        if (!this.sessionsList.equals(null) && !this.sessionsList.contains(seance)) {
            seance.setCours(this);
            this.sessionsList.add(seance);
        }
    }
    
    public void removeSeance(Seance seance) {
        if (!this.sessionsList.equals(null) && this.sessionsList.contains(seance)) {
            seance.setCours(null);
            this.sessionsList.remove(seance);
        }
    }
}
