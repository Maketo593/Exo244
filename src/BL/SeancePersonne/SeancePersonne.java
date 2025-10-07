package BL.SeancePersonne;
import BL.Personne.Personne;
import BL.Seance.Seance;

public class SeancePersonne {
    private Personne personne;
    private Seance seance;

    public SeancePersonne(Personne personne, Seance seance) {
        this.personne = personne;
        this.seance = seance;
    }
    public Personne getPersonne() {
        return this.personne;
    }
    public void setPersonne(Personne personne) {
        this.personne = personne;
    }
    public Seance getSeance() {
        return this.seance;
    }
    public void setSeance(Seance seance) {
        this.seance = seance;
    }
}
