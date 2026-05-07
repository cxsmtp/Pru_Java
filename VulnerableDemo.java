## comment-1
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class VulnerableDemo {
    public static void main(String[] args) throws Exception {
        String userInput = args.length > 0 ? args[0] : "guest";

        // SQL Injection vulnerability: untrusted input concatenated into SQL query.
        String jdbcUrl = "jdbc:h2:mem:test";
        Connection conn = DriverManager.getConnection(jdbcUrl, "sa", "");
        Statement stmt = conn.createStatement();
        String query = "SELECT * FROM users WHERE username = '" + userInput + "'";
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            System.out.println("User: " + rs.getString("username"));
        }

        // XSS vulnerability: user-controlled data rendered directly into HTML.
        String html = "<html><body><h1>Welcome " + userInput + "</h1></body></html>";
        System.out.println(html);

        rs.close();
        stmt.close();
        conn.close();
    }
}
