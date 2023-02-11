import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabasePopulateService {

    public static void main(String[] args) {
        String[] workersName = {
                "Djovany",
                "Axiles",
                "Leonid",
                "Kiril",
                "Piter",
                "Anastasia",
                "Ludmila",
                "Andrey",
                "Kuzma",
                "Pavel"
        };
        String[] workersBirthday = {
                "1979-01-15",
                "2003-06-11",
                "1989-02-28",
                "1998-08-09",
                "2001-05-11",
                "2005-05-21",
                "1999-10-16",
                "1994-08-12",
                "1983-04-06",
                "1979-04-15"
        };
        String[] workersLevel = {
                "Senior",
                "Trainee",
                "Trainee",
                "Trainee",
                "Junior",
                "Junior",
                "Middle",
                "Middle",
                "Senior",
                "Senior"
        };
        int[] workersSalary = {
                10000,
                450,
                600,
                500,
                1000,
                1100,
                2500,
                3000,
                6500,
                10000
        };
        String[] clientsName = {
                "Roman Borziy",
                "Jonny Deph",
                "Alex Prostoy",
                "Antonio Vdumchiviy",
                "Nikola Tesla",
                "MOU", "NTUU"
        };
        int[] clientsId = {7, 1, 1, 6, 5, 3, 2, 4, 3, 5};
        String[] projectsStartDate = {
                "2022-02-17",
                "2022-08-23",
                "2022-09-05",
                "2022-11-21",
                "2022-12-03",
                "2022-04-03",
                "2022-01-05",
                "2022-12-01",
                "2022-09-07",
                "2022-09-09"
        };
        String[] projectsFinishDate = {
                "2022-07-01",
                "2028-06-03",
                "2024-05-04",
                "2029-04-05",
                "2027-07-06",
                "2023-01-01",
                "2024-03-07",
                "2026-03-06",
                "2024-01-10",
                "2030-09-03"
        };
        int[] projectsId = {1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 5, 5, 6, 6, 7, 7, 7, 8, 8, 9, 9, 10, 10, 10, 10, 10};
        int[] workersId = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 3, 4, 5, 2, 4, 5, 1, 3, 1, 3, 9, 7, 8};

        addWorkers(workersName, workersBirthday, workersLevel, workersSalary);
        addClients(clientsName);
        addProjects(clientsId, projectsStartDate, projectsFinishDate);
        addLinkProjectWorker(projectsId, workersId);
    }

    private static void addWorkers(String[] name, String[] birthday, String[] level, int[] salary) {
        String workerInsertSql = "INSERT INTO worker (name, birthday, level, salary) VALUES (?, ?, ?, ?)";
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(workerInsertSql)) {
            for (int i = 0; i < name.length; i++) {
                preparedStatement.setString(1, name[i]);
                preparedStatement.setString(2, birthday[i]);
                preparedStatement.setString(3, level[i]);
                preparedStatement.setInt(4, salary[i]);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void addClients(String[] name) {
        String clientInsertSql = "INSERT INTO client (name) VALUES (?)";
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(clientInsertSql)) {
            for (String s : name) {
                preparedStatement.setString(1, s);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addProjects(int[] clientId, String[] startDate, String[] finishDate) {
        String projectInsertSql = "INSERT INTO project (client_id, start_date, finish_date) VALUES (?, ?, ?)";
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(projectInsertSql)) {
            for (int i = 0; i < clientId.length; i++) {
                preparedStatement.setInt(1, clientId[i]);
                preparedStatement.setString(2, startDate[i]);
                preparedStatement.setString(3, finishDate[i]);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addLinkProjectWorker(int[] projectId, int[] workerId) {
        String projectWorkerInsertSql = "INSERT INTO project_worker (project_id, worker_id) VALUES (?, ?)";
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(projectWorkerInsertSql)) {
            for (int i = 0; i < projectId.length; i++) {
                preparedStatement.setInt(1, projectId[i]);
                preparedStatement.setInt(2, workerId[i]);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
