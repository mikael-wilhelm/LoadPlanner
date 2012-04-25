package startupManageBean;

import com.googlecode.flyway.core.Flyway;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.net.URI;
import java.sql.Driver;


@ManagedBean(eager = true)
@ApplicationScoped
public class StartUp {
    public StartUp(){
        try {
            URI dbUri = new URI(System.getenv("SHARED_DATABASE_URL"));
            System.out.println("test: " + dbUri.toString());
            String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
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
