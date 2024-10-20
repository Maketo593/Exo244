package DAL.DAO.Implement;
import BL.Status.Status;
import DAL.DAO.DAO;
import DAL.Singleton.Singleton;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.postgresql.util.PSQLException;

public class StatusDAO extends DAO<Status> {
    
    public StatusDAO(Singleton single) {
        super(single);
    }

    @Override
    public Status get(int id) {
        Status status = null;
        try {
            ps = single.getConnection().prepareStatement("SELECT status FROM Status WHERE id = ?;");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                status = new Status(id, rs.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return status;
    }

    @Override
    public Status get(Status obj) {
        Status status = null;
        try {
            ps = single.getConnection().prepareStatement("SELECT id FROM Status WHERE status = ?;");
            ps.setString(1, obj.getStatus());
            rs = ps.executeQuery();
            if (rs.next()) {
                status = new Status(rs.getInt("id"), obj.getStatus());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return status;
    }

    @Override
    public List<Status> getAll() {
        ArrayList<Status> statusList = new ArrayList<>();
        Status status = null;
        try {
            ps = single.getConnection().prepareStatement("SELECT id, status FROM Status;");
            rs = ps.executeQuery();
            while (rs.next()) {
                status = new Status(rs.getInt("id"), rs.getString("status"));
                statusList.add(status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return statusList;
    }

    @Override
    public boolean create(Status obj) {
        boolean flag = false;
        try {
            ps = single.getConnection().prepareStatement("INSERT INTO Status (status) VALUES (?) ON CONFLICT (status) DO NOTHING;");
            ps.setString(1, obj.getStatus());
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
    public boolean update(Status obj) {
        boolean flag = false;
        try {
            ps = single.getConnection().prepareStatement("UPDATE Status SET status = ? WHERE id = ?;");
            ps.setString(1, obj.getStatus());
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
            ps = single.getConnection().prepareStatement("DELETE FROM Status WHERE id = ?;");
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
