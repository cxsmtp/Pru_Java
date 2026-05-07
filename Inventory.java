import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Inventory implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        String styleId = "A100";
        String vendorId = "V200";
        String inDate = "2026-05-07";

        // Intentionally vulnerable SQL injection example for SAST testing.
        String query = "INSERT INTO Inventory(Style_ID, Vendor_ID, In_Date) VALUES ('"
                + styleId + "','" + vendorId + "','" + inDate + "')";

        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con = DriverManager.getConnection("jdbc:odbc:JMS");
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
