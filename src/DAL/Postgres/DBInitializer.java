package DAL.Postgres;
import DAL.Singleton.Singleton;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.postgresql.util.PSQLException;
import java.sql.SQLException;


public class DBInitializer {
    private static final String createTables = "/resources/script.sql";

    private DBInitializer() {
    }

    public static void initDB(Singleton single, String dbName){
        PreparedStatement pstmt = null;
        try{
            pstmt = single.getConnection().prepareStatement("SELECT 1 FROM pg_database WHERE datname = ?;");
            pstmt.setString(1, dbName);
            ResultSet rs = pstmt.executeQuery();
            if(!rs.next()){
                System.out.println("La base de données '" + dbName + "' n'existe pas.");
                rs.close();
                pstmt.close();
                pstmt = single.getConnection().prepareStatement("CREATE DATABASE " + dbName + ";");
                pstmt.executeUpdate();
                System.out.println("Base de données '" + dbName + "' créée avec succès.");
            }
        } catch(PSQLException e){
            e.printStackTrace();
        } catch(SQLException e){
            e.printStackTrace();
        } finally {
            if(pstmt != null){
                try{
                    pstmt.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void initTables(Singleton single) throws SQLException{
        executeSQLScript(single, createTables);
    }

    private static void executeSQLScript(Singleton single, String sqlFilePath) throws SQLException{
        StringBuilder sqlScript = new StringBuilder();

        try {
            InputStream inputStream = DBInitializer.class.getResourceAsStream(sqlFilePath);
            if(inputStream == null) {
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sqlScript.append(line).append("\n");
            }
            if(bufferedReader != null) {
                bufferedReader.close();
            }
            if(inputStream != null) {
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try(Statement statement = single.getConnection().createStatement()) {
            statement.execute(sqlScript.toString());
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
