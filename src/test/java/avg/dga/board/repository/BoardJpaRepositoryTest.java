package avg.dga.board.repository;

import avg.dga.board.entity.Board;
import avg.dga.board.entity.DestiType;
import avg.dga.board.entity.TravelType;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Assertions;
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

  @Autowired BoardJpaRepository boardJpaRepository;

  @PersistenceContext
  private EntityManager em;

  @Test
  @Transactional
  @Rollback(value = false)
  public void testBoard() throws Exception {
      //given
    DestiType.User user = new DestiType.User("안상민");
    Board detachedBard = new Board(1L, user, "12321", "강원도", TravelType.KID, DestiType.MARKET, "네시간", 10, 10, 10, 10, 10, true);
    Board mergedBoard = (Board) em.merge(detachedBard);

    //when
    Board findBoard = boardJpaRepository.find(saveId);

    //then
    Assertions.assertEquals(findBoard.getId(),(board.getId()));
    Assertions.assertEquals(findBoard.getUser(),(board.getUser()));
  }
}