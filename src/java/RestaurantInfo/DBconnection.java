package RestaurantInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Saurabh
 */
@ManagedBean(name = "data", eager = true)
@SessionScoped
public class DBconnection {

    private String db = "test";
    private String table = "bistro";
    private List<Restaurant> bistros;

    /**
     *
     * @return
     */
    public String getDb() {
        return db;
    }

    /**
     *
     * @return
     */
    public String getTable() {
        return table;
    }

    private Connection getConnection() throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
        }

//        String host=System.getenv("OPENSHIFT_MYSQL_DB_HOST");
//        String port=System.getenv("OPENSHIFT_MYSQL_DB_PORT");
//        String username=System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
//        String password=System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
//        String name = "dbsample";
        String url = "jdbc:mysql://localhost:3306/" + db;

        return DriverManager.getConnection(url, "root", "");
    }

    /**
     *
     * @return @throws SQLException
     */
    public List<Restaurant> getBistros() throws SQLException {

        getConnection();

        bistros = new ArrayList<>();

        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + table + " ORDER BY RATING DESC,NAME");

            while (rs.next()) {
                Restaurant bistro = new Restaurant();

                bistro.setNAME(rs.getString("NAME"));
                bistro.setMENU(rs.getString("MENU"));
                bistro.setPRICE(rs.getString("PRICE"));
                bistro.setPHONE(rs.getString("PHONE"));
                bistro.setADDRESS(rs.getString("ADDRESS"));
                bistro.setPOSTAL_CODE(rs.getString("POSTAL_CODE"));
                bistro.setLATITUDE(rs.getDouble("LATITUDE"));
                bistro.setLONGITUDE(rs.getDouble("LONGITUDE"));
                bistro.setMOBILE_URL(rs.getString("MOBILE_URL"));
                bistro.setRATING(rs.getString("RATING"));
                bistro.setRATING_IMAGE_URL(rs.getString("RATING_IMAGE_URL"));
                bistro.setSNIPPET_TEXT(rs.getString("SNIPPET_TEXT"));

                bistros.add(bistro);
            }

            conn.close();

        } catch (SQLException ex) {
            System.out.println("SQL Exception" + ex.getMessage());
        }

        return bistros;
    }

    /**
     *
     * @param id
     * @return
     */
    public Restaurant getBistroById(int id) {
        return bistros.get(id);
    }
}
