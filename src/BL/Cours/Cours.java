package BL.Cours;
import BL.Section.Section;

public class Cours {
    private final int id;
    private String name;
    private Section section;

    public Cours(int id, String name, Section section) {
        this.id = id;
        this.name = name;
        this.section = section;
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
}
