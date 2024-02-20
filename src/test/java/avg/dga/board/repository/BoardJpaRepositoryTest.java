package avg.dga.board.repository;

import avg.dga.board.entity.Board;
import avg.dga.board.entity.DestiType;
import avg.dga.board.entity.TravelType;
import avg.dga.board.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springframework.transaction.annotation.Transactional;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class BoardJpaRepositoryTest {

  @Autowired BoardRepository boardRepository;

  @PersistenceContext
  private EntityManager em;

  @Test
  @Transactional
  @Rollback(value = false)
  public void testboard() throws Exception {
      //given
    User user = new User();
    Board detachedBard = new Board(1L, user, "12321", "강원도", TravelType.KID, DestiType.MARKET, "네시간", 10, 10, 10, true);
    Board mergedboard = (Board) em.merge(detachedBard);

    //when


  }
}