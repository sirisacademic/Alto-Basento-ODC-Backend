import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({controllers.OpenBasentoController.class, OpenBasentoApiConfiguration.class})
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class OpenBasentoApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(OpenBasentoApiApplication.class, args);
    }
}
