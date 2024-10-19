package DAL.DAO.Implement;
import BL.CoursPersonne.Cours;
import BL.Section.Section;
import DAL.DAO.DAO;
import DAL.DAO.DAOFactory;
import DAL.Singleton.Singleton;
import java.sql.SQLException;
import java.util.ArrayList;

import org.postgresql.util.PSQLException;

public class CoursDAO extends DAO<Cours> {

    public CoursDAO(Singleton single) {
        super(single);
        
    }

    @Override
    public Cours get(int id) {
        Cours cours = null;
        try {
            ps = single.getConnection().prepareStatement("SELECT id_section, nom FROM Cours WHERE id = ?;");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                cours = new Cours(id, rs.getString("nom"));
                cours.setSection(DAOFactory.getSectionDAO().get(rs.getInt("id_section")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return cours;
    }

    @Override
    public Cours get(Cours obj) {
        Cours cours = null;
        try {
            ps = single.getConnection().prepareStatement("SELECT id, id_section FROM Cours WHERE nom = ?;");
            ps.setString(1, obj.getNom());
            rs = ps.executeQuery();
            if (rs.next()) {
                cours = new Cours(rs.getInt("id"), obj.getNom());
                cours.setSection(DAOFactory.getSectionDAO().get(rs.getInt("id_section")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return cours;
    }

    @Override
    public ArrayList<Cours> getAll() {
        ArrayList<Cours> coursList = null;
        try {
            ps = single.getConnection().prepareStatement("SELECT id, id_section, nom FROM Cours;");
            rs = ps.executeQuery();
            coursList = new ArrayList<>();
            while (rs.next()) {
                Cours cours = new Cours(rs.getInt("id"), rs.getString("nom"));
                cours.setSection(DAOFactory.getSectionDAO().get(rs.getInt("id_section")));
                coursList.add(cours);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return coursList;
    }
    
    public ArrayList<Cours> getAllBySection(Section obj) {
        ArrayList<Cours> coursList = null;
        try {
            ps = single.getConnection().prepareStatement("SELECT id, nom FROM Cours WHERE id_section = ?;");
            ps.setInt(1, obj.getId());
            rs = ps.executeQuery();
            coursList = new ArrayList<>();
            while (rs.next()) {
                Cours cours = new Cours(rs.getInt("id"), rs.getString("nom"));
                cours.setSection(obj);
                coursList.add(cours);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return coursList;
    }

    @Override
    public boolean create(Cours obj) {
        boolean flag = false;
        try {
            ps = single.getConnection().prepareStatement("INSERT INTO Cours (nom, id_section) VALUES (?, ?) ON CONFLICT (id_section, nom) DO NOTHING;");
            ps.setString(1, obj.getNom());
            ps.setInt(2, obj.getSection().getId());
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
    public boolean update(Cours obj) {
        boolean flag = false;
        try {
            ps = single.getConnection().prepareStatement("UPDATE Cours SET nom = ?, id_section = ? WHERE id = ?;");
            ps.setString(1, obj.getNom());
            ps.setInt(2, obj.getSection().getId());
            ps.setInt(3, obj.getId());
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
            ps = single.getConnection().prepareStatement("DELETE FROM Cours WHERE id = ?;");
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
