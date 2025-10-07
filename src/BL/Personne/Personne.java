package BL.Personne;
import BL.Status.Status;

public class Personne {
    private final int id;
    private String lastName;
    private String firstName;
    private Status status;

    public Personne(int id, String lastName, String firstName, Status status) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.status = status;
    }

    public int getId() {
        return this.id;
    }
    public String getLastName() {
        return this.lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getFirstName() {
        return this.firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public Status getStatus() {
        return this.status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}
