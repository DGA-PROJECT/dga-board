package avg.dga;

import avg.dga.board.entity.Board;
import avg.dga.board.entity.DestiType;
import avg.dga.user.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DgaApplication {

  public DgaApplication() {

  }
  public static void main(String[] args) {
    SpringApplication.run(DgaApplication.class, args);


  }



}
