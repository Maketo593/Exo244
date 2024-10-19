package DAL.DAO.Implement;
import BL.Section.Section;
import DAL.DAO.DAO;
import DAL.DAO.DAOFactory;
import DAL.Singleton.Singleton;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.postgresql.util.PSQLException;

public class SectionDAO extends DAO<Section> {

    public SectionDAO(Singleton single) {
        super(single);
    }

    @Override
    public Section get(int id) {
        Section section = null;
        try {
            ps = single.getConnection().prepareStatement("SELECT id, nom FROM Section WHERE id = ?;");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                section = new Section(id, rs.getString("nom"));
                section.setCours(DAOFactory.getCoursDAO().getAllBySection(section));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return section;
    }

    @Override
    public Section get(Section obj) {
        Section section = null;
        try {
            ps = single.getConnection().prepareStatement("SELECT id FROM Section WHERE nom = ?;");
            ps.setString(1, obj.getNom());
            rs = ps.executeQuery();
            if (rs.next()) {
                section = new Section(rs.getInt("id"), obj.getNom());
                section.setCours(DAOFactory.getCoursDAO().getAllBySection(section));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return section;
    }

    @Override
    public List<Section> getAll() {
        ArrayList<Section> sectionList = null;
        try {
            ps = single.getConnection().prepareStatement("SELECT id, nom FROM Section;");
            rs = ps.executeQuery();
            sectionList = new ArrayList<>();
            while (rs.next()) {
                Section section = new Section(rs.getInt("id"), rs.getString("nom"));
                sectionList.add(section);
                section.setCours(DAOFactory.getCoursDAO().getAllBySection(section));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return sectionList;
    }

    @Override
    public boolean create(Section obj) {
        boolean flag = false;
        try {
            ps = single.getConnection().prepareStatement("INSERT INTO Section (nom) VALUES (?) ON CONFLICT (nom) DO NOTHING;");
            ps.setString(1, obj.getNom());
            ps.executeUpdate();
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
            ps = single.getConnection().prepareStatement("UPDATE Section SET nom = ? WHERE id = ?;");
            ps.setString(1, obj.getNom());
            ps.setInt(2, obj.getId());
            ps.executeUpdate();
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
            ps = single.getConnection().prepareStatement("DELETE FROM Section WHERE id = ?;");
            ps.setInt(1, id);
            ps.executeUpdate();
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
