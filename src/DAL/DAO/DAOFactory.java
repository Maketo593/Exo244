package DAL.DAO;
import DAL.DAO.Implement.CoursDAO;
import DAL.DAO.Implement.PersonneDAO;
import DAL.DAO.Implement.SectionDAO;
import DAL.DAO.Implement.StatusDAO;
import DAL.Singleton.Singleton;

public class DAOFactory {
    protected static Singleton single = Singleton.getInstance();

    public static SectionDAO getSectionDAO() {
        return new SectionDAO(single);
    }
    
    public static PersonneDAO getPersonneDAO() {
        return new PersonneDAO(single);
    }

    public static StatusDAO getStatusDAO() {
        return new StatusDAO(single);
    }

    public static CoursDAO getCoursDAO() {
        return new CoursDAO(single);
    }
}
