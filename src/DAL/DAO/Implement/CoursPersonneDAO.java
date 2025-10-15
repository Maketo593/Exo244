package DAL.DAO.Implement;
import BL.Cours_Personne.CoursPersonne;
import DAL.DAO.DAO;
import DAL.DAO.DAOFactory;
import DAL.Singleton.Singleton;
import org.postgresql.util.PSQLException;
import java.sql.SQLException;
import java.util.ArrayList;
public class CoursPersonneDAO extends DAO<CoursPersonne> {

    public CoursPersonneDAO(Singleton single) {
        super(single);
    }

    @Override
    public CoursPersonne find(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Méthode 'find' de la classe abstraite DAO non implémentée pour CoursPersonneDAO");
    }

    @Override
    public CoursPersonne find(CoursPersonne obj) {
        CoursPersonne cp = null;
        return cp;
    }

    @Override
    public ArrayList<CoursPersonne> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public boolean create(CoursPersonne obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public boolean update(CoursPersonne obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public boolean delete(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
