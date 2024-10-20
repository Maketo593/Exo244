package DAL.DAO.Implement;
import BL.CoursPersonne.Cours;
import BL.CoursPersonne.Personne;
import BL.Section.Section;
import DAL.DAO.DAO;
import DAL.DAO.DAOFactory;
import DAL.Singleton.Singleton;
import java.sql.SQLException;
import java.util.ArrayList;
import org.postgresql.util.PSQLException;

public class CoursDAO extends DAO<Cours> {

    public CoursDAO(Singleton single) {
        super(single);
        
    }

    @Override
    public Cours get(int id) {
        Cours cours = null;
        try {
            ps = single.getConnection().prepareStatement("SELECT id_section, nom FROM Cours WHERE id = ?;");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                cours = new Cours(id, rs.getString("nom"));
                cours.setSection(DAOFactory.getSectionDAO().get(rs.getInt("id_section")));
                DAOFactory.getPersonneDAO().getAllbyCours(cours);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return cours;
    }

    @Override
    public Cours get(Cours obj) {
        Cours cours = null;
        try {
            ps = single.getConnection().prepareStatement("SELECT id, id_section FROM Cours WHERE nom = ?;");
            ps.setString(1, obj.getNom());
            rs = ps.executeQuery();
            if (rs.next()) {
                cours = new Cours(rs.getInt("id"), obj.getNom());
                cours.setSection(DAOFactory.getSectionDAO().get(rs.getInt("id_section")));
                DAOFactory.getPersonneDAO().getAllbyCours(cours);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return cours;
    }

    @Override
    public ArrayList<Cours> getAll() {
        ArrayList<Cours> coursList = null;
        try {
            ps = single.getConnection().prepareStatement("SELECT id, id_section, nom FROM Cours;");
            rs = ps.executeQuery();
            coursList = new ArrayList<>();
            while (rs.next()) {
                Cours cours = new Cours(rs.getInt("id"), rs.getString("nom"));
                cours.setSection(DAOFactory.getSectionDAO().get(rs.getInt("id_section")));
                DAOFactory.getPersonneDAO().getAllbyCours(cours);
                coursList.add(cours);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return coursList;
    }
    
    public boolean getAllBySection(Section obj) {
        boolean flag = false;
        try {
            ps = single.getConnection().prepareStatement("SELECT id, nom FROM Cours WHERE id_section = ?;");
            ps.setInt(1, obj.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                Cours cours = new Cours(rs.getInt("id"), rs.getString("nom"));
                cours.setSection(obj);
                DAOFactory.getPersonneDAO().getAllbyCours(cours);
                obj.addCours(cours);
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return flag;
    }

    public boolean getAllbyPersonne(Personne obj) {
        boolean flag = false;
        try {
            ps = single.getConnection().prepareStatement("SELECT p.id, p.nom, p.prenom, s.status, cp.annee, c.id_section FROM Cours_Personne cp INNER JOIN Cours c ON cp.id_cours = c.id INNER JOIN Personne p ON cp.id_personne = p.id INNER JOIN Status s ON p.id_status = s.id WHERE cp.id_personne = ?;");
            ps.setInt(1, obj.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                Cours cours = new Cours(rs.getInt("id"), rs.getString("nom"));
                cours.setAnnee(rs.getInt("annee"));
                Section section = DAOFactory.getSectionDAO().get(rs.getInt("id_section"));
                if (section != null) cours.setSection(section);
                obj.addCours(cours);
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return flag;
    }

    @Override
    public boolean create(Cours obj) {
        boolean flag = false;
        try {
            ps = single.getConnection().prepareStatement("INSERT INTO Cours (nom, id_section) VALUES (?, ?) ON CONFLICT (id_section, nom) DO NOTHING;");
            ps.setString(1, obj.getNom());
            ps.setInt(2, obj.getSection().getId());
            ps.executeUpdate();
            flag = true;
        } catch (PSQLException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return flag;
    }

    public boolean addPersonne(Personne personne, Cours cours) {
        boolean flag = false;
        try {
            ps = single.getConnection().prepareStatement("INSERT INTO Cours_Personne (id_personne, id_cours, annee) VALUES (?, ?, ?) ON CONFLICT (id_personne, id_cours, annee) DO NOTHING;");
            ps.setInt(1, personne.getId());
            ps.setInt(2, cours.getId());
            ps.setInt(3, cours.getAnnee());
            ps.executeUpdate();
            flag = true;
        } catch (PSQLException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return flag;
    }

    @Override
    public boolean update(Cours obj) {
        boolean flag = false;
        try {
            ps = single.getConnection().prepareStatement("UPDATE Cours SET nom = ?, id_section = ? WHERE id = ?;");
            ps.setString(1, obj.getNom());
            ps.setInt(2, obj.getSection().getId());
            ps.setInt(3, obj.getId());
            ps.executeUpdate();
            flag = true;
        } catch (PSQLException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return flag;
    }

    public boolean updateAnnee(Cours obj) {
        boolean flag = false;
        try {
            ps = single.getConnection().prepareStatement("UPDATE Cours_Personne SET annee = ? WHERE id_cours = ?;");
            ps.setInt(1, obj.getAnnee());
            ps.setInt(2, obj.getId());
            ps.executeUpdate();
            flag = true;
        } catch (PSQLException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return flag;
    }

    @Override
    public boolean delete(int id) {
        boolean flag = false;
        try {
            ps = single.getConnection().prepareStatement("DELETE FROM Cours WHERE id = ?;");
            ps.setInt(1, id);
            ps.executeUpdate();
            flag = true;
        } catch (PSQLException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return flag;
    }

    public boolean removePersonne(Personne personne, Cours cours) {
        boolean flag = false;
        try {
            ps = single.getConnection().prepareStatement("DELETE FROM Cours_Personne WHERE id_personne = ? AND id_cours = ? AND annee = ?;");
            ps.setInt(1, personne.getId());
            ps.setInt(2, cours.getId());
            ps.setInt(3, cours.getAnnee());
            ps.executeUpdate();
            flag = true;
        } catch (PSQLException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return flag;
    }
}
