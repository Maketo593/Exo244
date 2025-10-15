import BL.Cours.Cours;
import BL.Cours_Personne.CoursPersonne;
import BL.Local.Local;
import BL.Personne.Personne;
import BL.Seance.Seance;
import BL.SeancePersonne.SeancePersonne;
import BL.Section.Section;
import BL.Status.Status;
import DAL.DAO.DAOFactory;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.time.LocalDate;


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
        System.out.println("Sections :");
        for (Section section : DAOFactory.getSectionDAO().findAll()) {
            System.out.println("[" + section.getId() +"] " + section.getname());  
        }

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
        System.out.println("Cours :");
        for (Cours cours : DAOFactory.getCoursDAO().findAll()) {
            System.out.println("[" + cours.getId() +"] " + cours.getname() + " - Section: " + cours.getSection().getname());
        }

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
        System.out.println("Status :");
        for (Status status : DAOFactory.getStatusDAO().findAll()) {
            System.out.println("[" + status.getId() +"] " + status.getname());
        }

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
        System.out.println("Locaux :");
        for (Local local : DAOFactory.getLocalDAO().findAll()) {
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
        DAOFactory.getPersonneDAO().create(pouletGilles);
        DAOFactory.getPersonneDAO().create(godissartEmmanuel);
        DAOFactory.getPersonneDAO().create(laiValeria);
        DAOFactory.getPersonneDAO().create(mairesseDavid);
        DAOFactory.getPersonneDAO().create(durantRichard);
        DAOFactory.getPersonneDAO().create(ortizValerie);
        // Récupération des Personnes depuis la base de données
        pouletGilles = DAOFactory.getPersonneDAO().find(pouletGilles);
        godissartEmmanuel = DAOFactory.getPersonneDAO().find(godissartEmmanuel);
        laiValeria = DAOFactory.getPersonneDAO().find(laiValeria);
        mairesseDavid = DAOFactory.getPersonneDAO().find(mairesseDavid);
        durantRichard = DAOFactory.getPersonneDAO().find(durantRichard);
        ortizValerie = DAOFactory.getPersonneDAO().find(ortizValerie);
        // Affichage des Personnes
        System.out.println("Personnes :");
        for (Personne personne : DAOFactory.getPersonneDAO().findAll()) {
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
        DAOFactory.getSeanceDAO().create(seance080925);
        DAOFactory.getSeanceDAO().create(seance150925);
        DAOFactory.getSeanceDAO().create(seance220925);
        DAOFactory.getSeanceDAO().create(seance090925);
        DAOFactory.getSeanceDAO().create(seance160925);
        DAOFactory.getSeanceDAO().create(seance230925);
        // Récupération des séances depuis la base de données
        seance080925 = DAOFactory.getSeanceDAO().find(seance080925);
        seance150925 = DAOFactory.getSeanceDAO().find(seance150925);
        seance220925 = DAOFactory.getSeanceDAO().find(seance220925);
        seance090925 = DAOFactory.getSeanceDAO().find(seance090925);
        seance160925 = DAOFactory.getSeanceDAO().find(seance160925);
        seance230925 = DAOFactory.getSeanceDAO().find(seance230925);

        
        // Association des personnes aux Cours
        CoursPersonne cp1 = new CoursPersonne(coursSysteme, durantRichard, LocalDate.of(2025, 9, 1));
        CoursPersonne cp2 = new CoursPersonne(coursRéseaux, durantRichard, LocalDate.of(2025, 9, 1));
        CoursPersonne cp3 = new CoursPersonne(coursRéseaux, ortizValerie, LocalDate.of(2025, 9, 1));
        // Insertion des associations dans la base de données
        DAOFactory.getCoursPersonneDAO().create(cp1);
        DAOFactory.getCoursPersonneDAO().create(cp2);
        DAOFactory.getCoursPersonneDAO().create(cp3);

        // Association des personnes aux séances
        SeancePersonne sp1 = new SeancePersonne(durantRichard, seance080925);
        SeancePersonne sp2 = new SeancePersonne(durantRichard, seance150925);
        SeancePersonne sp3 = new SeancePersonne(durantRichard, seance220925);
        SeancePersonne sp4 = new SeancePersonne(durantRichard, seance090925);
        SeancePersonne sp5 = new SeancePersonne(ortizValerie, seance090925);
        SeancePersonne sp6 = new SeancePersonne(durantRichard, seance160925);
        SeancePersonne sp7 = new SeancePersonne(durantRichard, seance230925);
        // Insertion des associations dans la base de données
        DAOFactory.getSeancePersonneDAO().create(sp1);
        DAOFactory.getSeancePersonneDAO().create(sp2);
        DAOFactory.getSeancePersonneDAO().create(sp3);
        DAOFactory.getSeancePersonneDAO().create(sp4);
        DAOFactory.getSeancePersonneDAO().create(sp5);
        DAOFactory.getSeancePersonneDAO().create(sp6);
        DAOFactory.getSeancePersonneDAO().create(sp7);

        // Affichage des séances
        ArrayList<Seance> seances = DAOFactory.getSeanceDAO().findAll();
        System.out.println("Séances :");
        for (Seance seance : seances) {
            ArrayList<SeancePersonne> participants = DAOFactory.getSeancePersonneDAO().findAllBySeance(seance);
            for(SeancePersonne sp : participants) {
                System.out.println("[" + sp.getSeance().getId() +"] " + sp.getSeance().getDate() + " - " + sp.getSeance().getLocal().getNumber() + " - " + sp.getSeance().getCours().getname() + " - " + sp.getPersonne().getLastName() + " " + sp.getPersonne().getFirstName());
            }
            //System.out.println("[" + seance.getId() +"] " + seance.getDate() + " - " + seance.getLocal().getNumber() + " - " + seance.getCours().getname());
        }
    }
}
