package DAL.DAO;
import DAL.Singleton.Singleton;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class DAO<T> {
    protected Singleton single;
    protected PreparedStatement ps;
    protected ResultSet rs;

    public DAO(Singleton single) {
        this.single = single;
    }

    public abstract T get(int id);
    public abstract T get(T obj);
    public abstract List<T> getAll();
    public abstract boolean create(T obj);
    public abstract boolean update(T obj);
    public abstract boolean delete(int id);
    protected void close() {
        try {
            if (this.rs != null) rs.close();
            if (this.ps != null) ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
