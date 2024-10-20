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
        statusEtudiant = DAOFactory.getStatusDAO().get(statusEtudiant);
        statusEnseignant = DAOFactory.getStatusDAO().get(statusEnseignant);
        statusEmpAdmin = DAOFactory.getStatusDAO().get(statusEmpAdmin);

        // Création des Sections
        Section sectionInfo = new Section("Informatique de gestion");
        Section sectionDroit = new Section("Droit");
        DAOFactory.getSectionDAO().create(sectionInfo);
        DAOFactory.getSectionDAO().create(sectionDroit);
        sectionInfo = DAOFactory.getSectionDAO().get(sectionInfo);
        sectionDroit = DAOFactory.getSectionDAO().get(sectionDroit);

        // Création des Cours
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
        coursReseaux = DAOFactory.getCoursDAO().get(coursReseaux);
        coursOS = DAOFactory.getCoursDAO().get(coursOS);
        coursPOO = DAOFactory.getCoursDAO().get(coursPOO);
        coursDCivil = DAOFactory.getCoursDAO().get(coursDCivil);
        coursDCommercial = DAOFactory.getCoursDAO().get(coursDCommercial);

        sectionInfo = DAOFactory.getSectionDAO().get(sectionInfo);
        sectionDroit = DAOFactory.getSectionDAO().get(sectionDroit);

        System.out.println("Cours de la section " + sectionInfo.getNom() + ":");
        for (Cours cours : sectionInfo.getCours()) {
            System.out.println(cours.getNom());
        }

        System.out.println("Cours de la section " + sectionDroit.getNom() + ":");
        for (Cours cours : sectionDroit.getCours()) {
            System.out.println(cours.getNom());
        }

        // Création des Personnes
        Personne pPoulet = new Personne("Poulet", "Gilles", statusEnseignant);
        Personne pGodissart = new Personne("Godissart", "Emmanuel", statusEnseignant);
        Personne pLai = new Personne("Lai", "Valeria", statusEmpAdmin);
        Personne pMairesse = new Personne("Mairesse", "David", statusEmpAdmin);
        Personne pDurant = new Personne("Durant", "Richard", statusEtudiant);
        Personne pOrtiz = new Personne("Ortiz", "Valerie", statusEtudiant);
        coursOS.setProf(pPoulet);
        coursReseaux.setProf(pGodissart);
        coursOS.addPersonne(pDurant);
        coursReseaux.addPersonne(pDurant);
        DAOFactory.getPersonneDAO().create(pPoulet);
        DAOFactory.getPersonneDAO().create(pGodissart);
        DAOFactory.getPersonneDAO().create(pLai);
        DAOFactory.getPersonneDAO().create(pMairesse);
        DAOFactory.getPersonneDAO().create(pDurant);
        DAOFactory.getPersonneDAO().create(pOrtiz);

        pPoulet = DAOFactory.getPersonneDAO().get(pPoulet);
        pGodissart = DAOFactory.getPersonneDAO().get(pGodissart);
        pLai = DAOFactory.getPersonneDAO().get(pLai);
        pMairesse = DAOFactory.getPersonneDAO().get(pMairesse);
        pDurant = DAOFactory.getPersonneDAO().get(pDurant);
        pOrtiz = DAOFactory.getPersonneDAO().get(pOrtiz);

        coursOS.setAnnee(2024);
        coursReseaux.setAnnee(2024);
        DAOFactory.getCoursDAO().addPersonne(pPoulet, coursOS);
        DAOFactory.getCoursDAO().addPersonne(pGodissart, coursReseaux);
        DAOFactory.getCoursDAO().addPersonne(pDurant, coursOS);
        DAOFactory.getCoursDAO().addPersonne(pDurant, coursReseaux);
        
        pPoulet = DAOFactory.getPersonneDAO().get(pPoulet);
        pGodissart = DAOFactory.getPersonneDAO().get(pGodissart);
        pLai = DAOFactory.getPersonneDAO().get(pLai);
        pMairesse = DAOFactory.getPersonneDAO().get(pMairesse);
        pDurant = DAOFactory.getPersonneDAO().get(pDurant);
        pOrtiz = DAOFactory.getPersonneDAO().get(pOrtiz);

        System.out.println("\nAprès intégration de toutes les données :\n");

        System.out.println("Section " + sectionInfo.getNom() + ":");
        for (Cours cours : sectionInfo.getCours()) {
            if (cours.getProf() != null) System.out.println("\t" + cours.getNom() + " "  + cours.getProf().getStatus().getStatus() + " : " + cours.getProf().getNom() + " " + cours.getProf().getPrenom());
            else System.out.println("\t" + cours.getNom() + " : Aucun enseignant");
            if (cours.getpersonneList() != null && !cours.getpersonneList().isEmpty()) {
                for (Personne personne : cours.getpersonneList()) {
                    System.out.println("\t\t" + personne.getNom() + " " + personne.getPrenom());
                }
            }
        }
        
        System.out.println("Section " + sectionDroit.getNom() + ":");
        for (Cours cours : sectionDroit.getCours()) {
            if(cours.getProf() != null) System.out.println("\t" + cours.getNom() + " "  + cours.getProf().getStatus().getStatus() + " : " + cours.getProf().getNom() + " " + cours.getProf().getPrenom());
            else System.out.println("\t" + cours.getNom() + " : Aucun enseignant");
            if (cours.getpersonneList() != null && !cours.getpersonneList().isEmpty()) {
                for (Personne personne : cours.getpersonneList()) {
                    System.out.println("\t\t" + personne.getNom() + " " + personne.getPrenom());
                }
            }
        }
        Singleton.getInstance().close();
    }
}
