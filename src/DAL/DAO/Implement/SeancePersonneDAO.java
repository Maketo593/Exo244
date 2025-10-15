package DAL.DAO.Implement;
import BL.Personne.Personne;
import BL.Seance.Seance;
import BL.SeancePersonne.SeancePersonne;
import DAL.DAO.DAO;
import DAL.DAO.DAOFactory;
import DAL.Singleton.Singleton;
import org.postgresql.util.PSQLException;
import java.sql.SQLException;
import java.util.ArrayList;

public class SeancePersonneDAO extends DAO<SeancePersonne> {

    public SeancePersonneDAO(Singleton single) {
        super(single);
    }

    @Override
    public SeancePersonne find(int id) {
        throw new UnsupportedOperationException("méthode 'find' de la classe abstraite DAO non implémentée pour SeancePersonneDAO");
    }

    @Override
    public SeancePersonne find(SeancePersonne obj) {
        SeancePersonne sp = null;
        try {
            pstmt = single.getConnection().prepareStatement("SELECT 1 FROM Seance_Personne WHERE id_personne = ? AND id_seance = ?;");
            pstmt.setInt(1, obj.getPersonne().getId());
            pstmt.setInt(2, obj.getSeance().getId());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                sp = obj;
            }   
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return sp;
    }

    @Override
    public ArrayList<SeancePersonne> findAll() {
        ArrayList<SeancePersonne> spList = new ArrayList<>();
        SeancePersonne sp = null;
        try {
            pstmt = single.getConnection().prepareStatement("SELECT id_personne, id_seance FROM Seance_Personne;");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                sp = new SeancePersonne(DAOFactory.getPersonneDAO().find(rs.getInt("id_personne")), DAOFactory.getSeanceDAO().find(rs.getInt("id_seance")));
                spList.add(sp);
            }   
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return spList;
    }

    public ArrayList<SeancePersonne> findAllBySeance(Seance seance) {
        ArrayList<SeancePersonne> spList = new ArrayList<>();
        SeancePersonne sp = null;
        try {
            pstmt = single.getConnection().prepareStatement("SELECT id_personne FROM Seance_Personne WHERE id_seance = ?;");
            pstmt.setInt(1, seance.getId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                sp = new SeancePersonne(DAOFactory.getPersonneDAO().find(rs.getInt("id_personne")), seance);
                spList.add(sp);
            }   
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return spList;
    }

    public ArrayList<SeancePersonne> findAllByPersonne(Personne personne) {
        ArrayList<SeancePersonne> spList = new ArrayList<>();
        SeancePersonne sp = null;
        try {
            pstmt = single.getConnection().prepareStatement("SELECT id_seance FROM Seance_Personne WHERE id_personne = ?;");
            pstmt.setInt(1, personne.getId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                sp = new SeancePersonne(personne, DAOFactory.getSeanceDAO().find(rs.getInt("id_seance")));
                spList.add(sp);
            }   
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return spList;
    }

    @Override
    public boolean create(SeancePersonne obj) {
        boolean flag = false;
        try {
            pstmt = single.getConnection().prepareStatement("INSERT INTO Seance_Personne (id_personne, id_seance) VALUES (?, ?) ON CONFLICT (id_personne, id_seance) DO NOTHING;");
            pstmt.setInt(1, obj.getPersonne().getId());
            pstmt.setInt(2, obj.getSeance().getId());
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
    public boolean update(SeancePersonne obj) {
        throw new UnsupportedOperationException("méthode 'update' de la classe abstraite DAO non implémentée pour SeancePersonneDAO");
    }

    public boolean update(SeancePersonne oldObj, SeancePersonne newObj) {
        boolean flag = false;
        try {
            pstmt = single.getConnection().prepareStatement("UPDATE Seance_Personne SET id_personne = ?, id_seance = ? WHERE id_personne = ? AND id_seance = ?;");
            pstmt.setInt(1, newObj.getPersonne().getId());
            pstmt.setInt(2, newObj.getSeance().getId());
            pstmt.setInt(3, oldObj.getPersonne().getId());
            pstmt.setInt(4, oldObj.getSeance().getId());
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
        throw new UnsupportedOperationException("méthode 'delete' de la classe abstraite DAO non implémentée pour SeancePersonneDAO");
    }

    public boolean delete(SeancePersonne obj) {
        boolean flag = false;
        try {
            pstmt = single.getConnection().prepareStatement("DELETE FROM Seance_Personne WHERE id_personne = ? AND id_seance = ?;");
            pstmt.setInt(1, obj.getPersonne().getId());
            pstmt.setInt(2, obj.getSeance().getId());
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
