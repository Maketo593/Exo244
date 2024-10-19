package DAL.DAO.Implement;
import DAL.DAO.DAO;
import DAL.DAO.DAOFactory;
import DAL.Singleton.Singleton;
import java.sql.SQLException;
import java.util.ArrayList;
import org.postgresql.util.PSQLException;
import BL.CoursPersonne.Personne;
import BL.Status.Status;

public class PersonneDAO extends DAO<Personne> {

    public PersonneDAO(Singleton single) {
        super(single);
    }

    @Override
    public Personne get(int id) {
        Personne p = null;
        try {
            ps = single.getConnection().prepareStatement("SELECT id_status, nom, prenom FROM Personne WHERE id = ?;");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                p = new Personne(id, rs.getString("nom"), rs.getString("prenom"), DAOFactory.getStatusDAO().get(rs.getInt("id_status")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return p;
    }

    @Override
    public Personne get(Personne obj) {
        Personne p = null;
        try {
            ps = single.getConnection().prepareStatement("SELECT id, id_status FROM Personne WHERE nom = ? AND prenom = ?;");
            ps.setString(1, obj.getNom());
            ps.setString(2, obj.getPrenom());
            rs = ps.executeQuery();
            if (rs.next()) {
                p = new Personne(rs.getInt("id"), obj.getNom(), obj.getPrenom(), DAOFactory.getStatusDAO().get(rs.getInt("id_status")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return p;
    }

    @Override
    public ArrayList<Personne> getAll() {
        ArrayList<Personne> personneList = null;
        try {
            ps = single.getConnection().prepareStatement("SELECT id, id_status, nom, prenom FROM Personne;");
            rs = ps.executeQuery();
            personneList = new ArrayList<>();
            while (rs.next()) {
                Personne p = new Personne(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"));
                Status s = DAOFactory.getStatusDAO().get(rs.getInt("id_status"));
                if (s != null) p.setStatus(s);
                personneList.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return personneList;
    }

    @Override
    public boolean create(Personne obj) {
        boolean flag = false;
        try {
            ps = single.getConnection().prepareStatement("INSERT INTO Personne (id_status, nom, prenom) VALUES (?, ?, ?) ON CONFLICT (nom, prenom) DO NOTHING;");
            ps.setInt(1, obj.getStatus().getId());
            ps.setString(2, obj.getNom());
            ps.setString(3, obj.getPrenom());            
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
    public boolean update(Personne obj) {
        boolean flag = false;
        try {
            ps = single.getConnection().prepareStatement("UPDATE Personne SET id_status = ?, nom = ?, prenom = ? WHERE id = ?;");
            ps.setInt(1, obj.getStatus().getId());
            ps.setString(2, obj.getNom());
            ps.setString(3, obj.getPrenom());
            ps.setInt(4, obj.getId());
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
            ps = single.getConnection().prepareStatement("DELETE FROM Personne WHERE id = ?;");
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
}
