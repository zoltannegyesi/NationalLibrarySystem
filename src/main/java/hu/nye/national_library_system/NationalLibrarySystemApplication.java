package hu.nye.national_library_system;

import hu.nye.national_library_system.etc.KeyHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class NationalLibrarySystemApplication {

	private static final String userPassword = "UserPassword123";
	private static final String groupPassword = "GroupPassword123";

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(NationalLibrarySystemApplication.class, args);
		setKeys(applicationContext);
	}

	private static void setKeys(ApplicationContext applicationContext) {
		KeyHolder keyHolder = applicationContext.getBean(KeyHolder.class);
		keyHolder.setUserWideKey(userPassword);
		keyHolder.setGroupWideKey(groupPassword);
	}

}
