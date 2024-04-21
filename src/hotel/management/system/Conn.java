package hotel.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conn {
    /*
     * This classes is created to connect the Java project to database ( JDBC connectivity )
     * it involves 5 steps
     * 1. Register the Driver, ( int this project i'm using mysql driver )
     * 2. Creating the connection
     * 3. Creating a statement
     * 4. Executing mysql queries
     * 5. Closing the connection
     */
    Connection c;   // #2
    Statement s;    // #3       // by using this statement we can execute the queries in mysql
    Conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");          // #1
/* #2 */    c = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelManagementSystem", "root", "Once@Jihyo5");
/* #3 */    s = c.createStatement();

        }catch (Exception e){
            System.out.println(e);
        }

    }

}
