import BL.CoursPersonne.Cours;
import BL.CoursPersonne.CoursPersonne;
import BL.CoursPersonne.Personne;
import BL.Section.Section;
import BL.Status.Status;
import DAL.DAO.DAOFactory;
import DAL.Singleton.Singleton;

public class App {
    public static void main(String[] args) throws Exception {
        Singleton.getInstance();
        
        // Creation des sections
        Section sectionInfo = new Section("Informatique de gestion");
        Section sectionDroit = new Section("Droit");
        DAOFactory.getSectionDAO().create(sectionInfo);
        DAOFactory.getSectionDAO().create(sectionDroit);

        if (sectionInfo.getCours() != null) {
            System.out.println("La section " + sectionInfo.getNom() + " a des cours :");
            for (Cours cours : sectionInfo.getCours()) {
                System.out.println("cours : " + cours.getNom());
            }
        } else {
            System.out.println("La section " + sectionInfo.getNom() + " n'a pas de cours");
        }

        if (sectionDroit.getCours() != null) {
            System.out.println("La section " + sectionDroit.getNom() + " a des cours :");
            for (Cours cours : sectionDroit.getCours()) {
                System.out.println("cours : " + cours.getNom());
            }
        } else {
            System.out.println("La section " + sectionDroit.getNom() + " n'a pas de cours");
        }

        // Creation des cours
        Cours coursReseaux = new Cours("Base de Réseaux", sectionInfo);
        Cours coursOS = new Cours("Systèmes d'exploitation", sectionInfo);
        Cours coursPOO = new Cours("Programmation orientée objet", sectionInfo);
        Cours coursDCivil = new Cours("Droit civil", sectionDroit);
        Cours coursDCommercial = new Cours("Droit commercial", sectionDroit);

        DAOFactory.getCoursDAO().create(coursReseaux);
        DAOFactory.getCoursDAO().create(coursOS);
        DAOFactory.getCoursDAO().create(coursPOO);
        DAOFactory.getCoursDAO().create(coursDCivil);
        DAOFactory.getCoursDAO().create(coursDCommercial);

        Singleton.getInstance().close();
    }
}
