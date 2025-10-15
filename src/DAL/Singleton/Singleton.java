package DAL.Singleton;
import DAL.Postgres.DBInitializer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Singleton {
    private String url = "jdbc:postgresql://localhost:5432/";
    private String db = "ecole";
    private String user = "postgres";
    private String password = "toto";
    private Connection conn = null;
    private volatile static Singleton single;

    private Singleton() {
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connecté à la base de donnée: " + url);
            DBInitializer.initDB(this, db);
            this.close();
            conn = DriverManager.getConnection(url + db, user, password);
            System.out.println("Connecté à la base de donnée: " + url + db);
            DBInitializer.initTables(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public static Singleton getInstance() {
        if(single == null) {
            synchronized(Singleton.class) {
                if(single == null) {
                    single = new Singleton();
                }
            }
        }
        return single;
    }

    public void close() {
        if(conn != null) {
            try {
                conn.close();
                System.out.println("Connexion fermée.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
