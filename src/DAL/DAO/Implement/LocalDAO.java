package DAL.DAO.Implement;
import BL.Local.Local;
import DAL.DAO.DAO;
import DAL.Singleton.Singleton;
import org.postgresql.util.PSQLException;
import java.sql.SQLException;
import java.util.ArrayList;
public class LocalDAO extends DAO<Local> {

    public LocalDAO(Singleton single) {
        super(single);
    }

    @Override
    public Local find(int id) {
        Local local = null;
        try {
            pstmt = single.getConnection().prepareStatement("SELECT numero, lieu, \"type\" FROM Local WHERE id = ?;");
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                local = new Local(id, rs.getString("numero"), rs.getString("lieu"), rs.getString("type"));
            }   
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return local;
    }

    @Override
    public Local find(Local obj) {
        Local local = null;
        try {
            pstmt = single.getConnection().prepareStatement("SELECT id, \"type\" FROM Local WHERE numero = ? AND lieu = ?;");
            pstmt.setString(1, obj.getNumber());
            pstmt.setString(2, obj.getLocation());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                local = new Local(rs.getInt("id"), obj.getNumber(), obj.getLocation(), rs.getString("type"));
            }   
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return local;
    }

    @Override
    public ArrayList<Local> findAll() {
        ArrayList<Local> localsList = new ArrayList<>();
        Local local = null;
        try {
            pstmt = single.getConnection().prepareStatement("SELECT id, numero, lieu, \"type\" FROM Local;");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                local = new Local(rs.getInt("id"), rs.getString("numero"), rs.getString("lieu"), rs.getString("type"));
                localsList.add(local);
            }   
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return localsList;
    }

    @Override
    public boolean create(Local obj) {
        boolean flag = false;
        try {
            pstmt = single.getConnection().prepareStatement("INSERT INTO Local (numero, lieu, \"type\") VALUES (?, ?, ?) ON CONFLICT (numero, lieu) DO NOTHING;");
            pstmt.setString(1, obj.getNumber());
            pstmt.setString(2, obj.getLocation());
            pstmt.setString(3, obj.getType());
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
    public boolean update(Local obj) {
        boolean flag = false;
        try {   
            pstmt = single.getConnection().prepareStatement("UPDATE Local SET numero = ?, lieu = ?, \"type\" = ? WHERE id = ?;");
            pstmt.setString(1, obj.getNumber());
            pstmt.setString(2, obj.getLocation());
            pstmt.setString(3, obj.getType());
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
            pstmt = single.getConnection().prepareStatement("DELETE FROM Local WHERE id = ?;");
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
