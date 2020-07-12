package bd.edu.seu.managemeeting;

import bd.edu.seu.managemeeting.config.SpringSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.security.core.Authentication;

@SpringBootApplication
@EnableMongoAuditing
public class ManageMeetinApplication  {

	public static void main(String[] args) {
		SpringApplication.run(ManageMeetinApplication.class, args);

	}


}
