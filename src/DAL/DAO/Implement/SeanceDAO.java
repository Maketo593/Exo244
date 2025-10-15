package DAL.DAO.Implement;
import BL.Seance.Seance;
import DAL.DAO.DAO;
import DAL.DAO.DAOFactory;
import DAL.Singleton.Singleton;
import org.postgresql.util.PSQLException;
import java.sql.SQLException;
import java.util.ArrayList;

public class SeanceDAO extends DAO<Seance> {

    public SeanceDAO(Singleton single) {
        super(single);
    }

    @Override
    public Seance find(int id) {
        Seance seance = null;
        try {
            pstmt = single.getConnection().prepareStatement("SELECT id_cours, id_local, date FROM Seance WHERE id = ?;");
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                seance = new Seance(id, DAOFactory.getCoursDAO().find(rs.getInt("id_cours")), DAOFactory.getLocalDAO().find(rs.getInt("id_local")), rs.getTimestamp("date"));
            }   
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return seance;
    }

    @Override
    public Seance find(Seance obj) {
        Seance seance = null;
        try {
            pstmt = single.getConnection().prepareStatement("SELECT id, id_local FROM Seance WHERE id_cours = ? AND date = ?;");
            pstmt.setInt(1, obj.getCours().getId());
            pstmt.setTimestamp(2, obj.getDate());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                seance = new Seance(rs.getInt("id"), obj.getCours(), DAOFactory.getLocalDAO().find(rs.getInt("id_local")), obj.getDate());
            }   
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return seance;
    }

    @Override
    public ArrayList<Seance> findAll() {
        ArrayList<Seance> seanceList = new ArrayList<>();
        Seance seance = null;
        try {
            pstmt = single.getConnection().prepareStatement("SELECT id, id_cours, id_local, date FROM Seance;");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                seance = new Seance(rs.getInt("id"), DAOFactory.getCoursDAO().find(rs.getInt("id_cours")), DAOFactory.getLocalDAO().find(rs.getInt("id_local")), rs.getTimestamp("date"));
                seanceList.add(seance);
            }   
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return seanceList;
    }

    @Override
    public boolean create(Seance obj) {
        boolean flag = false;
        try {
            pstmt = single.getConnection().prepareStatement("INSERT INTO Seance (id_cours, id_local, date) VALUES (?, ?, ?) ON CONFLICT (id_cours, id_local, date) DO NOTHING;");
            pstmt.setInt(1, obj.getCours().getId());
            pstmt.setInt(2, obj.getLocal().getId());
            pstmt.setTimestamp(3, obj.getDate());
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
    public boolean update(Seance obj) {
        boolean flag = false;
        try {
            pstmt = single.getConnection().prepareStatement("UPDATE Seance SET id_cours = ?, id_local = ?, date = ? WHERE id = ?;");
            pstmt.setInt(1, obj.getCours().getId());
            pstmt.setInt(2, obj.getLocal().getId());
            pstmt.setTimestamp(3, obj.getDate());
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
            pstmt = single.getConnection().prepareStatement("DELETE FROM Seance WHERE id = ?;");
            pstmt.setInt(1, id);
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
