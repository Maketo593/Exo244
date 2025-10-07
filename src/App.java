import BL.Cours.Cours;
import BL.Local.Local;
import BL.Section.Section;
import BL.Status.Status;
import DAL.DAO.DAOFactory;

public class App {
    public static void main(String[] args) throws Exception {        
        // Création des sections
        Section sectionInfo = new Section(-1,"Informatique de gestion");
        Section sectionDroit = new Section(-1,"Droit");
        // Insertion des sections dans la base de données
        DAOFactory.getSectionDAO().create(sectionInfo);
        DAOFactory.getSectionDAO().create(sectionDroit);
        // Récupération des sections depuis la base de données
        sectionInfo = DAOFactory.getSectionDAO().find(sectionInfo);
        sectionDroit = DAOFactory.getSectionDAO().find(sectionDroit);
        // Affichage des sections
        System.out.println("[" + sectionInfo.getId() +"] " + sectionInfo.getname());
        System.out.println("[" + sectionDroit.getId() +"] " + sectionDroit.getname());

        // Création des cours
        Cours coursRéseaux = new Cours(-1,"Base des réseaux",sectionInfo);
        Cours coursSysteme = new Cours(-1,"Système d'exploitation",sectionInfo);
        Cours coursPOO = new Cours(-1,"Programmation orientée objet",sectionInfo);
        Cours coursDroitCivil = new Cours(-1,"Droit civil",sectionDroit);
        Cours coursDroitcom = new Cours(-1,"Droit commercial",sectionDroit);
        // Insertion des cours dans la base de données
        DAOFactory.getCoursDAO().create(coursRéseaux);
        DAOFactory.getCoursDAO().create(coursSysteme);
        DAOFactory.getCoursDAO().create(coursPOO);
        DAOFactory.getCoursDAO().create(coursDroitCivil);
        DAOFactory.getCoursDAO().create(coursDroitcom);
        // Récupération des cours depuis la base de données
        coursRéseaux = DAOFactory.getCoursDAO().find(coursRéseaux);
        coursSysteme = DAOFactory.getCoursDAO().find(coursSysteme);
        coursPOO = DAOFactory.getCoursDAO().find(coursPOO);
        coursDroitCivil = DAOFactory.getCoursDAO().find(coursDroitCivil);
        coursDroitcom = DAOFactory.getCoursDAO().find(coursDroitcom);
        // Affichage des cours
        System.out.println("[" + coursRéseaux.getId() +"] " + coursRéseaux.getname() + " - Section: " + coursRéseaux.getSection().getname());
        System.out.println("[" + coursSysteme.getId() +"] " + coursSysteme.getname() + " - Section: " + coursSysteme.getSection().getname());
        System.out.println("[" + coursPOO.getId() +"] " + coursPOO.getname() + " - Section: " + coursPOO.getSection().getname());
        System.out.println("[" + coursDroitCivil.getId() +"] " + coursDroitCivil.getname() + " - Section: " + coursDroitCivil.getSection().getname());
        System.out.println("[" + coursDroitcom.getId() +"] " + coursDroitcom.getname() + " - Section: " + coursDroitcom.getSection().getname());

        // Création des Status
        Status statusEtudiant = new Status(-1,"Etudiant");
        Status statusEnseignant = new Status(-1,"Charge de cours");
        Status statusEmpAdmin = new Status(-1,"Employe administratif");
        // Insertion des Status dans la base de données
        DAOFactory.getStatusDAO().create(statusEtudiant);
        DAOFactory.getStatusDAO().create(statusEnseignant);
        DAOFactory.getStatusDAO().create(statusEmpAdmin);
        // Récupération des Status depuis la base de données
        statusEtudiant = DAOFactory.getStatusDAO().find(statusEtudiant);
        statusEnseignant = DAOFactory.getStatusDAO().find(statusEnseignant);
        statusEmpAdmin = DAOFactory.getStatusDAO().find(statusEmpAdmin);
        // Affichage des Status
        System.out.println("[" + statusEtudiant.getId() +"] " + statusEtudiant.getname());
        System.out.println("[" + statusEnseignant.getId() +"] " + statusEnseignant.getname());
        System.out.println("[" + statusEmpAdmin.getId() +"] " + statusEmpAdmin.getname());

        // Création des Locaux
        Local local3023 = new Local(-1,"3023","HelHa","STD");
        Local localPS02 = new Local(-1,"PS02","SLuc","INF");
        Local local2066 = new Local(-1,"2066","HelHa","INF");
        Local local3220 = new Local(-1,"3220","HelHa","STD");
        // Insertion des Locaux dans la base de données
        DAOFactory.getLocalDAO().create(local3023);
        DAOFactory.getLocalDAO().create(localPS02);
        DAOFactory.getLocalDAO().create(local2066);
        DAOFactory.getLocalDAO().create(local3220);
        // Récupération des Locaux depuis la base de données
        local3023 = DAOFactory.getLocalDAO().find(local3023);
        localPS02 = DAOFactory.getLocalDAO().find(localPS02);
        local2066 = DAOFactory.getLocalDAO().find(local2066);
        local3220 = DAOFactory.getLocalDAO().find(local3220);
        // Affichage des Locaux
        System.out.println("[" + local3023.getId() +"] " + local3023.getNumber() + " - " + local3023.getLocation() + " (" + local3023.getType() + ")");
        System.out.println("[" + localPS02.getId() +"] " + localPS02.getNumber() + " - " + localPS02.getLocation() + " (" + localPS02.getType() + ")");
        System.out.println("[" + local2066.getId() +"] " + local2066.getNumber() + " - " + local2066.getLocation() + " (" + local2066.getType() + ")");
        System.out.println("[" + local3220.getId() +"] " + local3220.getNumber() + " - " + local3220.getLocation() + " (" + local3220.getType() + ")");
    }
}
