package BL.Personne;
import BL.Status.Status;

public class Personne {
    private final int id;
    private String nom;
    private String prenom;
    private Status status;

    public Personne(int id, String nom, String prenom, Status status) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.status = status;
    }

    public int getId() {
        return this.id;
    }
    public String getNom() {
        return this.nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return this.prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public Status getStatus() {
        return this.status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}
