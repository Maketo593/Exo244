package DAL.DAO;
import DAL.Singleton.Singleton;
import DAL.DAO.Implement.CoursDAO;
import DAL.DAO.Implement.SectionDAO;
import DAL.DAO.Implement.StatusDAO;

public class DAOFactory {
    protected static Singleton single = Singleton.getInstance();

    public static StatusDAO getStatusDAO() {
        return new StatusDAO(single);
    }

    public static SectionDAO getSectionDAO() {
        return new SectionDAO(single);
    }

    public static CoursDAO getCoursDAO() {
        return new CoursDAO(single);
    }
}
