package DAL.DAO.Implement;
import BL.Cours.Cours;
import BL.Section.Section;
import DAL.DAO.DAO;
import DAL.DAO.DAOFactory;
import DAL.Singleton.Singleton;
import org.postgresql.util.PSQLException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CoursDAO extends DAO<Cours> {

    public CoursDAO(Singleton single) {
        super(single);
    }

    @Override
    public Cours find(int id) {
        Cours cours = null;
        try {
            pstmt = single.getConnection().prepareStatement("SELECT name, section_id FROM Cours WHERE id = ?;");
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                cours = new Cours(id, rs.getString("name"),DAOFactory.getSectionDAO().find(rs.getInt("section_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return cours;
    }

    @Override
    public Cours find(Cours obj) {
        Cours cours = null;
        try {
            pstmt = single.getConnection().prepareStatement("SELECT id, name, section_id FROM Cours WHERE name = ?;");
            pstmt.setString(1, obj.getname());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                cours = new Cours(rs.getInt("id"), rs.getString("name"),DAOFactory.getSectionDAO().find(rs.getInt("section_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return cours;
    }

    @Override
    public ArrayList<Cours> findAll() {
        ArrayList<Cours> coursList = new ArrayList<>();
        Cours cours = null;
        try {
            pstmt = single.getConnection().prepareStatement("SELECT id, name, section_id FROM Cours;");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                cours = new Cours(rs.getInt("id"), rs.getString("name"),DAOFactory.getSectionDAO().find(rs.getInt("section_id")));
                coursList.add(cours);
            }   
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return coursList;
    }

    public ArrayList<Cours> findBySection(Section section) {
        ArrayList<Cours> coursList = new ArrayList<>();
        Cours cours = null;
        try {
            pstmt = single.getConnection().prepareStatement("SELECT id, name, section_id FROM Cours WHERE section_id = ?;");
            pstmt.setInt(1, section.getId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                cours = new Cours(rs.getInt("id"), rs.getString("name"), section);
                coursList.add(cours);
            }   
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return coursList;
    }

    @Override
    public boolean create(Cours obj) {
        boolean flag = false;
        try {
            pstmt = single.getConnection().prepareStatement("INSERT INTO Cours (name, section_id) VALUES (?, ?) ON CONFLICT (name, section_id) DO NOTHING;");
            pstmt.setString(1, obj.getname());
            pstmt.setInt(2, obj.getSection().getId());
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
    public boolean update(Cours obj) {
        boolean flag = false;
        try {
            pstmt = single.getConnection().prepareStatement("UPDATE Cours SET name = ?, section_id = ? WHERE id = ?;");
            pstmt.setString(1, obj.getname());
            pstmt.setInt(2, obj.getSection().getId());
            pstmt.setInt(3, obj.getId());
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
            pstmt = single.getConnection().prepareStatement("DELETE FROM Cours WHERE id = ?;");
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
