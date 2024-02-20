package avg.dga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //Base_Entity에 있는 날짜 자동 입력 활성화
@SpringBootApplication
public class DgaApplication {

  public DgaApplication() {

  }

  public static final String APPLICATION_LOCATIONS = "spring.config.location="
      + "classpath:application.yml,"
      + "classpath:aws.yml";

  public static void main(String[] args) {
    new SpringApplicationBuilder(DgaApplication.class)
        .properties(APPLICATION_LOCATIONS)
        .run(args);

  }



}
