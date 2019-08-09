package ph.use.titus.grocerystore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import ph.use.titus.grocerystore.dto.Transaction;

@SpringBootApplication
public class GrocerystoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrocerystoreApplication.class, args);
	}

}
