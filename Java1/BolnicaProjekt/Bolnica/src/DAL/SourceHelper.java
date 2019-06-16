package DAL;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.io.File;
import javax.sql.DataSource;

public class SourceHelper {
    private static File datoteka = new File("C:\\Users\\Mgra\\Desktop\\MgraBolnica\\BolnicaJava\\build\\TxtBaza.txt");
    
     public static DataSource kreirajDataSource() {
        SQLServerDataSource dataSource = new SQLServerDataSource();

        dataSource.setServerName("localhost");
        dataSource.setDatabaseName("Bolnica");
        dataSource.setUser("SA");
        dataSource.setPassword("sql");

        return dataSource;
    }
}
