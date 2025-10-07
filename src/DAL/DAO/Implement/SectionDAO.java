package DAL.DAO.Implement;
import BL.Section.Section;
import DAL.DAO.DAO;
import DAL.Singleton.Singleton;
import org.postgresql.util.PSQLException;
import java.sql.SQLException;
import java.util.ArrayList;

public class SectionDAO extends DAO<Section> {
    public SectionDAO(Singleton single) {
        super(single);
    }

    @Override
    public Section find(int id) {
        Section section = null;
        try {
            pstmt = single.getConnection().prepareStatement("SELECT name FROM Section WHERE id = ?;");
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                section = new Section(id, rs.getString("name"));
            }   
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return section;
    }

    @Override
    public Section find(Section obj) {
        Section section = null;
        try {
            pstmt = single.getConnection().prepareStatement("SELECT id, name FROM Section WHERE name = ?;");
            pstmt.setString(1, obj.getname());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                section = new Section(rs.getInt("id"), rs.getString("name"));
            }   
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return section;
    }

    @Override
    public ArrayList<Section> findAll() {
        ArrayList<Section> sectionsList = new ArrayList<>();
        Section section = null;
        try {
            pstmt = single.getConnection().prepareStatement("SELECT id, name FROM Section;");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                section = new Section(rs.getInt("id"), rs.getString("name"));
                sectionsList.add(section);
            }   
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return sectionsList;
    }

    @Override
    public boolean create(Section obj) {
        boolean flag = false;
        try {
            pstmt = single.getConnection().prepareStatement("INSERT INTO Section (name) VALUES (?) ON CONFLICT (name) DO NOTHING;");
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
    public boolean update(Section obj) {
        boolean flag = false;
        try {
            pstmt = single.getConnection().prepareStatement("UPDATE Section SET name = ? WHERE id = ?;");
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
            pstmt = single.getConnection().prepareStatement("DELETE FROM Section WHERE id = ?;");
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
