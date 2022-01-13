package gulp;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableBatchProcessing
@ComponentScan(value = {"gulp"})
public class GulpApplication {

	public static void main(String[] args) {
		SpringApplication.run(GulpApplication.class, args);
	}

}
