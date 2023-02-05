import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseFileUpdate {
    public static void databaseUpdate(String urlDb) {
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(FileReaderDB.getFileReadDB(urlDb))) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
