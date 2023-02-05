import dataDto.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {
    private static final String INIT_DB_FILE_MAX_PROJECTS_CLIENT = "sql/find_max_projects_client.sql";
    private static final String INIT_DB_FILE_MAX_SALARY_WORKER = "sql/find_max_salary_worker.sql";
    private static final String INIT_DB_FILE_LONGEST_PROJECT = "sql/find_longest_project.sql";
    private static final String INIT_DB_FILE_PROJECT_PRICE = "sql/print_project_prices.sql";
    private static final String INIT_DB_FILE_YOUNGER_ELDEST_WORKERS = "sql/find_youngest_eldest_workers.sql";

    public List<MaxProjectClient> findMaxProjectsClient() {
        List<MaxProjectClient> maxProjectClientList = new ArrayList<>();

        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(FileReaderDB.getFileReadDB(INIT_DB_FILE_MAX_PROJECTS_CLIENT));
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                String name = rs.getString("name");
                int projectCount = rs.getInt("project_count");

                maxProjectClientList.add(new MaxProjectClient(name, projectCount));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return maxProjectClientList;
    }

    public List<MaxSalaryWorker> findMaxSalaryWorker() {
        List<MaxSalaryWorker> maxSalaryWorkerList = new ArrayList<>();

        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(FileReaderDB.getFileReadDB(INIT_DB_FILE_MAX_SALARY_WORKER));
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                String name = rs.getString("name");
                int salary = rs.getInt("salary");

                MaxSalaryWorker maxWorkerSalary = new MaxSalaryWorker(name, salary);
                maxSalaryWorkerList.add(maxWorkerSalary);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return maxSalaryWorkerList;
    }

    public List<LongestProject> findLongestProject() {
        List<LongestProject> longestProjectList = new ArrayList<>();

        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(FileReaderDB.getFileReadDB(INIT_DB_FILE_LONGEST_PROJECT));
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                String name = rs.getString("name");
                int monthCount = rs.getInt("month_count");

                LongestProject longestProject = new LongestProject(name, monthCount);
                longestProjectList.add(longestProject);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return longestProjectList;
    }

    public List<YoungestEldestWorkers> findYoungestOrEldestWorkers() {
        List<YoungestEldestWorkers> youngestOrEldestWorkersList = new ArrayList<>();

        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(FileReaderDB.getFileReadDB(INIT_DB_FILE_YOUNGER_ELDEST_WORKERS));
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                String type = rs.getString("type");
                String name = rs.getString("name");
                Date birthday = rs.getDate("birthday");

                YoungestEldestWorkers youngestOrEldestWorkers = new YoungestEldestWorkers(type, name, birthday);
                youngestOrEldestWorkersList.add(youngestOrEldestWorkers);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return youngestOrEldestWorkersList;
    }

    public List<ProjectPrices> findProjectPrices() {
        List<ProjectPrices> projectPricesList = new ArrayList<>();

        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(FileReaderDB.getFileReadDB(INIT_DB_FILE_PROJECT_PRICE));
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                String name = rs.getString("name");
                int price = rs.getInt("price");

                ProjectPrices projectPrices = new ProjectPrices(name, price);
                projectPricesList.add(projectPrices);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return projectPricesList;
    }
}
