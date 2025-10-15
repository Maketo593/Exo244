package DAL.DAO.Implement;
import BL.Cours.Cours;
import BL.Cours_Personne.CoursPersonne;
import BL.Personne.Personne;
import DAL.DAO.DAO;
import DAL.DAO.DAOFactory;
import DAL.Singleton.Singleton;
import org.postgresql.util.PSQLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
public class CoursPersonneDAO extends DAO<CoursPersonne> {

    public CoursPersonneDAO(Singleton single) {
        super(single);
    }

    @Override
    public CoursPersonne find(int id) {
        throw new UnsupportedOperationException("Méthode 'find' de la classe abstraite DAO non implémentée pour CoursPersonneDAO");
    }

    @Override
    public CoursPersonne find(CoursPersonne obj) {
        CoursPersonne cp = null;
        try {
            pstmt = single.getConnection().prepareStatement("SELECT annee_academique FROM Cours_Personne WHERE id_cours = ? AND id_personne = ?;");
            pstmt.setInt(1, obj.getCours().getId());
            pstmt.setInt(2, obj.getPersonne().getId());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                cp = obj;
                cp.setAnnnee_academique(rs.getDate("annee_academique").toLocalDate());
            }   
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return cp;
    }

    @Override
    public ArrayList<CoursPersonne> findAll() {
        ArrayList<CoursPersonne> cpList = new ArrayList<>();
        CoursPersonne cp = null;
        try {
            pstmt = single.getConnection().prepareStatement("SELECT id_cours, id_personne, annee_academique FROM Cours_Personne;");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                cp = new CoursPersonne(DAOFactory.getCoursDAO().find(rs.getInt("id_cours")), DAOFactory.getPersonneDAO().find(rs.getInt("id_personne")), rs.getDate("annee_academique").toLocalDate());
                cpList.add(cp);
            }   
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return cpList;
    }

    public ArrayList<CoursPersonne> findAllByCours(Cours cours) {
        ArrayList<CoursPersonne> cpList = new ArrayList<>();
        CoursPersonne cp = null;
        try {
            pstmt = single.getConnection().prepareStatement("SELECT id_personne, annee_academique FROM Cours_Personne WHERE id_cours = ?;");
            pstmt.setInt(1, cours.getId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                cp = new CoursPersonne(cours, DAOFactory.getPersonneDAO().find(rs.getInt("id_personne")), rs.getDate("annee_academique").toLocalDate());
                cpList.add(cp);
            }   
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return cpList;
    }
    public ArrayList<CoursPersonne> findAllByPersonne(Personne personne) {
        ArrayList<CoursPersonne> cpList = new ArrayList<>();
        CoursPersonne cp = null;
        try {
            pstmt = single.getConnection().prepareStatement("SELECT id_cours, annee_academique FROM Cours_Personne WHERE id_personne = ?;");
            pstmt.setInt(1, personne.getId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                cp = new CoursPersonne(DAOFactory.getCoursDAO().find(rs.getInt("id_cours")), personne, rs.getDate("annee_academique").toLocalDate());
                cpList.add(cp);
            }   
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return cpList;
    }

    @Override
    public boolean create(CoursPersonne obj) {
        boolean flag = false;
        try {
            pstmt = single.getConnection().prepareStatement("INSERT INTO Cours_Personne (id_cours, id_personne, annee_academique) VALUES (?, ?, ?) ON CONFLICT (id_cours, id_personne) DO NOTHING;");
            pstmt.setInt(1, obj.getCours().getId());
            pstmt.setInt(2, obj.getPersonne().getId());
            pstmt.setDate(3, Date.valueOf(obj.getAnnnee_academique()));
            pstmt.executeUpdate();
            flag = true;
        } catch (PSQLException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return flag;
    }

    @Override
    public boolean update(CoursPersonne obj) {
        throw new UnsupportedOperationException("Méthode 'update' de la classe abstraite DAO non implémentée pour CoursPersonneDAO");
    }

    public boolean update(CoursPersonne oldObj, CoursPersonne newObj) {
        boolean flag = false;
        try {
            pstmt = single.getConnection().prepareStatement("UPDATE Cours_Personne SET id_cours = ?, id_personne = ?, annee_academique = ? WHERE id_cours = ? AND id_personne = ?;");
            pstmt.setInt(1, newObj.getCours().getId());
            pstmt.setInt(2, newObj.getPersonne().getId());
            pstmt.setDate(3, Date.valueOf(newObj.getAnnnee_academique()));
            pstmt.setInt(4, oldObj.getCours().getId());
            pstmt.setInt(5, oldObj.getPersonne().getId());
            pstmt.executeUpdate();
            flag = true;
        } catch (PSQLException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return flag;
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Méthode 'delete' de la classe abstraite DAO non implémentée pour CoursPersonneDAO");
    }
    public boolean delete(CoursPersonne obj) {
        boolean flag = false;
        try {
            pstmt = single.getConnection().prepareStatement("DELETE FROM Cours_Personne WHERE id_cours = ? AND id_personne = ?;");
            pstmt.setInt(1, obj.getCours().getId());
            pstmt.setInt(2, obj.getPersonne().getId());
            pstmt.executeUpdate();
            flag = true;
        } catch (PSQLException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return flag;
    }
}
