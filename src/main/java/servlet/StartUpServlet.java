package servlet;

import com.googlecode.flyway.core.Flyway;
import org.postgresql.jdbc2.optional.SimpleDataSource;
import org.springframework.jdbc.core.metadata.PostgresCallMetaDataProvider;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class StartUpServlet extends HttpServlet {
    public StartUpServlet() throws URISyntaxException {
        URI dbUri = new URI(System.getenv("SHARED_DATABASE_URL"));
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
        try {
            Driver driver = new org.postgresql.Driver();
            SimpleDriverDataSource dataSource = new SimpleDriverDataSource(driver,dbUrl );
            dataSource.setUsername(dbUri.getUserInfo().split(":")[0]);
            dataSource.setPassword(dbUri.getUserInfo().split(":")[1]);
            Flyway flyway = new Flyway();
            flyway.setDataSource(dataSource);
            flyway.migrate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
