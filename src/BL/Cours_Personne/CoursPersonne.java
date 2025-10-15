package BL.Cours_Personne;

import java.util.Date;
import BL.Cours.Cours;
import BL.Personne.Personne;

public class CoursPersonne {
    private Cours cours;
    private Personne personne;
    private Date annnee_academique;

    public CoursPersonne(Cours cours, Personne personne, Date annee_academique) {
        this.cours = cours;
        this.personne = personne;
        this.annnee_academique = annee_academique;
    }

    public Cours getCours() {
        return this.cours;
    }
    public void setCours(Cours cours) {
        this.cours = cours;
    }
    public Personne getPersonne() {
        return this.personne;
    }
    public void setPersonne(Personne personne) {
        this.personne = personne;
    }
    public Date getAnnnee_academique() {
        return this.annnee_academique;
    }
    public void setAnnnee_academique(Date annnee_academique) {
        this.annnee_academique = annnee_academique;
    }
}
