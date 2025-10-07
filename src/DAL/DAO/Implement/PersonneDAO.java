package DAL.DAO.Implement;
import BL.Personne.Personne;
import DAL.DAO.DAO;
import DAL.DAO.DAOFactory;
import DAL.Singleton.Singleton;
import org.postgresql.util.PSQLException;
import java.sql.SQLException;
import java.util.ArrayList;

public class PersonneDAO extends DAO<Personne> {

    public PersonneDAO(Singleton single) {
        super(single);
    }

    @Override
    public Personne find(int id) {
        Personne p = null;
        try {
            pstmt = single.getConnection().prepareStatement("SELECT nom, prenom, id_status FROM Personne WHERE id = ?;");
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                p = new Personne(id, rs.getString("nom"), rs.getString("prenom"), DAOFactory.getStatusDAO().find(rs.getInt("id_status")));
            }   
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return p;
    }

    @Override
    public Personne find(Personne obj) {
        Personne p = null;
        try {
            pstmt = single.getConnection().prepareStatement("SELECT id, id_status FROM Personne WHERE nom = ? AND prenom = ?;");
            pstmt.setString(1, obj.getLastName());
            pstmt.setString(2, obj.getFirstName());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                p = new Personne(rs.getInt("id"), obj.getLastName(), obj.getFirstName(), DAOFactory.getStatusDAO().find(rs.getInt("id_status")));
            }   
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return p;
    }

    @Override
    public ArrayList<Personne> findAll() {
        ArrayList<Personne> personnesList = new ArrayList<>();
        Personne p = null;
        try {
            pstmt = single.getConnection().prepareStatement("SELECT id, nom, prenom, id_status FROM Personne;");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                p = new Personne(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), DAOFactory.getStatusDAO().find(rs.getInt("id_status")));
                personnesList.add(p);
            }   
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return personnesList;
    }

    @Override
    public boolean create(Personne obj) {
        boolean flag = false;
        try {
            pstmt = single.getConnection().prepareStatement("INSERT INTO Personne (nom, prenom, id_status) VALUES (?, ?, ?);");
            pstmt.setString(1, obj.getLastName());
            pstmt.setString(2, obj.getFirstName());
            pstmt.setInt(3, obj.getStatus().getId());
            pstmt.executeUpdate();
            flag = true;
        } catch (PSQLException e) {
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return flag;
    }

    @Override
    public boolean update(Personne obj) {
        boolean flag = false;
        try {
            pstmt = single.getConnection().prepareStatement("UPDATE Personne SET nom = ?, prenom = ?, id_status = ? WHERE id = ?;");
            pstmt.setString(1, obj.getLastName());
            pstmt.setString(2, obj.getFirstName());
            pstmt.setInt(3, obj.getStatus().getId());
            pstmt.setInt(4, obj.getId());
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
        boolean flag = false;
        try {
            pstmt = single.getConnection().prepareStatement("DELETE FROM Personne WHERE id = ?;");
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return flag;
    }
}
