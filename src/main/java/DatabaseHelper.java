import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHelper {

    private Connection connect() {
        // Connect to database
        String url = "jdbc:sqlite:/home/dahuangggg/IdeaProjects/GrowthModelDatabase.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public Integer getSpeciesIDByName(String name) {
        String sql = "SELECT TreeSpeciesID FROM TreeSpecies WHERE TreeSpeciesName = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("TreeSpeciesID");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public Integer getSpeciesIDByLatinName(String latinName) {
        String sql = "SELECT TreeSpeciesID FROM TreeSpecies WHERE LatinName = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, latinName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("TreeSpeciesID");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }


    public void getDataBySpeciesID(int speciesID) {
        String sql = "SELECT * FROM GrowthModel WHERE TreeSpeciesID = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, speciesID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                // Print or process the data as per your requirement
                System.out.println("Dependent Variable: " + rs.getString("DependentVariable"));
                System.out.println("Model Expression: " + rs.getString("ModelExpression"));
                System.out.println("Model Form: " + rs.getString("ModelForm"));
                // ... (print other columns as needed)
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) {
        DatabaseHelper dbHelper = new DatabaseHelper();

        Integer speciesID = dbHelper.getSpeciesIDByName("云杉");
        if (speciesID != null) {
            dbHelper.getDataBySpeciesID(speciesID);
        }
    }
}

