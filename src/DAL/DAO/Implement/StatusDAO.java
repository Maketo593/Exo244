package DAL.DAO.Implement;
import DAL.DAO.DAO;
import DAL.Singleton.Singleton;
import BL.Status.Status;
import org.postgresql.util.PSQLException;
import java.sql.SQLException;
import java.util.ArrayList;

public class StatusDAO extends DAO<Status> {
    public StatusDAO(Singleton single) {
        super(single);
    }

    @Override
    public Status find(int id) {
        Status status = null;
        try {
            pstmt = single.getConnection().prepareStatement("SELECT nom FROM Status WHERE id = ?;");
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                status = new Status(id, rs.getString("nom"));
            }   
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return status;
    }

    @Override
    public Status find(Status obj) {
        Status status = null;
        try {
            pstmt = single.getConnection().prepareStatement("SELECT id, nom FROM Status WHERE nom = ?;");
            pstmt.setString(1, obj.getname());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                status = new Status(rs.getInt("id"), rs.getString("nom"));
            }   
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return status;
    }

    @Override
    public ArrayList<Status> findAll() {
        ArrayList<Status> statusList = new ArrayList<>();
        Status status = null;
        try {
            pstmt = single.getConnection().prepareStatement("SELECT id, nom FROM Status;");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                status = new Status(rs.getInt("id"), rs.getString("nom"));
                statusList.add(status);
            }   
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return statusList;
    }

    @Override
    public boolean create(Status obj) {
        boolean flag = false;
        try {
            pstmt = single.getConnection().prepareStatement("INSERT INTO Status (nom) VALUES (?) ON CONFLICT DO NOTHING;");
            pstmt.setString(1, obj.getname());
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
    public boolean update(Status obj) {
        boolean flag = false;
        try {
            pstmt = single.getConnection().prepareStatement("UPDATE Status SET nom = ? WHERE id = ?;");
            pstmt.setString(1, obj.getname());
            pstmt.setInt(2, obj.getId());
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
            pstmt = single.getConnection().prepareStatement("DELETE FROM Status WHERE id = ?;");
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
