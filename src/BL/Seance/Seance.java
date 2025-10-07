package BL.Seance;
import BL.Cours.Cours;
import BL.Local.Local;
import java.sql.Timestamp;
public class Seance {

    private final int id;
    private Cours cours;
    private Local local;
    private Timestamp date;

    public Seance(int id, Cours cours, Local local, Timestamp date) {
        this.id = id;
        this.cours = cours;
        this.local = local;
        this.date = date;
    }

    public int getId() {
        return this.id;
    }
    public Cours getCours() {
        return this.cours;
    }

    public void setCours(Cours cours) {
        if(!this.cours.equals(null)) {
            this.cours.removeSeance(this);
        }
        this.cours = cours;
    }

    public Local getLocal() {
        return this.local;
    }
    public Timestamp getDate() {
        return this.date;
    }
}
