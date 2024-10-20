import BL.CoursPersonne.Cours;
import BL.CoursPersonne.Personne;
import BL.Section.Section;
import BL.Status.Status;
import DAL.DAO.DAOFactory;
import DAL.Singleton.Singleton;

public class App {
    public static void main(String[] args) throws Exception {
        Singleton.getInstance();

        // Création des Status
        Status statusEtudiant = new Status("Étudiant");
        Status statusEnseignant = new Status("Charge de cours");
        Status statusEmpAdmin = new Status("Employé administratif");
        DAOFactory.getStatusDAO().create(statusEtudiant);
        DAOFactory.getStatusDAO().create(statusEnseignant);
        DAOFactory.getStatusDAO().create(statusEmpAdmin);

        // Recupération des Status
        statusEtudiant = DAOFactory.getStatusDAO().get(statusEtudiant);
        statusEnseignant = DAOFactory.getStatusDAO().get(statusEnseignant);
        statusEmpAdmin = DAOFactory.getStatusDAO().get(statusEmpAdmin);

        // Création des Personnes
        Personne p1 = new Personne("Poulet", "Gilles", statusEnseignant);
        Personne p2 = new Personne("Godissart", "Emmanuel", statusEnseignant);
        Personne p3 = new Personne("Lai", "Valeria", statusEmpAdmin);
        Personne p4 = new Personne("Mairesse", "David", statusEmpAdmin);
        Personne p5 = new Personne("Durant", "Richard", statusEtudiant);
        Personne p6 = new Personne("Ortiz", "Valerie", statusEtudiant);
        DAOFactory.getPersonneDAO().create(p1);
        DAOFactory.getPersonneDAO().create(p2);
        DAOFactory.getPersonneDAO().create(p3);
        DAOFactory.getPersonneDAO().create(p4);
        DAOFactory.getPersonneDAO().create(p5);
        DAOFactory.getPersonneDAO().create(p6);

        DAOFactory.getStatusDAO().delete(1);

        // Recupération des Personnes
        for (Personne p : DAOFactory.getPersonneDAO().getAll()) {
            System.out.println(p.toString());
        }

        Singleton.getInstance().close();
    }
}
