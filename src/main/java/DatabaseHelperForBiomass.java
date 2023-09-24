import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHelperForBiomass {

    private Connection connect() {
        // Connect to database
        String url = "jdbc:sqlite:/home/dahuangggg/IdeaProjects/testdb/src/BiomassModel.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public Integer getSpeciesIDByName(String name) {
        String sql = "SELECT SerialNumber FROM BiomassModel WHERE Genus = ?";  // Update the column and table names

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("SerialNumber");  // Update the column name
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public Integer getSpeciesIDByLatinName(String latinName) {
        String sql = "SELECT SerialNumber FROM BiomassModel WHERE LatinName = ?";  // Update the column and table names

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, latinName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("SerialNumber");  // Update the column name
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }


    public void getDataBySpeciesID(int speciesID) {
        String sql = "SELECT * FROM BiomassModel WHERE SerialNumber = ?";  // Update the column and table names

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, speciesID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("Genus: " + rs.getString("Genus"));
                System.out.println("Latin Name: " + rs.getString("LatinName"));
                System.out.println("Tree Species: " + rs.getString("TreeSpecies"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) {
        DatabaseHelperForGrowth dbHelper = new DatabaseHelperForGrowth();

        Integer speciesID = dbHelper.getSpeciesIDByName("红松");  // Update the genus name
        if (speciesID != null) {
            dbHelper.getDataBySpeciesID(speciesID);
        }
    }
}
