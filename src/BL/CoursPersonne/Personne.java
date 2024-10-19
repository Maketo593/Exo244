package BL.CoursPersonne;
import java.util.ArrayList;
import BL.Status.Status;

public class Personne {
    private int id;
    private String nom;
    private String prenom;
    private Status status;
    private ArrayList<Cours> coursList;

    public Personne(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public Personne(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Personne(String nom, String prenom, Status status) {
        this.nom = nom;
        this.prenom = prenom;
        this.status = status;
    }

    public Personne(int id, String nom, String prenom, Status status) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.status = status;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ArrayList<Cours> getCoursList() {
        return coursList;
    }

    public void setCoursList(ArrayList<Cours> coursList) {
        this.coursList = coursList;
    }

    public void addCours(Cours cours) {
        if (this.coursList != null && !this.coursList.contains(cours)) this.coursList.add(cours);
    }

    public void removeCours(Cours cours) {
        if (this.coursList != null && this.coursList.contains(cours)) {
            if (this.status.getStatus().equals("Charge de cours")) this.coursList.remove(cours);
        }
    }

    @Override
    public String toString() {
        if (this.status == null) return this.nom + " " + this.prenom;
        else return this.nom + " " + this.prenom + " : " + this.status.getStatus();
    }
}
