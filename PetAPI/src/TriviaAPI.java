import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileInputStream;

import java.sql.*;

public class TriviaAPI {
    private static final String DB_URL = "jdbc:sqlite:trivia.db";
    private static final String CSV_PATH = "trivia.csv";
    private static Connection connection;
    private static int numberOfTrivia = 0;
    /**
     * Initializes the trivia database.
     * @param clearExisting whether to clear existing data
     */
    public static void initiate(boolean clearExisting) {
        try {
            connection = DriverManager.getConnection(DB_URL);
            Statement stmt = connection.createStatement();
            if (clearExisting) {
                stmt.executeUpdate("DROP TABLE IF EXISTS trivia");
            } else{             
                ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM trivia");
                if (rs.next()) {
                    numberOfTrivia = rs.getInt(1);
                    return; // Table exists, no need to recreate
                }
            }
            stmt.executeUpdate("CREATE TABLE trivia (id INTEGER PRIMARY KEY, question TEXT, option_a TEXT, option_b TEXT, option_c TEXT, option_d TEXT, answer TEXT)");
            try{
                FileInputStream fis = new FileInputStream(new File(CSV_PATH));
                Scanner scanner = new Scanner(fis);
                int id = 0;
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (id == 0) { // Skip header line
                        id++;
                        continue;
                    }
                    String[] parts = line.split(",");
                    if (parts.length == 6) {
                        String sql = String.format(
                            "INSERT INTO trivia values(%d, '%s', '%s', '%s', '%s', '%s', '%s')",
                            id,
                            parts[0].trim(),
                            parts[1].trim(),
                            parts[2].trim(),
                            parts[3].trim(),
                            parts[4].trim(),
                            parts[5].trim()
                        );
                        stmt.executeUpdate(sql);
                        id++;
                    }
                }
                scanner.close();
                numberOfTrivia = id;
            } catch (FileNotFoundException e) {
                System.out.println("CSV file not found: " + e.getMessage());
                return;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Retrieves all trivia questions from the database.
     * @return a list of Trivia objects
     */
    public static List<Trivia> getAllTrivia() {
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM trivia ORDER BY RANDOM()")) {

            List<Trivia> triviaList = new java.util.ArrayList<>();
            while (rs.next()) {
                Trivia trivia = new Trivia(
                    rs.getString("question"),
                    rs.getString("option_a"),
                    rs.getString("option_b"),
                    rs.getString("option_c"),
                    rs.getString("option_d"),
                    rs.getString("answer")
                );
                triviaList.add(trivia);
            }
            return triviaList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return java.util.Collections.emptyList();
        }
    }

    /**
     * Retrieves a trivia question by its ID.
     * @param id the ID of the trivia question
     * @return a Trivia object or null if not found
     */
    public static Trivia getTriviaById(int id) {
        try (PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM trivia WHERE id = ?")) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Trivia(
                    rs.getString("question"),
                    rs.getString("option_a"),
                    rs.getString("option_b"),
                    rs.getString("option_c"),
                    rs.getString("option_d"),
                    rs.getString("answer")
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Retrieves trivia questions that contain a specific keyword in the question.
     * @param keyword the keyword to search for
     * @return a list of Trivia objects
     */
    public static List<Trivia> getTriviaByKeyword(String keyword) {
        try (PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM trivia WHERE question LIKE ?")) {
            pstmt.setString(1, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();

            List<Trivia> triviaList = new java.util.ArrayList<>();
            while (rs.next()) {
                Trivia trivia = new Trivia(
                    rs.getString("question"),
                    rs.getString("option_a"),
                    rs.getString("option_b"),
                    rs.getString("option_c"),
                    rs.getString("option_d"),
                    rs.getString("answer")
                );
                triviaList.add(trivia);
            }
            return triviaList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return java.util.Collections.emptyList();
        }
    }

    /**
     * Adds a new trivia question to the database.
     * @param trivia the Trivia object to add
     */
    public static void addTrivia(Trivia trivia) {
        try (PreparedStatement pstmt = connection.prepareStatement(
                "INSERT INTO trivia (question, option_a, option_b, option_c, option_d, answer) VALUES (?, ?, ?, ?, ?, ?)")) {
            pstmt.setString(1, trivia.getQuestion());
            pstmt.setString(2, trivia.getOptions()[0]);
            pstmt.setString(3, trivia.getOptions()[1]);
            pstmt.setString(4, trivia.getOptions()[2]);
            pstmt.setString(5, trivia.getOptions()[3]);
            pstmt.setString(6, trivia.getAnswer());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Retrieves custom trivia questions that have an ID greater than the number of trivia questions in the CSV.
     * @return a list of Trivia objects
     */
    public static List<Trivia> getCustomTrivia(){
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM trivia WHERE id >= " + (numberOfTrivia + 1))) {

            List<Trivia> triviaList = new java.util.ArrayList<>();
            while (rs.next()) {
                Trivia trivia = new Trivia(
                    rs.getString("question"),
                    rs.getString("option_a"),
                    rs.getString("option_b"),
                    rs.getString("option_c"),
                    rs.getString("option_d"),
                    rs.getString("answer")
                );
                triviaList.add(trivia);
            }
            return triviaList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return java.util.Collections.emptyList();
        }
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
