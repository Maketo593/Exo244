package BL.Section;
import java.util.ArrayList;
import BL.CoursPersonne.Cours;


public class Section {
    private int id;
    private String nom;
    private ArrayList<Cours> coursList;

    public Section(String nom) {
        this.nom = nom;
        this.coursList = new ArrayList<Cours>();
    }

    public Section(int id, String nom) {
        this.id = id;
        this.nom = nom;
        this.coursList = new ArrayList<Cours>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Cours> getCours() {
        return coursList;
    }

    public void setCours(ArrayList<Cours> cours) {
        this.coursList = cours;
    }

    public void addCours(Cours cours) {
        if (this.coursList != null && !this.coursList.contains(cours)) {
            cours.setSection(this);
            this.coursList.add(cours);
        }
    }

    public void removeCours(Cours cours) {
        if (this.coursList != null && this.coursList.contains(cours)) {
            cours.setSection(null);
            this.coursList.remove(cours);
        }
    }
}
