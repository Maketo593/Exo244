package BL.Section;
import BL.Cours.Cours;
import java.util.ArrayList;

public class Section {
    private final int id;
    private String name;
    private ArrayList<Cours> coursList;

    public Section(int id, String name) {
        this.id = id;
        this.name = name;
        this.coursList = new ArrayList<>();
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

    public ArrayList<Cours> getCoursList() {
        return this.coursList;
    }

    public void setCoursList(ArrayList<Cours> coursList) {
        this.coursList = coursList;
    }

    public void addCours(Cours cours) {
        if (!this.coursList.equals(null) && !this.coursList.contains(cours)) {
            cours.setSection(this);
            this.coursList.add(cours);
        }
    }

    public void removeCours(Cours cours) {
        if (!this.coursList.equals(null) && this.coursList.contains(cours)) {
            cours.setSection(null);
            this.coursList.remove(cours);
        }
    }
}
