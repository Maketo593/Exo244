package BL.CoursPersonne;
import java.util.ArrayList;
import BL.Section.Section;

public class Cours {
    private int id;
    private String nom;
    private Section section;
    private int annee;
    private Personne prof;
    private ArrayList<Personne> personneList;

    public Cours(String nom, Section section) {
        this.nom = nom;
        this.section = section;
        section.addCours(this);
        this.personneList = new ArrayList<>();
    }

    public Cours(int id, String nom, Section section) {
        this.id = id;
        this.nom = nom;
        this.section = section;
        this.personneList = new ArrayList<>();
    }

    public Cours(int id, String nom, Section section, int annee, Personne prof, ArrayList<Personne> personneList) {
        this.id = id;
        this.nom = nom;
        this.section = section;
        this.annee = annee;
        this.prof = prof;
        this.personneList = personneList;
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

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        if (this.section != null) {
            section.getCours().remove(this);
        }
        this.section = section;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public Personne getProf() {
        return prof;
    }

    public void setProf(Personne prof) {
        if (this.prof == prof) return;
        if (this.prof != null) this.prof.removeCours(this);
        this.prof = prof;
        if (prof != null && !prof.getCoursList().contains(this)) prof.addCours(this);
    }

    public ArrayList<Personne> getpersonneList() {
        return personneList;
    }

    public void setpersonneList(ArrayList<Personne> personneList) {
        this.personneList = personneList;
    }

    public void addPersonne(Personne p) {
        if (this.personneList == null) this.personneList = new ArrayList<>();
        if (!this.personneList.contains(p) && !p.getStatus().getStatus().equals("Charge de cours")) {
            this.personneList.add(p);
            if (!p.getCoursList().contains(this)) p.addCours(this);
        }
    }

    public void removePersonne(Personne p) {
        if (this.personneList != null && this.personneList.contains(p)) {
            this.personneList.remove(p);
            p.removeCours(this);
        }
    }
}
