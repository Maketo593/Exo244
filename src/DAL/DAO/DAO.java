package DAL.DAO;
import DAL.Singleton.Singleton;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class DAO<T> {
    protected Singleton single;
    protected PreparedStatement pstmt;
    protected ResultSet rs;

    public DAO(Singleton single) {
        this.single = single;
    }

    public abstract T find(int id);
    public abstract T find(T obj);
    public abstract ArrayList<T> findAll();
    public abstract boolean create(T obj);
    public abstract boolean update(T obj);
    public abstract boolean delete(int id);

    protected void close() {
        try {
            if (this.rs != null) {
                rs.close();
            }
            if (this.pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
