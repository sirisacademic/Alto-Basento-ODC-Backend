import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class OpenBasentoApiConfiguration implements WebMvcConfigurer {

    Log logger = LogFactory.getLog(OpenBasentoApiConfiguration.class);

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
    //set path extension to true
    configurer.favorPathExtension(true).
        //set favor parameter to false
                favorParameter(false).
        //ignore the accept headers
                ignoreAcceptHeader(true).
        //dont use Java Activation Framework since we are manually specifying the mediatypes required below
                useJaf(false).
        defaultContentType(MediaType.APPLICATION_JSON).
        mediaType("xml", MediaType.APPLICATION_XML).
        mediaType("json", MediaType.APPLICATION_JSON);
    }
}
