import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseFileUpdate {
    public static void databaseUpdate(String urlDb) {
        Database database = Database.getInstance();
        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(FileReaderDB.getFileReadDB(urlDb))) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        database.close();
    }
}
