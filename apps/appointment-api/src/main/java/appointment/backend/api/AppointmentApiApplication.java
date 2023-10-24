package appointment.backend.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication
//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication(scanBasePackages = {"appointment.backend.*"})
public class AppointmentApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppointmentApiApplication.class, args);
	}

}
