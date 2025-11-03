import BL.Cours.Cours;
import BL.Cours_Personne.CoursPersonne;
import BL.Local.Local;
import BL.Personne.Personne;
import BL.Seance.Seance;
import BL.SeancePersonne.SeancePersonne;
import BL.Section.Section;
import BL.Status.Status;
import DAL.DAO.DAOFactory;
import DAL.DAO.Implement.CoursDAO;
import DAL.DAO.Implement.CoursPersonneDAO;
import DAL.DAO.Implement.LocalDAO;
import DAL.DAO.Implement.PersonneDAO;
import DAL.DAO.Implement.SeanceDAO;
import DAL.DAO.Implement.SeancePersonneDAO;
import DAL.DAO.Implement.SectionDAO;
import DAL.DAO.Implement.StatusDAO;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.time.LocalDate;


public class App {
    public static void main(String[] args) throws Exception {        
        // Création des sections
        Section sectionInfo = new Section(-1,"Informatique de gestion");
        Section sectionDroit = new Section(-1,"Droit");
        // Insertion des sections dans la base de données
        SectionDAO sectionDAO =  DAOFactory.getSectionDAO();
        sectionDAO.create(sectionInfo);
        sectionDAO.create(sectionDroit);
        // Récupération des sections depuis la base de données
        sectionInfo = sectionDAO.find(sectionInfo);
        sectionDroit = sectionDAO.find(sectionDroit);
        // Affichage des sections
        System.out.println("Sections :");
        for (Section section : sectionDAO.findAll()) {
            System.out.println("[" + section.getId() +"] " + section.getname());  
        }

        // Création des cours
        Cours coursRéseaux = new Cours(-1,"Base des réseaux",sectionInfo);
        Cours coursSysteme = new Cours(-1,"Système d'exploitation",sectionInfo);
        Cours coursPOO = new Cours(-1,"Programmation orientée objet",sectionInfo);
        Cours coursDroitCivil = new Cours(-1,"Droit civil",sectionDroit);
        Cours coursDroitcom = new Cours(-1,"Droit commercial",sectionDroit);
        // Insertion des cours dans la base de données
        CoursDAO coursDAO = DAOFactory.getCoursDAO();
        coursDAO.create(coursRéseaux);
        coursDAO.create(coursSysteme);
        coursDAO.create(coursPOO);
        coursDAO.create(coursDroitCivil);
        coursDAO.create(coursDroitcom);
        // Récupération des cours depuis la base de données
        coursRéseaux = coursDAO.find(coursRéseaux);
        coursSysteme = coursDAO.find(coursSysteme);
        coursPOO = coursDAO.find(coursPOO);
        coursDroitCivil = coursDAO.find(coursDroitCivil);
        coursDroitcom = coursDAO.find(coursDroitcom);
        // Affichage des cours
        System.out.println("Cours :");
        for (Cours cours : coursDAO.findAll()) {
            System.out.println("[" + cours.getId() +"] " + cours.getname() + " - Section: " + cours.getSection().getname());
        }

        // Création des Status
        Status statusEtudiant = new Status(-1,"Etudiant");
        Status statusEnseignant = new Status(-1,"Charge de cours");
        Status statusEmpAdmin = new Status(-1,"Employe administratif");
        // Insertion des Status dans la base de données
        StatusDAO statusDAO =  DAOFactory.getStatusDAO();
        statusDAO.create(statusEtudiant);
        statusDAO.create(statusEnseignant);
        statusDAO.create(statusEmpAdmin);
        // Récupération des Status depuis la base de données
        statusEtudiant = statusDAO.find(statusEtudiant);
        statusEnseignant = statusDAO.find(statusEnseignant);
        statusEmpAdmin = statusDAO.find(statusEmpAdmin);
        // Affichage des Status
        System.out.println("Status :");
        for (Status status : statusDAO.findAll()) {
            System.out.println("[" + status.getId() +"] " + status.getname());
        }

        // Création des Locaux
        Local local3023 = new Local(-1,"3023","HelHa","STD");
        Local localPS02 = new Local(-1,"PS02","SLuc","INF");
        Local local2066 = new Local(-1,"2066","HelHa","INF");
        Local local3220 = new Local(-1,"3220","HelHa","STD");
        // Insertion des Locaux dans la base de données
        LocalDAO localDAO = DAOFactory.getLocalDAO();
        localDAO.create(local3023);
        localDAO.create(localPS02);
        localDAO.create(local2066);
        localDAO.create(local3220);
        // Récupération des Locaux depuis la base de données
        local3023 = localDAO.find(local3023);
        localPS02 = localDAO.find(localPS02);
        local2066 = localDAO.find(local2066);
        local3220 = localDAO.find(local3220);
        // Affichage des Locaux
        System.out.println("Locaux :");
        for (Local local : localDAO.findAll()) {
            System.out.println("[" + local.getId() +"] " + local.getNumber() + " - " + local.getLocation() + " (" + local.getType() + ")");
        }
        
        // Création des Personnes
        Personne pouletGilles = new Personne(-1, "Gilles", "Poulet", statusEnseignant);
        Personne godissartEmmanuel = new Personne(-1, "Godissart", "Emmanuel", statusEnseignant);
        Personne laiValeria = new Personne(-1, "Lai", "Valeria", statusEmpAdmin);
        Personne mairesseDavid = new Personne(-1, "Mairesse", "David", statusEmpAdmin);
        Personne durantRichard = new Personne(-1, "Durant", "Richard", statusEtudiant);
        Personne ortizValerie = new Personne(-1, "Ortiz", "Valerie", statusEtudiant);
        // Insertion des Personnes dans la base de données
        PersonneDAO personneDAO = DAOFactory.getPersonneDAO();
        personneDAO.create(pouletGilles);
        personneDAO.create(godissartEmmanuel);
        personneDAO.create(laiValeria);
        personneDAO.create(mairesseDavid);
        personneDAO.create(durantRichard);
        personneDAO.create(ortizValerie);
        // Récupération des Personnes depuis la base de données
        pouletGilles = personneDAO.find(pouletGilles);
        godissartEmmanuel = personneDAO.find(godissartEmmanuel);
        laiValeria = personneDAO.find(laiValeria);
        mairesseDavid = personneDAO.find(mairesseDavid);
        durantRichard = personneDAO.find(durantRichard);
        ortizValerie = personneDAO.find(ortizValerie);
        // Affichage des Personnes
        System.out.println("Personnes :");
        for (Personne personne : personneDAO.findAll()) {
            System.out.println("[" + personne.getId() +"] " + personne.getLastName() + " " + personne.getFirstName() + " - " + personne.getStatus().getname());
        }
        
        // Création des séances
        Seance seance080925 = new Seance(-1, coursSysteme, local2066,Timestamp.valueOf("2025-09-08 18:00:00"));
        Seance seance150925 = new Seance(-1, coursSysteme, local2066,Timestamp.valueOf("2025-09-15 18:00:00"));
        Seance seance220925 = new Seance(-1, coursSysteme, localPS02,Timestamp.valueOf("2025-09-22 18:00:00"));
        Seance seance090925 = new Seance(-1, coursRéseaux, local3023,Timestamp.valueOf("2025-09-09 18:00:00"));
        Seance seance160925 = new Seance(-1, coursRéseaux, localPS02,Timestamp.valueOf("2025-09-16 18:00:00"));
        Seance seance230925 = new Seance(-1, coursRéseaux, localPS02,Timestamp.valueOf("2025-09-23 18:00:00"));
        // Insertion des séances dans la base de données
        SeanceDAO seanceDAO = DAOFactory.getSeanceDAO();
        seanceDAO.create(seance080925);
        seanceDAO.create(seance150925);
        seanceDAO.create(seance220925);
        seanceDAO.create(seance090925);
        seanceDAO.create(seance160925);
        seanceDAO.create(seance230925);
        // Récupération des séances depuis la base de données
        seance080925 = seanceDAO.find(seance080925);
        seance150925 = seanceDAO.find(seance150925);
        seance220925 = seanceDAO.find(seance220925);
        seance090925 = seanceDAO.find(seance090925);
        seance160925 = seanceDAO.find(seance160925);
        seance230925 = seanceDAO.find(seance230925);

        
        // Association des personnes aux Cours
        CoursPersonne cp1 = new CoursPersonne(coursSysteme, durantRichard, LocalDate.of(2025, 9, 1));
        CoursPersonne cp2 = new CoursPersonne(coursRéseaux, durantRichard, LocalDate.of(2025, 9, 1));
        CoursPersonne cp3 = new CoursPersonne(coursRéseaux, ortizValerie, LocalDate.of(2025, 9, 1));
        // Insertion des associations dans la base de données
        CoursPersonneDAO coursPersonneDAO = DAOFactory.getCoursPersonneDAO();
        coursPersonneDAO.create(cp1);
        coursPersonneDAO.create(cp2);
        coursPersonneDAO.create(cp3);

        // Association des personnes aux séances
        SeancePersonne sp1 = new SeancePersonne(durantRichard, seance080925);
        SeancePersonne sp2 = new SeancePersonne(durantRichard, seance150925);
        SeancePersonne sp3 = new SeancePersonne(durantRichard, seance220925);
        SeancePersonne sp4 = new SeancePersonne(durantRichard, seance090925);
        SeancePersonne sp5 = new SeancePersonne(ortizValerie, seance090925);
        SeancePersonne sp6 = new SeancePersonne(durantRichard, seance160925);
        SeancePersonne sp7 = new SeancePersonne(durantRichard, seance230925);
        // Insertion des associations dans la base de données
        SeancePersonneDAO seancePersonneDAO = DAOFactory.getSeancePersonneDAO();
        seancePersonneDAO.create(sp1);
        seancePersonneDAO.create(sp2);
        seancePersonneDAO.create(sp3);
        seancePersonneDAO.create(sp4);
        seancePersonneDAO.create(sp5);
        seancePersonneDAO.create(sp6);
        seancePersonneDAO.create(sp7);

        // Affichage des séances
        ArrayList<Seance> seances = seanceDAO.findAll();
        System.out.println("Séances :");
        for (Seance seance : seances) {
            ArrayList<SeancePersonne> participants = seancePersonneDAO.findAllBySeance(seance);
            for(SeancePersonne sp : participants) {
                System.out.println("[" + sp.getSeance().getId() +"] " + sp.getSeance().getDate() + " - " + sp.getSeance().getLocal().getNumber() + " - " + sp.getSeance().getCours().getname() + " - " + sp.getPersonne().getLastName() + " " + sp.getPersonne().getFirstName());
            }
        }
    }
}
