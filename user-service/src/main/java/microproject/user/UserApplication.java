package microproject.user;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class UserApplication {

    @PostConstruct
    public void init() {
        // Thiết lập múi giờ mặc định cho toàn bộ JVM là Hồ Chí Minh
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
    }

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

}
